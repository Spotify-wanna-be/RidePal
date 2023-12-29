package com.example.ridepal.repository;

import com.example.ridepal.models.Artist;

public interface ArtistRepository {
    Artist getByArtistId(int id);
    String getPictureFromArtist(Artist artist);
    void create(Artist artist);

    void update(Artist artist);

    void delete(int artistId);

    Artist getByFirstName(String firstName);

}
