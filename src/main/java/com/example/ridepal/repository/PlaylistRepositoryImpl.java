package com.example.ridepal.repository;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;
import com.example.ridepal.models.Track;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PlaylistRepositoryImpl implements PlaylistRepository {
    private final SessionFactory sessionFactory;
    private List<Track> allTracks;

    @Autowired
    public PlaylistRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Playlist> get(PlaylistFilterOptions playlistFilterOptions) {
        try (Session session = sessionFactory.openSession()) {
            List<String> filters = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();

            playlistFilterOptions.getName().ifPresent(value -> {
                filters.add(" name like :name ");
                params.put("name", String.format("%%%s%%", value));
            });

            playlistFilterOptions.getDuration().ifPresent(value -> {
                filters.add(" duration like :duration ");
                params.put("duration", String.format("%%%s%%", value));
            });

            playlistFilterOptions.getGenre().ifPresent(value -> {
                filters.add(" genre.type like :genre ");
                params.put("genre", String.format("%%%s%%", value));
            });

            StringBuilder queryString = new StringBuilder("from Playlist ");
            if (!filters.isEmpty()) {
                queryString.append(" where ")
                        .append(String.join(" and ", filters));
            }


            queryString.append(generateOrderBy(playlistFilterOptions));
            Query<Playlist> query = session.createQuery(queryString.toString(), Playlist.class);
            query.setProperties(params);
            return query.list();
        }
    }

    private String generateOrderBy(PlaylistFilterOptions playlistFilterOptions) {
        if (playlistFilterOptions.getSortBy().isEmpty()) {
            return "";
        }
        String orderBy = "";
        switch (playlistFilterOptions.getSortBy().get()) {
            case "name":
                orderBy = "name";
                break;
            case "duration":
                orderBy = "duration";
                break;
            case "genres":
                orderBy = "genres.type";
                break;
            default:
                return "";
        }
        orderBy = String.format(" order by %s", orderBy);
        if (playlistFilterOptions.getSortOrder().isPresent()
                && playlistFilterOptions.getSortOrder().get().equalsIgnoreCase("desc")) {
            orderBy = String.format("%s desc", orderBy);
        }
        return orderBy;
    }

    @Override
    public Playlist getByPlaylistId(int id) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            Playlist playlist = session.get(Playlist.class, id);
            if (playlist == null) {
                throw new EntityNotFoundException("Playlist", id);
            }
            return playlist;
        }
    }

    @Override
    public List<Track> generatePlaylist(Map<String, Integer> genrePercentages, int travelDuration) {
        List<Track> selectedTracks = new ArrayList<>();
        int playlistDuration = 0;

        List<String> allGenres = getAllGenresFromTracks(allTracks);
        Map<String, List<Track>> tracksByGenre = getTracksByGenres(allGenres);

        Random random = new Random();

        for (Map.Entry<String, Integer> entry : genrePercentages.entrySet()) {
            String genre = entry.getKey();
            int percentage = entry.getValue();

            if (tracksByGenre.containsKey(genre)) {
                List<Track> genreTracks = tracksByGenre.get(genre);
                Collections.shuffle(genreTracks);

                int genreDuration = 0;
                int targetGenreDuration = (int) ((percentage / 100.0) * travelDuration);

                for (Track track : genreTracks) {
                    if (genreDuration + track.getDuration().toLocalTime().getMinute() <= targetGenreDuration) {
                        selectedTracks.add(track);
                        genreDuration += track.getDuration().toLocalTime().getMinute();
                    } else {
                        break;
                    }
                }

                playlistDuration += genreDuration;
            }
        }

        while (playlistDuration < travelDuration - 5 || playlistDuration > travelDuration + 5) {
            String randomGenre = new ArrayList<>(genrePercentages.keySet()).get(random.nextInt(genrePercentages.size()));

            if (tracksByGenre.containsKey(randomGenre)) {
                List<Track> randomGenreTracks = tracksByGenre.get(randomGenre);
                Collections.shuffle(randomGenreTracks);

                Track randomTrack = randomGenreTracks.get(0);

                selectedTracks.add(randomTrack);
                playlistDuration += randomTrack.getDuration().toLocalTime().getMinute();
            }
        }

        return selectedTracks;
    }

    @Override
    public List<String> getAllGenresFromTracks(List<Track> tracks) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT DISTINCT g.type FROM Track t JOIN t.genre g WHERE t IN (:tracks)";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameterList("tracks", tracks);
            return query.list();
        }
    }


    @Override
    public Map<String, List<Track>> getTracksByGenres(List<String> genres) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT t FROM Track t JOIN t.genre g WHERE g.type IN (:genres)";
            Query<Track> query = session.createQuery(hql, Track.class);
            query.setParameterList("genres", genres);
            List<Track> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("Track", "genres", genres.toString());
            }
            Map<String, List<Track>> tracksByGenre = new HashMap<>();
            for (String genre : genres) {
                List<Track> genreTracks = new ArrayList<>();
                for (Track track : result) {
                    if (track.getGenre().getType().equals(genre)) {
                        genreTracks.add(track);
                    }
                }
                tracksByGenre.put(genre, genreTracks);
            }

            return tracksByGenre;
        }
    }


    @Override
    public void create(Playlist playlist) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(playlist);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Playlist playlist) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(playlist);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(int playlistId) {
        Playlist playlistToDelete = getByPlaylistId(playlistId);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(playlistToDelete);
            session.getTransaction().commit();
        }
    }
}
