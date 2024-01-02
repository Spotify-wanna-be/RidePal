package com.example.ridepal.jsonignore;

import com.example.ridepal.models.deezer.DeezerTrack;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeezerTrackListResponse {

    @JsonProperty("data")
    private List<DeezerTrack> data;

    public List<DeezerTrack> getData() {
        return data;
    }

    public void setData(List<DeezerTrack> data) {
        this.data = data;
    }
}
