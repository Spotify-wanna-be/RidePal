package com.example.ridepal.services;

import com.example.ridepal.exceptions.EntityDuplicateException;
import com.example.ridepal.exceptions.EntityNotFoundException;
import com.example.ridepal.exceptions.UnauthorizedOperationException;
import com.example.ridepal.models.User;
import com.example.ridepal.repository.interfaces.UserRepository;
import com.example.ridepal.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static com.example.ridepal.MockHelpers.createMockAdmin;
import static com.example.ridepal.MockHelpers.createMockUser;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @Mock
    UserRepository mockRepository;

    @InjectMocks
    UserServiceImpl userService;

//    @Test
//    void get_Should_CallRepository() {
//        // Arrange
//        User mockAdmin = createMockAdmin();
//        Mockito.when(mockRepository.getByUsername(mockAdmin.getUsername()))
//                .thenReturn(null);
//
//        // Act
//        userService.get(1); // Assuming that get() method doesn't take the repository as a parameter directly
//
//        // Assert
//        Mockito.verify(mockRepository, Mockito.times(1))
//                .getByUsername(mockAdmin.getUsername());
//    }
@Test
void getAllUsers_ShouldReturnListOfUsers() {
    // Arrange
    User mockUser = new User();
    Mockito.when(mockRepository.getAllUsers()).thenReturn(Collections.singletonList(mockUser));

    // Act
    List<User> users = userService.getAllUsers(mockUser);

    // Assert
    assertEquals(1, users.size());
    assertEquals(mockUser, users.get(0));
}
    @Test
    void get_ShouldReturnUserById() {
        // Arrange
        int userId = 1;
        User mockUser = new User();
        Mockito.when(mockRepository.getById(userId)).thenReturn(mockUser);

        // Act
        User resultUser = userService.get(userId);

        // Assert
        assertEquals(mockUser, resultUser);
    }


    @Test
    void getById_Should_ReturnUser_When_UserIsAdminOrSameUser() {
        // Arrange
        User mockUser = createMockUser();
        User mockAdmin = createMockAdmin();

        Mockito.when(mockRepository.getById(Mockito.anyInt()))
                .thenReturn(mockUser);

        // Act
        User result = userService.getById(1, mockAdmin);

        // Assert
        assertEquals(mockUser, result);
        Mockito.verify(mockRepository, Mockito.times(1))
                .getById(1);
    }

    @Test
    void getById_Should_ThrowException_When_UserIsNotAdminOrSameUser() {
        // Arrange
        User mockUser = createMockUser();

        // Act, Assert
        Assertions.assertThrows(
                UnauthorizedOperationException.class,
                () -> userService.getById(2, mockUser));
    }
    @Test
    void updateUser_Should_CallRepository_When_UsernameNotExists() {
        // Arrange
        User mockUser = createMockUser();
        User mockUserToUpdate = createMockUser();

        Mockito.when(mockRepository.getByUsername(mockUserToUpdate.getUsername()))
                .thenThrow(EntityNotFoundException.class);

        // Act
        userService.updateUser(mockUser, mockUserToUpdate);

        // Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .updateUser(mockUserToUpdate);
    }

//    @Test
//    void updateUser_Should_ThrowException_When_UsernameExists() {
//        // Arrange
//        User mockUser = createMockUser();
//        User mockUserToUpdate = createMockUser();
//
//        Mockito.when(mockRepository.getByUsername(mockUserToUpdate.getUsername()))
//                .thenReturn(mockUser);
//
//        // Act, Assert
//        Assertions.assertThrows(
//                EntityDuplicateException.class,
//                () -> userService.updateUser(mockUser, mockUserToUpdate));
//    }

    @Test
    void deleteUser_Should_CallRepository_When_UserIsAuthorized() {
        // Arrange
        User mockUser = createMockUser();
        User mockAdmin = createMockAdmin();

        Mockito.when(mockRepository.getById(1))
                .thenReturn(mockUser);

        // Act
        userService.deleteUser(1, mockAdmin);

        // Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .deleteUser(1);
    }

    @Test
    void deleteUser_Should_ThrowException_When_UserIsNotAuthorized() {
        // Arrange
        User mockUser = createMockUser();

        // Act, Assert
        Assertions.assertThrows(
                UnauthorizedOperationException.class,
                () -> userService.deleteUser(2, mockUser));
    }

    @Test
    void createUser_Should_CallRepository_When_UsernameNotExists() {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockRepository.getByUsername(mockUser.getUsername()))
                .thenThrow(EntityNotFoundException.class);

        // Act
        userService.createUser(mockUser);

        // Assert
        Mockito.verify(mockRepository, Mockito.times(1))
                .createUser(mockUser);
    }

    @Test
    void createUser_Should_ThrowException_When_UsernameExists() {
        // Arrange
        User mockUser = createMockUser();

        Mockito.when(mockRepository.getByUsername(mockUser.getUsername()))
                .thenReturn(mockUser);

        // Act, Assert
        Assertions.assertThrows(
                EntityDuplicateException.class,
                () -> userService.createUser(mockUser));
    }


}

