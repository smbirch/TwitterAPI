package com.cooksys.socialmedia.mappers;

import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.entities.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface UserMapper {

    UserResponseDto entityToDto(User entity);

    UserRequestDto dtoToEntity(User entity);


}