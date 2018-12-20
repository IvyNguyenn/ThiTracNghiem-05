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

@WebServlet("/ListLopHoc/EditLopHoc")
public class EditClass_CTR extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public EditClass_CTR() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String class_id = request.getParameter("classId");
		LopHoc cl = null;
		ArrayList<MonHoc> dsMonHoc = null;
		ArrayList<GiaoVien> dsGiaoVien = null;

		// Láº¥y danh sÃ¡ch mÃ´n há»�c
		try {
			dsMonHoc = LopHoc_DAO.LoadDanhSachMonHoc();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		// Láº¥y danh sÃ¡ch giÃ¡o viÃªn
		try {
			dsGiaoVien = LopHoc_DAO.LoadDanhSachGiaoVien();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// Láº¥y thÃ´ng tin lá»›p há»�c
		try {
			cl = LopHoc_DAO.LayThongTinLopHoc(class_id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		request.setAttribute("dsGiaoVien", dsGiaoVien);
		request.setAttribute("dsMonHoc", dsMonHoc);
		request.setAttribute("ClassInfor", cl);
		request.getRequestDispatcher("/WEB-INF/edit-class.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String classId = request.getParameter("txtClassId");
		String className = request.getParameter("txtCLassName");
		String teacherId = request.getParameter("teacherId");
		String subjectId = request.getParameter("subjectId");

		// Táº¡o Ä‘á»‘i tÆ°á»£ng lá»›p há»�c
		LopHoc lh = new LopHoc();
		lh.setClass_name(className);
		lh.setClass_id(Integer.parseInt(classId));
		lh.setSubject_id(Integer.parseInt(subjectId));
		lh.setTeacher_id(Integer.parseInt(teacherId));
		
		int kq =0;
		try {
			kq = LopHoc_DAO.CapNhatThongTinLopHoc(lh);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/404page.html").forward(request, response);
		}
		if(kq>0)
			response.sendRedirect(request.getContextPath()+"/ListLopHoc?page=1");
	}

}
