package com.example.ridepal.repository;

import com.example.ridepal.models.User;

public interface UserRepository {
    void createUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    User getByUsername(String username);

    User getById(int id);
}
