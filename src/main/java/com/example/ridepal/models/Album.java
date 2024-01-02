package com.example.ridepal.models;

import jakarta.persistence.*;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @Column(name = "album_id")
    private int id;
    @Column(name = "name")
    private String name;


    public Album() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
