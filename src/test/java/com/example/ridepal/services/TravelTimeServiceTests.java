package com.example.ridepal.services;
import com.example.ridepal.service.TravelTimeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelTimeServiceTests {

    @Mock
    private RestTemplate restTemplate;

    @Value("${google.maps.api.key}")
    private String apiKey;

    @InjectMocks
    private TravelTimeService travelTimeService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTravelTime_ShouldReturnNull_WhenJsonParsingFails() {
        // Arrange
        String origin = "Origin";
        String destination = "Destination";
        String jsonResponse = "{ \"invalid\": \"json\" }";

        when(restTemplate.getForObject(any(String.class), eq(String.class)))
                .thenReturn(jsonResponse);

        // Act
        Time result = travelTimeService.getTravelTime(origin, destination);

        // Assert
        assertEquals(null, result);
    }
    @Test
    void getTravelTime_ShouldReturnNull_WhenJsonResponseIsNull() {
        // Arrange
        String origin = "Origin";
        String destination = "Destination";

        when(restTemplate.getForObject(any(String.class), eq(String.class)))
                .thenReturn(null);

        // Act
        Time result = travelTimeService.getTravelTime(origin, destination);

        // Assert
        Assertions.assertEquals(null, result);
    }

    @Test
    void getTravelTime_ShouldReturnNull_WhenRoutesArrayIsMissing() {
        // Arrange
        String origin = "Origin";
        String destination = "Destination";
        String jsonResponse = "{ }";  // Missing "routes" array

        when(restTemplate.getForObject(any(String.class), eq(String.class)))
                .thenReturn(jsonResponse);

        // Act
        Time result = travelTimeService.getTravelTime(origin, destination);

        // Assert
       Assertions.assertEquals(null, result);
    }

//    @Test
//    void getTravelTime_ShouldReturnValidTime_WhenJsonParsingIsSuccessful() {
//        // Arrange
//        String origin = "Origin";
//        String destination = "Destination";
//        String jsonResponse = "{ \"routes\": [ { \"legs\": [ { \"duration\": { \"value\": 3600 } } ] } ] }";
//
//        when(restTemplate.getForObject(any(String.class), eq(String.class)))
//                .thenReturn(jsonResponse);
//
//        // Act
//        Time result = travelTimeService.getTravelTime(origin, destination);
//
//        // Assert
//       Assertions.assertEquals(new Time(1, 0, 0), result);  // 1 hour
//    }

@Test
void parseTravelTimeFromJson_ShouldReturnNull_WhenJsonResponseIsNull() {
    // Arrange
    String origin = "Origin";
    String destination = "Destination";

    when(restTemplate.getForObject(any(String.class), eq(String.class)))
            .thenReturn(null);

    // Act
    Time result = travelTimeService.getTravelTime(origin, destination);

    // Assert
    assertNull(result);
}

    @Test
    void parseTravelTimeFromJson_ShouldReturnNull_WhenDurationElementIsEmpty() {
        // Arrange
        String jsonResponse = "{ \"routes\": [ { \"legs\": [ { \"duration\": { } } ] } ] }";  // Empty "duration" element

        // Act
        Time result = travelTimeService.parseTravelTimeFromJson(jsonResponse);

        // Assert
        assertNull(result);
    }
    @Test
    void parseTravelTimeFromJson_ShouldReturnNull_WhenLegsArrayIsEmpty() {
        // Arrange
        String jsonResponse = "{ \"routes\": [ { \"legs\": [ ] } ] }";  // Empty "legs" array

        // Act
        Time result = travelTimeService.parseTravelTimeFromJson(jsonResponse);

        // Assert
        assertNull(result);
    }
    @Test
    void parseTravelTimeFromJson_ShouldReturnNull_WhenRoutesArrayIsEmpty() {
        // Arrange
        String jsonResponse = "{ \"routes\": [ ] }";  // Empty "routes" array

        // Act
        Time result = travelTimeService.parseTravelTimeFromJson(jsonResponse);

        // Assert
        assertNull(result);
    }
    @Test
    void parseTravelTimeFromJson_ShouldReturnNull_WhenJsonResponseIsEmpty() {
        // Arrange
        String jsonResponse = "{ }";  // Missing "routes" array

        // Act
        Time result = travelTimeService.parseTravelTimeFromJson(jsonResponse);

        // Assert
        assertNull(result);
    }
    @Test
    void parseTravelTimeFromJson_ShouldReturnValidTime_WhenJsonParsingIsSuccessful() {
        // Arrange
        String jsonResponse = "{ \"routes\": [ { \"legs\": [ { \"duration\": { \"value\": 3600 } } ] } ] }";

        // Act
        Time result = travelTimeService.parseTravelTimeFromJson(jsonResponse);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getHours());
        assertEquals(0, result.getMinutes());
        assertEquals(0, result.getSeconds());
    }


}

