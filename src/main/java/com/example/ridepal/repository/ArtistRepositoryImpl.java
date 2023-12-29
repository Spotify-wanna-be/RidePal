package com.example.ridepal.repository;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Artist;
import com.example.ridepal.repository.interfaces.ArtistRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public Artist getByFirstName(String name){
        try (Session session = sessionFactory.openSession()) {
            Query<Artist> query = session.createQuery("from Artist where name = :name", Artist.class);
            query.setParameter("name", name);

            List<Artist> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("Artist", "name", name);
            }
            return result.get(0);
        }
    }
    @Override
    public String getPictureFromArtist(Artist artist) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT a.picture FROM Artist a WHERE a.id = :artistId";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("id", artist.getId()); // Assuming artistId is the primary key
            return query.uniqueResult();        }
    }

    @Override
    public void create(Artist artist) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Artist mergedArtist = (Artist) session.merge(artist);
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
