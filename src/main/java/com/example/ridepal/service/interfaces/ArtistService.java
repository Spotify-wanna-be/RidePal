package com.example.ridepal.service.interfaces;

import com.example.ridepal.models.Artist;

public interface ArtistService {
    Artist getByArtistId(int id);
    String getPictureFromArtist(Artist artist);
    void create(Artist artist);

    void update(Artist artist);

    void delete(int id);

    Artist getByFirstName(String firstName);
}
