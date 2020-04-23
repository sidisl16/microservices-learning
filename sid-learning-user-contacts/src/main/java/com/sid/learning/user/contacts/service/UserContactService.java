package com.sid.learning.user.contacts.service;

import java.util.List;

import com.sid.learning.user.contacts.shared.UserContactDto;

public interface UserContactService {
	
	List<UserContactDto> getAllContactsByUserId(String userId);
}
