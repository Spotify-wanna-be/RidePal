package com.example.ridepal.models;

import jakarta.persistence.*;

import java.net.URL;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

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
    @Column(name = "rank")
    private int rank;
    @Column(name = "duration")
    private Time duration;
    @ManyToOne
    @JoinColumn(name = "genre")
    private Genre genre;

    @JoinColumn(name = "link")
    private String link;

    @JoinColumn(name = "preview")
    private String preview;

    @ManyToOne
    @JoinColumn(name = "albums")
    private Album album;
    @ManyToMany(mappedBy = "tracks")
    private Set<Playlist> playlists = new HashSet<>();

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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
