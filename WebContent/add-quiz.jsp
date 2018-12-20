<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.vy.dao.QuizDAO"%>
<%@page import="com.vy.dao.SubjectDAO,java.util.*"%>
<%@page import="com.vy.dao.SectionDAO"%>
<%@page import="com.vy.model.Subject" %>
<%@page import="com.vy.model.Section" %>
<%@page import="com.vy.model.Quiz" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<%@page import="com.vy.dao.QuizDAO,java.util.*"%>
	<%request.setCharacterEncoding("UTF-8");%>
	<%
	List<Subject> listSubject = SubjectDAO.getAllSubjects();
	List<Section> listSection = SectionDAO.getAllSections();
	List<Quiz> listQuiz = QuizDAO.getAllQuizzes();
	%>
	<jsp:include page="test-header.jsp"></jsp:include>
    <main role="main">
    	<div class="container-fluid mt-dashbroad">
		  <div class="row">
		    <jsp:include page="test-dashboard.jsp"></jsp:include>
		    <div class="col-sm-9">
		    	<h2>Tạo câu hỏi</h2>
		    	<hr>
		    	<c:if test="${errors!=null}">
			    	<c:forEach items="${errors}" var="error" >
			    		<center><h5 style="color:red">Error: ${error}</h5></center>
			    	</c:forEach>
		    	</c:if>
		    	  <form name="formSubject" id="formSubject" 
		    		action="quiz?command=add" 
		    		method="POST">
		    	  <input type="hidden" id="quizId" name="quizId" value="${quiz.id }">
				  <div class="form-group">
				      <label for="inputPassword4">Phần môn học</label>
				      <select name="sectionId" class="form-control" id="selectSection"> 
				      	<%for(Subject subject : listSubject ) {%>
				      		<optgroup label="<%=subject.getName()%>">
				      			<%for(Section section : listSection){ %>
				      				<%if(section.getSubjectId() == subject.getId()){ %>
				      					<option value="<%=section.getId() %>">
				      						<%=section.getName() %>
				      					</option>
				      				<%} %>
					      		<%} %>
				      		</optgroup>
				      	<%} %>
					  </select>
				   </div>
				  <div class="form-group">
					    <label for="exampleFormControlFile1">Chọn hình ảnh đính kèm</label>
					    <input type="file" class="form-control-file" id="quizImage">
				  </div>
				  <div class="form-group">
				  	<label for="inputAddress">Mức độ câu hỏi</label>
				  	<select name="level" class="form-control" id="selectLevel">
				  		<option value="easy">Easy</option>
				  		<option value="medium">Medium</option>
				  		<option value="hard">Hard</option>
				  	</select>
				  </div>
				  <div class="form-group">
				    <label for="inputAddress">Nội dung câu hỏi</label>
				    <textarea name="quizContent" class="form-control"
				    id="quizContent" rows="3"></textarea>
				  </div>
				  <div class="form-group">
				    <label for="inputAddress2">Câu trả lời đúng</label>
				    <textarea name="quizCorrectAnswer" class="form-control" 
				    id="quizCorrectAnswer" rows="2"></textarea>
				  </div>
				  <div class="form-group">
				    <label for="inputAddress2">Câu trả lời sai 1</label>
				    <textarea name="quizAnswer1" class="form-control" 
				    id="quizAnswer1" rows="2"></textarea>
				  </div>
				  <div class="form-group">
				    <label for="inputAddress2">Câu trả lời sai 2</label>
				    <textarea name="quizAnswer2" class="form-control" 
				    id="quizAnswer2" rows="2"></textarea>
				  </div>
				 <div class="form-group">
				    <label for="inputAddress2">Câu trả lời sai 3</label>
				    <textarea name="quizAnswer3" class="form-control" 
				    id="quizAnswer3" rows="2"></textarea>
				  </div>
				  <button type="submit" class="btn btn-primary btn-lg btn-block">
				  	Tạo câu hỏi</button>
				  <a class="btn btn-secondary btn-block" href="/ThiTracNghiem/quiz" role="button">Thoát</a>
				 </form>
		    </div>
		  </div>
	</div>
    </main>
    
    <script>
		function myFunction(listSection) {
			var subjectId = document.getElementById("selectSubject").value;
			console.log(listSection.length);
		}
	</script>

</body>
</html>