<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<%@page import="com.vy.dao.ExamineeDAO,java.util.*"%>
	<%request.setCharacterEncoding("UTF-8");%>
	<jsp:include page="test-header.jsp"></jsp:include>
    <main role="main">
    	<div class="container-fluid mt-dashbroad">
		  <div class="row">
		    <jsp:include page="test-dashboard.jsp">
		    	<jsp:param name="dashbroadContent" value="${dashbroadContent}" />
		    </jsp:include>
		    <div class="col-md-9">
		    	<a class="btn btn-primary float-right" href="/ThiTracNghiem/add-student.jsp" role="button">Thêm thí sinh</a>
		    	<h2>Danh sách thí sinh</h2>
		    	<hr>
		    	<table class="table table-striped">
				  <thead class="thead-light">
				    <tr>
				      <th scope="col">STT</th>
				      <th scope="col">Số báo danh</th>
				      <th scope="col">Họ tên</th>
				      <th scope="col">Xem</th>
				      <th scope="col">Sửa</th>
				      <th scope="col">Xóa</th>
				    </tr>
				  </thead>
				  <tbody>
				  <c:forEach items="${listExaminee}" var="ex" varStatus="loop">
				    <tr>
				      <th scope="row">${loop.index+1}</th>
				      <td>${ex.id}</td>
				      <td>${ex.username}</td>
				      <td><a href="/ThiTracNghiem/student-detail.jsp"><i class="far fa-eye"></i></a></td>
				      <td><a href="/ThiTracNghiem/add-student.jsp"><i class="fas fa-edit"></i></a></td>
				      <td><a href="#" data-toggle="modal" data-target="#exampleModalCenter"><i class="fas fa-trash-alt"></i></a></td>
				    </tr>
				   </c:forEach>
				  </tbody>
				</table><br>
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
			        Bạn có chắc chắn muốn xóa sinh viên?
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