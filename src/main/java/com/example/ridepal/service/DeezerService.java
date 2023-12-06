package com.example.ridepal.service;

import com.example.ridepal.jsonignore.DeezerAlbumResponse;
import com.example.ridepal.jsonignore.DeezerArtistResponse;
import com.example.ridepal.jsonignore.DeezerGenreListResponse;
import com.example.ridepal.jsonignore.DeezerTrackListResponse;
import com.example.ridepal.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.time.LocalTime;

@Service
public class DeezerService {

    @Value("${deezer.api.url}")
    private String deezerApiUrl;

    private final RestTemplate restTemplate;
    private final TrackService trackService;
    private final GenreService genreService;
    private final ArtistService artistService;

    @Value("${deezer.api.accessToken}")
    private String accessToken;

    @Autowired
    public DeezerService(RestTemplate restTemplate, TrackService trackService, GenreService genreService, ArtistService artistService) {
        this.restTemplate = restTemplate;
        this.trackService = trackService;
        this.genreService = genreService;
        this.artistService = artistService;
    }

    public void fetchAndInsertTracksByGenre(String albumId) {
        String deezerApiUrl = "https://api.deezer.com/album/" + albumId + "/tracks";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<DeezerTrackListResponse> responseEntity = restTemplate.exchange(deezerApiUrl, HttpMethod.GET, entity, DeezerTrackListResponse.class);

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

                    String albumUrl = "https://api.deezer.com/album/" + albumId;
                    ResponseEntity<DeezerAlbumResponse> albumResponseEntity = restTemplate.exchange(albumUrl,
                            HttpMethod.GET, entity, DeezerAlbumResponse.class);

                    if (albumResponseEntity.getStatusCode().is2xxSuccessful()) {
                        DeezerAlbumResponse albumResponse = albumResponseEntity.getBody();

                        if (albumResponse != null) {
                            String genreUrl = "https://api.deezer.com/genre/" + albumResponse.getGenreId();
                            ResponseEntity<DeezerGenre> genreResponseEntity = restTemplate.exchange(genreUrl,HttpMethod.GET,
                                    entity, DeezerGenre.class);

                            if(genreResponseEntity.getStatusCode().is2xxSuccessful()){
                                DeezerGenre genreResponse = genreResponseEntity.getBody();

                                if(genreResponse != null){
                                    Genre genre = new Genre();
                                    genre.setId(Integer.parseInt(genreResponse.getId()));
                                    genre.setType(genreResponse.getName());

                                    track.setGenre(genre);
                                }
                            }
                        }
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

    public void fetchAndInsertGenres() {
        String deezerApiUrl = "https://api.deezer.com/artist/27/related";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<DeezerGenreListResponse> responseEntity = restTemplate.exchange(deezerApiUrl, HttpMethod.GET, entity, DeezerGenreListResponse.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            DeezerGenreListResponse genreListResponse = responseEntity.getBody();

            if (genreListResponse != null && genreListResponse.getData() != null) {
                for (DeezerGenre deezerGenre : genreListResponse.getData()) {
                    Genre genre = new Genre();
                    genre.setId(Integer.parseInt(deezerGenre.getId()));
                    genre.setType(deezerGenre.getName());

                    genreService.create(genre);
                }
            }
        } else {
            System.out.println("Error fetching genres. Status code: " + responseEntity.getStatusCodeValue());
        }
    }


    public void fetchAndInsertArtists(){
        String deezerApiUrl = "https://api.deezer.com/artist/27/related";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<DeezerArtistResponse> responseEntity = restTemplate.exchange(deezerApiUrl, HttpMethod.GET, entity, DeezerArtistResponse.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            DeezerArtistResponse deezerArtistResponse = responseEntity.getBody();

            if (deezerArtistResponse != null && deezerArtistResponse.getData() != null) {
                for (DeezerArtist deezerArtist : deezerArtistResponse.getData()) {
                    Artist artist = new Artist();
                    artist.setId(Integer.parseInt(deezerArtist.getId()));
                    artist.setFirstName(deezerArtist.getName());

                    artistService.create(artist);
                }
            }
        } else {
            System.out.println("Error fetching genres. Status code: " + responseEntity.getStatusCodeValue());
        }
    }
}
