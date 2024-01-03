package com.example.ridepal.service.interfaces;

import com.example.ridepal.models.Album;
import com.example.ridepal.models.Artist;
import com.example.ridepal.models.Track;

public interface AlbumService {
    Album getById(int id);
    String getLinkFromAlbum(Album album);
    void create(Album album);
    void update(Album album);

    void delete(int id);

}
