package com.example.ridepal.service;

import com.example.ridepal.jsonignore.DeezerAlbumResponse;
import com.example.ridepal.jsonignore.DeezerTrackListResponse;
import com.example.ridepal.models.*;
import com.example.ridepal.models.deezer.DeezerAlbum;
import com.example.ridepal.models.deezer.DeezerArtist;
import com.example.ridepal.models.deezer.DeezerGenre;
import com.example.ridepal.models.deezer.DeezerTrack;
import com.example.ridepal.service.interfaces.AlbumService;
import com.example.ridepal.service.interfaces.ArtistService;
import com.example.ridepal.service.interfaces.GenreService;
import com.example.ridepal.service.interfaces.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Random;

@Service
public class DeezerService {

    @Value("${deezer.api.url}")
    private String deezerApiUrl;

    private final RestTemplate restTemplate;
    private final TrackService trackService;
    private final GenreService genreService;
    private final ArtistService artistService;
    private final AlbumService albumService;

    @Value("${deezer.api.accessToken}")
    private String accessToken;

    @Autowired
    public DeezerService(RestTemplate restTemplate, TrackService trackService,
                         GenreService genreService, ArtistService artistService, AlbumService albumService) {
        this.restTemplate = restTemplate;
        this.trackService = trackService;
        this.genreService = genreService;
        this.artistService = artistService;
        this.albumService = albumService;
    }

    public void fetchAndInsertTracksByAlbum() {
        int randomAlbumId;
        int count = 0;
        Random random = new Random();

        while (count < 100) {
            randomAlbumId = random.nextInt(0, 350000);

            String deezerApiUrl = "https://api.deezer.com/album/" + randomAlbumId + "/tracks";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<DeezerTrackListResponse> responseEntity = restTemplate.exchange(deezerApiUrl,
                    HttpMethod.GET, entity, DeezerTrackListResponse.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                DeezerTrackListResponse trackListResponse = responseEntity.getBody();


                if (trackListResponse != null && trackListResponse.getData() != null) {
                    for (DeezerTrack deezerTrack : trackListResponse.getData()) {

                        Track track = new Track();
                        track.setId(deezerTrack.getId());
                        track.setTitle(deezerTrack.getTitle());
                        track.setRank(deezerTrack.getRank());
                        track.setArtist(deezerTrack.getArtist());
                        track.setDuration(Time.valueOf(LocalTime.ofSecondOfDay(deezerTrack.getDuration())));
                        track.setPreview(deezerTrack.getPreview());
                        track.setLink(deezerTrack.getLink());
//
//                        Artist artist = new Artist();
//                        artist.setId(artist.getId());
//                        artist.setName(artist.getName());
//                        artist.setPicture(artist.getPicture());
//                        artist.setLink(artist.getLink());
//                        track.setArtist(deezerTrack.getArtist());
//                        artistService.create(deezerTrack.getArtist());


                        String albumUrl = "https://api.deezer.com/album/" + randomAlbumId;
                        ResponseEntity<DeezerAlbumResponse> albumResponseEntity = restTemplate.exchange(albumUrl,
                                HttpMethod.GET, entity, DeezerAlbumResponse.class);

                        if (albumResponseEntity.getStatusCode().is2xxSuccessful()) {
                            DeezerAlbumResponse albumResponse = albumResponseEntity.getBody();

                            Album album = new Album();
                            album.setId(albumResponse.getId());
                            album.setName(albumResponse.getTitle());
                            album.setLink(albumResponse.getLink());
                            albumService.create(album);


                            track.setAlbum(album);

                            if (albumResponse != null) {
                                String genreUrl = "https://api.deezer.com/genre/" + albumResponse.getGenreId();
                                ResponseEntity<DeezerGenre> genreResponseEntity = restTemplate.exchange(genreUrl, HttpMethod.GET,
                                        entity, DeezerGenre.class);


                                if (genreResponseEntity.getStatusCode().is2xxSuccessful()) {
                                    DeezerGenre genreResponse = genreResponseEntity.getBody();

                                    if (genreResponse != null) {
                                        Genre genre = new Genre();
                                        genre.setId(Integer.parseInt(genreResponse.getId()));
                                        genre.setType(genreResponse.getName());
                                        genreService.create(genre);

                                        track.setGenre(genre);
                                    }

                                }
                            }
//                            if (albumResponse != null) {
//                                String artistUrl = "https://api.deezer.com/artist/" + albumResponse.getArtist_id();
//                                ResponseEntity<DeezerArtist> artistResponseEntity = restTemplate.exchange(artistUrl, HttpMethod.GET,
//                                        entity, DeezerArtist.class);
//                                if (artistResponseEntity.getStatusCode().is2xxSuccessful()) {
//                                    DeezerArtist artistResponse = artistResponseEntity.getBody();
//
//                                    if (artistResponse != null) {
//                                        Artist artist = new Artist();
//                                        artist.setId(Integer.parseInt(artistResponse.getId()));
//                                        artist.setName(artistResponse.getName());
//                                        artist.setPicture(artistResponse.getPicture());
//                                        artist.setLink(artistResponse.getLink());
//                                        track.setArtist(artist);
//                                    }
//
//                                }
//                            }
                        } else {
                            System.out.println("Error fetching album details. Status code: " + albumResponseEntity.getStatusCodeValue());
                        }
                        trackService.create(track);
                    }
                }

            } else {
                System.out.println("Error fetching tracks. Status code: " + responseEntity.getStatusCodeValue());
            }
        }
    }
}