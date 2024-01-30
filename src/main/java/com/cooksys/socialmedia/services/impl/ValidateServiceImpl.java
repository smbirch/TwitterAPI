package com.cooksys.socialmedia.services.impl;

import com.cooksys.socialmedia.services.ValidateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
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