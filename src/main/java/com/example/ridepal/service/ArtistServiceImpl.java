package com.example.ridepal.service;

import com.example.ridepal.models.Artist;
import com.example.ridepal.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist getByArtistId(int id) {
        return artistRepository.getByArtistId(id);
    }

    @Override
    public void create(Artist artist) {
        artistRepository.create(artist);
    }

    @Override
    public void update(Artist artist) {
        artistRepository.update(artist);
    }

    @Override
    public void delete(int id) {
        artistRepository.delete(id);
    }
}
