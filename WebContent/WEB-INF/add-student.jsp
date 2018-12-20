<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/css/admin.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="test-header.jsp"></jsp:include>
	<main role="main">
	<div class="container-fluid mt-dashbroad">
		<div class="row">
			<jsp:include page="test-dashboard.jsp"></jsp:include>
			<div class="col-sm-9">
				<h2>Thêm thí sinh</h2>
				<hr>
				<form action="${pageContext.request.contextPath}/ListThiSinh/ThemThiSinh" method="POST">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputPassword4">Mã số sinh viên</label> <input
								type="text" class="form-control" readonly="readonly">
						</div>
						<div class="form-group col-md-6">
							<label for="inputEmail4">Họ tên</label> <input type="text"
								class="form-control" name="txtFullName">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputPassword4">UserName</label> <input type="text"
								class="form-control" name="txtUserName"
								placeholder="Nhập UserName....">
						</div>
						<div class="form-group col-md-6">
							<label for="inputPassword4">Password</label> <input type="text"
								class="form-control" name="txtPassword"
								placeholder="Nhập Password...">
						</div>
					</div>
					<button type="submit" class="btn btn-primary btn-lg btn-block">Thêm
						sinh viên</button>
					<a class="btn btn-secondary btn-block"
						href="${pageContext.request.contextPath}/ListThiSinh?page=1" role="button">Thoát</a>
				</form>
			</div>
		</div>
	</div>
	</main>
</body>
</html>