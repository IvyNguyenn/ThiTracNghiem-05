p<%@ page language="java" contentType="text/html; charset=UTF-8"
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
				<h2>Sửa thông tin lớp học</h2>
				<hr>
				<form action="${pageContext.request.contextPath}/ListLopHoc/EditLopHoc" method="POST">
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputPassword4">Mã lớp học</label> <input type="text"
								class="form-control" name="txtClassId"
								value="${ClassInfor.class_id}" readonly="readonly">
						</div>
						<div class="form-group col-md-6">
							<label for="inputEmail4">Tên lớp học</label> <input type="text"
								class="form-control" value="${ClassInfor.class_name}"
								name="txtCLassName">
						</div>

					</div>
					<div class="form-row">
						<div class="form-group col-md-6">
							<label for="inputEmail4">Giáo viên</label> <select
								class="form-control" name="teacherId">
								<c:forEach items="${dsGiaoVien}" var="gv">
									<c:choose>
										<c:when test="${gv.giaovien_id == ClassInfor.teacher_id}">
											<option value="${gv.giaovien_id}" selected="selected">${gv.fullname}</option>
										</c:when>
										<c:otherwise>
											<option value="${gv.giaovien_id}">${gv.fullname}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-md-6">
							<label for="inputEmail4">Môn học</label> <select
								class="form-control" name="subjectId">
								<c:forEach items="${dsMonHoc}" var="monhoc">
									<c:choose>
										<c:when test="${monhoc.subject_id == ClassInfor.subject_id }">
											<option value="${monhoc.subject_id}" selected="selected">${monhoc.subject_name}</option>
										</c:when>
										<c:otherwise>
											<option value="${monhoc.subject_id}">${monhoc.subject_name}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>

					<button type="submit" class="btn btn-primary btn-lg btn-block">Chấp nhận</button>
					<a class="btn btn-secondary btn-block"
						href="${pageContext.request.contextPath}/ListLopHoc?page=1"
						role="button">Thoát</a>
				</form>
			</div>
		</div>
	</div>
	</main>
</body>
</html>