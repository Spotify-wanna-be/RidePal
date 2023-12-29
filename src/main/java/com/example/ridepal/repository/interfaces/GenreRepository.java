package com.example.ridepal.repository.interfaces;

import com.example.ridepal.models.Genre;

import java.util.List;

public interface GenreRepository {
    Genre getByGenreId(int id);
    void create(Genre genre);

    List<Genre> getAll();
}
