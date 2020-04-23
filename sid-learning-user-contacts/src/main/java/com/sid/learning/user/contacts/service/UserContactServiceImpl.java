package com.sid.learning.user.contacts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sid.learning.user.contacts.shared.UserContactDto;

@Service
public class UserContactServiceImpl implements UserContactService {

	@Override
	public List<UserContactDto> getAllContactsByUserId(String userId) {
		List<UserContactDto> userContacts = new ArrayList<>();
		if (userId.equals("ad0173ed-afd7-481d-a3ee-0141d41e7bf9")) {
			userContacts.add(new UserContactDto(userId, "Dhanbad", "Jharkhand", "India", Boolean.FALSE));
			userContacts.add(new UserContactDto(userId, "Bangalore", "Karnataka", "India", Boolean.TRUE));
		}
		return userContacts;
	}
}
