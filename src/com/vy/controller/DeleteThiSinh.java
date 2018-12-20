package com.vy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.LopHoc_DAO;
import com.vy.dao.ThiSinh_DAO;

@WebServlet("/ListThiSinh/XoaThiSinh")
public class DeleteThiSinh extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DeleteThiSinh() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String examinee_id = request.getParameter("examineeId");
		String class_id = request.getParameter("classId");
		int kq = 0;
		// Kiá»ƒm tra xÃ³a sinh viÃªn khá»�i lá»›p
		if (class_id != null) {
			
			try {
				kq = LopHoc_DAO.XoaThiSinhKhoiLopHoc(examinee_id, class_id);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if(kq>0)
				response.sendRedirect(request.getContextPath()+"/ListLopHoc/DanhSachThiSinh-LopHoc?classId="+class_id);
		} else {
			// XÃ³a sinh viÃªn 
			try {
				kq = ThiSinh_DAO.XoaThiSinh(examinee_id);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			if (kq > 0)
				response.sendRedirect(request.getContextPath() + "/ListThiSinh");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
