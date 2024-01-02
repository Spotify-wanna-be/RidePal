package com.example.ridepal.service.interfaces;

import com.example.ridepal.models.dto.UpdateUserDto;
import com.example.ridepal.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers(User user);
    List<User> getAllUsers();
    User get(int id);
    User getById(int id, User user);
    int showUsersCount();
    User getByUsername(String username);
    List<User> getAllAdmins();
    void createUser(User user);
    void updateUser(User user, User userToUpdate);
    User updateUserV2(User user, User updatedUser, UpdateUserDto updateUserDto);
    void deleteUser(int id, User user);
    void modifyPermissions(int id, User user, boolean adminFlag);
}
