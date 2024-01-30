package com.cooksys.socialmedia.services.impl;

import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.mappers.TweetMapper;
import com.cooksys.socialmedia.repositories.TweetRepository;
import com.cooksys.socialmedia.services.TweetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

  private final TweetRepository tweetRepository;
  private final TweetMapper tweetMapper;

  public TweetServiceImpl(TweetRepository tweetRepository, TweetMapper tweetMapper) {
    this.tweetRepository = tweetRepository;
    this.tweetMapper = tweetMapper;
  }

  @Override
  public List<TweetResponseDto> getAllTweets() {
    return tweetMapper.entitiesToDtos(tweetRepository.findAll());
  }

}