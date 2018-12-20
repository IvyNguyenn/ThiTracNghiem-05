package com.vy.model;

import java.io.Serializable;
import java.util.Date;

public class Test implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private int testManagerId;
	private int timeLimit;
	private String dateOpen;
	private String dateClose;
	private String timeOpen;
	private String timeClose;
	private int numQuiz;
	private String password;
	private int maxSubmit;
	
	public Test(int id, String name, int testManagerId, int timeLimit, String dateOpen, String dateClose,
			String timeOpen, String timeClose, int numQuiz, String password, int maxSubmit) {
		this.id = id;
		this.name = name;
		this.testManagerId = testManagerId;
		this.timeLimit = timeLimit;
		this.dateOpen = dateOpen;
		this.dateClose = dateClose;
		this.timeOpen = timeOpen;
		this.timeClose = timeClose;
		this.numQuiz = numQuiz;
		this.password = password;
		this.maxSubmit = maxSubmit;
	}
	
	public Test() {}

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

	public int getTestManagerId() {
		return testManagerId;
	}

	public void setTestManagerId(int testManagerId) {
		this.testManagerId = testManagerId;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getDateOpen() {
		return dateOpen;
	}

	public void setDateOpen(String dateOpen) {
		this.dateOpen = dateOpen;
	}

	public String getDateClose() {
		return dateClose;
	}

	public void setDateClose(String dateClose) {
		this.dateClose = dateClose;
	}

	public int getNumQuiz() {
		return numQuiz;
	}

	public void setNumQuiz(int numQuiz) {
		this.numQuiz = numQuiz;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMaxSubmit() {
		return maxSubmit;
	}

	public void setMaxSubmit(int maxSubmit) {
		this.maxSubmit = maxSubmit;
	}

	public String getTimeOpen() {
		return timeOpen;
	}

	public void setTimeOpen(String timeOpen) {
		this.timeOpen = timeOpen;
	}

	public String getTimeClose() {
		return timeClose;
	}

	public void setTimeClose(String timeClose) {
		this.timeClose = timeClose;
	}
}
