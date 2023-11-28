package com.example.ridepal.controllers;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.helpers.ArtistMapper;
import com.example.ridepal.models.Artist;
import com.example.ridepal.models.ArtistDto;
import com.example.ridepal.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/artists")
public class ArtistRestController {
    private final ArtistService artistService;
    private final ArtistMapper artistMapper;
    @Autowired
    public ArtistRestController(ArtistService artistService,ArtistMapper artistMapper) {
        this.artistService = artistService;
        this.artistMapper=artistMapper;
    }
    @GetMapping("/{id}")
    public Artist getByArtistId(@PathVariable int id){
        try{
            return artistService.getByArtistId(id);
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
    @PostMapping()
    public Artist create(@RequestHeader HttpHeaders headers,@Valid @RequestBody ArtistDto artistDto){
        try{
            Artist artist=artistMapper.fromDto(artistDto);
            artistService.create(artist);
            return artist;
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public Artist update(@RequestHeader HttpHeaders headers,@RequestBody ArtistDto artistDto,@PathVariable int id){
        try{
            Artist artist=artistMapper.fromDto(id,artistDto);
            artistService.update(artist);
            return artist;
        }
        catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @DeleteMapping("{id}")
    public void delete(@RequestHeader HttpHeaders headers,@PathVariable int id){
        try{
            artistService.delete(id);
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
