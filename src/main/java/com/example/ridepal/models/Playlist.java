package com.example.ridepal.models;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name="playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private int id;

    @Column(name= "name")
    private  String name;

    @Column(name = "duration")
    private Time duration;

    @Column(name="rank")
    private int rank;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User createdBy;

    public Playlist() {
    }

    public int getId() {
        return id;
    }

    public void setId(int playlistId) {
        this.id = playlistId;
        

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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Playlist playlist = (Playlist) obj;
        return id == playlist.id;
    }

}
