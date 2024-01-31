package com.cooksys.socialmedia.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.socialmedia.dtos.CredentialsRequestDto;
import com.cooksys.socialmedia.entities.Credentials;
	
	@Mapper(componentModel = "spring")
	public interface CredentialsMapper {

	  CredentialsRequestDto entityToDto(Credentials entity);

	  List<CredentialsRequestDto> entitiesToDtos(List<Credentials> entities);
	}
