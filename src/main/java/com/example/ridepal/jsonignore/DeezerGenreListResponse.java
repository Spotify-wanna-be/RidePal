package com.example.ridepal.jsonignore;

import com.example.ridepal.models.deezer.DeezerGenre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeezerGenreListResponse {

    @JsonProperty("data")
    private List<DeezerGenre> data;

    public List<DeezerGenre> getData() {
        return data;
    }

    public void setData(List<DeezerGenre> data) {
        this.data = data;
    }
}
