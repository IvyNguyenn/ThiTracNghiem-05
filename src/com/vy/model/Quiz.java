package com.vy.model;

import java.io.Serializable;
import java.util.List;

public class Quiz implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String content;
	private String level;
	private String image;
	private int sectionId;
	private int quizManagerId;
	private List<Answer> listAnswer;	
	public Quiz(int id, String content, String level, String image, int sectionId, 
			int quizManagerId,List<Answer> listAnswer ) {
		this.id = id;
		this.content = content;
		this.level  = level;
		this.image = image;
		this.sectionId = sectionId;
		this.quizManagerId = quizManagerId;
		this.setListAnswer(listAnswer);
	}
	public Quiz() {}
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getSectionId() {
		return sectionId;
	}
	public void setSection(int sectionId) {
		this.sectionId = sectionId;
	}
	public int getquizManagerId() {
		return quizManagerId;
	}
	public void setquizManagerId(int quizManagerId) {
		this.quizManagerId = quizManagerId;
	}
	public List<Answer> getListAnswer() {
		return listAnswer;
	}
	public void setListAnswer(List<Answer> listAnswer) {
		this.listAnswer = listAnswer;
	}
}
