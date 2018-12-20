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
		    <div class="col-md-9">
		    	<a class="btn btn-primary float-right" 
		    	href="/ThiTracNghiem/quiz?command=add" role="button">Tạo câu hỏi</a>
		    	<h2>Danh sách câu hỏi</h2>
		    	<hr>
		    	<c:if test="${notification!=null}">
			    	<center><h5 style="color:green">Success: ${notification}</h5></center>
		    	</c:if>
		    	<form>
		    	<table class="table table-striped">
				  <thead class="thead-light">
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">Môn học</th>
				      <th scope="col">Nội dung</th>
				      <th scope="col">Level</th>
				      <th scope="col">Xem</th>
				      <th scope="col">Sửa</th>
				      <th scope="col">Xóa</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%for(Quiz quiz:listQuiz){ %>
				  	<tr>
				      <th scope="row"><%=quiz.getId() %></th>
				      <td><%=SubjectDAO.getSubjectByQuizId(quiz.getId()).getName()%></td>
				      <td><%=quiz.getContent() %></td>
				      <td><%=quiz.getLevel() %></td>
				      <td><a href="/ThiTracNghiem/quiz?command=update&id=<%=quiz.getId()%>"><i class="far fa-eye"></i></a></td>
				      <td><a href="/ThiTracNghiem/quiz?command=update&id=<%=quiz.getId()%>"><i class="fa fa-edit"></i></a></td>
				      <td><a href="/ThiTracNghiem/quiz?command=delete&id=<%=quiz.getId()%>"><i class="fa fa-trash-alt"></i></a></td>
				    </tr>
				  <%} %>
				  </tbody>
				</table>
				</form>
				<br>
				<nav aria-label="Page navigation example" class="float-right">
				  <ul class="pagination">
				    <li class="page-item">
				      <a class="page-link" href="#" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				        <span class="sr-only">Previous</span>
				      </a>
				    </li>
				    <li class="page-item active"><a class="page-link" href="#">1</a></li>
				    <li class="page-item"><a class="page-link" href="#">2</a></li>
				    <li class="page-item"><a class="page-link" href="#">3</a></li>
				    <li class="page-item">
				      <a class="page-link" href="#" aria-label="Next">
				        <span aria-hidden="true">&raquo;</span>
				        <span class="sr-only">Next</span>
				      </a>
				    </li>
				  </ul>
				</nav>
		    </div>
		  </div>
		  <!-- Modal -->
			<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalCenterTitle">Delete</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        Bạn có chắc chắn muốn xóa câu hỏi?
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        <a href="#" type="button" class="btn btn-danger" data-dismiss="modal">Delete</a>
			      </div>
			    </div>
			  </div>
			</div>
		</div>
    </main>
</body>
</html>