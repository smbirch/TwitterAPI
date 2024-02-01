package com.cooksys.socialmedia.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final TweetRepository tweetRepository;
	private final TweetMapper tweetMapper;
	
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
    
    @Override
    public List<TweetResponseDto> getFeed(String username){
    	User current = new User();
    	List<TweetResponseDto> tweets = new ArrayList<>();
    	for(User u: userRepository.findAll()) {
    		if(userMapper.entityToDto(u).getUsername().equals(username) && u.isDeleted() == false) {
    			current = u;
    		}
    	}
    	
    	if(userMapper.entityToDto(current).getUsername() == null) {
    		throw new NotFoundException("No user with the given username.");
    	}
    	
    	for(Tweet t: current.getTweets()) {
    		if(t.isDeleted() == false) {
    			tweets.add(tweetMapper.entityToDto(t));
    		}
    	}
    	
    	for(User u: current.getFollowing()) {
        	for(Tweet t: u.getTweets()) {
        		if(t.isDeleted() == false) {
        			tweets.add(tweetMapper.entityToDto(t));
        		}
        	}
        	
    	}
    	Collections.sort(tweets, Comparator.comparing(TweetResponseDto::getPosted, Comparator.reverseOrder()));
    	return tweets;
    	
    }
    
    @Override
    public List<UserResponseDto> getFollowers(String username){
    	User current = new User();
    	List<UserResponseDto> users = new ArrayList<>();
    	for(User u: userRepository.findAll()) {
    		if(userMapper.entityToDto(u).getUsername().equals(username) && u.isDeleted() == false) {
    			current = u;
    		}
    	}
    	
    	if(userMapper.entityToDto(current).getUsername() == null) {
    		throw new NotFoundException("No user with the given username.");
    	}
    	
    	for(User u: current.getFollowers()) {
    		if(u.isDeleted() == false) {
    			users.add(userMapper.entityToDto(u));
    		}
    	}
    	
    	Collections.sort(users, Comparator.comparing(UserResponseDto::getJoined, Comparator.reverseOrder()));
    	return users;
    	
    } 
    
}