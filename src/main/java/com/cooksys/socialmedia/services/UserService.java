package com.cooksys.socialmedia.services;

import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUserByUsername(String username);

    UserResponseDto updateUsername(String newUsername);

    UserResponseDto deleteUserByUsername(String username);

    UserResponseDto followUser(UserRequestDto usertoFollow);

    UserResponseDto unfollowUser(UserRequestDto userToUnfollow);
    
    List<TweetResponseDto> getFeed(String username);
    
    List<UserResponseDto> getFollowers(String username);
}