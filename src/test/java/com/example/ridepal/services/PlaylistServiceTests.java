package com.example.ridepal.services;

import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.PlaylistFilterOptions;
import com.example.ridepal.repository.interfaces.PlaylistRepository;
import com.example.ridepal.service.PlaylistServiceImpl;
import org.junit.jupiter.api.Assertions;
import com.example.ridepal.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import static com.example.ridepal.MockHelpers.*;
import static com.example.ridepal.helpers.CheckPermissionsHelper.checkIfSameUserOrAdmin;
;

@ExtendWith(MockitoExtension.class)
public class PlaylistServiceTests {
    @Mock
    PlaylistRepository mockRepository;

    @InjectMocks
    PlaylistServiceImpl playlistService;
    @Test
    public void get_Should_ReturnList() {
        // Arrange
        PlaylistFilterOptions filterOptions = createMockPlaylistFilterOptions();
        List<Playlist> expectedPlaylists = Arrays.asList(new Playlist(), new Playlist());
        Mockito.when(mockRepository.get(filterOptions)).thenReturn(expectedPlaylists);

        // Act
        List<Playlist> actualPlaylists = playlistService.get(filterOptions);

        // Assert
        Assertions.assertEquals(expectedPlaylists, actualPlaylists);
        Mockito.verify(mockRepository).get(filterOptions);
    }

    @Test
    public void getByPlaylistId_Should_ReturnPlaylistById() {
        // Arrange
        int playlistId = 1;
        Playlist expectedPlaylist = createMockPlaylist();
        Mockito.when(mockRepository.getByPlaylistId(playlistId)).thenReturn(expectedPlaylist);

        // Act
        Playlist actualPlaylist = playlistService.getByPlaylistId(playlistId);

        // Assert
        Assertions.assertEquals(expectedPlaylist, actualPlaylist);
        Mockito.verify(mockRepository).getByPlaylistId(playlistId);
    }
    //todo edit

//    @Test
//    public void create_ShouldCreate_NewPlaylist() {
//        // Arrange
//        Playlist playlistToCreate = new Playlist();
//        User mockUser = createMockUser();
//        int id = 1;
//
//        // Mocking the user authorization check
////        Mockito.doNothing().when(CheckPermissions.class);
//        checkUserAuthorization(id, mockUser);
//
//        // Act
//        playlistService.create(playlistToCreate, mockUser, id);
//
//        // Assert
//        Mockito.verify(mockRepository).create(playlistToCreate);
//    }

    @Test
    public void update_ShouldUpdate_Playlist() {
        // Arrange
        Playlist playlistToUpdate = new Playlist();
        User mockUser = createMockAdmin();

        // Mocking the user and admin check
//        Mockito.doNothing().when(CheckPermissions.class);
        checkIfSameUserOrAdmin(mockUser, playlistToUpdate);

        // Act
        playlistService.update(mockUser, playlistToUpdate);

        // Assert
        Mockito.verify(mockRepository).update(playlistToUpdate);
    }

    @Test
    public void delete_ShouldDeletePlaylist() {
        // Arrange
        int playlistIdToDelete = 1;
        User mockUser = createMockAdmin();
        Playlist mockPlaylist = new Playlist();

        // Mocking the user and admin check
//        Mockito.doNothing().when(CheckPermissions.class);
        checkIfSameUserOrAdmin(mockUser, mockPlaylist);

        // Mocking the getByPlaylistId method
        Mockito.when(mockRepository.getByPlaylistId(playlistIdToDelete)).thenReturn(mockPlaylist);

        // Act
        playlistService.delete(mockUser, playlistIdToDelete);

        // Assert
        Mockito.verify(mockRepository).delete(playlistIdToDelete);
    }


}
