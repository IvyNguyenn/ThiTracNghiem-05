<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<%@page import="com.vy.dao.QuizDAO,java.util.*"%>
	<%@page import="com.vy.dao.SubjectDAO,java.util.*"%>
	<%@page import="com.vy.dao.SectionDAO,java.util.*"%>
	<%@page import="com.vy.dao.AnswerDAO,java.util.*"%>
	<%request.setCharacterEncoding("UTF-8");%>
	<jsp:include page="test-header.jsp"></jsp:include>
    <main role="main">
    	<div class="container-fluid mt-dashbroad">
		  <div class="row">
		    <jsp:include page="test-dashboard.jsp"></jsp:include>
		    <div class="col-md-9">
		    	<h2>Chi tiết câu hỏi</h2>
		    	<hr>
		    	<p><b>Môn học: &nbsp</b>${SubjectDAO.getSubjectByQuizId(quiz.id).name}</p>
		    	<p><b>Phần: &nbsp</b>${SectionDAO.getSectionByQuizId(quiz.id).name}</p>
		    	<h4><b>Câu hỏi: &nbsp</b>${quiz.content}</h4>
		    	<div class="form-check">
				  <label class="form-check-label">
				    <input type="radio" class="form-check-input" name="optradio"><b>A.</b> 
				    ${AnswerDAO.getAnswersByQuizId(quiz.id)[0].content}
				  </label>
				</div>
				<div class="form-check">
				  <label class="form-check-label">
				    <input type="radio" class="form-check-input" name="optradio"><b>B.</b> 
				    ${AnswerDAO.getAnswersByQuizId(quiz.id)[1].content}
				  </label>
				</div>
				<div class="form-check">
				  <label class="form-check-label">
				    <input type="radio" class="form-check-input" name="optradio"><b>C.</b> 
				    ${AnswerDAO.getAnswersByQuizId(quiz.id)[2].content}
				  </label>
				</div>
				<div class="form-check">
				  <label class="form-check-label">
				    <input type="radio" class="form-check-input" name="optradio"><b>D.</b>
				    ${AnswerDAO.getAnswersByQuizId(quiz.id)[3].content}
				  </label>
				</div>
				<c:forEach items="${AnswerDAO.getAnswersByQuizId(quiz.id)}" var="ans" >
					<c:if test="${ans.status == true}">
						<b>Câu trả lời đúng:&nbsp ${ans.content}</b>
					</c:if>
				</c:forEach>	
				<div class="float-right">
					<a href="/ThiTracNghiem/edit-question.jsp"><i class="fa fa-edit  mr-3"></i></a>
				    <a href="#"><i class="fa fa-trash-alt"></i></a>
				</div>
				<br>
				<a href="/ThiTracNghiem/quiz" role="button" class="btn btn-outline-secondary mt-2">&larr;Back</a>
		    </div>
		  </div>
		</div>

    </main>
</body>
</html>