package com.example.ridepal.service.interfaces;

import com.example.ridepal.models.Genre;

import java.util.List;

public interface GenreService {
    Genre getByGenreId(int id);
    void create(Genre genre);
    List<Genre> getAll();
}
