package com.cooksys.socialmedia.services;

public interface ValidateService {

    boolean checkForHashtagExistance(String label);

    boolean checkForUsernameExistance(String username);

    boolean checkUsernameAvailability(String username);
    
}