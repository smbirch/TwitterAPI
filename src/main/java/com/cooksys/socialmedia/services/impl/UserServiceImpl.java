package com.cooksys.socialmedia.services.impl;

import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TweetMapper tweetMapper;

    private User getUserHelper(String username) {
        Optional<User> userToCheckFor = userRepository.findByCredentials_Username(username);

        if (userToCheckFor.isEmpty() || userToCheckFor.get().isDeleted()) {
            throw new NotFoundException("No user found with username: '" + username + "'");
        }
        return userToCheckFor.get();
    }

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
        return userMapper.entityToDto(getUserHelper(username));
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

    @Override
    public List<TweetResponseDto> getTweetsByUsername(String username) {
        User thisUser = getUserHelper(username);
        List<Tweet> userTweets = thisUser.getTweets();
        if (userTweets.isEmpty()) {
            throw new NotFoundException("This user has no tweets!");
        }
        userTweets.sort(Comparator.comparing(Tweet::getPosted).reversed());

        return tweetMapper.entitiesToDtos(userTweets);

    }

    @Override
    public List<UserResponseDto> getFollowing(String username) {
        User thisUser = getUserHelper(username);
        List<User> followers = thisUser.getFollowers();
        List<User> followersSafeCopy = new ArrayList<>(followers);

        followersSafeCopy.removeIf(User::isDeleted);

        return userMapper.entitiesToDtos(followersSafeCopy);

    }
}