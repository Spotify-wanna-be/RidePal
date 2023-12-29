package com.example.ridepal.repository;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Genre;
import com.example.ridepal.repository.interfaces.GenreRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreRepositoryImpl implements GenreRepository {
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
    @Override
    public List<Genre> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Genre> query = session.createQuery("from Genre ", Genre.class);
            List<Genre> genres = query.list();
            return genres;
        }
    }
}
