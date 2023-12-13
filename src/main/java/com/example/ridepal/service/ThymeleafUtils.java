package com.example.ridepal.service;

import org.springframework.stereotype.Service;

@Service
public class ThymeleafUtils {
    public String generateAttribute(String genre) {
        return "genrePercentages['" + genre + "']";
    }
}
