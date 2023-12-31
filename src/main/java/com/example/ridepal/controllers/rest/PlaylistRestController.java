package com.example.ridepal.controllers.rest;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.exceptions.UnauthorizedOperationException;
import com.example.ridepal.helpers.AuthenticationHelper;
import com.example.ridepal.models.*;
import com.example.ridepal.service.interfaces.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public void delete(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            playlistService.delete(user, id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/new/{origin}/{destination}/{repeatArtist}")
    public Set<Track> getGeneratedPlaylist(
            @RequestBody Map<String, Integer> genrePercentages,
            @PathVariable String origin,
            @PathVariable String destination,
            @PathVariable Boolean repeatArtist) {
        return playlistService.generatePlaylist(genrePercentages, origin, destination,repeatArtist);
    }

    @GetMapping("/ranked")
    public List<Playlist> getRanked() {
        return playlistService.getRanked();
    }

    @GetMapping("/{id}")
    public Playlist getById(@PathVariable int id) {
        try {
            return playlistService.getByPlaylistId(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/{name}")
    public void create(
            @RequestHeader HttpHeaders headers,
            @PathVariable String name,
            @RequestBody Map<String, Integer> genrePercentages,
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam Boolean repeatArtist) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            playlistService.create(name, user, genrePercentages, origin, destination,repeatArtist);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (UnauthorizedOperationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}
