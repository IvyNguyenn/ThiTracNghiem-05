<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<body>
	<header>
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="/ThiTracNghiem/">ThiTracNghiem123</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
           <li class="nav-item active">
              <a class="nav-link" href="#">Trang chủ<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Đề thi <span class="sr-only">(current)</span></a>
            </li>
          </ul>
          <form class="form-inline mt-2 mt-md-0">
          	<%
          		boolean islogin=false;
				for (Cookie cookie : request.getCookies()) {
					if (cookie.getName().equals("loginUser")){ 
						islogin=true;			
			%>
			<%		}
				}
			%>
			<%if(islogin){%>
				<a class="btn btn-success my-sm-2" href="logout.jsp" role="button">Logout</a>
			<%} else{%>
				 <a class="btn btn-primary my-sm-2" href="/ThiTracNghiem/login.jsp" role="button" style="margin-left:10px">Login</a>
			<%} %>
           
          </form>
        </div>
      </nav>
    </header>
</body>
</html>