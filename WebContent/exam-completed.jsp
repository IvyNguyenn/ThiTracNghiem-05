<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<jsp:include page="test-header.jsp"></jsp:include>
	<main role="main">		
		<div class="container-fluid mt-dashbroad">
			<div class="row">
				<div class="col-md-3 mh-dashbroad">
					<div class="list-group">
						<div class="list-group-item list-group-item-action active">Mã đề thi ${test.id }</div>
						<div class="list-group-item list-group-item-action">
							<p>
							Tên đề thi: ${test.name } <br>
							Thời lượng: ${test.timeLimit } &nbsp phút<br>
							Số câu hỏi: ${test.numQuiz }<br>
							Thời gian bắt đầu: ${test.dateOpen} &nbsp ${test.timeOpen }<br>
							Thời gian kết thúc: ${test.dateClose} &nbsp ${test.timeClose }</p>
						</div>
					</div>
					<button type="button" class="btn btn-primary btn-lg btn-block">Trở về</button>
				</div>
				<div class="col-md-9">
					<h2>Đề: ${test.name}</h2>
					<hr>
					
					<h3 class="text-justify text-center" >Bạn đã kết thúc bài thi </h3>
					<center><img src="images/congratulations1.png" alt="congratulation" width="550" height="200"></center><br>
					<table class="table table-striped">
					  <thead class="thead-light">
					    <tr>
					      <th scope="col">STT</th>
					      <th scope="col">Mã đề thi</th>
					      <th scope="col">Tên đề thi</th>
					      <th scope="col">Điểm</th>
					      <th scope="col">Thời gian nộp</th>
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach items="${listResult}" var="result" varStatus="loop">
					  		<tr>
						      <th scope="row">${loop.index+1 }</th>
						      <td>${test.id}</td>
						      <td>${test.name}</td>
						      <td>${result.scores} / ${test.numQuiz}</td>
						      <td>${result.timeEnd}</td>
						    </tr>
					  	</c:forEach>
					  </tbody>
					</table>
				</div>
			</div>
		</div>
	</main>
</body>

</html>