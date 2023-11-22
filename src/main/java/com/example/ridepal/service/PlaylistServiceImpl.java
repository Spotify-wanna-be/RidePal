package com.example.ridepal.service;

import com.example.ridepal.models.Playlist;
import com.example.ridepal.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public Playlist getByPlaylistId(int id) {
        return playlistRepository.getByPlaylistId(id);
    }

    @Override
    public void create(Playlist playlist) {
        playlistRepository.create(playlist);
    }

    @Override
    public void update(Playlist playlist) {
        playlistRepository.update(playlist);
    }

    @Override
    public void delete(int playlistId) {
        playlistRepository.delete(playlistId);
    }
}
