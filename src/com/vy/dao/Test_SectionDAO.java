package com.vy.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.vy.model.Section;
import com.vy.model.Test_Section;;

public class Test_SectionDAO {

	public static List<Test_Section> getTestSectionsByTestId(int id) {
		List<Test_Section> testSections = new ArrayList<Test_Section>();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM SECTIONS S, TEST_SECTION T WHERE S.ID = T.SECTIONID AND T.TESTID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Test_Section ts = new Test_Section();
				ts.setSectionName(rs.getString("name"));
				ts.setSubjectId(rs.getInt("subjectId"));
				ts.setTestId(rs.getInt("testId"));
				ts.setSectionId(rs.getInt("sectionId"));
				ts.setNumQuiz(rs.getInt("numQuiz"));
				testSections.add(ts);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return testSections;
	}
	
	public static Test_Section getSectionsById(int id) {
		Test_Section ts = new Test_Section();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM SECTIONS WHERE ID=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ts.setSectionName(rs.getString("name"));
				ts.setSubjectId(rs.getInt("subjectId"));
				ts.setSectionId(rs.getInt("id"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return ts;
	}
}
