package com.vy.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.vy.controller.MD5Password;
import com.vy.model.Test;;

public class TestDAO {
	
	public static int add(Test test) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			String query = "INSERT INTO TESTS VALUES (null,?,?,?,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, test.getName());
			ps.setInt(2, test.getTestManagerId());
			ps.setInt(3, test.getTimeLimit());
			ps.setString(4, test.getDateOpen()+" "+test.getTimeOpen());
			ps.setString(5, test.getDateClose()+" "+test.getTimeClose());
			ps.setInt(6, test.getNumQuiz());
			ps.setString(7, test.getPassword()!=null?MD5Password.MD5(test.getPassword()):null );
			ps.setInt(8, test.getMaxSubmit());
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static int update(Test test) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE TESTS SET NAME=?,TIMELIMIT=?,TIMEOPEN=?,TIMECLOSE=?,NUMQUIZ=?,PASSWORD=?,MAXSUBMIT=? WHERE ID=?;");
			ps.setString(1, test.getName());
			ps.setInt(2, test.getTimeLimit());
			ps.setString(3, test.getDateOpen()+" "+test.getTimeOpen());
			ps.setString(4, test.getDateClose()+" "+test.getTimeClose());
			ps.setInt(5, test.getNumQuiz());
			ps.setString(6, test.getPassword()!=null?MD5Password.MD5(test.getPassword()):null );
			ps.setInt(7, test.getMaxSubmit());
			ps.setInt(8, test.getId());
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
			// delete section of test
			String query = "DELETE FROM TEST_SECTION WHERE TESTID=?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			status = ps.executeUpdate();
			// delete test
			query = "DELETE FROM TESTS WHERE ID=?;";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}
	
	public static int addSectionToTest(int testId,int sectionId, int numQuiz) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			String query = "INSERT INTO TEST_SECTION VALUES (?,?,?);";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, testId);
			ps.setInt(2, sectionId);
			ps.setInt(3, numQuiz);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static int updateSectionToTest(int testId,int sectionId, int numQuiz) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			String query = "UPDATE TEST_SECTION SET SECTIONID=?, NUMQUIZ=? WHERE TESTID=?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, sectionId);
			ps.setInt(2, numQuiz);
			ps.setInt(3, testId);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static List<Test> getAllTests() {
		List<Test> list = new ArrayList<Test>();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM TESTS";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Test test = new Test();
				test.setId(rs.getInt("id"));
				test.setName(rs.getString("name"));
				test.setTestManagerId(rs.getInt("testManagerId"));
				test.setTimeLimit(rs.getInt("timeLimit"));
				test.setDateOpen(rs.getDate("timeOpen")+"");
				test.setDateClose(rs.getDate("timeClose")+"");
				test.setTimeOpen(rs.getTime("timeOpen")+"");
				test.setTimeClose(rs.getTime("timeClose")+"");
				test.setNumQuiz(rs.getInt("numQuiz"));
				test.setPassword(rs.getString("password"));
				test.setMaxSubmit(rs.getInt("maxSubmit"));
				list.add(test);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static Test getTestById(int id) {
		Test test = new Test();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM TESTS WHERE ID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				test.setId(rs.getInt("id"));
				test.setName(rs.getString("name"));
				test.setTestManagerId(rs.getInt("testManagerId"));
				test.setTimeLimit(rs.getInt("timeLimit"));
				test.setDateOpen(rs.getDate("timeOpen")+"");
				test.setDateClose(rs.getDate("timeClose")+"");
				test.setTimeOpen(rs.getTime("timeOpen")+"");
				test.setTimeClose(rs.getTime("timeClose")+"");
				test.setNumQuiz(rs.getInt("numQuiz"));
				test.setPassword(rs.getString("password"));
				test.setMaxSubmit(rs.getInt("maxSubmit"));
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return test;
	}
	
	public static int getLastTestId() {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT MAX(ID) AS MAXID FROM TESTS";
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
	
	public static int updateNumQuiz(int numQuiz,int testId) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			PreparedStatement ps = con
					.prepareStatement("UPDATE TESTS SET NUMQUIZ=? WHERE ID=?;");
			ps.setInt(1, numQuiz);
			ps.setInt(2, testId);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static List<Test> getTestsByClassId(int id) {
		List<Test> list = new ArrayList<Test>();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM TESTS T, TEST_CLASS C\r\n" + 
					"WHERE T.ID = C.TESTID\r\n" + 
					"AND C.CLASSID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Test test = new Test();
				test.setId(rs.getInt("id"));
				test.setName(rs.getString("name"));
				test.setTestManagerId(rs.getInt("testManagerId"));
				test.setTimeLimit(rs.getInt("timeLimit"));
				test.setDateOpen(rs.getDate("timeOpen")+"");
				test.setDateClose(rs.getDate("timeClose")+"");
				test.setTimeOpen(rs.getTime("timeOpen")+"");
				test.setTimeClose(rs.getTime("timeClose")+"");
				test.setNumQuiz(rs.getInt("numQuiz"));
				test.setPassword(rs.getString("password"));
				test.setMaxSubmit(rs.getInt("maxSubmit"));
				list.add(test);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static int addClassToTest(int classId,int testId) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			String query = "INSERT INTO TEST_CLASS VALUES (?,?);";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, testId);
			ps.setInt(2, classId);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static int deleteClassTest(int classId, int testId) {
		int status = 0;
		try {
			Connection con = DBconnect.getConnection();
			String query = "DELETE FROM TEST_CLASS WHERE CLASSID=? AND TESTID=?;";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, classId);
			ps.setInt(2, testId);
			status = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}

		return status;
	}
}
