package com.sid.app.ws.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sid.app.ws.shared.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto userDto);
	UserDto getUserDetailsByEmail(String email);
	UserDto getUserByUserId(String userId);
}
