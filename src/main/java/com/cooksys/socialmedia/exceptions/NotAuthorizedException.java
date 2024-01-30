package com.cooksys.socialmedia.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotAuthorizedException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4697954669021325223L;
	private String message;
}