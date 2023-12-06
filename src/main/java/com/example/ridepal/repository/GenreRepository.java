package com.example.ridepal.repository;

import com.example.ridepal.models.Genre;

public interface GenreRepository {
    Genre getByGenreId(int id);
    void create(Genre genre);
}
