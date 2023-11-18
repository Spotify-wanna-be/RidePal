package com.example.ridepal.controllers;

import com.example.ridepal.exceptions.EntityDuplicateException;
import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Track;
import com.example.ridepal.service.TrackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tracks")
public class TrackRestController {
    private final TrackService trackService;

    public TrackRestController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public List<Track> get() {
        return trackService.getAll();
    }

    @GetMapping("/{id}")
    public Track get(@PathVariable int id) {
        try {
            return trackService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/{albumName}")
    public List<Track> get(@PathVariable String albumName) {
        try {
            return trackService.getAllFromAlbum(albumName);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Track create(@RequestHeader HttpHeaders headers, @Valid @RequestBody Track track) {
        try {
            trackService.create(track);
            return track;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public Track update(@RequestHeader HttpHeaders headers, @PathVariable int id, @Valid @RequestBody Track track) {
        try {
            trackService.update(track);
            return track;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        Track track = trackService.getById(id);
        try {
            trackService.delete(track);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
