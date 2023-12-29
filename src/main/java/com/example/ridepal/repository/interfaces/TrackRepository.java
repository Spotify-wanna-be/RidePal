package com.example.ridepal.repository.interfaces;

import com.example.ridepal.models.Track;

import java.net.URL;
import java.util.List;
import java.util.Map;

public interface TrackRepository {
    List<Track> getAll();
    List<Track> getBestRanked();

    List<Track> getAllFromAlbum(String albumName);
    String getLinkFromTrack(Track track);
    String getPreviewFromTrack(Track tracks);

    Track getById(int id);

    List<String> getAllGenresFromTracks(List<Track> tracks);

    Map<String, List<Track>> getTracksByGenres(List<String> genres);

    Track getByTitle(String title);

    void create(Track track);

    void update(Track track);

    void delete(Track track);
}
