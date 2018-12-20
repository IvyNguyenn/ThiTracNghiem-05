package com.vy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.LopHoc_DAO;
import com.vy.model.GiaoVien;
import com.vy.model.LopHoc;
import com.vy.model.MonHoc;

@WebServlet("/ListLopHoc/ThemLopHoc")
public class CreateLopHoc_CTR extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CreateLopHoc_CTR() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<MonHoc> dsMonHoc = null;
		ArrayList<GiaoVien> dsGiaoVien = null;
		
		try {
			dsMonHoc = LopHoc_DAO.LoadDanhSachMonHoc();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			dsGiaoVien= LopHoc_DAO.LoadDanhSachGiaoVien();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("dsGiaoVien", dsGiaoVien);
		request.setAttribute("dsMonHoc", dsMonHoc);
		request.getRequestDispatcher("/WEB-INF/add-class.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String className = request.getParameter("txtCLassName");
		String teacherId = request.getParameter("teacherId");
		String subjectId = request.getParameter("subjectId");
		// Táº¡o Ä‘á»‘i tÆ°á»£ng lá»›p há»�c
		LopHoc lh= new LopHoc();
		lh.setClass_name(className);;
		lh.setSubject_id(Integer.parseInt(subjectId));
		lh.setTeacher_id(Integer.parseInt(teacherId));
		// ThÃªm lá»›p há»�c
		int kq=0;
		try {
			kq = LopHoc_DAO.ThemLopHoc(lh);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(kq>0)
			response.sendRedirect(request.getContextPath()+"/ListLopHoc");
	}

}
