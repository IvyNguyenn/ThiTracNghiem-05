<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
<link href="./css/admin.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="test-header.jsp"></jsp:include>
	<main role="main">
	<div class="container-fluid mt-dashbroad">
		<div class="row">
			<jsp:include page="test-dashboard.jsp"></jsp:include>
			<div class="col-sm-9">
				<h2>Sửa thông tin</h2>
				<hr>
				<form action="${pageContext.request.contextPath}/ListThiSinh/EditThiSinh" method="post">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputPassword4">Mã số sinh viên</label> <input
								type="text" class="form-control" readonly="readonly"
								value="${ThiSinhInfor.examinee_id}" name="txtExamineeId">
						</div>
						<div class="form-group col-md-6">
							<label for="inputEmail4">Họ tên</label> <input type="text"
								class="form-control" name="txtFullName"
								value="${ThiSinhInfor.fullname}" >
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputPassword4">UserName</label> <input type="text"
								class="form-control" name="txtUserName"
								placeholder="Nhập UserName...." value="${ThiSinhInfor.username}" readonly="readonly">
						</div>
						<div class="form-group col-md-6">
							<label for="inputPassword4">Password</label> <input type="text"
								class="form-control" name="txtPassword"
								placeholder="Nhập Password..." value="${ThiSinhInfor.password}">
						</div>
					</div>

					<h5>Danh sách lớp đã đăng ký</h5>
					<hr>
					<table class="table table-striped">
						<thead class="thead-light">
							<tr>
								<th scope="col">STT</th>
								<th scope="col">Mã lớp học</th>
								<th scope="col">Tên lớp học</th>
								<th scope="col">Xoá</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${dsLopHoc}" var="lophoc" varStatus="status">
								<tr>
									<th scope="row">${status.index+1}</th>
									<td>${lophoc.class_id}</td>
									<td>${lophoc.class_name}</td>
									<td>
										<div class="form-group form-check text-center">
											<input type="checkbox" class="form-check-input"
												name="ClassId" value="${lophoc.class_id}">
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<button type="submit" class="btn btn-primary btn-lg btn-block">Chấp nhận</button>
					<a class="btn btn-secondary btn-block"
						href="${pageContext.request.contextPath}/ListThiSinh?page=1" role="button">Thoát</a>
				</form>
			</div>
		</div>
	</div>
	</main>
</body>
</html>