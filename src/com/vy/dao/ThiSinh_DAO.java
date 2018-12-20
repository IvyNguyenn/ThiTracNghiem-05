package com.vy.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdk.nashorn.internal.codegen.CompilerConstants.Call;
import com.vy.model.*;

public class ThiSinh_DAO {

	public ThiSinh_DAO() {

	}
	
	
	public static int CapNhatThongTin(ThiSinh ts) throws ClassNotFoundException {
		int kq = 0;
		try {
			CallableStatement cs = DBconnect.getMySQLConnection().prepareCall("{ call CapNhatThongTinThiSinh(?,?,?) }");
			cs.setInt(1, ts.getExaminee_id());
			cs.setString(2,ts.getFullname());
			cs.setString(3, ts.getPassword());
			kq = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	public static int XoaThiSinh(String examinee_id) throws ClassNotFoundException {
		int kq = 0;
		try {
			CallableStatement cs = DBconnect.getMySQLConnection().prepareCall("{ call XoaThiSinh(?) }");
			cs.setInt(1, Integer.parseInt(examinee_id));
			kq = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kq;
	}

	public static int ThemThiSinh(ThiSinh ts) throws ClassNotFoundException {
		int kq = 0;
		try {
			CallableStatement cs = DBconnect.getMySQLConnection().prepareCall("{ call ThemThiSinh(?,?,?) }");
			cs.setString(1, ts.getUsername());
			cs.setString(2, ts.getPassword());
			cs.setString(3, ts.getFullname());
			kq = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}

	public static int LayTongSoTrang(int limit) throws ClassNotFoundException {
		int max_page = 0;
		int records = 0;
		try {
			CallableStatement cs = DBconnect.getMySQLConnection().prepareCall("select count(*) from examinees");
			ResultSet rs = cs.executeQuery();
			if (rs.next()) {
				records = rs.getInt(1);
			}

			if (records / limit == 0)
				max_page = records / limit;
			else
				max_page = records / limit + 1;

		} catch (SQLException e) {
			e.printStackTrace();
			max_page = 1;
		}

		return max_page;
	}

	public static ArrayList<ThiSinh> LoadDanhSachThiSinh(int page, int limit) throws ClassNotFoundException {
		ArrayList<ThiSinh> ls = new ArrayList<>();
		try {
			CallableStatement cs = DBconnect.getMySQLConnection().prepareCall("{ call LoadExaminee_phantrang(?,?) }");
			cs.setInt(1, page);
			cs.setInt(2, limit);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				ThiSinh th = new ThiSinh();
				th.setExaminee_id(rs.getInt("id"));
				th.setUsername(rs.getString("username"));
				th.setPassword(rs.getString("password"));
				th.setFullname(rs.getString("fullName"));

				ls.add(th);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ls;
	}
	
	public static ThiSinh TimThongTinThiSinh(String examinee_id) throws ClassNotFoundException {
		ThiSinh ts = new ThiSinh();
		try {
			CallableStatement cs = DBconnect.getMySQLConnection().prepareCall("select * from examinees where id ="+examinee_id);
			ResultSet rs =cs.executeQuery();
			if(rs.next()) {
				ts.setExaminee_id(rs.getInt("id"));
				ts.setUsername(rs.getString("username"));
				ts.setPassword(rs.getString("password"));
				ts.setFullname(rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ts;
	}
}
