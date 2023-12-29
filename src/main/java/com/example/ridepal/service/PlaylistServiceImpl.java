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
import java.time.LocalTime;
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
    public List<Playlist> getUsersPlaylists(User user){
        return playlistRepository.getUsersPlaylists(user);
    }

    @Override
    public List<Playlist> getHighestRankPlaylist() {
        return playlistRepository.getHighestRank();
    }
    @Override
    public List<Playlist> getRanked() {
        return playlistRepository.getRanked();
    }

    @Override
    public Set<Track> generatePlaylist(Map<String, Integer> genrePercentages, String origin, String destination) {
        Set<Track> selectedTracks = new HashSet<>();
        int playlistDuration = 0;

        Time duration = travelTimeService.getTravelTime(origin, destination);
        int travelDuration = (duration.getHours() * 3600) + (duration.getMinutes() * 60) + duration.getSeconds();


        List<String> allGenres = trackRepository.getAllGenresFromTracks(trackRepository.getAll());
        Map<String, List<Track>> tracksByGenre = trackRepository.getTracksByGenres(allGenres);

        Random random = new Random();

        for (Map.Entry<String, Integer> entry : genrePercentages.entrySet()) {
            String genre = entry.getKey();
            Integer percentage = entry.getValue();

            int percentageValue = (percentage != null) ? percentage.intValue() : 0;

            if (tracksByGenre.containsKey(genre)) {
                List<Track> genreTracks = tracksByGenre.get(genre);
                Collections.shuffle(genreTracks);

                int genreDuration = 0;
                int targetGenreDuration = (int) ((percentageValue / 100.0) * travelDuration);

                for (Track track : genreTracks) {
                    if (genreDuration + track.getDuration().toLocalTime().getSecond() + track.getDuration().toLocalTime().getMinute() * 60 <= targetGenreDuration) {
                        selectedTracks.add(track);
                        genreDuration += (track.getDuration().toLocalTime().getMinute() * 60) + track.getDuration().toLocalTime().getSecond();
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

    private Time playlistDuration(Set<Track> selectedTracks) {
        int seconds=0;

        for (Track track : selectedTracks) {
            seconds += track.getDuration().getSeconds()+(track.getDuration().getMinutes()*60);
        }
        // Convert seconds to LocalTime
        LocalTime localTime = LocalTime.ofSecondOfDay(seconds);

        // Convert LocalTime to Time
        Time time = Time.valueOf(localTime);

        return time;

    }


    @Override
    public int showPostsCount() {
        return playlistRepository.getAllCount().size();
    }

    @Override
    public void create(String name, User user,
                       Map<String, Integer> genrePercentages,
                       String origin, String destination) {
        Playlist playlist = new Playlist();
        checkUserAuthorization(user.getId(), user);
        Set<Track> selectedTracks = generatePlaylist(genrePercentages, origin, destination);
        playlist.setDuration(playlistDuration(selectedTracks));
        playlist.setName(name);
        playlist.setTracks(selectedTracks);
        playlist.setCreatedBy(user);
        playlist.setRank(rankPlaylist(selectedTracks));
        playlistRepository.create(playlist);
    }

    private int rankPlaylist(Set<Track> selectedTracks) {
        int totalRank = 0;
        int counter = 0;
        for (Track track : selectedTracks) {
            counter++;
            if(track.getRank()>0) {
                totalRank += track.getRank();
            }
        }
        int rankPlaylist = totalRank / counter;
        return rankPlaylist;
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
