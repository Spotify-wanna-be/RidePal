package com.example.ridepal.helpers;

import com.example.ridepal.exceptions.UnauthorizedOperationException;
import com.example.ridepal.models.Playlist;
import com.example.ridepal.models.User;

public class CheckPermissions {
    public static final String AUTH_ERR_MESSAGE = "You are not authorized to perform this operation";


    public static void checkIfSameUser(User user, User userToUpdate) {
        if (!(user.getId() == userToUpdate.getId())) {
            throw new UnauthorizedOperationException(AUTH_ERR_MESSAGE);
        }
    }

    public static void checkUserAuthorization(int targetUserId, User executingUser) {
        if (!executingUser.isAdmin() && executingUser.getId() != targetUserId) {
            throw new UnauthorizedOperationException(AUTH_ERR_MESSAGE);
        }
    }
    public static void checkIfSameUserOrAdmin(User user, Playlist playlist) {
        if (!(user.equals(playlist.getCreatedBy()) || user.isAdmin())) {
            throw new UnauthorizedOperationException(AUTH_ERR_MESSAGE);
        }
    }
}
