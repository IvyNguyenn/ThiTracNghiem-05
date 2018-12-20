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
	<div class="col-md-3 mh-dashbroad">
		<div class="list-group">
			<a href="/ThiTracNghiem/list-question.jsp" class="list-group-item list-group-item-action active">Quản lý câu hỏi</a>
			<a href="${pageContext.request.contextPath}/QuanLyDeThi" class="list-group-item list-group-item-action">Quản lý đề thi</a>
			<a href="${pageContext.request.contextPath}/ListLopHoc?page=1" class="list-group-item list-group-item-action">Quản lý lớp học</a>
			<a href="${pageContext.request.contextPath}/ListThiSinh?page=1" class="list-group-item list-group-item-action">Quản lý sinh viên</a>
		</div>
	</div>
</body>
</html>