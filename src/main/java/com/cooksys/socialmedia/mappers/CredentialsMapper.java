package com.cooksys.socialmedia.mappers;

import com.cooksys.socialmedia.dtos.CredentialsDto;
import com.cooksys.socialmedia.entities.Credentials;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CredentialsMapper {

    CredentialsDto entityToDto(Credentials entity);

    List<CredentialsDto> entitiesToDtos(List<Credentials> entities);
}