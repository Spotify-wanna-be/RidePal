package com.example.ridepal.controllers.rest;

import com.example.ridepal.service.DeezerService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Hidden
public class DeezerRestController {

    private final DeezerService deezerService;

    @Autowired
    public DeezerRestController(DeezerService deezerService) {
        this.deezerService = deezerService;
    }

    @GetMapping("/search")
    public String searchAndSaveTracks() {
            deezerService.fetchAndInsertTracksByAlbum();
            return "Tracks saved successfully!";
    }

}
