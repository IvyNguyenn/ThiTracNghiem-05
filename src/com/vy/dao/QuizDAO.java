package com.vy.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.vy.model.Answer;
import com.vy.model.Quiz;

public class QuizDAO {
	
	public QuizDAO () {}
	
	public static int add(Quiz quiz) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			String query = "INSERT INTO QUIZZES VALUES (null,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, quiz.getContent());
			ps.setString(2,quiz.getLevel());
			ps.setString(3, quiz.getImage());
			ps.setInt(4, quiz.getSectionId());
			ps.setInt(5, quiz.getquizManagerId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static int update(Quiz quiz) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			PreparedStatement ps = con
					.prepareStatement("UPDATE QUIZZES SET CONTENT=?,LEVEL=?,IMAGE=?,SECTIONID=? WHERE ID=?;");
			ps.setString(1, quiz.getContent());
			ps.setString(2, quiz.getLevel());
			ps.setString(3, quiz.getImage());
			ps.setInt(4, quiz.getSectionId());
			ps.setInt(5, quiz.getId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static int delete(int id) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			// delete answers of quiz
			String query = "DELETE FROM ANSWERS WHERE QUIZID=?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			status = ps.executeUpdate();
			// delete quiz
			query = "DELETE FROM QUIZZES WHERE ID=?;";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}
	
	public static List<Quiz> getAllQuizzes() {
		List<Quiz> list = new ArrayList<Quiz>();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM QUIZZES";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Quiz quiz = new Quiz();
				quiz.setId(rs.getInt("id"));
				quiz.setContent(rs.getString("content"));
				quiz.setLevel(rs.getString("level"));
				quiz.setImage(rs.getString("image"));
				quiz.setSection(rs.getInt("sectionId"));
				quiz.setquizManagerId(rs.getInt("quizManagerId"));
				list.add(quiz);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static Quiz getQuizById(int id) {
		Quiz quiz = new Quiz();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM QUIZZES WHERE ID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				quiz.setId(rs.getInt("id"));
				quiz.setContent(rs.getString("content"));
				quiz.setLevel(rs.getString("level"));
				quiz.setImage(rs.getString("image"));
				quiz.setSection(rs.getInt("sectionId"));
				quiz.setquizManagerId(rs.getInt("quizManagerId"));
			}
			quiz.setListAnswer(getAnswerByQuizId(id));
		}catch (Exception e) {
			System.out.println(e);
		}
		return quiz;
	}
	
	public static List<Quiz> getQuizzesBySectionId(int sectionId, int numQuiz) {
		List<Quiz> list = new ArrayList<Quiz>();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM QUIZZES WHERE SECTIONID = ? ORDER BY RAND() limit ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, sectionId);
			ps.setInt(2, numQuiz);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Quiz quiz = new Quiz();
				quiz.setId(rs.getInt("id"));
				quiz.setContent(rs.getString("content"));
				quiz.setLevel(rs.getString("level"));
				quiz.setImage(rs.getString("image"));
				quiz.setSection(rs.getInt("sectionId"));
				quiz.setquizManagerId(rs.getInt("quizManagerId"));
				list.add(quiz);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static List<Answer> getAnswerByQuizId(int id) {
		List<Answer> listAns = new ArrayList<Answer>();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM ANSWERS WHERE QUIZID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Answer ans = new Answer();
				ans.setId(rs.getInt("id"));
				ans.setContent(rs.getString("content"));
				ans.setStatus(rs.getBoolean("status"));
				ans.setQuizId(rs.getInt("quizId"));
				listAns.add(ans);
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return listAns;
	}
	
	public static int getLastQuizId() {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT MAX(ID) AS MAXID FROM QUIZZES";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				status = rs.getInt("maxid");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static int getNumQuizOfSection(int id) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT COUNT(*) AS COUNT FROM QUIZZES WHERE SECTIONID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				status = rs.getInt("count");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
}
