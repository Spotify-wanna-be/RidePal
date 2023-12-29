package com.example.ridepal.service.interfaces;

import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;
import com.example.ridepal.models.Track;
import com.example.ridepal.models.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PlaylistService {
    List<Playlist> getHighestRankPlaylist();
    List<Playlist> getRanked();
    List<Playlist> get(PlaylistFilterOptions playlistFilterOptions);
    List<Playlist> getUsersPlaylists(User user);
    Set<Track> generatePlaylist(Map<String, Integer> genrePercentages, String origin, String destination);
    Playlist getByPlaylistId(int id);
    void create(String name,User user, Map<String, Integer> genrePercentages, String origin, String destination);
    void update(User user, Playlist playlist);
    void delete(User user, int playlistId);
    List<Playlist> getAll();
    int showPostsCount();
}
