package com.example.ridepal.repository;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Artist;
import com.example.ridepal.models.Genre;
import jakarta.transaction.Transactional;
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
    public Genre getByGenreId(int id) {
        try (
                Session session = sessionFactory.openSession()
        ) {
            Genre genre = session.get(Genre.class, id);
            if (genre == null) {
                throw new EntityNotFoundException("Genre", id);
            }
            return genre;
        }
    }

    @Override
    public void create(Genre genre) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Genre mergedGenre = (Genre) session.merge(genre);
            session.getTransaction().commit();
        }
    }
}
