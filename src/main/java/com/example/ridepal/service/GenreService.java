package com.example.ridepal.service;

import com.example.ridepal.models.Genre;

public interface GenreService {
    Genre getByGenreId(int id);
    void create(Genre genre);
}
