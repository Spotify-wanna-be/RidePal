package com.example.ridepal.controllers.mvc;

import com.example.ridepal.exceptions.AuthorizationException;
import com.example.ridepal.exceptions.EntityDuplicateException;
import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.exceptions.UnauthorizedOperationException;
import com.example.ridepal.helpers.AuthenticationHelper;
import com.example.ridepal.helpers.PlaylistMapper;
import com.example.ridepal.helpers.PlaylistMapper;
import com.example.ridepal.helpers.UserMapper;
import com.example.ridepal.models.*;
import com.example.ridepal.service.GenreService;
import com.example.ridepal.service.GenreService;
import com.example.ridepal.service.PlaylistService;
import com.example.ridepal.service.ThymeleafUtils;
import com.example.ridepal.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/")
public class HomeMvcController {
    private final AuthenticationHelper authenticationHelper;
    private final PlaylistService playlistService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final GenreService genreService;
    private final ThymeleafUtils thymeleafUtils;

    @Autowired
    public HomeMvcController(AuthenticationHelper authenticationHelper,
                             PlaylistService playlistService,
                             UserService userService,
                             UserMapper userMapper, GenreService genreService, ThymeleafUtils thymeleafUtils) {
        this.authenticationHelper = authenticationHelper;
        this.playlistService = playlistService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.genreService = genreService;
        this.thymeleafUtils = thymeleafUtils;
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "OurMissionPage";
    }

    @ModelAttribute("isAuthenticated")
    public boolean populateIsAuthenticated(HttpSession session) {
        return session.getAttribute("currentUser") != null;
    }

    @GetMapping
    public String showHomePage(Model model) {
        // -- TODO method getHighestRankPlaylist() --
        List<Playlist> highestRankPlaylist = playlistService.getHighestRankPlaylist();
        model.addAttribute("highestRankPlaylist", highestRankPlaylist);
        List<Playlist> playlists = playlistService.getAll();
        model.addAttribute("playlists", playlists);
        return "index";
    }


    @GetMapping("/settings")
    public String showUserSettings(HttpSession session, Model model) {
        User user;
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
            UpdateUserDto updateUserDto = new UpdateUserDto();
            updateUserDto.setFirstName(user.getFirstName());
            updateUserDto.setLastName(user.getLastName());
            updateUserDto.setEmail(user.getEmail());
            updateUserDto.setPassword(user.getPassword());
            model.addAttribute("updateUser", updateUserDto);
            model.addAttribute("currentUser", user);
            return "SettingsUser";
        } catch (AuthorizationException e) {

            return "redirect:/auth/login";
        }
    }

    @PostMapping("/settings/update")
    public String updateUser(
            @ModelAttribute("updateUser") UpdateUserDto updateUserDto,
            BindingResult result,
            HttpSession httpSession) {
        User user;
        try {
            if (result.hasErrors()) {
                return "SettingsUser";
            }
            user = authenticationHelper.tryGetCurrentUser(httpSession);
            User userToUpdate = userMapper.fromDto(user.getId(), updateUserDto, user);
            userService.updateUserV2(user, userToUpdate, updateUserDto);
            return "redirect:/settings";
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (UnauthorizedOperationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
    @GetMapping("/edit")
    public String showDashboard(HttpSession session, Model model) {
        try {
            User user = authenticationHelper.tryGetCurrentUser(session);
            if (user.isAdmin()) {
                UpdateUserDto updateUserDto = new UpdateUserDto();
                updateUserDto.setFirstName(user.getFirstName());
                updateUserDto.setLastName(user.getLastName());
                updateUserDto.setEmail(user.getEmail());
                updateUserDto.setPassword(user.getPassword());
                model.addAttribute("updateAdmin", updateUserDto);
                model.addAttribute("currentAdmin", user);

                return "DashboardAdmin";
            }
            return "ErrorView";
        }
        catch (AuthorizationException e) {
            return "redirect:/auth/login";


        }
    }
    @GetMapping("/generate")
    public String showGenerate(HttpSession session, Model model) {
        User user;
        List<String> allGenres = new ArrayList<>();
        List<Genre> genres=genreService.getAll();
        for(Genre genre: genres){
            allGenres.add(genre.getType());
        }
        model.addAttribute("allGenre",allGenres);
        model.addAttribute("travelInfoForm", new TravelInfoForm());
        model.addAttribute("thymeleafUtils", thymeleafUtils);
        try {
            user = authenticationHelper.tryGetCurrentUser(session);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
        try {
            return "TripGenerate";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

    @PostMapping("/edit")
    public String updateAdmin(
            @ModelAttribute("updateAdmin") UpdateUserDto updateUserDto,
            BindingResult result,
            HttpSession httpSession) {
        User user;
        try {
            if (result.hasErrors()) {
                return "ErrorView";
            }
            user = authenticationHelper.tryGetCurrentUser(httpSession);
            User userToUpdate = userMapper.fromDto(user.getId(), updateUserDto, user);
            userService.updateUserV2(user, userToUpdate, updateUserDto);
            return "redirect:/edit";
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (UnauthorizedOperationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
    @PostMapping("/generate")
    public  String generatePlaylist(@Valid @ModelAttribute("travelInfo") TravelInfoForm travelInfoForm,
                                    BindingResult result,
                                    Model model,
                                    HttpSession httpSession){
        User user;
        model.addAttribute("travelInfo", travelInfoForm);
        try {
            user = authenticationHelper.tryGetCurrentUser(httpSession);
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
        if (result.hasErrors()) {
            return "TripGenerate";
        }

        try {
            user = authenticationHelper.tryGetCurrentUser(httpSession);
            String origin = travelInfoForm.getOrigin();
            String destination = travelInfoForm.getDestination();
            String name = travelInfoForm.getName();
            Map<String, Integer> genrePercentageMap = travelInfoForm.getGenrePercentages();
            
            playlistService.create(name, user, genrePercentageMap, origin, destination);
            return "redirect:/users/myPlaylist";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        } catch (AuthorizationException e) {
            return "redirect:/auth/login";
        }
    }
    @GetMapping("/browse")
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
        model.addAttribute("playlists", playlists);
        model.addAttribute("filterOptions", filterDto);
        return "BrowsePlaylists";
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
            model.addAttribute("playlist", playlist);
            model.addAttribute("track", new PlaylistDto()); //todo look up
            return "BrowsePlaylists";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

}
