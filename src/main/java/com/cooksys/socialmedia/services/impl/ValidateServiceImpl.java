package com.cooksys.socialmedia.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.socialmedia.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {
    @Override
    public boolean checkForHashtagExistance(String label) {
        return false;
    }

    @Override
    public boolean checkForUsernameExistance(String username) {
        return false;
    }

    @Override
    public boolean checkUsernameAvailability(String username) {
        return false;
    }
}