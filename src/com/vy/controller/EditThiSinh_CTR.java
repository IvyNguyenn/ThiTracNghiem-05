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
import com.vy.model.LopHoc;
import com.vy.model.ThiSinh;

@WebServlet("/ListThiSinh/EditThiSinh")
public class EditThiSinh_CTR extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public EditThiSinh_CTR() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String examinee_id = request.getParameter("id");
		ThiSinh ts = null;
		ArrayList<LopHoc> ls = null;
		// Lấy thông tin thí sinh
		try {
			ts = ThiSinh_DAO.TimThongTinThiSinh(examinee_id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// Lấy danh sách lớp mà thí sinh thuộc
		try {
			ls = LopHoc_DAO.DanhSachLop_ExamineeDangKy(examinee_id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		request.setAttribute("ThiSinhInfor", ts);
		request.setAttribute("dsLopHoc", ls);
		request.getRequestDispatcher("/WEB-INF/edit-student.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String examinee_id = request.getParameter("txtExamineeId");
		String fullname = request.getParameter("txtFullName");
		String password = request.getParameter("txtPassword");
		//
		ThiSinh ts = new ThiSinh();
		ts.setExaminee_id(Integer.parseInt(examinee_id));
		ts.setFullname(fullname);
		ts.setPassword(password);
		//
		int kq = 0;
		try {
			kq = ThiSinh_DAO.CapNhatThongTin(ts);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			request.getRequestDispatcher("/WEB-INF/404page.html").forward(request, response);
		}

		String[] lsLopHocId = request.getParameterValues("ClassId");
		if (lsLopHocId != null) {
			try {
				for (int i = 0; i < lsLopHocId.length; i++)
					LopHoc_DAO.XoaThiSinhKhoiLopHoc(examinee_id, lsLopHocId[i]);

			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("/WEB-INF/404page.html").forward(request, response);
			}
		}

		response.sendRedirect(request.getContextPath() + "/ListThiSinh?page=1");
	}

}
