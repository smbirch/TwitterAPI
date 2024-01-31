package com.cooksys.socialmedia.mappers;

import com.cooksys.socialmedia.dtos.TweetResponseDto;
import com.cooksys.socialmedia.entities.Tweet;
import org.mapstruct.Mapper;

import java.util.List;

//import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { UserMapper.class})
public interface TweetMapper {

  TweetResponseDto entityToDto(Tweet entity);

  List<TweetResponseDto> entitiesToDtos(List<Tweet> entities);

}