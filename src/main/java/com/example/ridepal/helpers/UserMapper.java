package com.example.ridepal.helpers;

import com.example.ridepal.models.RegisterDto;
import com.example.ridepal.models.UpdateUserDto;
import com.example.ridepal.models.User;
import com.example.ridepal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final UserService userService;

    @Autowired
    public UserMapper(UserService userService) {
        this.userService = userService;
    }

    public User fromDto(int id, RegisterDto dto, User user) {
        User existingUser = userService.getById(id);

        existingUser.setFirstName(dto.getFirstName());
        existingUser.setLastName(dto.getLastName());
        existingUser.setEmail(dto.getEmail());
        existingUser.setUsername(dto.getUsername());
        existingUser.setPassword(dto.getPassword());

        return existingUser;
    }
    public User fromDto(int id, UpdateUserDto dto, User user) {
        User existingUser = userService.getById(id);

        existingUser.setFirstName(dto.getFirstName());
        existingUser.setLastName(dto.getLastName());
        existingUser.setEmail(dto.getEmail());
        existingUser.setPassword(dto.getPassword());

        return existingUser;
    }

    public User fromDto(RegisterDto registerDto) {
        User user = new User();

        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());

        return user;
    }
}
