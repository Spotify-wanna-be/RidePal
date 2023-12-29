package com.example.ridepal.service;

import com.example.ridepal.exceptions.EntityDuplicateException;
import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Track;
import com.example.ridepal.repository.interfaces.TrackRepository;
import com.example.ridepal.service.interfaces.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public List<Track> getAll() {
        return trackRepository.getAll();
    }

    @Override
    public List<Track> getAllFromAlbum(String albumName) {
        return trackRepository.getAllFromAlbum(albumName);
    }

    @Override
    public Track getById(int id) {
        return trackRepository.getById(id);
    }

    @Override
    public Track getByTitle(String title) {
        Track track = trackRepository.getByTitle(title);

        if (track == null) {
            throw new EntityNotFoundException("Track", "title", title);
        }

        return track;

    }

    public List<Track> getBestRanked() {
        return trackRepository.getBestRanked();
    }

    @Override
    public String getLinkFromTrack(Track track) {
        return trackRepository.getLinkFromTrack(track);
    }

    @Override
    public String getPreviewFromTrack(Track tracks) {
        return trackRepository.getPreviewFromTrack(tracks);
    }



    @Override
    public void create(Track track) {
        boolean duplicateExists = true;
        try {
            trackRepository.getByTitle(track.getTitle());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }
        if (duplicateExists) {
            throw new EntityDuplicateException("Track", "name", track.getTitle());
        }

        trackRepository.create(track);
    }

    @Override
    public void update(Track track) {
        boolean duplicateExists = true;

        try {
            Track existingTrack = trackRepository.getByTitle(track.getTitle());
            if (existingTrack.getId() == track.getId()) {
                duplicateExists = false;
            }
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new EntityDuplicateException("Track", "title", track.getTitle());
        }

        trackRepository.update(track);
    }

    @Override
    public void delete(Track track) {
        trackRepository.delete(track);
    }
}
