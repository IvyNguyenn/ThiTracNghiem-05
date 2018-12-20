package com.vy.model;

import java.io.Serializable;

public class QuizManager implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String fullName;
	private String username;
	private String password;
	private String image;
	
	public QuizManager (int id, String fullName, String username, String password, String image) {
		this.id = id;
		this.fullName = fullName;
		this.username = username;
		this.password = password;
		this.image = image;
	}
	
	public QuizManager () {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}	
}
