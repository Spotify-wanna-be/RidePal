package com.example.ridepal.jsonignore;

import com.example.ridepal.models.deezer.DeezerArtist;
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
