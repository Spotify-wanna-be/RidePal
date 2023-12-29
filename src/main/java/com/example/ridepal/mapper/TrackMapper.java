package com.example.ridepal.mapper;

import com.example.ridepal.models.Track;
import com.example.ridepal.models.TrackDto;
import com.example.ridepal.service.interfaces.ArtistService;
import com.example.ridepal.service.interfaces.TrackService;
import org.springframework.stereotype.Component;

@Component
public class TrackMapper {
    private final TrackService trackService;
    private final ArtistService artistService;

    public TrackMapper(TrackService trackService, ArtistService artistService) {
        this.trackService = trackService;
        this.artistService = artistService;
    }

    public Track fromDto(int id, TrackDto dto){
        Track existingTrack = trackService.getById(id);

        existingTrack.setTitle(dto.getTitle());
        existingTrack.setDuration(dto.getDuration());
        existingTrack.setRank(dto.getRank());

        return existingTrack;
    }

    public Track fromDto( TrackDto dto){
        Track track = new Track();

        track.setTitle(dto.getTitle());
        track.setDuration(dto.getDuration());
        track.setRank(dto.getRank());
        track.setArtist(artistService.getByArtistId(dto.getArtist()));
        return track;
    }
}
