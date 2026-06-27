package com.example.ridepal.service;

import com.example.ridepal.jsonignore.DeezerAlbumResponse;
import com.example.ridepal.jsonignore.DeezerTrackListResponse;
import com.example.ridepal.models.*;
import com.example.ridepal.models.deezer.DeezerGenre;
import com.example.ridepal.models.deezer.DeezerTrack;
import com.example.ridepal.service.interfaces.AlbumService;
import com.example.ridepal.service.interfaces.ArtistService;
import com.example.ridepal.service.interfaces.GenreService;
import com.example.ridepal.service.interfaces.TrackService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class DeezerService {

    @Value("${deezer.api.url}")
    private String deezerApiUrl;

    private final RestTemplate restTemplate;
    private final TrackService trackService;
    private final GenreService genreService;
    private final ArtistService artistService;
    private final AlbumService albumService;

    @Autowired
    public DeezerService(RestTemplate restTemplate, TrackService trackService,
                         GenreService genreService, ArtistService artistService, AlbumService albumService) {
        this.restTemplate = restTemplate;
        this.trackService = trackService;
        this.genreService = genreService;
        this.artistService = artistService;
        this.albumService = albumService;
    }

    /**
     * Imports tracks from real Deezer albums.
     *
     * Instead of guessing random album IDs (most of which don't exist), this
     * pulls the top albums from Deezer's per-genre charts, so the data we
     * insert is real and already genre-tagged. Each album is imported inside
     * its own try/catch, so a single failing request never aborts the whole run.
     */
    public void fetchAndInsertTracksByAlbum() {
        HttpEntity<String> entity = buildPublicEntity();

        List<String> albumIds = fetchChartAlbumIds(entity, 100);

        for (String albumId : albumIds) {
            try {
                importAlbum(albumId, entity);
            } catch (Exception e) {
                System.out.println("Skipping album " + albumId + ": " + e.getMessage());
            }
        }
    }

    private HttpEntity<String> buildPublicEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        return new HttpEntity<>(headers);
    }

    /**
     * Collects up to {@code targetCount} distinct album IDs by walking the
     * genre list and reading the top albums of each genre's chart.
     */
    private List<String> fetchChartAlbumIds(HttpEntity<String> entity, int targetCount) {
        Set<String> ids = new LinkedHashSet<>();
        try {
            ObjectMapper mapper = new ObjectMapper();

            String genresUrl = deezerApiUrl + "/genre";
            ResponseEntity<String> genresResponse =
                    restTemplate.exchange(genresUrl, HttpMethod.GET, entity, String.class);
            JsonNode genres = mapper.readTree(genresResponse.getBody()).path("data");

            for (JsonNode genre : genres) {
                if (ids.size() >= targetCount) {
                    break;
                }
                String genreId = genre.path("id").asText();
                String chartUrl = deezerApiUrl + "/chart/" + genreId + "/albums?limit=25";

                ResponseEntity<String> chartResponse =
                        restTemplate.exchange(chartUrl, HttpMethod.GET, entity, String.class);
                JsonNode albums = mapper.readTree(chartResponse.getBody()).path("data");

                for (JsonNode album : albums) {
                    if (ids.size() >= targetCount) {
                        break;
                    }
                    String id = album.path("id").asText(null);
                    if (id != null && !id.isEmpty()) {
                        ids.add(id);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch chart album IDs: " + e.getMessage());
        }
        return new ArrayList<>(ids);
    }

    /**
     * Imports one album: its tracks, the album record, and its genre.
     */
    private void importAlbum(String albumId, HttpEntity<String> entity) {
        String tracksUrl = deezerApiUrl + "/album/" + albumId + "/tracks";
        ResponseEntity<DeezerTrackListResponse> tracksResponse =
                restTemplate.exchange(tracksUrl, HttpMethod.GET, entity, DeezerTrackListResponse.class);

        if (!tracksResponse.getStatusCode().is2xxSuccessful()
                || tracksResponse.getBody() == null
                || tracksResponse.getBody().getData() == null) {
            return;
        }

        // Fetch album + genre once per album (the original code did this once
        // per track, repeating the same two calls for every track on the album).
        Album album = null;
        Genre genre = null;

        String albumUrl = deezerApiUrl + "/album/" + albumId;
        ResponseEntity<DeezerAlbumResponse> albumResponseEntity =
                restTemplate.exchange(albumUrl, HttpMethod.GET, entity, DeezerAlbumResponse.class);

        if (albumResponseEntity.getStatusCode().is2xxSuccessful()
                && albumResponseEntity.getBody() != null) {
            DeezerAlbumResponse albumResponse = albumResponseEntity.getBody();

            album = new Album();
            album.setId(albumResponse.getId());
            album.setName(albumResponse.getTitle());
            album.setLink(albumResponse.getLink());
            albumService.create(album);

            String genreUrl = deezerApiUrl + "/genre/" + albumResponse.getGenreId();
            ResponseEntity<DeezerGenre> genreResponseEntity =
                    restTemplate.exchange(genreUrl, HttpMethod.GET, entity, DeezerGenre.class);

            if (genreResponseEntity.getStatusCode().is2xxSuccessful()
                    && genreResponseEntity.getBody() != null
                    && genreResponseEntity.getBody().getId() != null) {
                DeezerGenre genreResponse = genreResponseEntity.getBody();
                genre = new Genre();
                genre.setId(Integer.parseInt(genreResponse.getId()));
                genre.setType(genreResponse.getName());
                genreService.create(genre);
            }
        } else {
            System.out.println("Error fetching album details for album " + albumId
                    + ". Status code: " + albumResponseEntity.getStatusCodeValue());
        }

        for (DeezerTrack deezerTrack : tracksResponse.getBody().getData()) {
            // The artist comes straight from the track payload (correct id/name).
            artistService.create(deezerTrack.getArtist());

            Track track = new Track();
            track.setTitle(deezerTrack.getTitle());
            track.setRank(deezerTrack.getRank());
            track.setArtist(deezerTrack.getArtist());
            track.setDuration(Time.valueOf(LocalTime.ofSecondOfDay(deezerTrack.getDuration())));
            track.setPreview(deezerTrack.getPreview());
            track.setLink(deezerTrack.getLink());

            if (album != null) {
                track.setAlbum(album);
            }
            if (genre != null) {
                track.setGenre(genre);
            }

            trackService.create(track);
        }
    }
}
