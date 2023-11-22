package com.example.ridepal.repository;

import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;

import java.util.List;

public interface PlaylistRepository {
    List<Playlist> get(PlaylistFilterOptions playlistFilterOptions);
    Playlist getByPlaylistId(int id);
    void create(Playlist playlist);

    void update(Playlist playlist);

    void delete(int playlistId);
}
