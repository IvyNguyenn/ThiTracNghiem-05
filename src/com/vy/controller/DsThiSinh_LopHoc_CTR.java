package com.vy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.LopHoc_DAO;
import com.vy.model.ThiSinh;

@WebServlet("/ListLopHoc/DanhSachThiSinh-LopHoc")
public class DsThiSinh_LopHoc_CTR extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public DsThiSinh_LopHoc_CTR() {
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String class_id = request.getParameter("classId");
		ArrayList<ThiSinh> ls = null;
		
		try {
			ls = LopHoc_DAO.LoadThiSinhTrongLopHoc(class_id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("classId", class_id);
		request.setAttribute("dsSinhVien", ls);
		request.getRequestDispatcher("/WEB-INF/list-student-class.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
}
