package com.vy.model;

import java.io.Serializable;

public class Result implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int examineeId;
	private int testId;
	private String timeDone;
	private String timeStart;
	private String timeEnd;
	private int scores;
	
	public Result () {}
	
	public Result (int id, int examineeId, int testId, String timeDone, String timeStart, String timeEnd, int scores) {
		this.setId(id);
		this.setExamineeId(examineeId);
		this.setTestId(testId);
		this.setTimeDone(timeDone);
		this.setTimeStart(timeStart);
		this.setTimeEnd(timeEnd);
		this.setScores(scores);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExamineeId() {
		return examineeId;
	}

	public void setExamineeId(int examineeId) {
		this.examineeId = examineeId;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getTimeDone() {
		return timeDone;
	}

	public void setTimeDone(String timeDone) {
		this.timeDone = timeDone;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public int getScores() {
		return scores;
	}

	public void setScores(int scores) {
		this.scores = scores;
	}
}
