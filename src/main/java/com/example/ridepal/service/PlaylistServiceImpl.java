package com.example.ridepal.service;

import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;
import com.example.ridepal.models.Track;
import com.example.ridepal.models.User;
import com.example.ridepal.repository.PlaylistRepository;
import com.example.ridepal.repository.TrackRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

import static com.example.ridepal.helpers.CheckPermissions.*;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final SessionFactory sessionFactory;
    private final TravelTimeService travelTimeService;
    private final TrackRepository trackRepository;

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository, SessionFactory sessionFactory, TravelTimeService travelTimeService, TrackRepository trackRepository) {
        this.playlistRepository = playlistRepository;
        this.sessionFactory = sessionFactory;
        this.travelTimeService = travelTimeService;
        this.trackRepository = trackRepository;
    }

    @Override
    public List<Playlist> getAll() {
        return playlistRepository.getAll();
    }

    @Override
    public List<Playlist> get(PlaylistFilterOptions playlistFilterOptions) {
        return playlistRepository.get(playlistFilterOptions);
    }

    @Override
    public Playlist getByPlaylistId(int id) {
        return playlistRepository.getByPlaylistId(id);
    }

    @Override
    public List<Playlist> getHighestRankPlaylist() {
        return playlistRepository.getHighestRank();
    }

    @Override
    public List<Track> generatePlaylist(Map<String, Integer> genrePercentages, String origin, String destination) {
        List<Track> selectedTracks = new ArrayList<>();
        int playlistDuration = 0;

        Time duration = travelTimeService.getTravelTime(origin,destination);
        int travelDuration = (duration.getHours()*3600)+(duration.getMinutes()*60)+duration.getSeconds();


        List<String> allGenres = trackRepository.getAllGenresFromTracks(trackRepository.getAll());
        Map<String, List<Track>> tracksByGenre = trackRepository.getTracksByGenres(allGenres);

        Random random = new Random();

        for (Map.Entry<String, Integer> entry : genrePercentages.entrySet()) {
            String genre = entry.getKey();
            int percentage = entry.getValue();

            if (tracksByGenre.containsKey(genre)) {
                List<Track> genreTracks = tracksByGenre.get(genre);
                Collections.shuffle(genreTracks);

                int genreDuration = 0;
                int targetGenreDuration = (int) ((percentage / 100.0) * travelDuration);

                for (Track track : genreTracks) {
                    if (genreDuration + track.getDuration().toLocalTime().getSecond()+ track.getDuration().toLocalTime().getMinute()*60 <= targetGenreDuration) {
                        selectedTracks.add(track);
                        genreDuration += (track.getDuration().toLocalTime().getMinute()*60)+track.getDuration().toLocalTime().getSecond();
                    } else {
                        break;
                    }
                }

                playlistDuration += genreDuration;
            }
        }

        while (playlistDuration < travelDuration - 300 || playlistDuration > travelDuration + 300) {
            String randomGenre = new ArrayList<>(genrePercentages.keySet()).get(random.nextInt(genrePercentages.size()));

            if (tracksByGenre.containsKey(randomGenre)) {
                List<Track> randomGenreTracks = tracksByGenre.get(randomGenre);
                Collections.shuffle(randomGenreTracks);

                Track randomTrack = randomGenreTracks.get(0);

                selectedTracks.add(randomTrack);
                playlistDuration += (randomTrack.getDuration().toLocalTime().getMinute()*60)+randomTrack.getDuration().toLocalTime().getSecond();
            }
        }

        return selectedTracks;
    }

    public int showPostsCount() {
        return playlistRepository.getAllCount().size();
    }

    @Override
    public void create(Playlist playlist, User user, int id) {
        checkUserAuthorization(id, user);
        playlist.setCreatedBy(user);
        playlistRepository.create(playlist);
    }

    @Override
    public void update(User user, Playlist playlist) {
        checkIfSameUserOrAdmin(user, playlist);

        playlistRepository.update(playlist);
    }

    @Override
    public void delete(User user, int playlistId) {
        Playlist playlist = getByPlaylistId(playlistId);
        checkIfSameUserOrAdmin(user, playlist);

        playlistRepository.delete(playlistId);
    }
}
