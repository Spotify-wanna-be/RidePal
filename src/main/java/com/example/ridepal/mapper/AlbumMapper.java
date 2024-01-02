package com.example.ridepal.mapper;

import com.example.ridepal.models.Album;
import com.example.ridepal.models.dto.AlbumDto;
import com.example.ridepal.service.interfaces.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper {
    private final AlbumService albumService;

    @Autowired
    public AlbumMapper(AlbumService albumService) {
        this.albumService = albumService;
    }
    public Album fromDto(int id, AlbumDto dto){
        Album existingAlbum = albumService.getById(id);

        existingAlbum.setName(dto.getName());

        return existingAlbum;
    }

    public Album fromDto(AlbumDto dto){
        Album album = new Album();

        album.setName(dto.getName());

        return album;
    }
}
