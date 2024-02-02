package com.cooksys.socialmedia.services.impl;


import com.cooksys.socialmedia.entities.Hashtag;
import com.cooksys.socialmedia.entities.User;
import com.cooksys.socialmedia.mappers.UserMapper;
import com.cooksys.socialmedia.repositories.HashtagRepository;
import com.cooksys.socialmedia.repositories.UserRepository;
import com.cooksys.socialmedia.services.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final HashtagRepository hashtagRepository;

    @Override
    public boolean checkForHashtagExistance(String label) {
        Optional<Hashtag> foundHashtag = hashtagRepository.findByLabel(label);
        return foundHashtag.isPresent();

    }

    @Override
    public boolean checkForUsernameExistance(String username) {
        for (User u : userRepository.findAll()) {
            if (userMapper.entityToDto(u).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validateUsername(String username) {
        Optional<User> userToCheckFor = userRepository.findByCredentials_Username(username);

        return userToCheckFor.isEmpty() || userToCheckFor.get().isDeleted();
    }
}