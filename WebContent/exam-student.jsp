<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.vy.dao.QuizDAO"%>
<%@page import="com.vy.dao.SubjectDAO,java.util.*"%>
<%@page import="com.vy.dao.SectionDAO"%>
<%@page import="com.vy.dao.TestDAO"%>
<%@page import="com.vy.model.Subject" %>
<%@page import="com.vy.model.Section" %>
<%@page import="com.vy.model.Quiz" %>
<%@page import="com.vy.model.Test" %>
<%@page import="com.vy.model.Answer" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
<script type="text/javascript" src="./js/countdown.js"></script>
</head>
<body>
	<jsp:include page="test-header.jsp"></jsp:include>
    <main role="main">
    	<div class="container-fluid mt-dashbroad">
		  <form class="row" action="exam?command=result&id=${test.id}" method="POST">
		    <div class="col-md-3 mh-dashbroad">
					<div class="list-group">
						<div class="list-group-item list-group-item-action active">Mã đề thi: ${test.id }</div>
						<div class="list-group-item">
						<p>Môn: <br>
							Tên đề thi: ${test.name } <br>
							Thời lượng: ${test.timeLimit } &nbsp phút<br>
							Số câu hỏi: ${test.numQuiz }<br>
							Thời gian bắt đầu: ${test.dateOpen} &nbsp ${test.timeOpen }<br>
							Thời gian kết thúc: ${test.dateClose} &nbsp ${test.timeClose }<br>
							Thời gian còn lại:</p>
							<input type="hidden" id="timeLimit" value="${test.timeLimit}">
							<div class="row" style="margin-left:25px">
							  <div id="hour" class="col-sm-3 countdown-clock"></div>
							  <div id="minute" class="col-sm-3 countdown-clock"></div>
							  <div id="second" class="col-sm-3 countdown-clock"></div>
							</div>
							<div style="margin:30px">
								 <c:forEach var = "i" begin = "1" end = "${test.numQuiz}">
									<div class="float-left quiz-box"><a href="#">${i}</a></div>
								</c:forEach>
							</div>
						</div>
						<br>
						<button type="submit" class="btn btn-primary btn-lg btn-block">Nộp bài</button>
					</div>
				</div>
		     <div class="col-md-9 form-exam">
		    	<h2>Đề: ${test.name}</h2>
		    	<hr>
		    	<br>
		    	<!-- List question -->
		    	<%List<Quiz> listQuiz = (ArrayList)request.getAttribute("listQuiz"); %>
		    	<%List<Answer> listAnswer = (ArrayList)request.getAttribute("listAnswer"); %>
		    	<div>
		    		<%for(int i=0,k=0; i < listQuiz.size() ;i++,k+=4){ %>
		    			<p><b>Câu hỏi <%=i+1 %>: &nbsp</b><%=listQuiz.get(i).getContent() %></p>
				    	<div class="form-check">
						  <label class="form-check-label">
						    <input type="radio" class="form-check-input" name="optradio<%=listQuiz.get(i).getId() %>" 
						    value="<%=listAnswer.get(k).getId() %>"><b>A.</b><%=listAnswer.get(k).getContent() %>
						  </label>
						</div>
						<div class="form-check">
						  <label class="form-check-label">
						    <input type="radio" class="form-check-input" name="optradio<%=listQuiz.get(i).getId() %>"
						    value="<%=listAnswer.get(k+1).getId() %>"><b>B.</b><%=listAnswer.get(k+1).getContent() %>
						  </label>
						</div>
						<div class="form-check">
						  <label class="form-check-label">
						    <input type="radio" class="form-check-input" name="optradio<%=listQuiz.get(i).getId() %>"
						    value="<%=listAnswer.get(k+2).getId() %>"><b>C.</b><%=listAnswer.get(k+2).getContent() %>
						  </label>
						</div>
						<div class="form-check">
						  <label class="form-check-label">
						    <input type="radio" class="form-check-input" name="optradio<%=listQuiz.get(i).getId() %>"
						    value="<%=listAnswer.get(k+3).getId() %>"><b>D.</b><%=listAnswer.get(k+3).getContent() %>
						  </label>
						</div>
						<hr>
					<%} %>
		    	</div>
		    	<!-- end list question -->
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
		  </form>
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
			        <button type="button" class="btn btn-danger" data-dismiss="modal">Delete</button>
			      </div>
			    </div>
			  </div>
			</div>
		</div>
    </main>
</body>
</html>