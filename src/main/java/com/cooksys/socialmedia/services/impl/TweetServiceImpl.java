package com.cooksys.socialmedia.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.dtos.TweetRequestDto;
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
import com.cooksys.socialmedia.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {

  private final TweetRepository tweetRepository;
  private final TweetMapper tweetMapper;
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final CredentialsMapper credentialsMapper;

  @Override
  public List<TweetResponseDto> getAllTweets() {
    return tweetMapper.entitiesToDtos(tweetRepository.findAll());
  }
  
  @Override
  public TweetResponseDto getTweetById(Long id) {
	  
	  Optional<Tweet> current = tweetRepository.findById(id);
	  
	  if(current.isEmpty()) {
		  throw new NotFoundException("Tweet not found.");
	  }
	  
	  return tweetMapper.entityToDto(current.get());
  }
  
  @Override
  public List<TweetResponseDto> getRepostsById(Long id){
	  Optional<Tweet> current = tweetRepository.findById(id);
	  
	  if(current.isEmpty()) {
		  throw new NotFoundException("Tweet not found.");
	  }
	  
	  Tweet finall = current.get();
	  
	  if(finall.isDeleted()) {
		  throw new NotFoundException("Tweet not found.");
	  }
	  
	  List<TweetResponseDto> repostList = new ArrayList<>();
	  
	  for(Tweet t: finall.getReposts()) {
		  if(t.isDeleted() == false) {
			  repostList.add(tweetMapper.entityToDto(t));
		  }
	  }
	  
	  return repostList;
  }
  
  @Override
  public List<UserResponseDto> getLikesById(Long id){
	  Optional<Tweet> current = tweetRepository.findById(id);
	  
	  if(current.isEmpty()) {
		  throw new NotFoundException("Tweet not found.");
	  }
	  
	  Tweet finall = current.get();
	  
	  List<UserResponseDto> userList = new ArrayList<>();
	  
	  for(User u: finall.getLikedByUsers()) {
		  if(u.isDeleted()==false) {
			  userList.add(userMapper.entityToDto(u));
		  }
	  }
	  return userList;
  }
  
  @Override
  public TweetResponseDto createReply(Long id, TweetRequestDto tweetRequest) {
  	Tweet current = new Tweet();
  	CredentialsDto credentials = tweetRequest.getCredentials();
  	Optional<Tweet> checker = tweetRepository.findById(id);
  	User author = new User();
  	if(checker.isEmpty()) {
  		throw new NotFoundException("Tweet with this id not found.");
  	}
  	for(User u: userRepository.findAll()) {
  		if(credentialsMapper.entityToDto(u.getCredentials()).equals(credentials) && u.isDeleted() == false){
  			author = u;
  		}
  	}
  	
  	if(author.getCredentials() == null) {
 		throw new NotAuthorizedException("Credentials are not correct.");
  	}
  	
  	
  	Tweet checkerTweet = checker.get();
  	if(checkerTweet.isDeleted() == true)
  	{
  		throw new NotFoundException("Tweet with this id not found.");
  	}
  	if(tweetRequest.getContent() == null) {
  		throw new BadRequestException("Content needs to be filled in.");
  	}

  	current.setAuthor(author);
  	current.setContent(tweetRequest.getContent());
  	current.setInReplyTo(checkerTweet);
  	
  	return tweetMapper.entityToDto(tweetRepository.saveAndFlush(current));
  }
}