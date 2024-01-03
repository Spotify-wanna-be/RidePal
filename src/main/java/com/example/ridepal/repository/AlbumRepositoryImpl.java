package com.example.ridepal.repository;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Album;
import com.example.ridepal.models.Artist;
import com.example.ridepal.models.Track;
import com.example.ridepal.repository.interfaces.AlbumRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AlbumRepositoryImpl implements AlbumRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public AlbumRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    @Override
//    public String getCoverFromTrack(Album album) {
//        try (Session session = sessionFactory.openSession()) {
//            String hql = "SELECT a.cover FROM Album a WHERE a = :album";
//            Query<String> query = session.createQuery(hql, String.class);
//            query.setParameter("album", album);
//            return query.uniqueResult();
//        }
//    }
    @Override
    public void create(Album album) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Album mergedAlbum = (Album) session.merge(album);
            session.getTransaction().commit();
        }
    }



    @Override
    public Album getById(int id) {
        try(Session session = sessionFactory.openSession()){
            Album album = session.get(Album.class, id);
            if (album == null){
                throw new EntityNotFoundException("Album", id);
            }
            return album;
        }
    }

    @Override
    public String getLinkFromAlbum(Album album) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT a.link FROM Album a WHERE a = :album";
            Query<String> query = session.createQuery(hql, String.class);
            query.setParameter("album", album);
            return query.uniqueResult();
        }
    }

    @Override
    public void update(Album album) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(album);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(int id) {
        Album albumToDelete = getById(id);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(albumToDelete);
            session.getTransaction().commit();
        }
    }
}
