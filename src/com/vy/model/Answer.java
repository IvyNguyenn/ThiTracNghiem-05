package com.vy.model;

import java.io.Serializable;

public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String content;
	private boolean status;
	private int  quizId;
	
	public Answer(int id, String content, boolean status, int quizId) {
		this.setId(id);
		this.setContent(content);
		this.setStatus(status);
		this.setQuizId(quizId);
	}
	public Answer() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getQuizId() {
		return quizId;
	}
	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}
}
