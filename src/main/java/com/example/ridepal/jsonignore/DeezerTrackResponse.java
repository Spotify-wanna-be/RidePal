package com.example.ridepal.jsonignore;

import com.example.ridepal.models.Track;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeezerTrackResponse {
    private Track[] data;

    public Track[] getData() {
        return data;
    }

    public void setData(Track[] data) {
        this.data = data;
    }
}
