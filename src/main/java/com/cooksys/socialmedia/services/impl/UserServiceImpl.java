package com.cooksys.socialmedia.services.impl;

import org.springframework.stereotype.Service;
import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.ProfileDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.mappers.HashtagMapper;
import com.cooksys.socialmedia.repositories.HashtagRepository;
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
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final TweetRepository tweetRepository;
	private final TweetMapper tweetMapper;
	private final CredentialsMapper credentialsMapper;

  private User getUserHelper(String username) {
        Optional<User> userToCheckFor = userRepository.findByCredentials_Username(username);

        if (userToCheckFor.isEmpty() || userToCheckFor.get().isDeleted()) {
            throw new NotFoundException("No user found with username: '" + username + "'");
        }
        return userToCheckFor.get();
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
    		return userMapper.entitiesToDtos(userRepository.findAll());
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
        return userMapper.entityToDto(getUserHelper(username));
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
