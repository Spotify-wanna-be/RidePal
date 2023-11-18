package com.example.ridepal.repository;

import com.example.ridepal.models.Artist;

public interface ArtistRepository {
    Artist getByArtistId(int id);
    void create(Artist artist);

    void update(Artist artist);

    void delete(int artistId);

}
