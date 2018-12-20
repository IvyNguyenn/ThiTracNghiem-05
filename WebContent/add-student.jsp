<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="test-header.jsp"></jsp:include>
    <main role="main">
    	<div class="container-fluid mt-dashbroad">
		  <div class="row">
		    <jsp:include page="test-dashboard.jsp"></jsp:include>
		    <div class="col-sm-9">
		    	<h2>Thêm sinh viên</h2>
		    	<hr>
		    	<form>
				  <div class="form-row">
				    <div class="form-group col-md-3">
				    	<img class="img-fluid mx-auto" src="images/default-avatar.png" alt="Generic placeholder image" width=200px height= 200px>
				    	<input type="file" class="form-control-file" id="exampleFormControlFile1">
				    </div>
				    <div class="form-group col-md-9">
				    	<div class="form-group row">
						    <label for="inputEmail3" class="col-sm-2 col-form-label">Họ tên</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="inputEmail3">
						    </div>
					  	</div>
					  	<div class="form-group row">
						    <label for="inputEmail3" class="col-sm-2 col-form-label">Mã số sv</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="inputEmail3">
						    </div>
					  	</div>
					  	<div class="form-group row">
						    <label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
						    <div class="col-sm-10">
						      <input type="email" class="form-control" id="inputEmail3">
						    </div>
					  	</div>
					  	<div class="form-group row">
						    <label for="inputEmail3" class="col-sm-2 col-form-label">Số điện thoại</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" id="inputEmail3">
						    </div>
					  	</div>
					  	<div class="form-group row">
						    <label for="inputEmail3" class="col-sm-2 col-form-label">Năm sinh</label>
						    <div class="col-sm-10">
						      <input type="date" name="bday" max="3000-12-31" min="1000-01-01" class="form-control" id="inputEmail3">
						    </div>
					  	</div>
				    </div>
				  </div>
				  <h5>Danh sách đăng kí lớp học</h5>
			    	<hr>
			    	<table class="table table-striped">
					  <thead class="thead-light">
					    <tr>
					      <th scope="col">STT</th>
					      <th scope="col">Mã lớp học</th>
					      <th scope="col">Tên lớp học</th>
					      <th scope="col">Số lượng sinh viên</th>
					      <th scope="col">Thời gian bắt đầu</th>
					      <th scope="col">Thời gian kết thúc</th>
					      <th scope="col">Đăng kí</th>
					    </tr>
					  </thead>
					  <tbody>
					    <tr>
					      <th scope="row">1</th>
					      <td>MATH01</td>
					      <td>Toán 1</td>
					      <td>100</td>
					      <td>20-10-2018 14:15</td>
					      <td>22-10-2018 14:15</td>
					      <td>
					      	<div class="form-group form-check text-center">
							    <input type="checkbox" class="form-check-input" id="exampleCheck1">
							</div>
					      </td>
					    </tr>
						<tr>
					      <th scope="row">2</th>
					      <td>MATH02</td>
					      <td>Toán 2</td>
					      <td>100</td>
					      <td>20-10-2018 14:15</td>
					      <td>22-10-2018 14:15</td>
					      <td>
					      	<div class="form-group form-check text-center ">
							    <input type="checkbox" class="form-check-input" id="exampleCheck1">
							</div>
					      </td>
					    </tr>
					    <tr>
					      <th scope="row">3</th>
					      <td>ENGL01</td>
					      <td>Anh Văn 1</td>
					      <td>100</td>
					      <td>20-10-2018 14:15</td>
					      <td>22-10-2018 14:15</td>
					      <td>
					      	<div class="form-group form-check text-center">
							    <input type="checkbox" class="form-check-input" id="exampleCheck1">
							</div>
					      </td>
					    </tr>
					    <tr>
					      <th scope="row">4</th>
					      <td>ENGL02</td>
					      <td>Anh Văn 2</td>
					      <td>200</td>
					      <td>20-10-2018 14:15</td>
					      <td>22-10-2018 14:15</td>
					      <td>
					      	<div class="form-group form-check text-center">
							    <input type="checkbox" class="form-check-input" id="exampleCheck1">
							</div>
					      </td>
					    </tr>
					  </tbody>
					</table>
				  
				  
				  
				  <button type="submit" class="btn btn-primary btn-lg btn-block">Thêm sinh viên</button>
				  <a class="btn btn-secondary btn-block" href="/ThiTracNghiem/list-student.jsp" role="button">Thoát</a>
				</form>
		    </div>
		  </div>
	</div>
    </main>
</body>
</html>