package com.example.ridepal.helpers;

import com.example.ridepal.models.Artist;
import com.example.ridepal.models.ArtistDto;
import com.example.ridepal.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {
    private final ArtistService artistService;

    @Autowired
    public ArtistMapper(ArtistService artistService) {
        this.artistService = artistService;
    }
    public Artist fromDto(int id, ArtistDto dto){
        Artist existingArtist=artistService.getByArtistId(id);

        existingArtist.setName(dto.getName());
        existingArtist.setPicture(dto.getPicture());

        return existingArtist;
    }

    public Artist fromDto(ArtistDto dto){
        Artist artist=new Artist();

        artist.setName(dto.getName());
        artist.setPicture(dto.getPicture());

        return artist;
    }
}
