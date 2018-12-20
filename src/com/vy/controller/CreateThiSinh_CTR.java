package com.vy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.LopHoc_DAO;
import com.vy.dao.ThiSinh_DAO;
import com.vy.model.GiaoVien;
import com.vy.model.MonHoc;
import com.vy.model.ThiSinh;

@WebServlet("/ListThiSinh/ThemThiSinh")
public class CreateThiSinh_CTR  extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public CreateThiSinh_CTR() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/add-student.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String fullname = request.getParameter("txtFullName");
		String username = request.getParameter("txtUserName");
		String password = request.getParameter("txtPassword");
		// Tạo đối tượng sinh viên 
		ThiSinh sv = new ThiSinh();
		sv.setFullname(fullname);
		sv.setUsername(username);
		sv.setPassword(password);
		// Thêm sinh viên
		int kq = 0;
		try {
			kq = ThiSinh_DAO.ThemThiSinh(sv);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(kq>0)
			response.sendRedirect(request.getContextPath()+"/ListThiSinh");
	}

}
