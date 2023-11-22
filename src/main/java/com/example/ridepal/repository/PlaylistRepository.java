package com.example.ridepal.repository;

import com.example.ridepal.models.Playlist;

public interface PlaylistRepository {
    Playlist getByPlaylistId(int id);
    void create(Playlist playlist);

    void update(Playlist playlist);

    void delete(int playlistId);
}
