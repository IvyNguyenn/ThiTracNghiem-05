package com.vy.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.vy.model.Quiz;
import com.vy.model.Result;

public class ResultDAO {
	public ResultDAO () {}
	
	public static int add(Result result) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			String query = "INSERT INTO RESULTS VALUES (null,?,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, result.getExamineeId());
			ps.setInt(2, result.getTestId());
			ps.setString(3, result.getTimeDone());
			ps.setString(4, result.getTimeStart());
			ps.setString(5, result.getTimeEnd());
			ps.setInt(6, result.getScores());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static List<Result> getResultByTestId(int testId, int examineeId) {
		List<Result> list = new ArrayList<Result>();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM RESULTS WHERE TESTID = ? AND EXAMINEEID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, testId);
			ps.setInt(2, examineeId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Result result = new Result();
				result.setId(rs.getInt("id"));
				result.setExamineeId(rs.getInt("examineeId"));
				result.setTestId(rs.getInt("testId"));
				result.setTimeDone(rs.getDate("timeDone")+" "+rs.getTime("timeDone"));
				result.setTimeStart(rs.getDate("timeStart")+" "+rs.getTime("timeStart"));
				result.setTimeEnd(rs.getDate("timeEnd")+" "+rs.getTime("timeEnd"));
				result.setScores(rs.getInt("scores"));
				list.add(result);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static int getNumResultOfTest(int testId, int examineeId) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT COUNT(*) AS COUNT FROM RESULTS WHERE TESTID = ? AND EXAMINEEID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, testId);
			ps.setInt(2, examineeId);
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
