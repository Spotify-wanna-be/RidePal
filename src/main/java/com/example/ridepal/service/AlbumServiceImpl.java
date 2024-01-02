package com.example.ridepal.service;

import com.example.ridepal.models.Album;
import com.example.ridepal.repository.interfaces.AlbumRepository;
import com.example.ridepal.service.interfaces.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;



    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public Album getById(int id) {
        return albumRepository.getById(id);
    }

    @Override
    public void create(Album album) {
        albumRepository.create(album);
    }

    @Override
    public void update(Album album) {
        albumRepository.update(album);
    }

    @Override
    public void delete(int id) {
        albumRepository.delete(id);
    }
}
