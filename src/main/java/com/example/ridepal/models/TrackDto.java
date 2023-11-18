package com.example.ridepal.models;

import java.sql.Time;

public class TrackDto {
    private String title;
    private String album;
    private int rank;
    private Time duration;
    private int artist;

    public TrackDto(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public int getArtist() {
        return artist;
    }

    public void setArtist(int artist) {
        this.artist = artist;
    }
}
