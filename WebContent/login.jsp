<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Boostrap core CSS -->
<link href="./css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles CSS -->
<link href="./css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
    <form class="form-signin" action="login" method="POST" name="formSignin">
      <img class="mb-4" src="./images/login.png" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Please login in</h1>
      <c:if test="${errors!=null}">
		<c:forEach items="${errors}" var="error" >
			<h6 style="color:red">Error: ${error}</h6>
		</c:forEach>
	  </c:if>
      <label for="inputEmail" class="sr-only">Username</label>
      <input type="text" name="username" class="form-control" placeholder="Username" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" name="password" class="form-control" placeholder="Password" required>
      <div class="float-left">
	      <div class="form-check" style="margin-left:-100px;">
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input" name="role" value="examinee" checked>Thí sinh
			  </label>
		  </div>
		  <div class="form-check">
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input" name="role" value="quizManager" >Người quản lý câu hỏi
			  </label>
		  </div>
		  <div class="form-check" style="margin-left:-71px;">
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input" name="role" value="testManager" >Người ra đề
			  </label>
		  </div>
		  <div class="form-check">
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input" name="role" value="classManager" >Người quản lý thí sinh
			  </label>
		  </div>
	  </div>
	  <br>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
      <br>
      <a href="/ThiTracNghiem">Home page</a><br>
      <p class="mt-5 mb-3 text-muted">&copy; 2018-2019</p>
    </form>
</body>
</html>