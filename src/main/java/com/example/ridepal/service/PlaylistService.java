package com.example.ridepal.service;

import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;
import com.example.ridepal.models.Track;
import com.example.ridepal.models.User;

import java.util.List;
import java.util.Map;

public interface PlaylistService {
    List<Playlist> getHighestRankPlaylist();
    List<Playlist> get(PlaylistFilterOptions playlistFilterOptions);
    List<Track> generatePlaylist(Map<String, Integer> genrePercentages, String origin, String destination);
    Playlist getByPlaylistId(int id);
    void create(String name,User user, Map<String, Integer> genrePercentages, String origin, String destination);
    void update(User user, Playlist playlist);
    void delete(User user, int playlistId);
    List<Playlist> getAll();
    int showPostsCount();
}
