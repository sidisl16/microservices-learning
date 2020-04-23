package com.sid.learning.user.contacts.shared;

public class UserContactDto {

	private String userId;
	private String city;
	private String state;
	private String country;
	private boolean active;

	public UserContactDto() {
	}

	public UserContactDto(String userId, String city, String state, String country, boolean active) {
		super();
		this.userId = userId;
		this.city = city;
		this.state = state;
		this.country = country;
		this.active = active;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}