package com.cooksys.socialmedia.mappers;

import com.cooksys.socialmedia.dtos.HashtagResponseDto;
import com.cooksys.socialmedia.entities.Hashtag;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface HashtagMapper {

    HashtagResponseDto entityToDto(Hashtag entity);

    List<HashtagResponseDto> entitiesToDtos(List<Hashtag> entities);
}