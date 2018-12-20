package com.vy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.LopHoc_DAO;

@WebServlet("/ListLopHoc/XoaLopHoc")
public class DeleteLopHoc_CTR extends HttpServlet{


	private static final long serialVersionUID = 1L;

	public DeleteLopHoc_CTR() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String classId = request.getParameter("classId");
		int kq = 0;
		try {
			kq = LopHoc_DAO.XoaLopHoc(classId);
			response.sendRedirect(request.getContextPath()+"/ListLopHoc?page=1");
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/404page.html").forward(request, response);
			
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
