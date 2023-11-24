package com.example.ridepal.controllers;

import com.example.ridepal.service.TravelTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;

@RestController
public class TravelTimeController {

    @Autowired
    private TravelTimeService travelTimeService;

    @GetMapping("/travel-time")
    public Time getTravelTime(@RequestParam String origin, @RequestParam String destination) {
        return travelTimeService.getTravelTime(origin, destination);
    }
}