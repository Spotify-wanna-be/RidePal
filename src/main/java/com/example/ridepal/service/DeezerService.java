package com.example.ridepal.service;

import com.example.ridepal.models.Album;
import com.example.ridepal.models.Artist;
import com.example.ridepal.models.Genre;
import com.example.ridepal.models.Track;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.time.LocalTime;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class DeezerService {

    /*
     * The import starts from Deezer charts. Increase this value if you want
     * one request to import more albums. Keep in mind that more albums mean
     * more requests to Deezer and a longer Postman request.
     */
    private static final int MAX_ALBUMS_TO_IMPORT = 100;

    /*
     * We collect a much larger pool than the number we import, shuffle it,
     * and only then choose the albums for the current request. This prevents
     * every GET /search call from always selecting the first chart albums.
     */
    private static final int MAX_CANDIDATE_ALBUMS = 3_000;
    private static final int CHART_LIMIT = 100;
    private static final int TRACK_PAGE_LIMIT = 100;
    private static final int MAX_VARCHAR_LENGTH = 255;

    @Value("${deezer.api.url:https://api.deezer.com}")
    private String deezerApiUrl;

    private final RestTemplate restTemplate;
    private final SessionFactory sessionFactory;
    private final ObjectMapper objectMapper;
    private final Random random;

    @Autowired
    public DeezerService(RestTemplate restTemplate, SessionFactory sessionFactory) {
        this.restTemplate = restTemplate;
        this.sessionFactory = sessionFactory;
        this.objectMapper = new ObjectMapper();
        this.random = new SecureRandom();
    }

    /**
     * Called by GET /search.
     *
     * Builds a large album pool from Deezer's global and per-genre charts,
     * removes albums that already have tracks in the local database, randomly
     * selects albums for this run, imports every page of their tracks, and
     * creates or updates albums, artists, genres and tracks.
     */
    public void fetchAndInsertTracksByAlbum() {
        List<Integer> albumIds = fetchAlbumIdsFromCharts();

        if (albumIds.isEmpty()) {
            throw new IllegalStateException(
                    "No new Deezer albums were available. Either the API returned no albums " +
                            "or all albums from the current chart pool already have tracks in the database."
            );
        }

        ImportSummary total = new ImportSummary();

        for (Integer albumId : albumIds) {
            try {
                ImportSummary albumResult = importAlbum(albumId);
                total.add(albumResult);
            } catch (Exception e) {
                total.failedAlbums++;
                System.err.println("Skipping Deezer album " + albumId + ": " + rootMessage(e));
            }
        }

        String resultMessage =
                "Deezer import finished. " +
                        "Albums found: " + albumIds.size() +
                        ", albums saved: " + total.savedAlbums +
                        ", tracks created: " + total.createdTracks +
                        ", tracks updated: " + total.updatedTracks +
                        ", tracks without preview: " + total.skippedWithoutPreview +
                        ", failed albums: " + total.failedAlbums;

        System.out.println(resultMessage);

        if (total.savedAlbums == 0 || total.createdTracks + total.updatedTracks == 0) {
            throw new IllegalStateException(resultMessage);
        }
    }

    /**
     * Uses the complete chart response. The album list in that response is at
     * albums.data, while chart tracks contain their album at tracks.data[].album.
     */
    private List<Integer> fetchAlbumIdsFromCharts() {
        Set<Integer> candidateAlbumIds = new LinkedHashSet<>();

        // The global chart is useful as a fallback, but it is no longer allowed
        // to fill the whole import by itself. We continue through all genre charts
        // and create a much larger candidate pool.
        collectAlbumIdsFromChart(0, candidateAlbumIds, MAX_CANDIDATE_ALBUMS);

        List<Integer> genreIds = fetchGenreIds();
        Collections.shuffle(genreIds, random);

        for (Integer genreId : genreIds) {
            if (candidateAlbumIds.size() >= MAX_CANDIDATE_ALBUMS) {
                break;
            }

            collectAlbumIdsFromChart(genreId, candidateAlbumIds, MAX_CANDIDATE_ALBUMS);
        }

        // Fallback for API responses where the chart album sub-resource is
        // returned as a normal paged list with top-level data.
        if (candidateAlbumIds.isEmpty()) {
            try {
                JsonNode albumList = getJson(
                        apiUrl("/chart/0/albums?limit=" + CHART_LIMIT)
                );
                collectAlbumIdsFromArray(
                        albumList.path("data"),
                        candidateAlbumIds,
                        MAX_CANDIDATE_ALBUMS
                );
            } catch (Exception e) {
                System.err.println("Could not load Deezer chart albums: " + rootMessage(e));
            }
        }

        if (candidateAlbumIds.isEmpty()) {
            return new ArrayList<>();
        }

        /*
         * Do not select an album that already has at least one saved Track.
         * Therefore, calling GET /search again adds different songs instead of
         * repeatedly updating the same chart entries. If tracks were truncated,
         * this set is empty and all albums are eligible again.
         */
        Set<Integer> albumIdsWithTracks = findAlbumIdsThatAlreadyHaveTracks();
        candidateAlbumIds.removeAll(albumIdsWithTracks);

        List<Integer> randomizedAlbumIds = new ArrayList<>(candidateAlbumIds);
        Collections.shuffle(randomizedAlbumIds, random);

        if (randomizedAlbumIds.size() > MAX_ALBUMS_TO_IMPORT) {
            return new ArrayList<>(
                    randomizedAlbumIds.subList(0, MAX_ALBUMS_TO_IMPORT)
            );
        }

        return randomizedAlbumIds;
    }

    private List<Integer> fetchGenreIds() {
        List<Integer> genreIds = new ArrayList<>();

        try {
            JsonNode genreResponse = getJson(apiUrl("/genre"));
            JsonNode genres = genreResponse.path("data");

            if (!genres.isArray()) {
                return genreIds;
            }

            for (JsonNode genreNode : genres) {
                Integer genreId = positiveInt(genreNode.path("id"));

                // Genre 0 is the global chart, which has already been requested.
                if (genreId != null && genreId != 0 && !genreIds.contains(genreId)) {
                    genreIds.add(genreId);
                }
            }
        } catch (Exception e) {
            System.err.println("Could not load Deezer genres: " + rootMessage(e));
        }

        return genreIds;
    }

    private Set<Integer> findAlbumIdsThatAlreadyHaveTracks() {
        try (Session session = sessionFactory.openSession()) {
            Query<Integer> query = session.createQuery(
                    "select distinct t.album.id " +
                            "from Track t " +
                            "where t.album is not null",
                    Integer.class
            );

            return new HashSet<>(query.list());
        } catch (Exception e) {
            // Import can still continue if this lookup fails. Duplicate detection
            // inside saveAlbumAndTracks remains as a second line of defence.
            System.err.println(
                    "Could not load already imported album IDs: " + rootMessage(e)
            );
            return new HashSet<>();
        }
    }

    private void collectAlbumIdsFromChart(
            int genreId,
            Set<Integer> albumIds,
            int maximumAlbumCount
    ) {
        if (albumIds.size() >= maximumAlbumCount) {
            return;
        }

        try {
            JsonNode chart = getJson(
                    apiUrl("/chart/" + genreId + "?limit=" + CHART_LIMIT)
            );

            JsonNode albumsContainer = chart.path("albums");
            if (albumsContainer.isObject()) {
                collectAlbumIdsFromArray(
                        albumsContainer.path("data"),
                        albumIds,
                        maximumAlbumCount
                );
            } else if (albumsContainer.isArray()) {
                collectAlbumIdsFromArray(
                        albumsContainer,
                        albumIds,
                        maximumAlbumCount
                );
            }

            JsonNode tracksContainer = chart.path("tracks");
            JsonNode chartTracks = tracksContainer.isObject()
                    ? tracksContainer.path("data")
                    : tracksContainer;

            if (chartTracks.isArray()) {
                for (JsonNode trackNode : chartTracks) {
                    if (albumIds.size() >= maximumAlbumCount) {
                        break;
                    }
                    addAlbumId(trackNode.path("album").path("id"), albumIds);
                }
            }

            // This also supports a direct paged-list response with top-level data.
            if (chart.path("data").isArray()) {
                collectAlbumIdsFromArray(
                        chart.path("data"),
                        albumIds,
                        maximumAlbumCount
                );
            }
        } catch (Exception e) {
            System.err.println(
                    "Could not load Deezer chart for genre " +
                            genreId + ": " + rootMessage(e)
            );
        }
    }

    private void collectAlbumIdsFromArray(
            JsonNode albums,
            Set<Integer> albumIds,
            int maximumAlbumCount
    ) {
        if (!albums.isArray()) {
            return;
        }

        for (JsonNode albumNode : albums) {
            if (albumIds.size() >= maximumAlbumCount) {
                break;
            }
            addAlbumId(albumNode.path("id"), albumIds);
        }
    }

    private void addAlbumId(JsonNode idNode, Set<Integer> albumIds) {
        Integer albumId = positiveInt(idNode);
        if (albumId != null) {
            albumIds.add(albumId);
        }
    }

    private ImportSummary importAlbum(int requestedAlbumId) {
        JsonNode albumJson = getJson(apiUrl("/album/" + requestedAlbumId));

        Integer albumId = positiveInt(albumJson.path("id"));
        if (albumId == null) {
            throw new IllegalStateException("Album response has no valid id");
        }

        List<JsonNode> trackNodes = fetchAllAlbumTracks(albumId, albumJson.path("tracks"));
        if (trackNodes.isEmpty()) {
            throw new IllegalStateException("Album contains no tracks");
        }

        GenreData genreData = resolveGenre(albumJson);

        // Some paged album-track responses may omit fields such as preview.
        // Fetch the single-track response only when fields needed by the app
        // are absent.
        List<JsonNode> completeTracks = new ArrayList<>();
        for (JsonNode trackNode : trackNodes) {
            completeTracks.add(enrichTrackWhenNeeded(trackNode));
        }

        return saveAlbumAndTracks(albumJson, albumId, genreData, completeTracks);
    }

    private List<JsonNode> fetchAllAlbumTracks(int albumId, JsonNode tracksFromAlbumResponse) {
        List<JsonNode> result = new ArrayList<>();
        String nextUrl = null;

        if (tracksFromAlbumResponse != null && tracksFromAlbumResponse.isObject()) {
            appendTrackData(tracksFromAlbumResponse.path("data"), result);
            nextUrl = nullableText(tracksFromAlbumResponse.path("next"));
        }

        // If the album response did not contain its track list, request it.
        if (result.isEmpty() && isBlank(nextUrl)) {
            nextUrl = apiUrl("/album/" + albumId + "/tracks?limit=" + TRACK_PAGE_LIMIT);
        }

        Set<String> visitedPages = new LinkedHashSet<>();

        while (!isBlank(nextUrl) && visitedPages.add(nextUrl)) {
            JsonNode page = getJson(normalizeDeezerUrl(nextUrl));
            appendTrackData(page.path("data"), result);
            nextUrl = nullableText(page.path("next"));
        }

        return result;
    }

    private void appendTrackData(JsonNode data, List<JsonNode> target) {
        if (!data.isArray()) {
            return;
        }

        for (JsonNode trackNode : data) {
            target.add(trackNode);
        }
    }

    private JsonNode enrichTrackWhenNeeded(JsonNode trackNode) {
        boolean missingPreview = isBlank(nullableText(trackNode.path("preview")));
        boolean missingArtist = positiveInt(trackNode.path("artist").path("id")) == null;
        boolean missingLink = isBlank(nullableText(trackNode.path("link")));

        if (!missingPreview && !missingArtist && !missingLink) {
            return trackNode;
        }

        long trackId = trackNode.path("id").asLong(0L);
        if (trackId <= 0) {
            return trackNode;
        }

        try {
            return getJson(apiUrl("/track/" + trackId));
        } catch (Exception e) {
            System.err.println("Could not enrich Deezer track " + trackId + ": " + rootMessage(e));
            return trackNode;
        }
    }

    private GenreData resolveGenre(JsonNode albumJson) {
        Integer genreId = positiveInt(albumJson.path("genre_id"));
        if (genreId == null) {
            return null;
        }

        String genreName = null;
        JsonNode albumGenres = albumJson.path("genres").path("data");

        if (albumGenres.isArray()) {
            for (JsonNode genreNode : albumGenres) {
                Integer currentId = positiveInt(genreNode.path("id"));
                if (genreId.equals(currentId)) {
                    genreName = nullableText(genreNode.path("name"));
                    break;
                }
            }
        }

        if (isBlank(genreName)) {
            try {
                JsonNode genreJson = getJson(apiUrl("/genre/" + genreId));
                genreName = nullableText(genreJson.path("name"));
            } catch (Exception e) {
                System.err.println("Could not load Deezer genre " + genreId + ": " + rootMessage(e));
            }
        }

        if (isBlank(genreName)) {
            genreName = "Genre " + genreId;
        }

        return new GenreData(genreId, limit(genreName));
    }

    private ImportSummary saveAlbumAndTracks(
            JsonNode albumJson,
            int albumId,
            GenreData genreData,
            List<JsonNode> trackNodes
    ) {
        ImportSummary summary = new ImportSummary();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                Genre genre = upsertGenre(session, genreData);
                Album album = upsertAlbum(session, albumJson, albumId);
                summary.savedAlbums = 1;

                JsonNode albumArtistNode = albumJson.path("artist");

                for (JsonNode trackJson : trackNodes) {
                    String preview = nullableText(trackJson.path("preview"));

                    // The project uses preview as the playable MP3 source and the
                    // database column is NOT NULL. Saving an empty value would
                    // recreate the broken player, so such tracks are skipped.
                    if (isBlank(preview)) {
                        summary.skippedWithoutPreview++;
                        continue;
                    }

                    JsonNode artistNode = trackJson.path("artist");
                    if (positiveInt(artistNode.path("id")) == null) {
                        artistNode = albumArtistNode;
                    }

                    Artist artist = upsertArtist(session, artistNode);
                    if (artist == null) {
                        System.err.println(
                                "Skipping track without a valid artist: " +
                                        nullableText(trackJson.path("title"))
                        );
                        continue;
                    }

                    String title = limit(nullableText(trackJson.path("title")));
                    if (isBlank(title)) {
                        continue;
                    }

                    String link = limit(nullableText(trackJson.path("link")));
                    Track track = findExistingTrack(session, link, title, artist.getId(), album.getId());
                    boolean isNew = track == null;

                    if (isNew) {
                        track = new Track();
                    }

                    applyTrackValues(track, trackJson, title, link, preview, artist, album, genre);

                    if (isNew) {
                        session.persist(track);
                        summary.createdTracks++;
                    } else {
                        summary.updatedTracks++;
                    }
                }

                transaction.commit();
                return summary;
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }

    private Genre upsertGenre(Session session, GenreData genreData) {
        if (genreData == null) {
            return null;
        }

        Genre genre = session.get(Genre.class, genreData.id);
        if (genre == null) {
            genre = new Genre();
            genre.setId(genreData.id);
            genre.setType(genreData.name);
            session.persist(genre);
        } else if (!genreData.name.equals(genre.getType())) {
            genre.setType(genreData.name);
        }

        return genre;
    }

    private Album upsertAlbum(Session session, JsonNode albumJson, int albumId) {
        String albumName = limit(nullableText(albumJson.path("title")));
        if (isBlank(albumName)) {
            albumName = "Album " + albumId;
        }

        String albumLink = limit(nullableText(albumJson.path("link")));
        Album album = session.get(Album.class, albumId);

        if (album == null) {
            album = new Album();
            album.setId(albumId);

            // Set every NOT NULL value before persist. Depending on the mapping
            // and flush timing, Hibernate may execute the INSERT immediately.
            album.setName(albumName);
            album.setLink(albumLink);
            session.persist(album);
        } else {
            album.setName(albumName);
            album.setLink(albumLink);
        }

        return album;
    }

    private Artist upsertArtist(Session session, JsonNode artistNode) {
        Integer artistId = positiveInt(artistNode.path("id"));
        if (artistId == null) {
            return null;
        }

        String artistName = limit(nullableText(artistNode.path("name")));
        if (isBlank(artistName)) {
            artistName = "Artist " + artistId;
        }

        Artist artist = session.get(Artist.class, artistId);
        if (artist == null) {
            artist = new Artist();
            artist.setId(artistId);

            // The artists table may also require name to be NOT NULL.
            artist.setName(artistName);
            session.persist(artist);
        } else {
            artist.setName(artistName);
        }

        return artist;
    }

    private Track findExistingTrack(
            Session session,
            String link,
            String title,
            int artistId,
            int albumId
    ) {
        if (!isBlank(link)) {
            Query<Track> byLink = session.createQuery(
                    "from Track t where t.link = :link",
                    Track.class
            );
            byLink.setParameter("link", link);
            byLink.setMaxResults(1);

            List<Track> result = byLink.list();
            if (!result.isEmpty()) {
                return result.get(0);
            }
        }

        Query<Track> byNaturalKey = session.createQuery(
                "from Track t " +
                        "where t.title = :title " +
                        "and t.artist.id = :artistId " +
                        "and t.album.id = :albumId",
                Track.class
        );
        byNaturalKey.setParameter("title", title);
        byNaturalKey.setParameter("artistId", artistId);
        byNaturalKey.setParameter("albumId", albumId);
        byNaturalKey.setMaxResults(1);

        List<Track> result = byNaturalKey.list();
        return result.isEmpty() ? null : result.get(0);
    }

    private void applyTrackValues(
            Track track,
            JsonNode trackJson,
            String title,
            String link,
            String preview,
            Artist artist,
            Album album,
            Genre genre
    ) {
        long durationSeconds = Math.max(0L, trackJson.path("duration").asLong(0L));
        durationSeconds = Math.min(durationSeconds, 86_399L);

        track.setTitle(title);
        track.setArtist(artist);
        track.setAlbum(album);
        track.setGenre(genre);
        track.setRank(trackJson.path("rank").asInt(0));
        track.setDuration(Time.valueOf(LocalTime.ofSecondOfDay(durationSeconds)));
        track.setPreview(preview);
        track.setLink(link);
    }

    private JsonNode getJson(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    normalizeDeezerUrl(url),
                    HttpMethod.GET,
                    request,
                    String.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new IllegalStateException(
                        "HTTP " + response.getStatusCodeValue() + " from " + url
                );
            }

            String body = response.getBody();
            if (isBlank(body)) {
                throw new IllegalStateException("Empty response from " + url);
            }

            JsonNode json = objectMapper.readTree(body);
            if (json.has("error")) {
                JsonNode error = json.path("error");
                String message = nullableText(error.path("message"));
                if (isBlank(message)) {
                    message = error.toString();
                }
                throw new IllegalStateException("Deezer API error: " + message);
            }

            return json;
        } catch (RestClientException e) {
            throw new IllegalStateException("Request to Deezer failed for " + url, e);
        } catch (Exception e) {
            if (e instanceof IllegalStateException) {
                throw (IllegalStateException) e;
            }
            throw new IllegalStateException("Could not read Deezer response from " + url, e);
        }
    }

    private String apiUrl(String path) {
        String base = deezerApiUrl == null || deezerApiUrl.isBlank()
                ? "https://api.deezer.com"
                : deezerApiUrl.trim();

        while (base.endsWith("/")) {
            base = base.substring(0, base.length() - 1);
        }

        return base + (path.startsWith("/") ? path : "/" + path);
    }

    private String normalizeDeezerUrl(String url) {
        if (url == null) {
            return null;
        }

        if (url.startsWith("http://api.deezer.com")) {
            return "https://api.deezer.com" + url.substring("http://api.deezer.com".length());
        }

        return url;
    }

    private Integer positiveInt(JsonNode node) {
        if (node == null || node.isMissingNode() || node.isNull()) {
            return null;
        }

        long value = node.asLong(-1L);
        if (value <= 0L || value > Integer.MAX_VALUE) {
            return null;
        }

        return (int) value;
    }

    private String nullableText(JsonNode node) {
        if (node == null || node.isMissingNode() || node.isNull()) {
            return null;
        }

        String value = node.asText(null);
        return value == null ? null : value.trim();
    }

    private String limit(String value) {
        if (value == null || value.length() <= MAX_VARCHAR_LENGTH) {
            return value;
        }
        return value.substring(0, MAX_VARCHAR_LENGTH);
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private String rootMessage(Throwable throwable) {
        Throwable current = throwable;
        while (current.getCause() != null) {
            current = current.getCause();
        }

        String message = current.getMessage();
        return isBlank(message) ? current.getClass().getSimpleName() : message;
    }

    private static final class GenreData {
        private final int id;
        private final String name;

        private GenreData(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private static final class ImportSummary {
        private int savedAlbums;
        private int createdTracks;
        private int updatedTracks;
        private int skippedWithoutPreview;
        private int failedAlbums;

        private void add(ImportSummary other) {
            this.savedAlbums += other.savedAlbums;
            this.createdTracks += other.createdTracks;
            this.updatedTracks += other.updatedTracks;
            this.skippedWithoutPreview += other.skippedWithoutPreview;
            this.failedAlbums += other.failedAlbums;
        }
    }
}
