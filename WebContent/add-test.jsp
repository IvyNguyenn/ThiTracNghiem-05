<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.vy.dao.SubjectDAO,java.util.*"%>
<%@page import="com.vy.dao.SectionDAO"%>
<%@page import="com.vy.model.Subject" %>
<%@page import="com.vy.model.Section" %>
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
	List<Section> selectedSections = new ArrayList<Section>();
	%>
	<jsp:include page="test-header.jsp"></jsp:include>
    <main role="main">
    	<div class="container-fluid mt-dashbroad">
		  <div class="row">
		    <jsp:include page="test-dashboard.jsp"></jsp:include>
		    <div class="col-md-9">
		    	<h2>Tạo đề thi</h2>
		    	<hr>
		    	<c:if test="${errors!=null && errors.size()>0}">
			    	<c:forEach items="${errors}" var="error" >
			    		<center><h5 style="color:red">Error: ${error}</h5></center>
			    	</c:forEach>
		    	</c:if>	
		    	<form action="test?command=add" method="POST">
				  <div class="form-row">
				    <input type="hidden" name="addSection" value="addSection">
				    <div class="form-group col-md-4">
				      <label for="inputPassword4">Phần môn học</label>
				      <select name="selectSection" class="form-control" id="selectSection"> 
				      	<%for(Subject subject : listSubject ) {%>
				      		<optgroup label="<%=subject.getName()%>">
				      			<%for(Section section : listSection){ %>
				      				<%if(section.getSubjectId() == subject.getId()){ %>
				      					<option value="<%=section.getId()%>">
				      						<%=section.getName() %>
				      					</option>
				      				<%} %>
					      		<%} %>
				      		</optgroup>
				      	<%} %>
					  </select>
				    </div>
				    <div class="form-group col-md-4">
				      <label for="inputPassword4">&nbsp</label>
				     <button type="submit" class="btn btn-primary btn-block">Thêm phần học</button>
				    </div>
				  </form>
				  </div>
				  <form action="test?command=add" method="POST">
					  <table class="table table-striped">
					  <thead class="thead-light">
					    <tr>
					      <th scope="col">STT</th>
					      <th scope="col">Tên Phần</th>
					      <th scope="col">Số câu</th>
					      <th scope="col">Xóa</th>
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach items="${selectedSections}" var="section" varStatus="loop">
					  		<tr>
						      <th scope="row">${loop.index+1}</th>
						      <td>${section.sectionName}</td>
						      <td><input type="number" name="numQuiz${section.sectionId}" step="1"  min="1" max="500" value="1"></td>
						      <td><a href="test?command=deleteSection&id=${section.sectionId}"><i class="fa fa-trash-alt"></i></a></td>
						    </tr>
					  	</c:forEach>
					  </tbody>
					  </table>
				  
					  <div class="form-group row">
					    <label for="inputEmail3" class="col-sm-2 col-form-label">Tên đề thi:</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" name="testName">
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="inputEmail3" class="col-sm-2 col-form-label">Thời lượng thi (phút):</label>
					    <div class="col-sm-10">
					      <input type="number" class="form-control" name="timeLimit" min="5" value="5">
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="inputEmail3" class="col-sm-2 col-form-label">Thời gian bắt đầu:</label>
					    <div class="col-sm-5">
					      <input type="date" name="dateOpen" max="3000-12-31" min="1000-01-01" class="form-control">
					    </div>
					    <div class="col-sm-5">
					      <input type="time" name="timeOpen" max="23:59" min="00:00" class="form-control">
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="inputEmail3" class="col-sm-2 col-form-label">Thời gian kết thúc:</label>
					    <div class="col-sm-5">
					      <input type="date" name="dateClose" max="3000-12-31" min="1000-01-01" class="form-control">
					    </div>
					    <div class="col-sm-5">
					      <input type="time" name="timeClose" max="23:59" min="00:00" class="form-control">
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="inputEmail3" class="col-sm-2 col-form-label">Password:</label>
					    <div class="col-sm-10">
					      <input type="password" class="form-control" name="password">
					    </div>
					  </div>
					  <div class="form-group row">
					    <label for="inputEmail3" class="col-sm-2 col-form-label">Số lần làm tối đa:</label>
					    <div class="col-sm-10">
					      <input type="number" name="maxSubmit" step="1"  min="1" max="500" value="1" class="form-control">
					    </div>
					  </div>
					  <button type="submit" class="btn btn-primary btn-block">Tạo đề thi</button>
					  <a class="btn btn-secondary btn-block" href="/ThiTracNghiem/test" role="button">Thoát</a>
				</form>
		    </div>
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
			        Bạn có chắc chắn muốn xóa phần câu hỏi?
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			        <button type="button" class="btn btn-danger" data-dismiss="modal">Delete</button>
			      </div>
			    </div>
			  </div>
			</div>
			
			
			<!-- ---------------------------------------------------------------------------------- -->
			
			
			
			
			<!-- ---------------------------------------------------------------------------------- -->
			
		</div>
    </main>
</body>
</html>