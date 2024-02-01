package com.cooksys.socialmedia.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {
    
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	@Override
    public boolean checkForHashtagExistance(String label) {
        return false;
    }

    @Override
    public boolean checkForUsernameExistance(String username) {
        for(User u: userRepository.findAll()) {
        	if(userMapper.entityToDto(u).getUsername().equals(username)) {
        		return true;
        	}
        }
        return false;
    }

    @Override
    public boolean checkUsernameAvailability(String username) {
        return false;
    }
}