package com.example.ridepal.repository;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Track;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrackRepositoryImpl implements TrackRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public TrackRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Track> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Track> query = session.createQuery("from Track", Track.class);
            List<Track> tracks = query.list();
            return tracks;
        }
    }

    public List<Track> getAllFromAlbum(String albumName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Track> query = session.createQuery("FROM Track WHERE album = :albumName");
            query.setParameter("albumName", albumName);
            List<Track> tracks = query.list();
            return tracks;
        }
    }

    public Track getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Track track = session.get(Track.class, id);
            if (track == null) {
                throw new EntityNotFoundException("Post", id);
            }
            return track;
        }
    }

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

    public void create(Track track) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(track);
            session.getTransaction().commit();
        }
    }

    public void update(Track track) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(track);
            session.getTransaction().commit();
        }
    }

    public void delete(Track track) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(track);
            session.getTransaction().commit();
        }
    }


}
