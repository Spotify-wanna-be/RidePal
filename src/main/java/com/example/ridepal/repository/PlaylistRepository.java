package com.example.ridepal.repository;

import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;
import com.example.ridepal.models.Track;

import java.util.List;
import java.util.Map;

public interface PlaylistRepository {
    List<Playlist> get(PlaylistFilterOptions playlistFilterOptions);
    Playlist getByPlaylistId(int id);
    List<Playlist> getHighestRank();
    void create(Playlist playlist);
    void update(Playlist playlist);
    List<Playlist> getAll();
    void delete(int playlistId);
    List<Playlist> getAllCount();
}
