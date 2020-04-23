package com.sid.app.ws.shared;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2628152266246522089L;

	private String userId;
	private String firstName;
	private String lastName;
	@JsonIgnore
	private String password;
	private String email;
	private List<UserContactDto> userContacts;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserContactDto> getUserContacts() {
		return userContacts;
	}

	public void setUserContacts(List<UserContactDto> userContacts) {
		this.userContacts = userContacts;
	}
}