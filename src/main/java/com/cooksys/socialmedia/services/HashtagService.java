package com.cooksys.socialmedia.services;
import java.util.List;

import com.cooksys.socialmedia.dtos.HashtagResponseDto;
import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.exceptions.NotFoundException;


public interface HashtagService {
	List<HashtagResponseDto> getAllTags();
	List<TweetResponseDto> getTweetsByTag(String label) throws NotFoundException;

}
