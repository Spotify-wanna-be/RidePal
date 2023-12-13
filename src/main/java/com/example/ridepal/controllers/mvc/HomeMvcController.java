package com.example.ridepal.controllers.mvc;

import com.example.ridepal.exceptions.AuthorizationException;
import com.example.ridepal.exceptions.EntityDuplicateException;
import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.exceptions.UnauthorizedOperationException;
import com.example.ridepal.helpers.AuthenticationHelper;
import com.example.ridepal.helpers.UserMapper;
import com.example.ridepal.models.*;
import com.example.ridepal.service.PlaylistService;
import com.example.ridepal.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Controller
@RequestMapping("/")
public class HomeMvcController {
    private final AuthenticationHelper authenticationHelper;
    private final PlaylistService playlistService;
    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public HomeMvcController(AuthenticationHelper authenticationHelper,
                             PlaylistService playlistService,
                             UserService userService,
                             UserMapper userMapper) {
        this.authenticationHelper = authenticationHelper;
        this.playlistService = playlistService;
        this.userService = userService;
        this.userMapper = userMapper;
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
            return "redirect:edit";
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (UnauthorizedOperationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
