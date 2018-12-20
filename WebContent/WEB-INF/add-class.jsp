p<%@ page language="java" contentType="text/html; charset=UTF-8"
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
				<h2>Tạo lớp</h2>
				<hr>
				<form action="${pageContext.request.contextPath}/ListLopHoc/ThemLopHoc" method="POST">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail4">Tên lớp học</label> <input type="text"
								class="form-control" name="txtCLassName">
						</div>
						<div class="form-group col-md-6">
							<label for="inputPassword4">Mã lớp học</label> <input type="text"
								class="form-control" name="txtClassId" readonly="readonly">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail4">Giáo viên</label> <select
								class="form-control" name="teacherId">
								<c:forEach items="${dsGiaoVien}" var="gv">
									<option value="${gv.giaovien_id}">${gv.fullname}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-md-6">
							<label for="inputEmail4">Môn học</label> <select
								class="form-control" name="subjectId">
								<c:forEach items="${dsMonHoc}" var="monhoc">
								<option value="${monhoc.subject_id}">${monhoc.subject_name}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<button type="submit" class="btn btn-primary btn-lg btn-block">Tạo
						lớp học</button>
					<a class="btn btn-secondary btn-block"
						href="${pageContext.request.contextPath}/ListLopHoc?page=1" role="button">Thoát</a>
				</form>
			</div>
		</div>
	</div>
	</main>
</body>
</html>