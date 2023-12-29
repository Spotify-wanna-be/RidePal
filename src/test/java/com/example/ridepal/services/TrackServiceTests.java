package com.example.ridepal.services;

import com.example.ridepal.exceptions.EntityDuplicateException;
import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.Track;
import com.example.ridepal.repository.interfaces.TrackRepository;
import com.example.ridepal.service.TrackServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static com.example.ridepal.MockHelpers.createMockTrack;
import static org.mockito.Mockito.verify;

public class TrackServiceTests {

    @Mock
    TrackRepository mockRepository;

    @InjectMocks
    TrackServiceImpl trackService;

    @BeforeEach
    void setUp() {
        mockRepository = Mockito.mock(TrackRepository.class);
        trackService = new TrackServiceImpl(mockRepository);
    }

    @Test
    void get_Should_ReturnSingleTrack_When_TrackExists() {
        // Arrange
        Track mockTrack = createMockTrack();
        Mockito.when(mockRepository.getById(Mockito.anyInt())).thenReturn(mockTrack);

        // Act
        Track expectTrack = trackService.getById(1);

        // Assert
        Assertions.assertEquals(mockTrack, expectTrack);
    }

    @Test
    void getAll_ShouldReturnListOfTracks() {
        // Arrange
        List<Track> mockTracks = new ArrayList<>();
        Mockito.when(mockRepository.getAll()).thenReturn(mockTracks);

        // Act
        List<Track> result = trackService.getAll();

        // Assert
        Assertions.assertEquals(mockTracks, result);
    }

    @Test
    void getById_ShouldReturnTrack() {
        // Arrange
        int trackId = 1;
        Track mockTrack = createMockTrack();
        Mockito.when(mockRepository.getById(trackId)).thenReturn(mockTrack);

        // Act
        Track result = trackService.getById(trackId);

        // Assert
        Assertions.assertEquals(mockTrack, result);
    }

    @Test
    void create_ShouldThrowEntityDuplicateException_WhenTrackWithSameTitleExists() {
        // Arrange
        Track existingTrack = createMockTrack();
        Mockito.when(mockRepository.getByTitle(existingTrack.getTitle()))
                .thenReturn(existingTrack);

        // Act and Assert
        Assertions.assertThrows(EntityDuplicateException.class,
                () -> trackService.create(existingTrack));
    }
    @Test
    void create_ShouldNotThrowException_WhenCreatingNewTrack() {
        // Arrange
        Track newTrack = createMockTrack();
        Mockito.when(mockRepository.getByTitle(newTrack.getTitle()))
                .thenThrow(EntityNotFoundException.class);
        Mockito.doNothing().when(mockRepository).create(newTrack);

        // Act and Assert
        Assertions.assertDoesNotThrow(() -> trackService.create(newTrack));
    }


    @Test
    void update_ShouldUpdateTrack_WhenExistingTrackIsSameAsUpdatedTrack() {
        // Arrange
        Track updatedTrack = createMockTrack();
        Mockito.when(mockRepository.getByTitle(updatedTrack.getTitle())).thenReturn(updatedTrack);

        // Act
        trackService.update(updatedTrack);

        // Assert
        verify(mockRepository).update(updatedTrack);
    }
    @Test
    void update_ShouldNotThrowException_WhenUpdatingTrackWithSameTitle() {
        // Arrange
        Track updatedTrack = createMockTrack();
        Mockito.when(mockRepository.getByTitle(updatedTrack.getTitle()))
                .thenReturn(updatedTrack);
        Mockito.doNothing().when(mockRepository).update(updatedTrack);

        // Act and Assert
        Assertions.assertDoesNotThrow(() -> trackService.update(updatedTrack));
    }


    @Test
    void delete_ShouldDeleteTrack() {
        // Arrange
        Track trackToDelete = createMockTrack();

        // Act
        trackService.delete(trackToDelete);

        // Assert
        verify(mockRepository).delete(trackToDelete);
    }
    @Test
    void delete_ShouldNotThrowException_WhenTrackDoesNotExist() {
        // Arrange
        Track trackToDelete = createMockTrack();

        // Act
        trackService.delete(trackToDelete);

        // Assert
        verify(mockRepository).delete(trackToDelete);
    }
    @Test
    void getByTitle_ShouldThrowEntityNotFoundException_WhenTrackNotFound() {
        // Arrange
        String nonExistingTitle = "NonExistingTitle";
        Mockito.when(mockRepository.getByTitle(nonExistingTitle))
                .thenReturn(null);

        // Act and Assert
        Assertions.assertThrows(EntityNotFoundException.class,
                () -> trackService.getByTitle(nonExistingTitle));
    }


    @Test
    void getAllFromAlbum_ShouldReturnListOfTracksFromAlbum() {
        // Arrange
        String albumName = "MockAlbum";
        List<Track> mockTracks = new ArrayList<>();
        Mockito.when(mockRepository.getAllFromAlbum(albumName)).thenReturn(mockTracks);

        // Act
        List<Track> result = trackService.getAllFromAlbum(albumName);

        // Assert
        Assertions.assertEquals(mockTracks, result);
    }
    @Test
    void getByTitle_ShouldReturnTrackByTitle() {
        // Arrange
        String title = "MockTitle";
        Track mockTrack = createMockTrack();
        Mockito.when(mockRepository.getByTitle(title)).thenReturn(mockTrack);

        // Act
        Track result = trackService.getByTitle(title);

        // Assert
        Assertions.assertEquals(mockTrack, result);
    }

}
