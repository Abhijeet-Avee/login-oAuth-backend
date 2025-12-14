package com.login.auth.auth_app.service.impl;

import com.login.auth.auth_app.dto.UserDto;
import com.login.auth.auth_app.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDto createUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto getUserById(String userId) {
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public Iterable<UserDto> getAllUsers() {
        return null;
    }
}
