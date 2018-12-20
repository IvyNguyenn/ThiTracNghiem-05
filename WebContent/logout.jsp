<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%
	for (Cookie cookie : request.getCookies()) {
		if (cookie.getName().equals("loginUser")){
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		if (cookie.getName().equals("roleUser")){
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
	response.sendRedirect("/ThiTracNghiem/");
%>