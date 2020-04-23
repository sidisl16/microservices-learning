package com.sid.learning.user.contacts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sid.learning.user.contacts.service.UserContactService;
import com.sid.learning.user.contacts.shared.UserContactDto;

@RestController
@RequestMapping(value = "/contacts")
public class ContactsController {

	@Autowired
	private UserContactService userContactService;

	@GetMapping(value = "/users/{userId}", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getAllContactsForUser(@PathVariable(value = "userId") String userId) {
		List<UserContactDto> userContacts = userContactService.getAllContactsByUserId(userId);
		if (userContacts.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(userContacts);
	}

}
