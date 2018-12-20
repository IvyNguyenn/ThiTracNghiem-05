package com.vy.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.vy.model.Section;

public class SectionDAO {
	
	public static List<Section> getAllSections() {
		List<Section> listSection = new ArrayList<Section>();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM SECTIONS";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Section section = new Section();
				section.setId(rs.getInt("id"));
				section.setName(rs.getString("name"));
				section.setSubjectId(rs.getInt("subjectId"));
				listSection.add(section);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return listSection;
	}
	
	public static List<Section> getSectionsBySubjectId(int id) {
		List<Section> listSection = new ArrayList<Section>();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM SECTIONS WHERE SUBJECTID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Section section = new Section();
				section.setId(rs.getInt("id"));
				section.setName(rs.getString("name"));
				section.setSubjectId(rs.getInt("subjectId"));
				listSection.add(section);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return listSection;
	}
	
	public static List<Section> getSectionsByTestId(int id) {
		List<Section> listSection = new ArrayList<Section>();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM SECTIONS S, TEST_SECTION WHERE S.ID = T.SECTIONID AND T.TESTID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Section section = new Section();
				section.setId(rs.getInt("id"));
				section.setName(rs.getString("name"));
				section.setSubjectId(rs.getInt("subjectId"));
				listSection.add(section);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return listSection;
	}
	
	public static Section getSectionByQuizId(int id) {
		Section section = new Section();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT S.ID, S.NAME, S.SUBJECTID FROM SECTIONS S,QUIZZES Q\r\n" + 
					"WHERE S.ID = Q.SECTIONID\r\n" + 
					"AND Q.ID = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				section.setId(rs.getInt("id"));
				section.setName(rs.getString("name"));
				section.setSubjectId(rs.getInt("subjectId"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return section;
	}
	
	public static Section getSectionById(int id) {
		Section section = new Section();
		try {
			Connection con = DBconnect.getConnection();
			String query = "SELECT * FROM SECTIONS WHERE ID=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				section.setId(rs.getInt("id"));
				section.setName(rs.getString("name"));
				section.setSubjectId(rs.getInt("subjectId"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return section;
	}
}
