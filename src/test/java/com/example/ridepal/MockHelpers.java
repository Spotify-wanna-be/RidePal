package com.example.ridepal;

import com.example.ridepal.models.*;

import java.sql.Time;
import java.time.Duration;

public class MockHelpers {
    public static Track createMockTrack() {
        var mockTrack = new Track();
        mockTrack.setId(1);
        mockTrack.setTitle("MockTrackTitle");
        mockTrack.setArtist(createMockArtist());
        mockTrack.setDuration(Time.valueOf("00:04:00"));
        mockTrack.setRank(1);
        return mockTrack;
    }

    public static Artist createMockArtist(){
        var mockArtist = new Artist();
        mockArtist.setId(1);
        mockArtist.setName("mockFirstName");
        return mockArtist;
    }
    public static Genre createMockGenre() {
        Genre mockGenre = new Genre();
        mockGenre.setId(1);
        mockGenre.setType("MockGenreType");
        return mockGenre;
    }
    public static User createMockUser() {
        var mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("MockUsername");
        mockUser.setPassword("MockPassword");
        mockUser.setLastName("MockLastName");
        mockUser.setFirstName("MockFirstName");
        mockUser.setEmail("mock@user.com");
        return mockUser;
    }
    public static User createMockAdmin() {
        User mockUser = createMockUser();
        mockUser.setAdmin(true);
        return mockUser;
    }
    public static Playlist createMockPlaylist() {
        Playlist mockPlaylist = new Playlist();
        mockPlaylist.setId(1);
        mockPlaylist.setName("MockPlaylistName");
        mockPlaylist.setDuration(Time.valueOf("00:30:00"));
        mockPlaylist.setRank(1);
        mockPlaylist.setCreatedBy(createMockUser());
        return mockPlaylist;
    }
    public static PlaylistFilterOptions createMockPlaylistFilterOptions() {
        return new PlaylistFilterOptions(
                "MockPlaylistName",
                Time.valueOf("00:30:00"),
                "MockGenre",
                "MockSortBy",
                "MockSortOrder"
        );
    }
}
