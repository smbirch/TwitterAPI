package com.cooksys.socialmedia.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public List<UserResponseDto> getAllUsers() {
        return null;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public UserResponseDto getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserResponseDto updateUsername(String newUsername) {
        return null;
    }

    @Override
    public UserResponseDto deleteUserByUsername(String username) {
        return null;
    }

    @Override
    public UserResponseDto followUser(UserRequestDto usertoFollow) {
        return null;
    }

    @Override
    public UserResponseDto unfollowUser(UserRequestDto userToUnfollow) {
        return null;
    }
}