package com.vy.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vy.dao.AnswerDAO;
import com.vy.dao.ExamineeDAO;
import com.vy.dao.QuizDAO;
import com.vy.dao.ResultDAO;
import com.vy.dao.TestDAO;
import com.vy.dao.Test_SectionDAO;
import com.vy.model.Answer;
import com.vy.model.Quiz;
import com.vy.model.Result;
import com.vy.model.Test;
import com.vy.model.Test_Section;

@WebServlet("/exam")
public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Quiz> listQuiz = new ArrayList<Quiz>();
	Test test;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	Date timeStart;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean access = false;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getValue().equals("examinee")){ 
				for (Cookie c : request.getCookies()) {
					if (c.getName().equals("loginUser")){ 
						int examineeId = ExamineeDAO.getExamineeByUsername(c.getValue()).getId();
						int testId = Integer.parseInt(request.getParameter("id"));
						int num = ResultDAO.getNumResultOfTest(testId, examineeId);
						int maxSubmit = TestDAO.getTestById(testId).getMaxSubmit();
						if(num<maxSubmit)
							access = true;
					}
				}
			}
		}
		if(!access) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			//response.sendRedirect("index.jsp");
		}
		
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();
		if(request.getParameter("command") != null) {	
			switch(request.getParameter("command")) {
			case "do":
				int testId = Integer.parseInt(request.getParameter("id"));
				test = TestDAO.getTestById(testId);
				List<Test_Section> listSection = Test_SectionDAO.getTestSectionsByTestId(testId);
				listQuiz.clear();
				for(Test_Section ts : listSection) {
					List<Quiz> quizzes = QuizDAO.getQuizzesBySectionId(ts.getSectionId(), ts.getNumQuiz());
					listQuiz.addAll(quizzes);
				}
				List<Answer> listAnswer = new ArrayList<Answer>();
				for(Quiz quiz : listQuiz ) {
					listAnswer.addAll(AnswerDAO.getAnswersByQuizId(quiz.getId()));
				}
				//Calendar c = Calendar.getInstance();
				//timeStart = c.getTime();
				timeStart = new Date();
				request.setAttribute("test", test);
				request.setAttribute("listQuiz", listQuiz);
				request.setAttribute("listAnswer", listAnswer);
				request.getRequestDispatcher("exam-student.jsp").forward(request, response);
				break;
				
			case "result":
				int id = Integer.parseInt(request.getParameter("id"));
				String res = request.getParameter("optradio"+id);
				break;
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();
		Date timeEnd = new Date();
//		Date timeDone = timeEnd - timeStart;
		// Cham diem
		int testId = Integer.parseInt(request.getParameter("id"));
		int scores = 0;
		for(Quiz quiz : listQuiz) {
			int answerId = Integer.parseInt(request.getParameter("optradio"+quiz.getId()));
			if(AnswerDAO.getAnswerById(answerId).getStatus()) {
				scores++;
			}
		}
		Result result = new Result();
		result.setExamineeId(1);
		result.setTestId(testId);
//		result.setTimeDone(formatter.format(timeDone));
		result.setTimeStart(formatter.format(timeStart));
		result.setTimeEnd(formatter.format(timeEnd));
		result.setScores(scores);
		
		ResultDAO.add(result);
		List<Result> listResult = ResultDAO.getResultByTestId(testId, 1);
		request.setAttribute("test", test);
		request.setAttribute("listResult", listResult);
		request.getRequestDispatcher("exam-completed.jsp").forward(request, response);
	}

}
