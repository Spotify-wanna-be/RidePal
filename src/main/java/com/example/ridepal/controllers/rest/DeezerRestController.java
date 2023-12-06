package com.example.ridepal.controllers.rest;

import com.example.ridepal.service.DeezerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DeezerRestController {

    private final DeezerService deezerService;

    @Autowired
    public DeezerRestController(DeezerService deezerService) {
        this.deezerService = deezerService;
    }

    @GetMapping("/search/{genreId}")
    public String searchAndSaveTracks(@PathVariable String genreId) {
            deezerService.fetchAndInsertTracksByGenre(genreId);
            return "Tracks saved successfully!";
    }

    @GetMapping("/insert/genre")
    public String searchAndSaveGenres() {
        deezerService.fetchAndInsertGenres();
        return "Genres saved successfully!";
    }

    @GetMapping("/insert/artists")
    public String searchAndSaveArtists(){
        deezerService.fetchAndInsertArtists();
        return "Artists saved successfully!";
    }



}
