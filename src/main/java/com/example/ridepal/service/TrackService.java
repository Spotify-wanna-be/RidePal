package com.example.ridepal.service;

import com.example.ridepal.models.Artist;
import com.example.ridepal.models.Track;

import java.util.List;

public interface TrackService {
    List<Track> getAll();

    List<Track> getAllFromAlbum(String albumName);

    List<Track> getBestRanked();

    Track getById(int id);

    Track getByTitle(String title);

    void create(Track track);

    void update(Track track);

    void delete(Track track);
}
