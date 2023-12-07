package com.example.ridepal.repository;

import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;
import com.example.ridepal.models.Track;

import java.util.List;
import java.util.Map;

public interface PlaylistRepository {
    List<Playlist> get(PlaylistFilterOptions playlistFilterOptions);
    Playlist getByPlaylistId(int id);
    List<Track> generatePlaylist(Map<String, Integer> genrePercentages, int travelDuration);
    List<String> getAllGenresFromTracks(List<Track> tracks);
    Map<String, List<Track>> getTracksByGenres(List<String> genres);
    List<Playlist> getHighestRank();
    void create(Playlist playlist);
    void update(Playlist playlist);
    List<Playlist> getAll();
    void delete(int playlistId);
    List<Playlist> getAllCount();
}
