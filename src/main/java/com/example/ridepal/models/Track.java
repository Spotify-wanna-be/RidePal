package com.example.ridepal.models;

import jakarta.persistence.*;

import java.time.Duration;

@Entity
@Table(name="tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "track_id")
    private int id;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "artist")
    private Artist artist;
    @Column(name = "album")
    private String album;
    @Column(name = "rank")
    private int rank;
    @Column(name = "duration")
    private Duration duration;

    public Track(){
    }

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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
