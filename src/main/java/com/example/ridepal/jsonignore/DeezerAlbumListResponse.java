package com.example.ridepal.jsonignore;

import com.example.ridepal.models.deezer.DeezerAlbum;
import com.example.ridepal.models.deezer.DeezerGenre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class DeezerAlbumListResponse {
    @JsonProperty("data")
    private List<DeezerAlbum> data;

    public List<DeezerAlbum> getData() {
        return data;
    }

    public void setData(List<DeezerAlbum> data) {
        this.data = data;
    }
}