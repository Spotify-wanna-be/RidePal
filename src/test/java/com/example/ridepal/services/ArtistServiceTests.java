package com.example.ridepal.services;

import com.example.ridepal.models.Artist;
import com.example.ridepal.repository.ArtistRepository;
import com.example.ridepal.service.ArtistService;
import com.example.ridepal.service.ArtistServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.ridepal.MockHelpers.*;;
@ExtendWith(MockitoExtension.class)
public class ArtistServiceTests {
    @Mock
    ArtistRepository mockRepository;

    @InjectMocks
    ArtistServiceImpl artistService;

    @Test
    public void GetByArtistId_Should_ReturnArtistById() {
        // Arrange
        Artist mockArtist= createMockArtist();
        Mockito.when(mockRepository.getByArtistId(1))
                .thenReturn(mockArtist);

        // Act
        Artist result = artistService.getByArtistId(1);

        // Assert
        Assertions.assertEquals(1,result.getId() );
        Assertions.assertEquals(mockArtist,result);
    }
    @Test
    public void GetByArtistFirstName_Should_ReturnArtistByFirstName() {
        // Arrange
        String mockFirstName = "MockFirstName";
        Artist expectedArtist = createMockArtist();

        // Mocking the repository method
        Mockito.when(mockRepository.getByFirstName(mockFirstName)).thenReturn(expectedArtist);

        // Act
        Artist actualArtist = artistService.getByFirstName(mockFirstName);

        // Assert
        Assertions.assertEquals(expectedArtist, actualArtist);
        Mockito.verify(mockRepository).getByFirstName(mockFirstName);
    }
    @Test
    public void CreateShould_CreateNewArtist() {
        //Arrange
        Artist mockArtist= createMockArtist();

        // Act
        artistService.create(mockArtist);

        // Assert
        Mockito.verify(mockRepository).create(mockArtist);
    }
    @Test
    public void update_ShouldUpdateArtist() {
        // Arrange
        Artist mockArtist= createMockArtist();

        // Act
        artistService.update(mockArtist);

        // Assert
        Mockito.verify(mockRepository).update(mockArtist);
    }
    @Test
    public void delete_ShouldDeleteArtist() {
        // Arrange
        Artist mockArtist= createMockArtist();

        // Act
        artistService.delete(1);

        // Assert
        Mockito.verify(mockRepository).delete(1);
    }




}
