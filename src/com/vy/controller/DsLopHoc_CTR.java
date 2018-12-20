package com.vy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.LopHoc_DAO;
import com.vy.model.LopHoc;

@WebServlet("/ListLopHoc")
public class DsLopHoc_CTR extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	public DsLopHoc_CTR() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page =request.getParameter("page");
		int limit = 10;  // Sá»‘ lá»›p há»�c trong 1 trang
		int max_page=0;
		ArrayList<LopHoc> ls =null;
		// Load danh sÃ¡ch lá»›p há»�c
		if(page == null) {
			try {
				ls  = LopHoc_DAO.LoadDanhSachLopHoc(1, limit);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				ls = LopHoc_DAO.LoadDanhSachLopHoc(Integer.parseInt(page), limit);
			} catch (NumberFormatException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		// Láº¥y tá»•ng sá»‘ trang
		try {
			max_page = LopHoc_DAO.LayTongSoTrang(limit);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("dsLopHoc", ls);
		request.setAttribute("max_page",max_page);
		request.getRequestDispatcher("/WEB-INF/list-class.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
