package com.example.ridepal.service;

import com.example.ridepal.jsonignore.DeezerGenreListResponse;
import com.example.ridepal.jsonignore.DeezerTrackListResponse;
import com.example.ridepal.models.DeezerGenre;
import com.example.ridepal.models.DeezerTrack;
import com.example.ridepal.models.Genre;
import com.example.ridepal.models.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service
public class DeezerService {

    @Value("${deezer.api.url}")
    private String deezerApiUrl;

    private final RestTemplate restTemplate;
    private final TrackService trackService;
    private final GenreService genreService;

    @Value("${deezer.api.accessToken}")
    private String accessToken;

    @Autowired
    public DeezerService(RestTemplate restTemplate, TrackService trackService, GenreService genreService) {
        this.restTemplate = restTemplate;
        this.trackService = trackService;
        this.genreService = genreService;
    }

    public void fetchAndInsertTracksByGenre(String albumId) {
        String deezerApiUrl = "https://api.deezer.com/album/" + albumId + "/tracks?access_token=" + accessToken;

        String deezerApiResponse = restTemplate.getForObject(deezerApiUrl, String.class);
        System.out.println(deezerApiResponse);

        DeezerTrackListResponse trackListResponse = restTemplate.getForObject(deezerApiUrl, DeezerTrackListResponse.class);

        if (trackListResponse != null && trackListResponse.getData() != null) {
            for (DeezerTrack deezerTrack : trackListResponse.getData()) {
                Track track = new Track();
                track.setId(deezerTrack.getId());
                track.setTitle(deezerTrack.getTitle());
                track.setRank(deezerTrack.getRank());
                track.setArtist(deezerTrack.getArtist());
                track.setDuration(deezerTrack.getDuration());

                trackService.create(track);
            }
        }
    }

    public void fetchAndInsertGenres() {
        String deezerApiUrl = "https://api.deezer.com/genre?access_token=" + accessToken;

        String deezerApiResponse = restTemplate.getForObject(deezerApiUrl, String.class);
        System.out.println(deezerApiResponse);

        DeezerGenreListResponse genreListResponse = restTemplate.getForObject(deezerApiUrl, DeezerGenreListResponse.class);

        if (genreListResponse != null && genreListResponse.getData() != null) {
            for (DeezerGenre deezerGenre : genreListResponse.getData()) {
                Genre genre = new Genre();
                genre.setId(Integer.parseInt(deezerGenre.getId()));
                genre.setType(deezerGenre.getName());

                genreService.create(genre);
            }
        }
    }
}
