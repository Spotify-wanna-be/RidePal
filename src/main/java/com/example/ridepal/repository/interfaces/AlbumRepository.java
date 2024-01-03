package com.example.ridepal.repository.interfaces;

import com.example.ridepal.models.Album;
import com.example.ridepal.models.Track;

public interface AlbumRepository {
    Album getById(int id);
    String getLinkFromAlbum(Album album);
    void create(Album album);
    void update(Album album);

    void delete(int id);

}
