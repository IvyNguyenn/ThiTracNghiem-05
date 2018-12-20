package com.vy.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.vy.model.Answer;
import com.vy.model.Quiz;

public class AnswerDAO {
	
	public static int add(Answer answer) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			String query = "INSERT INTO ANSWERS VALUES (null,?,?,?);";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, answer.getQuizId());
			ps.setString(2,answer.getContent());
			ps.setBoolean(3, answer.getStatus());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static int update(Answer answer) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			PreparedStatement ps = con
					.prepareStatement("UPDATE ANSWERS SET CONTENT=? WHERE ID=?;");
			ps.setString(1, answer.getContent());
			ps.setInt(2, answer.getId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static int delete(Answer answer) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE ANSWERS WHERE ID=?;");
			ps.setInt(1, answer.getId());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}
	
	public static int deleteAnswerByQuizId(int id) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE ANSWERS WHERE QUIZID=?;");
			ps.setInt(1, id);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}
	
	public static List<Answer> getAnswersByQuizId(int id) {
		List<Answer> listAns = new ArrayList<Answer>();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM ANSWERS WHERE QUIZID = ? ORDER BY RAND()";
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
	
	public static List<Answer> getWrongAnswersByQuizId(int id) {
		List<Answer> listAns = new ArrayList<Answer>();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM ANSWERS WHERE STATUS = 0 AND QUIZID = ?";
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
	
	public static Answer getCorrectAnswerByQuizId(int id) {
		Answer answer = new Answer();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM ANSWERS WHERE STATUS = 1 AND QUIZID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				answer.setId(rs.getInt("id"));
				answer.setContent(rs.getString("content"));
				answer.setStatus(rs.getBoolean("status"));
				answer.setQuizId(rs.getInt("quizId"));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return answer;
	}
	
	public static Answer getAnswerById(int id) {
		Answer answer = new Answer();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM ANSWERS WHERE ID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				answer.setId(rs.getInt("id"));
				answer.setContent(rs.getString("content"));
				answer.setStatus(rs.getBoolean("status"));
				answer.setQuizId(rs.getInt("quizId"));
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return answer;
	}
}
