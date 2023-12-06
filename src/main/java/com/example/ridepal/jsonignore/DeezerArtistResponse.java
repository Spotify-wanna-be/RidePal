package com.example.ridepal.jsonignore;

import com.example.ridepal.models.DeezerArtist;
import com.example.ridepal.models.DeezerGenre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeezerArtistResponse {

    @JsonProperty("data")
    private List<DeezerArtist> data;

    public List<DeezerArtist> getData() {
        return data;
    }

    public void setData(List<DeezerArtist> data) {
        this.data = data;
    }
}
