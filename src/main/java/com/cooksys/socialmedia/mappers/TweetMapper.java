package com.cooksys.socialmedia.mappers;

import java.util.List;

import com.cooksys.socialmedia.dtos.TweetResponseDTo;
import com.cooksys.socialmedia.entities.Tweet;

//import org.mapstruct.Mapper;

@Mapper(componentModel = "spring"/*, uses = { UserMapper.class }*/)
public interface TweetMapper {

  TweetResponseDTo entityToDto(Tweet entity);

  List<TweetResponseDTo> entitiesToDtos(List<Tweet> entities);

}
