package com.example.ridepal.service;

import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;
import com.example.ridepal.models.User;

import java.util.List;

public interface PlaylistService {
    List<Playlist> get(PlaylistFilterOptions playlistFilterOptions);
    Playlist getByPlaylistId(int id);
    void create(Playlist playlist, User user, int id);

    void update(User user, Playlist playlist);

    void delete(User user, int playlistId);
}
