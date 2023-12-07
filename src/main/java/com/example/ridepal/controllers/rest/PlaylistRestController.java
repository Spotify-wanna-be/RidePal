package com.example.ridepal.controllers.rest;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.helpers.AuthenticationHelper;
import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;
import com.example.ridepal.models.Track;
import com.example.ridepal.models.User;
import com.example.ridepal.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Time;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistRestController {
    private PlaylistService playlistService;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public PlaylistRestController(PlaylistService playlistService, AuthenticationHelper authenticationHelper) {
        this.playlistService = playlistService;
        this.authenticationHelper = authenticationHelper;
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
    @DeleteMapping("{id}")
    public void delete(@RequestHeader HttpHeaders headers, @PathVariable int id){
        try{
            User user = authenticationHelper.tryGetUser(headers);
            playlistService.delete(user,id);
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/new")
    public List<Track> getGeneratedPlaylist(
            @RequestParam() Map<String, Integer> genrePercentages,
            @RequestParam() String origin,
            @RequestParam() String destination){
        return playlistService.generatePlaylist(genrePercentages, origin,destination);

    }
}
