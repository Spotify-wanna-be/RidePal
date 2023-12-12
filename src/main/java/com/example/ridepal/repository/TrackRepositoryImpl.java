package com.example.ridepal.repository;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Track;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TrackRepositoryImpl implements TrackRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public TrackRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Track> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Track> query = session.createQuery("from Track", Track.class);
            List<Track> tracks = query.list();
            return tracks;
        }
    }

    @Override
    public List<Track> getAllFromAlbum(String albumName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Track> query = session.createQuery("FROM Track WHERE album = :albumName");
            query.setParameter("albumName", albumName);
            List<Track> tracks = query.list();
            return tracks;
        }
    }

    @Override
    public Track getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Track track = session.get(Track.class, id);
            if (track == null) {
                throw new EntityNotFoundException("Track", id);
            }
            return track;
        }
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

    public List<Track> getBestRanked() {
        try (Session session = sessionFactory.openSession()) {
            Query<Track> query = session.createQuery("FROM Track ORDER BY rank asc", Track.class);
            query.setMaxResults(10);
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
    public Track getByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            Query<Track> query = session.createQuery("from Track where title = :title", Track.class);
            query.setParameter("title", title);

            List<Track> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("Post", "title", title);
            }

            return result.get(0);
        }
    }

    @Override
    public void create(Track track) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Track mergedTrack = (Track) session.merge(track);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Track track) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(track);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Track track) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(track);
            session.getTransaction().commit();
        }
    }


}
