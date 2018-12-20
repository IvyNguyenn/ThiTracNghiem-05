<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<header>
      <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="/ThiTracNghiem">ThiTracNghiem123</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav mr-auto">
            <% 
            for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("loginUser")){ %>
				 <li class="nav-item active">
      				<a class="nav-link" href=""><i class="fa fa-user"></i><%=cookie.getValue() %><span class="sr-only">(current)</span></a>
    			</li>
				<% }
			}
			%>
            <!-- Dropdown -->
			<li class="nav-item dropdown">
			      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
			        <i class="fa fa-cog"></i>
			      </a>
			      <div class="dropdown-menu">
			        <a class="dropdown-item" href="logout.jsp">Logout</a>
			      </div>
			</li>
          </ul>
        </div>
      </nav>
    </header>
</body>
</html>