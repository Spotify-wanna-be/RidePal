package com.example.ridepal.service;

import com.example.ridepal.models.Playlist;

public interface PlaylistService {
    Playlist getByPlaylistId(int id);
    void create(Playlist playlist);

    void update(Playlist playlist);

    void delete(int playlistId);
}
