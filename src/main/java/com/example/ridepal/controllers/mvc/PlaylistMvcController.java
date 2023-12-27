package com.example.ridepal.controllers.mvc;

import com.example.ridepal.exceptions.AuthorizationException;
import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.exceptions.UnauthorizedOperationException;
import com.example.ridepal.helpers.AuthenticationHelper;
import com.example.ridepal.helpers.PlaylistMapper;
import com.example.ridepal.models.*;
import com.example.ridepal.service.PlaylistService;
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
@RequestMapping("/playlists")
public class PlaylistMvcController {
    private final PlaylistService playlistService;
    private final AuthenticationHelper authenticationHelper;

    private final PlaylistMapper playlistMapper;

    public PlaylistMvcController(PlaylistService playlistService, AuthenticationHelper authenticationHelper, PlaylistMapper playlistMapper) {
        this.playlistService = playlistService;
        this.authenticationHelper = authenticationHelper;
        this.playlistMapper = playlistMapper;
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    @ModelAttribute("requestURI")
    public String getRequestURI(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @GetMapping("/{id}/delete")
    public String deletePlaylist(@PathVariable int id,
                                 Model model,
                                 HttpSession httpSession) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(httpSession);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
        try {
            playlistService.delete(user, id);
            return "redirect:/playlists";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "redirect:/auth/login";
        } catch (UnauthorizedOperationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }
    @GetMapping
    public String showAllPlaylists(@ModelAttribute("filterOptions") PlaylistFilterDto filterDto,
                               Model model, HttpSession httpSession) {
        PlaylistFilterOptions filterOptions = new PlaylistFilterOptions(
                filterDto.getName(),
                filterDto.getDuration(),
                filterDto.getGenres(),
                filterDto.getSortBy(),
                filterDto.getSortOrder());
        try {
            authenticationHelper.tryGetCurrentUser(httpSession);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
        List<Playlist> playlists = playlistService.get(filterOptions);
        List<Playlist> ranked = playlistService.getRanked();
        model.addAttribute("ranked", ranked);
        model.addAttribute("playlists", playlists);
        model.addAttribute("filterOptions", filterDto);
        return "index";
    }

    @GetMapping("/{id}")
    public String showPlaylist(@PathVariable int id, Model model, HttpSession httpSession) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(httpSession);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
        try {
            Playlist playlist = playlistService.getByPlaylistId(id);
            model.addAttribute("tracks", playlist.getTracks());
            model.addAttribute("playlist", playlist);
            return "Playlist";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

}
