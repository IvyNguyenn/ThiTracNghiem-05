package com.vy.model;

import java.io.Serializable;

public class Examinee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username;
	private String password;
	private String image;
	
	public Examinee(int id,String username, String password, String image) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.image = image;
	}
	public Examinee() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
