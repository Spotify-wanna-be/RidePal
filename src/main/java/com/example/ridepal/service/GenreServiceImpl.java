package com.example.ridepal.service;

import com.example.ridepal.models.Genre;
import com.example.ridepal.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService{
    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    public void create(Genre genre){
        genreRepository.create(genre);
    }
}
