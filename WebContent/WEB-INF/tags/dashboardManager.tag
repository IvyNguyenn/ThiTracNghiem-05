<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ tag description="Dashboard" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ attribute name="dashbroadContent" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../header.jsp"></jsp:include>

</head>
<body>
	<div class="col-md-3 mh-dashbroad">
		<div class="list-group nav">
			<a href="/ThiTracNghiem/list-question.jsp" class="list-group-item list-group-item-action">
				<i class="fas fa-quidditch"></i> Quản lý câu hỏi
			</a>
			<a href="/ThiTracNghiem/list-test.jsp" class="list-group-item list-group-item-action">
				<i class="fas fa-list-alt"></i> Quản lý đề thi
			</a>
			<a href="/ThiTracNghiem/list-class.jsp" class="list-group-item list-group-item-action">
				<i class="fas fa-users"></i> Quản lý lớp học
			</a>
			<c:choose>
				<c:when test="${dashbroadContent.equal("examinee-manager")}">
					<a href="/ThiTracNghiem/list-student.jsp" class="list-group-item list-group-item-action active">
						<i class="fas fa-address-card"></i> Quản lý thí sinh
					</a>
				</c:when>
				<c:otherwise>
					<a href="/ThiTracNghiem/list-student.jsp" class="list-group-item list-group-item-action">
						<i class="fas fa-address-card"></i> Quản lý thí sinh
					</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>
