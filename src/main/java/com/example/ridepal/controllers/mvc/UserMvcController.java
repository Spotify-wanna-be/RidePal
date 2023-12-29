package com.example.ridepal.controllers.mvc;

import com.example.ridepal.exceptions.AuthorizationException;
import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.exceptions.UnauthorizedOperationException;
import com.example.ridepal.helpers.AuthenticationHelper;
import com.example.ridepal.mapper.UserMapper;
import com.example.ridepal.models.Track;
import com.example.ridepal.models.UpdateUserDto;
import com.example.ridepal.models.User;
import com.example.ridepal.service.interfaces.PlaylistService;
import com.example.ridepal.service.interfaces.TrackService;
import com.example.ridepal.service.interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserMvcController {
    private final UserService userService;
    private final TrackService trackService;
    private final AuthenticationHelper authenticationHelper;
    private final PlaylistService playlistService;
    private final UserMapper userMapper;

    public UserMvcController(UserService userService, TrackService trackService, AuthenticationHelper authenticationHelper, PlaylistService playlistService, UserMapper userMapper) {
        this.userService = userService;
        this.trackService = trackService;
        this.authenticationHelper = authenticationHelper;
        this.playlistService = playlistService;
        this.userMapper = userMapper;
    }
    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }


    @ModelAttribute("requestURI")
    public String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }
    @GetMapping("/{id}/admin")
    public String makeAdmin(@PathVariable int id, Model model, HttpSession httpSession) {
        User user;
        User userToMakeAdmin = userService.get(id);
        try {
            user = authenticationHelper.tryGetCurrentUser(httpSession);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
        if (userToMakeAdmin.isAdmin()) {
            try {
                userService.modifyPermissions(id, user, false);
            } catch (UnauthorizedOperationException e) {
                model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
                model.addAttribute("error", e.getMessage());
                return "ErrorView";
            }
        } else {
            try {
                userService.modifyPermissions(id, user, true);
            } catch (UnauthorizedOperationException e) {
                model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
                model.addAttribute("error", e.getMessage());
                return "ErrorView";
            }
        }
        return "redirect:/users";
    }
    @GetMapping
    public String showUsers(Model model, HttpSession httpSession) {
        try {
            authenticationHelper.tryGetCurrentUser(httpSession);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        try {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "AllUsersView";
        } catch (UnauthorizedOperationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        } catch (EntityNotFoundException e) {
            return "AllUsersView";
        }

    }


    @GetMapping("/admins")
    public String showAdmins(Model model, HttpSession httpSession) {
        try {
            authenticationHelper.tryGetCurrentUser(httpSession);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        try {
            List<User> users = userService.getAllAdmins();
            model.addAttribute("users", users);
            return "AllAdminsView";
        } catch (UnauthorizedOperationException e) {
            model.addAttribute("statusCode",
                    HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        } catch (EntityNotFoundException e) {
            return "AllAdminsView";
        }

    }

    @GetMapping("/myPlaylist")
    public String showMyPlaylistPage(Model model, HttpSession session) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);
            List<Track> bestTracks = trackService.getBestRanked();

            System.out.printf("Best Tracks: %s%n ", bestTracks);

            model.addAttribute("currentUser", user);
            model.addAttribute("bestTracks", bestTracks);
            model.addAttribute("myPlaylists", playlistService.getUsersPlaylists(user));

            return "MyPlaylist";

        } catch (AuthorizationException e) {
            return "MyPlaylist";
        }
    }

    @GetMapping("/update")
    public String showSettingsUserPage(Model model, HttpSession session) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);
            model.addAttribute("currentUser", user);

            return "SettingsUser";

        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }

    @PostMapping("/update")
    public String updateUserProfile(@Valid @ModelAttribute("currentUser") UpdateUserDto userUpdateDto,
                                    BindingResult bindingResult,
                                    Model model,
                                    HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }

        if (bindingResult.hasErrors()) {
            System.out.println("Hello");
            return "SettingsUser";
        }

        try {
            user = userMapper.fromDto(user.getId(), userUpdateDto, authenticationHelper.tryGetCurrentUser(session));
            userService.updateUser(authenticationHelper.tryGetCurrentUser(session), user);
            model.addAttribute("currentUser", user);

            return "redirect:/";

        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());

            return "ErrorView";

        }
    }
    @DeleteMapping("/{id}/delete")
    public String deleteUser ( @PathVariable int id, Model model, HttpSession httpSession){
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(httpSession);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
        try {
            userService.deleteUser(id, user);
        } catch (UnauthorizedOperationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
        return "redirect:/users";
    }
}
