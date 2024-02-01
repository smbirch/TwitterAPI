package com.cooksys.socialmedia.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.Tweet;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.exceptions.NotFoundException;
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
  private final UserRepository userRespository;
  private final UserMapper userMapper;

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
}