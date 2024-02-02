package com.cooksys.socialmedia.controllers;

import com.cooksys.socialmedia.services.ValidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {

    private final ValidateService validateService;


    //    Checks whether a given hashtag exists.
    @GetMapping("/tag/exists/{label}")
    public boolean checkForHashtagExistance(@PathVariable("label") String label) {
        return validateService.checkForHashtagExistance(label);
    }

    // Checks whether a given username exists.
    @GetMapping("/username/exists/@{username}")
    public boolean checkforUsernameExistance(@PathVariable("username") String username) {
        return validateService.checkForUsernameExistance(username);
    }

    // Checks whether a given username is available
    @GetMapping("/username/available/@{username}")
    public boolean checkUsernameAvailability(@PathVariable String username) {
        return validateService.checkUsernameAvailability(username);
    }


}