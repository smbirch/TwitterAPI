package com.cooksys.socialmedia.services;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUserByUsername(String username);

    UserResponseDto updateUsername(String newUsername);

    UserResponseDto deleteUserByUsername(String username, CredentialsDto credentials);

    UserResponseDto unfollowUser(UserRequestDto userToUnfollow);

    List<TweetResponseDto> getTweetsByUsername(String username);

    List<UserResponseDto> getFollowing(String username);

    List<TweetResponseDto> getFeed(String username);

    List<UserResponseDto> getFollowers(String username);

    void followUser(String username, CredentialsDto credentialsDto);
}