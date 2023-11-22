package com.example.ridepal.repository;

import com.example.ridepal.models.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    void createUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    User getByUsername(String username);

    User getById(int id);
}
