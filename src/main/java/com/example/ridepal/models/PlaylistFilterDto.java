package com.example.ridepal.models;

import java.sql.Time;

public class PlaylistFilterDto {
    private String name;
    private Time duration;
    private  String genres;

    public PlaylistFilterDto(String name, Time duration, String genres) {
        this.name = name;
        this.duration = duration;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
