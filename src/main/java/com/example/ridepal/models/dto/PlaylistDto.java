package com.example.ridepal.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;


public class PlaylistDto {
    @NotEmpty(message = "Name can't be empty")
    private String name;

    public PlaylistDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
