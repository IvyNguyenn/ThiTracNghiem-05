package com.vy.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.vy.model.*;


public class LopHoc_DAO {

	public LopHoc_DAO() {
		
	}
	
	public static int XoaLopHoc(String classId) throws ClassNotFoundException {
		int kq =  0;
		
		try {
			CallableStatement cs = DBconnect.getMySQLConnection().prepareCall("{ call XoaLopHoc(?) }");
			cs.setInt(1, Integer.parseInt(classId));
			kq  = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			kq =0;
		}
		
		return kq;
	}
	
	public static int CapNhatThongTinLopHoc(LopHoc lh) throws ClassNotFoundException {
		int kq = 0;
		try {
			CallableStatement cs = DBconnect.getMySQLConnection().prepareCall("{ call CapNhatThongTinLopHoc(?,?,?,?) }");
			cs.setInt(1, lh.getClass_id());
			cs.setString(2, lh.getClass_name());
			cs.setInt(3, lh.getSubject_id());
			cs.setInt(4, lh.getTeacher_id());
			kq = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	public static LopHoc LayThongTinLopHoc(String class_id) throws ClassNotFoundException {
		LopHoc cl = new LopHoc();
		try {
			CallableStatement cs= DBconnect.getMySQLConnection().prepareCall("{ call LoadThongTinLopHoc(?) }");
			cs.setInt(1, Integer.parseInt(class_id));
			ResultSet rs = cs.executeQuery();
			if(rs.next()) {
				cl.setClass_id(rs.getInt("id"));
				cl.setClass_name(rs.getString("name"));
				cl.setSubject_id(rs.getInt("subjectId"));
				cl.setTeacher_id(rs.getInt("classManagerId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cl;
	}
	
	public static int ThemThiSinhVaoLopHoc(String examinee_id,String class_id ) throws ClassNotFoundException {
		int kq = 0;
		try {
			String sql = "insert into examinee_class values(?,?)";
			CallableStatement cs = DBconnect.getMySQLConnection().prepareCall(sql);
			cs.setInt(1, Integer.parseInt(examinee_id));
			cs.setInt(2, Integer.parseInt(class_id));
			kq = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	// Truyá»�n Class_ID Ä‘á»ƒ loáº¡i nhá»¯ng thÃ­ sinh Ä‘Ã£ cÃ³ sáºµn trong lá»›p há»�c
	public static ArrayList<ThiSinh> LoadDanhSachThiSinh_AddToClass(String class_id) throws ClassNotFoundException{
		ArrayList<ThiSinh> ls =new ArrayList<>();
		try {
			CallableStatement cs= DBconnect.getMySQLConnection().prepareCall("{ call LoadDanhSachSinhVien(?) }");
			cs.setInt(1, Integer.parseInt(class_id));
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				ThiSinh ts = new ThiSinh();
				ts.setExaminee_id(rs.getInt("id"));
				ts.setFullname(rs.getString("fullname"));
				ls.add(ts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}
	
	public static int XoaThiSinhKhoiLopHoc(String examinee_id,String class_id) throws ClassNotFoundException {
		int kq  = 0;
		try {
			CallableStatement cs= DBconnect.getMySQLConnection().prepareCall("{ call XoaThiSinhKhoiLopHoc(?,?) }");
			cs.setInt(1, Integer.parseInt(examinee_id));
			cs.setInt(2, Integer.parseInt(class_id));
			kq = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	public static ArrayList<LopHoc> DanhSachLop_ExamineeDangKy(String examinee_id) throws ClassNotFoundException{
		ArrayList<LopHoc> ls = new ArrayList<>();
		try {
			CallableStatement cs = DBconnect.getMySQLConnection().prepareCall("{ call DanhSachLop_ExamineeDangKy(?) }");
			cs.setInt(1, Integer.parseInt(examinee_id));
			ResultSet rs= cs.executeQuery();
			while(rs.next()) {
				LopHoc mh = new LopHoc();
				mh.setClass_id(rs.getInt("id"));
				mh.setClass_name(rs.getString("name"));
				ls.add(mh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}
	
	public static ArrayList<ThiSinh> LoadThiSinhTrongLopHoc(String class_id) throws ClassNotFoundException{
		ArrayList<ThiSinh> ls = new ArrayList<>();
		try {
			CallableStatement cs=DBconnect.getMySQLConnection().prepareCall("{ call LoadDanhSachThiSinhTrongLop(?) }");
			cs.setInt(1, Integer.parseInt(class_id));
			ResultSet rs =cs.executeQuery();
			while(rs.next()) {
				ThiSinh sv = new ThiSinh();
				sv.setExaminee_id(rs.getInt("id"));
				sv.setFullname(rs.getString("fullname"));
				
				ls.add(sv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	} 
	
	public static ArrayList<GiaoVien> LoadDanhSachGiaoVien() throws ClassNotFoundException{
		ArrayList<GiaoVien> ls = new ArrayList<>();
		
		try {
			CallableStatement cs = DBconnect.getMySQLConnection().prepareCall("select * from class_managers");
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				GiaoVien gv  =new GiaoVien();
				gv.setGiaovien_id(rs.getInt("id"));
				gv.setFullname(rs.getString("fullname"));
				gv.setPassword(rs.getString("password"));
				gv.setUsername(rs.getString("username"));		
				ls.add(gv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	public static ArrayList<MonHoc> LoadDanhSachMonHoc() throws ClassNotFoundException{
		ArrayList<MonHoc> ls =new ArrayList<>();
		try {
			CallableStatement cs = DBconnect.getMySQLConnection().prepareCall("SELECT * FROM subjects");
			ResultSet rs =cs.executeQuery();
			while(rs.next()) {
				MonHoc mh = new MonHoc();
				mh.setSubject_id(rs.getInt("id"));
				mh.setSubject_name(rs.getString("name"));
				
				ls.add(mh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}
	
	public static ArrayList<LopHoc> LoadDanhSachLopHoc(int page,int limit) throws ClassNotFoundException{
		ArrayList<LopHoc> ls = new ArrayList<>();
		try {
			CallableStatement cs =  DBconnect.getMySQLConnection().prepareCall("{ call LoadDanhSachLopHoc_phantrang(?,?) }");
			cs.setInt(1, page);
			cs.setInt(2, limit);
			ResultSet rs = cs.executeQuery();
			while(rs.next()) {
				LopHoc lh = new LopHoc();
				lh.setClass_id(rs.getInt("id"));
				lh.setClass_name(rs.getString("name"));
				lh.setSubject_id(rs.getInt("subjectId"));
				lh.setSubject_name(rs.getString("tenMonHoc"));
				lh.setTeacher_name(rs.getString("fullname"));
				
				ls.add(lh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}
	
	public static int LayTongSoTrang(int limit) throws ClassNotFoundException {
		int max_page=0;
		int records = 0;
		try {
			CallableStatement cs= DBconnect.getMySQLConnection().prepareCall("select count(*) from classes ");
			ResultSet rs =cs.executeQuery();
			if(rs.next()) {
				records = rs.getInt(1);
			}
			if(records % limit == 0 ) {
				max_page = records/limit;
			}
			else {
				max_page = records/limit + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			max_page=1;
		}
		
		return max_page;
	}
	
	public static int ThemLopHoc(LopHoc lh) throws ClassNotFoundException {
		int kq = 0;
		try {
			CallableStatement cs= DBconnect.getMySQLConnection().prepareCall("{ call ThemLopHoc(?,?,?) }");
			cs.setString(1, lh.getClass_name());
			cs.setInt(2, lh.getSubject_id());
			cs.setInt(3, lh.getTeacher_id());
			kq = cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		ArrayList<LopHoc> ls = null;
		ls = LopHoc_DAO.LoadDanhSachLopHoc(1, 10);
		System.out.println(7%3);
	}
	
	public static ArrayList<LopHoc> LoadDanhSachLopHoc() throws ClassNotFoundException{
		ArrayList<LopHoc> ls = new ArrayList<>();
		try {
			Connection con = DBconnect.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM CLASSES");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LopHoc lh = new LopHoc();
				lh.setClass_id(rs.getInt("id"));
				lh.setClass_name(rs.getString("name"));
				lh.setSubject_id(rs.getInt("subjectId"));
				
				ls.add(lh);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}
}
