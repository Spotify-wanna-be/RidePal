package com.example.ridepal.services;

import com.example.ridepal.models.Genre;
import com.example.ridepal.repository.interfaces.GenreRepository;
import com.example.ridepal.service.GenreServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.ridepal.MockHelpers.*;

@ExtendWith(MockitoExtension.class)
public class GenreServiceTests {
    @Mock
    GenreRepository mockRepository;

    @InjectMocks
    GenreServiceImpl mockService;
    @Test
    public void CreateShould_CreateNewGenre() {
        //Arrange
        Genre mockGenre= createMockGenre();

        // Act
        mockService.create(mockGenre);

        // Assert
        Mockito.verify(mockRepository).create(mockGenre);
    }

}
