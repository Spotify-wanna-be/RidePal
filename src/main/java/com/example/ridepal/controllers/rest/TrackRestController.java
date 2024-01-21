package com.example.ridepal.controllers.rest;

import com.example.ridepal.exceptions.EntityDuplicateException;
import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.helpers.AuthenticationHelper;
import com.example.ridepal.mapper.TrackMapper;
import com.example.ridepal.models.Track;
import com.example.ridepal.models.dto.TrackDto;
import com.example.ridepal.service.interfaces.TrackService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/tracks")
@Hidden
public class TrackRestController {
    private final TrackService trackService;
    private final AuthenticationHelper authenticationHelper;
    private final TrackMapper trackMapper;


    @Autowired
    public TrackRestController(TrackService trackService,
                               AuthenticationHelper authenticationHelper,
                               TrackMapper trackMapper) {
        this.trackService = trackService;
        this.authenticationHelper = authenticationHelper;
        this.trackMapper = trackMapper;
    }

    @GetMapping
    public List<Track> get() {
        return trackService.getAll();
    }

    @GetMapping("/{id}")
    public Track getById(@PathVariable int id) {
        try {
            return trackService.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/best")
    public List<Track> getBestRanked(){
        return trackService.getBestRanked();
    }

//    @GetMapping("/{albumName}")
//    public List<Track> getFromAlbum(@PathVariable String albumName) {
//        try {
//            return trackService.getAllFromAlbum(albumName);
//        } catch (EntityNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        }
//    }

    @PostMapping
    public Track create(@RequestHeader HttpHeaders headers, @Valid @RequestBody TrackDto trackDto) {
        try {
            Track track = trackMapper.fromDto(trackDto);
            trackService.create(track);
            return track;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public Track update(@RequestHeader HttpHeaders headers, @PathVariable int id, @Valid @RequestBody TrackDto trackDto) {
        try {
            Track track = trackMapper.fromDto(id, trackDto);
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
