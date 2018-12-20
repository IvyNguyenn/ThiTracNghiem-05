package com.vy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.vy.controller.MD5Password;

public class AuthenticationDAO {

	public static boolean login(String username, String password, String role) {
		try {
			Connection con = DBconnect.getConnection();
			String query="";
			switch(role) {
			case "examinee":
				query = "SELECT COUNT(*) AS COUNT FROM EXAMINEES WHERE USERNAME=? AND PASSWORD=?;";
				break;
			case "quizManager":
				query = "SELECT COUNT(*) AS COUNT FROM QUIZ_MANAGERS WHERE USERNAME=? AND PASSWORD=?;";
				break;
			}
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2,MD5Password.MD5(password));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt("count") == 1)
					return true;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
}
