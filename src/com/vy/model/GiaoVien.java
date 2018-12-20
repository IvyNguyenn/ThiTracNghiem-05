package com.vy.model;

public class GiaoVien {

	private int giaovien_id;
	private String username;
	private String password;
	private String fullname;
	
	public GiaoVien() {
		
	}

	public int getGiaovien_id() {
		return giaovien_id;
	}

	public void setGiaovien_id(int giaovien_id) {
		this.giaovien_id = giaovien_id;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}
