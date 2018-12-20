package com.vy.model;

import java.io.Serializable;

public class Section implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private int subjectId;
	
	public Section(int id, String name, int subjectId) {
		this.id = id;
		this.name = name;
		this.subjectId = subjectId;
	}
	public Section() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}
