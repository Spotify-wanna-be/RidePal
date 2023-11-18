package com.example.ridepal.controllers;

import com.example.ridepal.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserMvcController {
    private final UserService userService;
//--TODO--
    public UserMvcController(UserService userService) {
        this.userService = userService;
    }

}
