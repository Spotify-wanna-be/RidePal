package com.example.ridepal.repository;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Artist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistRepositoryImpl implements ArtistRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public ArtistRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Artist getByArtistId(int id) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            Artist artist = session.get(Artist.class, id);
            if (artist == null) {
                throw new EntityNotFoundException("Artist", id);
            }
            return artist;
        }
    }

    @Override
    public void create(Artist artist) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(artist);
            session.getTransaction().commit();
        }

    }

    @Override
    public void update(Artist artist) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(artist);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(int artistId) {
        Artist artistToDelete = getByArtistId(artistId);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(artistToDelete);
            session.getTransaction().commit();
        }
    }
}
