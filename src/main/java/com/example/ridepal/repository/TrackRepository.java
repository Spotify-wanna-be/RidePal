package com.example.ridepal.repository;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Track;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public interface TrackRepository {
    List<Track> getAll();

    List<Track> getAllFromAlbum(String albumName);

    Track getById(int id);

    Track getByTitle(String title);

    void create(Track track);

    void update(Track track);

    void delete(Track track);
}
