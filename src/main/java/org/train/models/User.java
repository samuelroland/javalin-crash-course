package org.train.models;

public class User {
	// Attributes are public because Jackson (the JSON parser) needs to access it
	public Integer id;
	public String username;
	public String password;

	// This default constructor is important to not get annoying parsing errors from Jackson
	public User() {
	}

	public User(Integer id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
}
