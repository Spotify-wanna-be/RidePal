package com.example.ridepal.repository.interfaces;

import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;
import com.example.ridepal.models.Track;
import com.example.ridepal.models.User;

import java.util.List;
import java.util.Map;

public interface PlaylistRepository {
    List<Playlist> get(PlaylistFilterOptions playlistFilterOptions);
    Playlist getByPlaylistId(int id);
    List<Playlist> getUsersPlaylists(User user);
    List<Playlist> getHighestRank();
    List<Playlist> getRanked();
    void create(Playlist playlist);
    void update(Playlist playlist);
    List<Playlist> getAll();
    void delete(int playlistId);
    List<Playlist> getAllCount();
}
