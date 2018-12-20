package com.vy.controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vy.dao.DBconnect;
import com.vy.model.GiaoVien;

@WebServlet("/Login")
public class Login_CTR extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Login_CTR() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("Logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} else
			request.getRequestDispatcher("/WEB-INF/signin.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("txtUserName");
		String password = request.getParameter("txtPassword");
		String actor = request.getParameter("actor");

		if (actor.equals("student")) {

		} else if (actor.equals("teacher")) {
			// Kiem tra tai khoan giao vien
			if (KiemTraTaiKhoanGiaoVien(username, password)) {
				GiaoVien gv = new GiaoVien();
				gv.setUsername(username);
				gv.setPassword(password);
				request.getSession(true).setAttribute("ATT_GIAOVIEN", gv);
				response.sendRedirect(request.getContextPath() + "/ListThiSinh?page=1");
			} else {
				response.sendRedirect(request.getContextPath() + "/Login?action=Login");
			}
		}
	}

	private boolean KiemTraTaiKhoanGiaoVien(String username, String password) {
		boolean kq = false;
		try {
			String sql = "select * from class_managers where username = ? and password = ?";
			CallableStatement cs = DBconnect.getMySQLConnection().prepareCall(sql);
			cs.setString(1, username);
			cs.setString(2, password);
			ResultSet rs = cs.executeQuery();
			if (rs.next()) {
				kq = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			kq = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return kq;
	}
}
