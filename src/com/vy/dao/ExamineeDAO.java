package com.vy.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.vy.controller.MD5Password;
import com.vy.model.Examinee;

public class ExamineeDAO {

	public static List<Examinee> getAllRecords() {
		List<Examinee> list = new ArrayList<Examinee>();

		try {
			Connection con = DBconnect.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM EXAMINEES");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Examinee ex = new Examinee();
				ex.setId(rs.getInt("id"));
				ex.setUsername(rs.getString("username"));
				ex.setImage(rs.getString("image"));
				list.add(ex);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	public static Examinee getExamineeByUsername(String username) {
		Examinee examinee = new Examinee();

		try {
			Connection con = DBconnect.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM EXAMINEES WHERE USERNAME = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				examinee.setId(rs.getInt("id"));
				examinee.setUsername(rs.getString("username"));
				examinee.setImage(rs.getString("image"));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return examinee;
	}
}
