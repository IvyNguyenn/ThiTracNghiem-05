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
			<div class="col-md-9">
				<h2>Danh sách thí sinh</h2>
				<hr>
				<form
					action="${pageContext.request.contextPath}/ListLopHoc/AddThiSinh-To-LopHoc"
					method="post">
					<table class="table table-striped">
						<thead class="thead-light">
							<tr>
								<th scope="col">STT</th>
								<th scope="col">Mã thí sinh</th>
								<th scope="col">Họ tên</th>
								<th scope="col">Thêm</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${dsThiSinh}" var="thisinh" varStatus="status">
								<tr>
									<th scope="row">${status.index+1}</th>
									<td>${thisinh.examinee_id}</td>
									<td>${thisinh.fullname}</td>
									<td>
										<div class="form-group form-check text-center">
											<input type="checkbox" class="form-check-input"
												name="ExamineeId" value="${thisinh.examinee_id}">
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<button type="submit" class="btn btn-primary btn-lg btn-block">Thêm
						vào lớp học</button>
					<a class="btn btn-secondary btn-block"
						href="${pageContext.request.contextPath}/ListLopHoc?page=1" role="button">Thoát</a>
				</form>
				<br>
				<nav aria-label="Page navigation example" class="float-right">
					<ul class="pagination">
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								<span class="sr-only">Previous</span>
						</a></li>
						<c:forEach begin='1' end='${max_page}' varStatus="status">
							<li class="page-item "><a class="page-link"
								href="${pageContext.request.contextPath}/ListLopHoc?page=${status.index }">${status.index}</a></li>
						</c:forEach>
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
								class="sr-only">Next</span>
						</a></li>
					</ul>
				</nav>
			</div>
		</div>

	</div>
	<script>
		function btnXoa(g) {
			var t = window
					.confirm("Bạn có chắc chắn xóa không ?\nKhi xóa sẽ xóa mọi thí sinh thuộc lớp học");
			if (t == true)
				window.location.href = "DeleteExaminee?id=" + g;

		}
	</script> </main>
</body>
</html>