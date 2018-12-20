package com.vy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import com.vy.dao.ThiSinh_DAO;
import com.vy.model.ThiSinh;

@WebServlet("/ListThiSinh")
public class DsThiSinh_CTR extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public DsThiSinh_CTR() {
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		ArrayList<ThiSinh> ls =null;
		int limit  = 10;	// Số phần tử trong 1 trang
		int max_page=0;
		
		if(page == null) {
			try {
				ls = ThiSinh_DAO.LoadDanhSachThiSinh(1, limit);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}else {
			try {
				ls = ThiSinh_DAO.LoadDanhSachThiSinh(Integer.parseInt(page), limit);
			} catch (NumberFormatException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		// Lấy tổng số trang
		try {
			max_page = ThiSinh_DAO.LayTongSoTrang(limit);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("lsSinhVien", ls);
		request.setAttribute("max_page", max_page);
		request.getRequestDispatcher("/WEB-INF/list-student.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
