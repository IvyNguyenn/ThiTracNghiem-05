package com.vy.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.AnswerDAO;
import com.vy.dao.QuizDAO;
import com.vy.dao.SectionDAO;
import com.vy.dao.SubjectDAO;
import com.vy.model.Answer;
import com.vy.model.Quiz;
import com.vy.model.Section;
import com.vy.model.Subject;

@WebServlet("/quiz")
public class QuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean access = false;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getValue().equals("quizManager")){ 
				access = true;
			}
		}
		if(!access) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		if(request.getParameter("command") != null) {
			switch(request.getParameter("command")) {
			case "add":
				request.getRequestDispatcher("add-quiz.jsp").forward(request, response);
				break;
			case "update":
				int id = Integer.parseInt(request.getParameter("id"));
				Quiz quiz = QuizDAO.getQuizById(id);
				List<Subject> listSubject = SubjectDAO.getAllSubjects();
				List<Section> listSection = SectionDAO.getAllSections();
				List<Answer> listWrongAnswer = AnswerDAO.getWrongAnswersByQuizId(id);
				Answer correctAnswer = AnswerDAO.getCorrectAnswerByQuizId(id);
				request.setAttribute("listSubject", listSubject);
				request.setAttribute("listSection", listSection);
				request.setAttribute("quiz", quiz);
				request.setAttribute("correctAnswer", correctAnswer);
				request.setAttribute("listWrongAnswer", listWrongAnswer);
				request.getRequestDispatcher("edit-quiz.jsp").forward(request, response);
				break;
			case "delete":
				int quizid = Integer.parseInt(request.getParameter("id"));
				QuizDAO.delete(quizid);
				response.sendRedirect("/ThiTracNghiem/quiz");
				System.out.println("DELETED QUIZ "+quizid);
				break;
			}
		}
		else {
			request.getRequestDispatcher("list-quiz.jsp").forward(request, response);
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();
		if(request.getParameter("command") != null) {	
			String sectionid = request.getParameter("sectionId");
			String level = request.getParameter("level");
			String quizContent = request.getParameter("quizContent");
			String quizCorrectAnswer = request.getParameter("quizCorrectAnswer");
			String quizAnswer1 = request.getParameter("quizAnswer1");
			String quizAnswer2 = request.getParameter("quizAnswer2");
			String quizAnswer3 = request.getParameter("quizAnswer3");
			
			if(sectionid!="" && level!="" && quizContent!="" && quizCorrectAnswer!="" 
					&& quizAnswer1!="" && quizAnswer2!="" && quizAnswer3!="" ) {
				int sectionId = Integer.parseInt(sectionid);
				
				Quiz quiz = new Quiz();
				Answer answer = new Answer();
				quiz.setContent(quizContent);
				quiz.setLevel(level);
				quiz.setImage(null);
				quiz.setSection(sectionId);
				quiz.setquizManagerId(1);
				
				if(request.getParameter("id")!=null) {
					//update
					int quizId = Integer.parseInt(request.getParameter("id"));
					
					quiz.setId(quizId);
					QuizDAO.update(quiz);
					
					answer.setId(Integer.parseInt(request.getParameter("correctAnswerId")));
					answer.setContent(quizCorrectAnswer);
					answer.setQuizId(quizId);
					try {
						AnswerDAO.update(answer);
					}catch(Exception e) {
						errors.add("Không thể sửa!");
					}
					
					answer.setId(Integer.parseInt(request.getParameter("answer1Id")));
					answer.setContent(quizAnswer1);
					try {
						AnswerDAO.update(answer);
					}catch(Exception e) {
						errors.add("Không thể sửa!");
					}
					
					answer.setId(Integer.parseInt(request.getParameter("answer2Id")));
					answer.setContent(quizAnswer2);
					try {
						AnswerDAO.update(answer);
					}catch(Exception e) {
						errors.add("Không thể sửa!");
					}
					
					answer.setId(Integer.parseInt(request.getParameter("answer3Id")));
					answer.setContent(quizAnswer3);
					try {
						AnswerDAO.update(answer);
					}catch(Exception e) {
						errors.add("Không thể sửa!");
					}
				}else {
					//add
					QuizDAO.add(quiz);
					int quizId = QuizDAO.getLastQuizId();
					
					answer.setContent(quizCorrectAnswer);
					answer.setStatus(true);
					answer.setQuizId(quizId);
					try {
						AnswerDAO.add(answer);
					}catch(Exception e) {
						errors.add("Không thể thêm!");
					}
					
					answer.setContent(quizAnswer1);
					answer.setStatus(false);
					answer.setQuizId(quizId);
					try {
						AnswerDAO.add(answer);
					}catch(Exception e) {
						errors.add("Không thể thêm!");
					}
					
					answer.setContent(quizAnswer2);
					answer.setStatus(false);
					answer.setQuizId(quizId);
					try {
						AnswerDAO.add(answer);
					}catch(Exception e) {
						errors.add("Không thể thêm!");
					}
					
					answer.setContent(quizAnswer3);
					answer.setStatus(false);
					answer.setQuizId(quizId);
					try {
						AnswerDAO.add(answer);
					}catch(Exception e) {
						errors.add("Không thể thêm!");
					}
				}
			}else {
				errors.add("Vui lòng điền đầy đủ thông tin các trường bắt buộc!");
			}
		}else {
			errors.add("Có lỗi xảy ra!");
		}
		if (errors.size() == 0) {
			response.sendRedirect("/ThiTracNghiem/quiz");
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
				request.getRequestDispatcher("add-quiz.jsp").forward(request, response);
				break;
			case "update":	
				request.getRequestDispatcher("edit-quiz.jsp").forward(request, response);
				break;
			}
			
		}
	}
}
