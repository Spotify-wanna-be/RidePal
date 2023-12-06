package com.example.ridepal.service;

import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;
import com.example.ridepal.models.Track;
import com.example.ridepal.models.User;
import com.example.ridepal.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.example.ridepal.helpers.CheckPermissions.*;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public List<Playlist> get(PlaylistFilterOptions playlistFilterOptions) {
        return playlistRepository.get(playlistFilterOptions);
    }

    @Override
    public Playlist getByPlaylistId(int id) {
        return playlistRepository.getByPlaylistId(id);
    }

    @Override
    public List<Playlist> getHighestRankPlaylist() {
        return null;
        //-- TODO --
    }

    @Override
    public List<Track> generatePlaylist(Map<String, Integer> genrePercentages, int travelDuration) {
        return playlistRepository.generatePlaylist(genrePercentages, travelDuration);
    }

    @Override
    public void create(Playlist playlist, User user, int id) {
        checkUserAuthorization(id, user);
        playlist.setCreatedBy(user);
        playlistRepository.create(playlist);
    }

    @Override
    public void update(User user, Playlist playlist) {
        checkIfSameUserOrAdmin(user, playlist);

        playlistRepository.update(playlist);
    }

    @Override
    public void delete(User user, int playlistId) {
        Playlist playlist = getByPlaylistId(playlistId);
        checkIfSameUserOrAdmin(user, playlist);

        playlistRepository.delete(playlistId);
    }
}
