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

@WebServlet("/ListLopHoc/AddThiSinh-To-LopHoc")
public class AddThiSinhToLopHoc_CTR  extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public AddThiSinhToLopHoc_CTR() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		String class_id =(String) request.getSession().getAttribute("classId");
		ArrayList<ThiSinh> ls= null;
		try {
			ls = LopHoc_DAO.LoadDanhSachThiSinh_AddToClass(class_id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("dsThiSinh", ls);
		request.getRequestDispatcher("/WEB-INF/add-student-class.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String class_id = (String) request.getSession().getAttribute("classId");
		String[] dsExamineeId = request.getParameterValues("ExamineeId");
		
		try {
			for(int i =0;i<dsExamineeId.length;i++) {
				LopHoc_DAO.ThemThiSinhVaoLopHoc(dsExamineeId[i], class_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/ListLopHoc/DanhSachThiSinh-LopHoc?classId="+class_id);
	}

	
}
