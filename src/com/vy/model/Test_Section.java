package com.vy.model;

import java.io.Serializable;

public class Test_Section implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String sectionName;
	private int subjectId;
	private int testId;
	private int sectionId;
	private int numQuiz;
	
	public Test_Section(String sectionName, int subjectId, int testId, int sectionId, int numQuiz) {
		this.setSectionName(sectionName);
		this.setSubjectId(subjectId);
		this.setTestId(testId);
		this.setSectionId(sectionId);
		this.setNumQuiz(numQuiz);
	}
	
	public Test_Section() {}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public int getNumQuiz() {
		return numQuiz;
	}

	public void setNumQuiz(int numQuiz) {
		this.numQuiz = numQuiz;
	}

}
