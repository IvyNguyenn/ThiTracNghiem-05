package com.vy.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.QuizDAO;
import com.vy.dao.SectionDAO;
import com.vy.dao.SubjectDAO;
import com.vy.dao.TestDAO;
import com.vy.dao.Test_SectionDAO;
import com.vy.model.Section;
import com.vy.model.Subject;
import com.vy.model.Test;
import com.vy.model.Test_Section;;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Test_Section> selectedSections = new ArrayList<Test_Section>();
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		boolean access = false;
//		for (Cookie cookie : request.getCookies()) {
//			if (cookie.getValue().equals("testManager")){ 
//				access = true;
//			}
//		}
//		if(!access) {
//			request.getRequestDispatcher("index.jsp").forward(request, response);
//		}
		if(request.getParameter("command") != null) {
			switch(request.getParameter("command")) {
			case "add":
				request.getRequestDispatcher("add-test.jsp").forward(request, response);
				break;
			case "update":
				int id = Integer.parseInt(request.getParameter("id"));
				// get add DATA by testId to request
				Test test = TestDAO.getTestById(id);
				selectedSections = Test_SectionDAO.getTestSectionsByTestId(id);
				
				request.setAttribute("test", test);
				request.setAttribute("selectedSections", selectedSections);
				request.getRequestDispatcher("edit-test.jsp").forward(request, response);
				break;
			case "delete":
				int testid = Integer.parseInt(request.getParameter("id"));
				TestDAO.delete(testid);
				response.sendRedirect("/ThiTracNghiem/test");
				System.out.println("DELETED TEST "+testid);
				break;
			case "deleteSection":
				int sectionid = Integer.parseInt(request.getParameter("id"));
				for(Test_Section s : selectedSections) {
					if(s.getSectionId() == sectionid) {
						selectedSections.remove(s);
						break;
					}
				}
				response.setContentType("text/html;charset=UTF-8");
				request.setCharacterEncoding("UTF-8");
				request.setAttribute("selectedSections", selectedSections);
				request.getRequestDispatcher("add-test.jsp").include(request, response);
				break;
			case "add-test-class":
				request.getRequestDispatcher("add-test-class.jsp").forward(request, response);
				break;
			case "delete-test-class":
				int classId = Integer.parseInt(request.getParameter("classId"));
				int testId = Integer.parseInt(request.getParameter("testId"));
				System.out.println(classId + " - " + testId);
				TestDAO.deleteClassTest(classId, testId);
				response.sendRedirect("/ThiTracNghiem/test?command=add-test-class");
				break;
			}
		}
		else {
			request.getRequestDispatcher("list-test.jsp").forward(request, response);
		}	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();
			if(request.getParameter("command") != null) {
				if(request.getParameter("command").equals("add-test-class")) {
					int classId = Integer.parseInt(request.getParameter("selectClass"));
					int testId = Integer.parseInt(request.getParameter("selectTest"));
					TestDAO.addClassToTest(classId, testId);
					request.getRequestDispatcher("add-test-class.jsp").forward(request, response);
					return;
				}else if(request.getParameter("addSection")!=null) {
					int sectionId = Integer.parseInt(request.getParameter("selectSection"));
					for (Test_Section section : selectedSections) {
						if (section.getSectionId() == sectionId){
							errors.add("Đã thêm phần này!");
							sectionId = 0;
							break;
						}
					}
					if(sectionId > 0) {
						selectedSections.add(Test_SectionDAO.getSectionsById(sectionId));
						request.setAttribute("selectedSections", selectedSections);
					}
					request.setAttribute("errors", errors);
					request.getRequestDispatcher("add-test.jsp").forward(request, response);
					return;
				}
				// get Parameter
				String testName = request.getParameter("testName");
				String dateOpen = request.getParameter("dateOpen");
				String timeOpen = request.getParameter("timeOpen");
				String dateClose = request.getParameter("dateClose");
				String timeClose = request.getParameter("timeClose");
				String password = request.getParameter("password");
				int timeLimit;
				int maxSubmit;
				
				if(testName!="" && dateOpen!="" && timeOpen!="" && dateClose!="" && timeClose!="" &&
						request.getParameter("timeLimit")!="" && request.getParameter("maxSubmit")!=""
						&& selectedSections.size()>0) {
					timeLimit = Integer.parseInt(request.getParameter("timeLimit"));
					maxSubmit = Integer.parseInt(request.getParameter("maxSubmit"));
					
					Test test = new Test();
					test.setName(testName);
					test.setTestManagerId(1);
					test.setTimeLimit(timeLimit);
					test.setDateOpen(dateOpen);
					test.setTimeOpen(timeOpen);
					test.setDateClose(dateClose);
					test.setTimeClose(timeClose);
					test.setPassword(password);
					test.setMaxSubmit(maxSubmit);
					
					int sumQuiz = 0;
					for(Test_Section ts : selectedSections) {
						int num = Integer.parseInt(request.getParameter("numQuiz"+ts.getSectionId()));
						if(num<=QuizDAO.getNumQuizOfSection(ts.getSectionId())) {
							sumQuiz+=num;
							ts.setNumQuiz(num);
						}
						else {
							errors.add("Không đủ câu hỏi để tạo bài thi");
							selectedSections.clear();
							sumQuiz=0;
							break;
						}
					}
					if(sumQuiz > 0)	{
						if(request.getParameter("id")!=null) {
							//update
							int testId = Integer.parseInt(request.getParameter("id"));
							System.out.println("update test:----------- "+testId);
							try {
								test.setId(testId);
								test.setNumQuiz(sumQuiz);
								TestDAO.update(test);
								for(Test_Section ts : selectedSections) {
									TestDAO.updateSectionToTest(testId, ts.getSectionId(), ts.getNumQuiz());
								}
								selectedSections.clear();
							}catch(Exception e) {
								errors.add("Không thể sửa!");
							}
							
						}else {
							//add
							System.out.println("add test: "+test);
							try {
								test.setNumQuiz(sumQuiz);
								TestDAO.add(test);
								int testId = TestDAO.getLastTestId();						
								for(Test_Section ts : selectedSections) {
									TestDAO.addSectionToTest(testId, ts.getSectionId(), ts.getNumQuiz());
								}
								selectedSections.clear();
								System.out.println("sumQuiz  "+sumQuiz);
							}catch(Exception e) {
								errors.add("Không thể thêm!");
							}
						}
					}
				}else {
					errors.add("Vui lòng điền đầy đủ thông tin các trường bắt buộc!");
					selectedSections.clear();
				}
			}else {
				errors.add("Có lỗi xảy ra!");
		}
		if (errors.size() == 0) {
			response.sendRedirect("/ThiTracNghiem/test");
		} 
		else {
			System.out.println(errors);
			List<Subject> listSubject = SubjectDAO.getAllSubjects();
			List<Section> listSection = SectionDAO.getAllSections();
			request.setAttribute("listSubject", listSubject);
			request.setAttribute("listSection", listSection);
			request.setAttribute("errors", errors);
			switch(request.getParameter("command")){
			case "add":
				request.getRequestDispatcher("add-test.jsp").forward(request, response);
				break;
			case "update":	
				request.getRequestDispatcher("edit-quiz.jsp").forward(request, response);
				break;
			}
			
		}
	}

}
