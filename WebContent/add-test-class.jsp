<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.vy.dao.SubjectDAO,java.util.*"%>
<%@page import="com.vy.dao.LopHoc_DAO"%>
<%@page import="com.vy.dao.TestDAO"%>
<%@page import="com.vy.model.LopHoc" %>
<%@page import="com.vy.model.Test" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<%request.setCharacterEncoding("UTF-8");%>
	<%
	List<Test> listTest = TestDAO.getAllTests();
	List<LopHoc> listClass = LopHoc_DAO.LoadDanhSachLopHoc();
	%>
	<jsp:include page="test-header.jsp"></jsp:include>
    <main role="main">
    	<div class="container-fluid mt-dashbroad">
		  <div class="row">
		    <jsp:include page="test-dashboard.jsp"></jsp:include>
		    <div class="col-md-9">
		    	<h2>Gán đề thi vào lớp học</h2>
		    	<hr>
		    	<c:if test="${errors!=null && errors.size()>0}">
			    	<c:forEach items="${errors}" var="error" >
			    		<center><h5 style="color:red">Error: ${error}</h5></center>
			    	</c:forEach>
		    	</c:if>	
		    	<form action="test?command=add-test-class" method="POST">
				  <div class="form-row">
				  	<div class="form-group col-md-4">
				      <label for="inputEmail4">Chọn Lớp học</label>
				      <select class="form-control" name="selectClass">
				      <%for(LopHoc lh : listClass){ %>
				      	<option value=<%=lh.getClass_id() %>><%=lh.getClass_name() %></option>
				      <%} %>
					  </select>
				    </div>
				    <input type="hidden" name="addSection" value="addSection">
				    <div class="form-group col-md-4">
				      <label for="inputPassword4">Chọn đề thi</label>
				      <select class="form-control" name="selectTest">
					  <%for(Test test : listTest){ %>
				      	<option value=<%=test.getId() %>><%=test.getName() %></option>
				      <%} %>
					  </select>
				    </div>
				    <div class="form-group col-md-4">
				      <label for="inputPassword4">&nbsp</label>
				     <button type="submit" class="btn btn-primary btn-block">Thêm</button>
				    </div>
				  </form>
				  </div>
				  <table class="table table-striped">
				  <thead class="thead-light">
				    <tr>
				      <th scope="col">Lớp học</th>
				      <th scope="col">Đề thi</th>
				      <th scope="col">Xóa</th>
				    </tr>
				  </thead>
				  <tbody>
				  <%for(LopHoc lh : listClass){ 
					  List<Test> listTest2 = TestDAO.getTestsByClassId(lh.getClass_id());
					  for(Test test : listTest2){
				  %>
				  <tr>
				      <td><%=lh.getClass_name()%></td>
				      <td><%=test.getName()%></td>
				      <td><a href="/ThiTracNghiem/test?command=delete-test-class&classId=<%=lh.getClass_id()%>&testId=<%=test.getId()%>">
				      <i class="fa fa-trash-alt"></i></a></td>
				    </tr>
				  	<%}
					} %>
				  </tbody>
				</table>
		    </div>
		  </div>
		</div>	
		</div>
    </main>
</body>
</html>