package com.example.ridepal.repository;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Artist;
import com.example.ridepal.models.Playlist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaylistRepositoryImpl implements PlaylistRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public PlaylistRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
