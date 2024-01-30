package com.cooksys.socialmedia.mappers;

import java.util.List;

import com.cooksys.socialmedia.dtos.ProfileDto;
import com.cooksys.socialmedia.entities.Profile;

//import org.mapstruct.Mapper;

@Mapper(componentModel = "spring"/*, uses = { UserMapper.class }*/)
public interface ProfileMapper {

  ProfileDto entityToDto(Profile entity);

  List<ProfileDto> entitiesToDtos(List<Profile> entities);

}