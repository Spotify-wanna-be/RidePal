package com.example.ridepal.repository;

import com.example.ridepal.models.Artist;
import com.example.ridepal.models.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenreRepositoryImpl implements GenreRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public GenreRepositoryImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Genre genre) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(genre);
            session.getTransaction().commit();
        }

    }

}
