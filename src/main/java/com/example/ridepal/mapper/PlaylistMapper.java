package com.example.ridepal.mapper;

import com.example.ridepal.models.Playlist;

import com.example.ridepal.models.dto.PlaylistDto;
import com.example.ridepal.service.interfaces.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlaylistMapper {
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistMapper(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    public Playlist fromDto(int id, PlaylistDto dto) {
        Playlist playlist = playlistService.getByPlaylistId(id);
        playlist.setName(dto.getName());

        return playlist;
    }

    public Playlist fromDto(PlaylistDto dto) {
        Playlist playlist = new Playlist();
        playlist.setName(dto.getName());

        return playlist;
    }

    public PlaylistDto toDto(Playlist playlist) {
        PlaylistDto dto = new PlaylistDto();
        dto.setName(playlist.getName());
        return dto;
    }
}
