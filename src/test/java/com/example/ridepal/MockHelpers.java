package com.example.ridepal;

import com.example.ridepal.models.Artist;
import com.example.ridepal.models.Track;
import com.example.ridepal.models.User;

import java.sql.Time;
import java.time.Duration;

public class MockHelpers {
    public static Track createMockTrack() {
        var mockTrack = new Track();
        mockTrack.setId(1);
        mockTrack.setTitle("MockTrackTitle");
        mockTrack.setAlbum("MockTrackAlbum");
        mockTrack.setArtist(createMockArtist());
        mockTrack.setDuration(Time.valueOf("00:04:00"));
        mockTrack.setRank(1);
        return mockTrack;
    }

    public static Artist createMockArtist(){
        var mockArtist = new Artist();
        mockArtist.setId(1);
        mockArtist.setFirstName("mockFirstName");
        mockArtist.setLastName("mockLastName");
        return mockArtist;
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
}
