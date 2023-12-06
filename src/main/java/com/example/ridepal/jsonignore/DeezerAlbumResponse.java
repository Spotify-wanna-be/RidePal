package com.example.ridepal.jsonignore;

import com.example.ridepal.models.DeezerAlbum;
import com.example.ridepal.models.DeezerArtist;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeezerAlbumResponse {
    @JsonProperty("id")
    private int id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("cover")
    private String cover;

    @JsonProperty("genre_id")
    private int genreId;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "DeezerAlbum{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                // Add other fields to the toString representation
                '}';
    }
}
