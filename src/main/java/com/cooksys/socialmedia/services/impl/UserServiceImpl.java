package com.cooksys.socialmedia.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.ProfileDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.exceptions.BadRequestException;
import com.cooksys.socialmedia.exceptions.NotAuthorizedException;
import com.cooksys.socialmedia.exceptions.NotFoundException;
import com.cooksys.socialmedia.mappers.CredentialsMapper;
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
	private final CredentialsMapper credentialsMapper;
	
    @Override
    public List<UserResponseDto> getAllUsers() {
        return null;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
      User u = new User();
      CredentialsDto credentials = userRequestDto.getCredentials();
      ProfileDto profile = userRequestDto.getProfile();
      
      for(User use: userRepository.findAll()) {
    	  if(use.getCredentials().getUsername().equals(credentials.getUsername())) {
    		  if(use.isDeleted() == true) {
    			  use.setDeleted(false);
    			  userRepository.flush();
    			  return userMapper.entityToDto(use);
    		  }
    		  else {
    			  throw new BadRequestException("This username is already in use.");  
    		  }
    	  }
      }
      
      if(credentials == null || profile == null || profile.getEmail() == null || credentials.getPassword() == null || credentials.getUsername() == null) {
    	  throw new BadRequestException("A required parameter is missing");
      }  
  	  u.setProfile(userMapper.requestDtoToEntity(userRequestDto).getProfile());
  	  u.setCredentials(userMapper.requestDtoToEntity(userRequestDto).getCredentials());
  	  System.out.println(u.getProfile());
  	  System.out.println(u.getCredentials());
  	  
  	  
  	  
  	  return userMapper.entityToDto(userRepository.saveAndFlush(u));
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
    public UserResponseDto deleteUserByUsername(String username, CredentialsDto credentials) {
    	User current = new User();
    	for(User u: userRepository.findAll()) {
    		if(userMapper.entityToDto(u).getUsername().equals(username) && u.isDeleted() == false) {
    			current = u;
    		}
    	}
    	
    	if(userMapper.entityToDto(current).getUsername() == null) {
    		throw new NotFoundException("No user with the given username.");
    	}
    	
    	System.out.println(current.getCredentials());
    	System.out.println(credentials);
    	if(credentialsMapper.entityToDto(current.getCredentials()).equals(credentials)) {
    		current.setDeleted(true);
    	}
    	else {
    		throw new NotAuthorizedException("Credentials wrong.");
    	}
    	
    	userRepository.flush();
    	
    	return userMapper.entityToDto(current);
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