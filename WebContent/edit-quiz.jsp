<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<%@page import="com.vy.dao.QuizDAO,com.vy.model.Quiz,java.util.*"%>
	<%request.setCharacterEncoding("UTF-8");%>
	<jsp:include page="test-header.jsp"></jsp:include>
    <main role="main">
    	<div class="container-fluid mt-dashbroad">
		  <div class="row">
		    <jsp:include page="test-dashboard.jsp"></jsp:include>
		    <div class="col-sm-9">
		    	<h2>Sửa câu hỏi</h2>
		    	<hr>
		    	<c:if test="${errors!=null && errors.size()>0}">
			    	<c:forEach items="${errors}" var="error" >
			    		<center><h5 style="color:red">Error: ${error}</h5></center>
			    	</c:forEach>
		    	</c:if>	
		    	  <form name="formSubject" id="formSubject" 
		    		action="quiz?command=update&id=${quiz.id}" 
		    		method="POST">
		    	  <input type="hidden" id="quizId" name="quizId" value="${quiz.id }">
				  <div class="form-group">
				      <label for="inputPassword4">Phần môn học</label>
				      <select name="sectionId" class="form-control" id="selectSection">
				      	<c:forEach items="${listSubject}" var="subject" >
				      		<optgroup label="${subject.name}">
				      			<c:forEach items="${listSection}" var="section" >
				      				<c:if test="${section.subjectId == subject.id }" >
				      					<option value="${section.id}" 
				      						${section.id == quiz.sectionId?'selected':''}>${section.name}
				      					</option>
				      				</c:if>	
					      		</c:forEach>
				      		</optgroup>
				      	</c:forEach>      
					  </select>
				   </div>
				  <div class="form-group">
					    <label for="exampleFormControlFile1">Chọn hình ảnh đính kèm</label>
					    <input type="file" class="form-control-file" id="quizImage">
				  </div>
				  <div class="form-group">
				  	<label for="inputAddress">Mức độ câu hỏi</label>
				  	<select name="level" class="form-control" id="selectLevel">
				  		<option value="easy" ${quiz.level.equals('easy')?'selected':''}>Easy</option>
				  		<option value="medium" ${quiz.level.equals('medium')?'selected':''}>Medium</option>
				  		<option value="hard" ${quiz.level.equals('hard')?'selected':''}>Hard</option>
				  	</select>
				  </div>
				  <div class="form-group">
				    <label for="inputAddress">Nội dung câu hỏi</label>
				    <textarea name="quizContent" class="form-control"
				    id="quizContent" rows="3">${quiz.content}</textarea>
				  </div>
				  <div class="form-group">
				  	<input type="hidden" name="correctAnswerId" value="${correctAnswer.id}">
				    <label for="inputAddress2">Câu trả lời đúng</label>
				    <textarea name="quizCorrectAnswer" class="form-control" 
				    id="quizCorrectAnswer" rows="2">${correctAnswer.content}</textarea>
				  </div>
				  <div class="form-group">
				  	<input type="hidden" name="answer1Id" value="${listWrongAnswer[0].id}">
				    <label for="inputAddress2">Câu trả lời sai 1</label>
				    <textarea name="quizAnswer1" class="form-control" 
				    id="quizAnswer1" rows="2">${listWrongAnswer[0].content}</textarea>
				  </div>
				  <div class="form-group">
				  	<input type="hidden" name="answer2Id" value="${listWrongAnswer[1].id}">
				    <label for="inputAddress2">Câu trả lời sai 2</label>
				    <textarea name="quizAnswer2" class="form-control" 
				    id="quizAnswer2" rows="2">${listWrongAnswer[1].content}</textarea>
				  </div>
				 <div class="form-group">
				 	<input type="hidden" name="answer3Id" value="${listWrongAnswer[2].id}">
				    <label for="inputAddress2">Câu trả lời sai 3</label>
				    <textarea name="quizAnswer3" class="form-control" 
				    id="quizAnswer3" rows="2">${listWrongAnswer[2].content}</textarea>
				  </div>
				  <button type="submit" class="btn btn-primary btn-lg btn-block">
				  	Sửa câu hỏi</button>
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