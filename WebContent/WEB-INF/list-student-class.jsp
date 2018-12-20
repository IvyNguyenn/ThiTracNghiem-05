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
				<a class="btn btn-primary float-right" href="${pageContext.request.contextPath}/ListLopHoc/AddThiSinh-To-LopHoc"
					role="button">Thêm sinh viên</a>
				<h2>Danh sách thành viên</h2>
				<hr>
				<table class="table table-striped">
					<thead class="thead-light">
						<tr>
							<th scope="col">STT</th>
							<th scope="col">Mã thí sinh</th>
							<th scope="col">Họ tên</th>
							<th scope="col">Xóa</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${dsSinhVien}" var="sv" varStatus="status">
							<tr>
								<th scope="row">${status.index+1 }</th>
								<th>${sv.examinee_id}</th>
								<td>${sv.fullname}</td>
								<td><a href = "#" onclick="btnXoa(${sv.examinee_id},${classId})"><i class="fas fa-trash-alt"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
				<nav aria-label="Page navigation example" class="float-right">
					<ul class="pagination">
						<li class="page-item"><a class="page-link" href="#"
							aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
								<span class="sr-only">Previous</span>
						</a></li>

						<c:forEach begin='1' end='${max_page}' varStatus="status">
							<li class="page-item "><a class="page-link"
								href="${pageContext.request.contextPath}/ListThiSinh?page=${status.index }">${status.index}</a></li>
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
		function btnXoa(a,b) {
			var t = window.confirm("Bạn có chắc chắn xóa không ?");
			if (t == true)
				window.location.href = "${pageContext.request.contextPath}/ListThiSinh/XoaThiSinh?examineeId="+a+"&classId="+b;

		}
	</script> 
	</main>

</body>
</html>