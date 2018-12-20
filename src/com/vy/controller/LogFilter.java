package com.vy.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.vy.model.GiaoVien;

@WebFilter("/*")
public class LogFilter implements Filter{

	public LogFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = (HttpSession) req.getSession(true);
		String servletPath = req.getServletPath();
		System.out.println(" - ServletPath :" + servletPath  + ", URL =" + req.getRequestURL());
		
		if(servletPath.contains("/index")) {
			chain.doFilter(request, response);
		}
		else if( servletPath.contains("/ListThiSinh") || servletPath.contains("/ListLopHoc")) {
			GiaoVien gv = (GiaoVien) session.getAttribute("ATT_GIAOVIEN");
			if(gv!=null)
				chain.doFilter(request, response);
			else
			{
				HttpServletResponse resp = (HttpServletResponse) response;
	            resp.sendRedirect(req.getContextPath() + "/Login?action=Login");
			}
		}
		else if(servletPath.contains("/QuanLyDeThi")) {
			GiaoVien gv = (GiaoVien) session.getAttribute("ATT_ActorQuanLyDeThi");
			if(gv==null)
			{
				HttpServletResponse resp = (HttpServletResponse) response;
	            resp.sendRedirect(req.getContextPath() + "/Login?action=Login");
			}
		}
		else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Filter.super.init(filterConfig);
	}

}
