package com.vy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.AuthenticationDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("signin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		System.out.println(MD5Password.MD5(password));
		
		//System.out.println(username +" - "+password +" - "+role);
		try {
			if(username =="" || password =="" || role =="" || !AuthenticationDAO.login(username, password, role)) {
				errors.add("Sai tài khoản hoặc mật khẩu!");
			}
		}catch(Exception e) {
			e.printStackTrace();
			errors.add("Có lỗi xảy ra!");
		}
		if (errors.size() == 0) {
			Cookie loginCookie = new Cookie("loginUser", username);
			loginCookie.setMaxAge(-1);
			response.addCookie(loginCookie);
			Cookie roleCookie = new Cookie("roleUser", role);
			roleCookie.setMaxAge(-1);
			response.addCookie(roleCookie);
			
			switch(role) {
			case "quizManager":
				response.sendRedirect("/ThiTracNghiem/quiz");
				break;
			case "examinee":
				response.sendRedirect("/ThiTracNghiem/");
				break;
			}
		} else {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}	
	}

}
