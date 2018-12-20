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
				<a class="btn btn-primary float-right"
					href="${pageContext.request.contextPath}/ListLopHoc/ThemLopHoc" role="button">Thêm
					lớp học</a>
				<h2>Danh sách lớp học</h2>
				<hr>
				<table class="table table-striped">
					<thead class="thead-light">
						<tr>
							<th scope="col">STT</th>
							<th scope="col">Mã lớp học</th>
							<th scope="col">Tên lớp học</th>
							<th scope="col">Tên môn học</th>
							<th scope="col">Giáo viên</th>
							<th scope="col">Xem thành viên</th>
							<th scope="col">Sửa</th>
							<th scope="col">Xóa</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${dsLopHoc}" var="lophoc" varStatus="status">
							<tr>
								<th scope="row">${status.index+1}</th>
								<td>${lophoc.class_id}</td>
								<td>${lophoc.class_name}</td>
								<td>${lophoc.subject_name}</td>
								<td>${lophoc.teacher_name}</td>
								<td><a
									href="${pageContext.request.contextPath}/ListLopHoc/DanhSachThiSinh-LopHoc?classId=${lophoc.class_id}"><i
										class="far fa-eye"></i></a></td>
								<td><a href="${pageContext.request.contextPath}/ListLopHoc/EditLopHoc?classId=${lophoc.class_id}"><i
										class="fas fa-edit"></i></a></td>
								<td><a href="#" onclick="btnXoa(${lophoc.class_id})"><i
										class="fas fa-trash-alt"></i></a></td>
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
				window.location.href = "${pageContext.request.contextPath}/ListLopHoc/XoaLopHoc?classId=" + g;

		}
	</script> </main>
</body>
</html>