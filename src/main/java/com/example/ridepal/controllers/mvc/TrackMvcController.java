package com.example.ridepal.controllers.mvc;

import com.example.ridepal.exceptions.AuthorizationException;
import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.helpers.AuthenticationHelper;
import com.example.ridepal.models.Track;
import com.example.ridepal.models.User;
import com.example.ridepal.service.TrackService;
import com.example.ridepal.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tracks/{id}")
public class TrackMvcController {
    private final AuthenticationHelper authenticationHelper;
    private final UserService userService;
    private final TrackService trackService;


    @Autowired
    public TrackMvcController(
            AuthenticationHelper authenticationHelper,
            UserService userService, TrackService trackService) {
        this.authenticationHelper = authenticationHelper;
        this.userService = userService;
        this.trackService = trackService;
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("isAdmin")
    public boolean populateIsAdmin(HttpSession session) {
        if(session.getAttribute("currentUser") != null){
            Object currentUser = session.getAttribute("currentUser");
            User user = userService.getByUsername(currentUser.toString());
            return user.isAdmin();
        }
        return false;
    }


    @ModelAttribute("currentUser")
    public User currentUser(HttpSession session) {
        if (populateIsAuthenticated(session)) {
            String username = session.getAttribute("currentUser").toString();
            return userService.getByUsername(username);
        }
        return null;
    }

    @GetMapping
    public String showPostPage(@PathVariable int id, Model model) {
        try {
            Track track = trackService.getById(id);
            model.addAttribute("track", track);

            return "Post";

        } catch (EntityNotFoundException e) {
            return "Error_Page";
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteTrack(@PathVariable int id, HttpSession session) {
        User user;
        Track track;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
        track=trackService.getById(id);
        trackService.delete(track);

        return "redirect:/";
    }
}
