package com.example.ridepal.service;

import com.example.ridepal.models.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getById(int id);

    User getByUsername(String username);

    void createUser(User user);

    void updateUser(User user, User userToUpdate);

    void deleteUser(int id, User user);


}
