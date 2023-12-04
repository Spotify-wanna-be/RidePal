package com.example.ridepal.service;

import com.example.ridepal.models.Artist;

public interface ArtistService {
    Artist getByArtistId(int id);
    void create(Artist artist);

    void update(Artist artist);

    void delete(int id);

    Artist getByFirstName(String firstName);
}
