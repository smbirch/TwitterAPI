package com.cooksys.socialmedia.controllers;

import com.cooksys.socialmedia.dtos.UserRequestDto;
import com.cooksys.socialmedia.dtos.UserResponseDto;
import com.cooksys.socialmedia.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Creates a new user. If any required fields are missing or the username provided
     * is already taken, an error should be sent in lieu of a response. If the given
     * credentials match a previously-deleted user, re-activate the deleted user
     * instead of creating a new one.
     * <p>
     * Request:
     * credentials: 'Credentials',
     * profile: 'Profile'
     * <p>
     * Returns:
     * User
     */
    @PostMapping
    public UserResponseDto createUser(@RequestBody UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    /**
     * Retrieves a user with the given username. If no such user exists or is deleted, an error should be sent in lieu of a response.
     *
     * @return 'User' - The user retrieved with the given username.
     */
    @GetMapping("/@{username}")
    public UserResponseDto getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    /**
     * Updates the profile of a user with the given username. If no such user exists,
     * the user is deleted, or the provided credentials do not match the user, an error
     * should be sent in lieu of a response. In the case of a successful update, the
     * returned user should contain the updated data.
     * <p>
     * Request:
     * credentials: 'Credentials',
     * profile: 'Profile'
     * <p>
     * Returns:
     * User
     */
    @PatchMapping("/@{username}")
    public UserResponseDto updateUsername(@PathVariable String newUsername) {
        return userService.updateUsername(newUsername);
    }

    /**
     * Deletes a user with the given username. If no such user exists or the provided
     * credentials do not match the user, an error should be sent in lieu of a response.
     * If a user is successfully "deleted", the response should contain the user data
     * prior to deletion.
     * <p>
     * IMPORTANT: This action should not actually drop any records from the database!
     * Instead, develop a way to keep track of "deleted" users so that if a user is
     * re-activated, all of their tweets and information are restored.
     * <p>
     * Request:
     * 'Credentials'
     * <p>
     * Response:
     * 'User'
     */
    @DeleteMapping("/@{username}")
    public UserResponseDto deleteUser(@PathVariable String username) {
        return userService.deleteUserByUsername(username);
    }

    /**
     * Subscribes the user whose credentials are provided by the request body to the
     * user whose username is given in the URL. If there is already a following
     * relationship between the two users, no such followable user exists (deleted or
     * never created), or the credentials provided do not match an active user in the
     * database, an error should be sent as a response. If successful, no data is sent.
     * <p>
     * Request:
     * 'Credentials'
     */
    @PostMapping("/@{username}/follow")
    public UserResponseDto followUser(@RequestBody UserRequestDto usertoFollow) {
        return userService.followUser(usertoFollow);
    }

    /**
     * Unsubscribes the user whose credentials are provided by the request body from
     * the user whose username is given in the URL. If there is no preexisting
     * following relationship between the two users, no such followable user exists
     * (deleted or never created), or the credentials provided do not match an active
     * user in the database, an error should be sent as a response. If successful,
     * no data is sent.
     * <p>
     * Request:
     * 'Credentials'
     */
    @PostMapping("/@{username}/unfollow")
    public UserResponseDto unfollowUser(@RequestBody UserRequestDto userToUnfollow) {
        return userService.unfollowUser(userToUnfollow);
    }

}