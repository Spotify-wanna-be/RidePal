package com.example.ridepal.service;

import com.example.ridepal.models.Genre;
import com.example.ridepal.repository.interfaces.GenreRepository;
import com.example.ridepal.service.interfaces.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre getByGenreId(int id) {
        return genreRepository.getByGenreId(id);
    }

    @Override
    public void create(Genre genre) {
        genreRepository.create(genre);
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.getAll();
    }
}
