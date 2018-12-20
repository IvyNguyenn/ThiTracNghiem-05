package com.vy.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.vy.model.Subject;;


public class SubjectDAO {
	public static List<Subject> getAllSubjects() {
		List<Subject> listSubject = new ArrayList<Subject>();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM SUBJECTS";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Subject subject = new Subject();
				subject.setId(rs.getInt("id"));
				subject.setName(rs.getString("name"));
				subject.setDescription(rs.getString("description"));
				listSubject.add(subject);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return listSubject;
	}
	
	public static Subject getSubjectByQuizId(int id) {
		Subject subject = new Subject();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT S.ID, S.NAME, S.DESCRIPTION  FROM SUBJECTS S,SECTIONS E, QUIZZES Q \r\n" + 
					"WHERE S.ID = E.SUBJECTID\r\n" + 
					"AND E.ID = Q.SECTIONID\r\n" + 
					"AND Q.ID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				subject.setId(rs.getInt("id"));
				subject.setName(rs.getString("name"));
				subject.setDescription(rs.getString("description"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return subject;
	}
	
	public static Subject getSubjectByTestId(int id) {
		Subject subject = new Subject();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM SUBJECTS S\r\n" + 
							"WHERE S.ID IN\r\n" + 
							"(SELECT E.SUBJECTID FROM TESTS T, TEST_SECTION S, SECTIONS E\r\n" + 
							"WHERE T.ID = S.TESTID\r\n" + 
							"AND S.SECTIONID = E.ID\r\n" + 
							"AND T.ID = ?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				subject.setId(rs.getInt("id"));
				subject.setName(rs.getString("name"));
				subject.setDescription(rs.getString("description"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return subject;
	}
}
