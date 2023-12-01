package com.example.ridepal.service;

import com.example.ridepal.exceptions.EntityDuplicateException;
import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.models.User;
import com.example.ridepal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.ridepal.helpers.CheckPermissions.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers(User user) {
        return userRepository.getAllUsers();
    }

    @Override
    public User get(int id) {
        return userRepository.getById(id);
    }

    @Override
    public User getById(int id, User user) {
        checkIfSameUserOrAdmin(id, user);
        return userRepository.getById(id);
    }


    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public void createUser(User user) {
        boolean usernameExist = true;
        try {
            userRepository.getByUsername(user.getUsername());
        } catch (EntityNotFoundException e) {
            usernameExist = false;
        }
        if (usernameExist) {
            throw new EntityDuplicateException("User", "username", user.getUsername());
        }
        userRepository.createUser(user);
    }

    @Override
    public void updateUser(User user, User userToUpdate) {
        checkIfSameUser(user, userToUpdate);
        boolean usernameExist = true;
        try {
            userRepository.getByUsername(userToUpdate.getUsername());
        } catch (EntityNotFoundException e) {
            usernameExist = false;
        }
        if (usernameExist) {
            throw new EntityDuplicateException("User", "username", user.getUsername());
        }
        userRepository.updateUser(userToUpdate);
    }

    @Override
    public void deleteUser(int id, User user) {
        checkUserAuthorization(id, user);

        boolean userExists = true;

        try {
            userRepository.getById(id);
        } catch (EntityNotFoundException e) {
            userExists = false;
        }
        if (userExists) {
            userRepository.deleteUser(id);
        }

    }
}