package com.example.ridepal.controllers;

import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;
import com.example.ridepal.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.List;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistRestController {
    private PlaylistService playlistService;

    @Autowired
    public PlaylistRestController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public List<Playlist> get(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Time duration,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder) {
        PlaylistFilterOptions playlistFilterOptions = new PlaylistFilterOptions
                (name, duration, genre, sortBy, sortOrder);
        return playlistService.get(playlistFilterOptions);
    }
}
