package com.example.ridepal.repository.interfaces;

import com.example.ridepal.models.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    List<User> getAllAdmins();

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    User getByUsername(String username);

    User getById(int id);
    void modifyPermissions(User user);

}
