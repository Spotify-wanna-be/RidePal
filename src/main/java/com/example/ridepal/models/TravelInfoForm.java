package com.example.ridepal.models;

import java.util.Map;

public class TravelInfoForm {
    private String name;
    private String origin;
    private String destination;
    private Map<String, Integer> genrePercentages;
    private Boolean repeatArtist;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Map<String, Integer> getGenrePercentages() {
        return genrePercentages;
    }

    public void setGenrePercentages(Map<String, Integer> genrePercentages) {
        this.genrePercentages = genrePercentages;
    }

    public Boolean getRepeatArtist() {
        return repeatArtist;
    }

    public void setRepeatArtist(Boolean repeatArtist) {
        this.repeatArtist = repeatArtist;
    }
}