package com.example.ridepal.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Time;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeezerTrack {


    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("link")
    private String link;

    @JsonProperty("duration")
    private int duration;

    @JsonProperty("rank")
    private int rank;

    @JsonProperty("artist")
    private Artist artist;

    @JsonProperty("album_id")
    private DeezerAlbum album;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public DeezerAlbum getAlbum() {
        return album;
    }

    public void setAlbum(DeezerAlbum album) {
        this.album = album;
    }
}
