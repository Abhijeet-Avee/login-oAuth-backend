package com.login.auth.auth_app.service;


import com.login.auth.auth_app.dto.UserDto;

public interface UserService {

    // Create a new user
    UserDto createUser(UserDto userDto);

    // Get user by ID
    UserDto getUserById(String userId);

    // Get user by email
    UserDto getUserByEmail(String email);

    // Update user
    UserDto updateUser(String userId, UserDto userDto);

    // Delete user
    void deleteUser(String userId);

    // Get all users
    Iterable<UserDto> getAllUsers();
}
