package com.cooksys.socialmedia.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.dtos.HashtagResponseDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.mappers.HashtagMapper;
import com.cooksys.socialmedia.repositories.HashtagRepository;
import com.cooksys.socialmedia.services.HashtagService;

import java.util.List;

@Service
public class HashtagServiceImpl implements HashtagService {
	
	private final HashtagRepository hashtagRepository;
	private final HashtagMapper hashtagMapper;
	
	public HashtagServiceImpl(HashtagRepository hashtagRepository, HashtagMapper hashtagMapper) {
		this.hashtagRepository = hashtagRepository;
		this.hashtagMapper = hashtagMapper;
	}
	
	@Override
	public 	List<HashtagResponseDto> getAllTags() {
		return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
	}
	
	@Override
	public List<TweetResponseDto> getTweetsByTag(String label){
		return null;
	}

}
