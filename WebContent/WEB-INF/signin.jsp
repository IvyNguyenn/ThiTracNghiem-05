<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Sign In</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Boostrap core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles CSS -->
<link href="${pageContext.request.contextPath}/css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
    <form class="form-signin" action="${pageContext.request.contextPath}/Login" method="post">
      <img class="mb-4" src="${pageContext.request.contextPath}/images/login.png" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      <label for="inputEmail" class="sr-only">Email address</label>
      <input type="text" id="inputEmail"  name="txtUserName" class="form-control" placeholder="Email address" required autofocus>
      <label for="inputPassword" class="sr-only">Password</label>
      <input type="password" id="inputPassword" class="form-control" name="txtPassword" placeholder="Password" required>
      <div class="float-left">
	      <div class="form-check">
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input" name="actor" value="admin">Admin
			  </label>
		  </div>
		  <div class="form-check">
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input" name="actor" value="teacher" >Teacher
			  </label>
		  </div>
		  <div class="form-check">
			  <label class="form-check-label">
			    <input type="radio" class="form-check-input" name="actor" value="student" >Student
			  </label>
		  </div>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      <br>
      <a href="/ThiTracNghiem">Home page</a>
      <p class="mt-5 mb-3 text-muted">&copy; 2018-2019</p>
    </form>
</body>
</html>