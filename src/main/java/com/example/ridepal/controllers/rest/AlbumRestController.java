package com.example.ridepal.controllers.rest;

import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.mapper.AlbumMapper;
import com.example.ridepal.models.Album;
import com.example.ridepal.models.Artist;
import com.example.ridepal.models.dto.AlbumDto;
import com.example.ridepal.models.dto.ArtistDto;
import com.example.ridepal.service.interfaces.AlbumService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/albums")
@Hidden
public class AlbumRestController {
    private final AlbumService albumService;
    private final AlbumMapper albumMapper;

    @Autowired

    public AlbumRestController(AlbumService albumService, AlbumMapper albumMapper) {
        this.albumService = albumService;
        this.albumMapper = albumMapper;
    }

    @GetMapping("/{id}")
    public Album getById(@PathVariable int id){
        try{
            return albumService.getById(id);
        }catch (EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
    @PostMapping()
    public Album create(@RequestHeader HttpHeaders headers, @Valid @RequestBody AlbumDto albumDto){
        try{
            Album album=albumMapper.fromDto(albumDto);
            albumService.create(album);
            return album;
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public Album update(@RequestHeader HttpHeaders headers,@RequestBody AlbumDto albumDto,@PathVariable int id){
        try{
            Album album=albumMapper.fromDto(id,albumDto);
            albumService.update(album);
            return album;
        }
        catch(EntityNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @DeleteMapping("{id}")
    public void delete(@RequestHeader HttpHeaders headers,@PathVariable int id){
        try{
            albumService.delete(id);
        }catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
