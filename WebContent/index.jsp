<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.vy.dao.QuizDAO"%>
<%@page import="com.vy.dao.SubjectDAO,java.util.*"%>
<%@page import="com.vy.dao.SectionDAO"%>
<%@page import="com.vy.dao.TestDAO"%>
<%@page import="com.vy.model.Subject" %>
<%@page import="com.vy.model.Section" %>
<%@page import="com.vy.model.Quiz" %>
<%@page import="com.vy.model.Test" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<%request.setCharacterEncoding("UTF-8");%>
	<%
	List<Subject> listSubject = SubjectDAO.getAllSubjects();
	List<Test> listTest = TestDAO.getAllTests();
	%>
    <jsp:include page="navbar-header.jsp"></jsp:include>
    <main role="main">
      <jsp:include page="carousel.jsp"></jsp:include>
      <div class="container marketing">
        <!-- Three columns of text below the carousel -->
        <div class="row">
        	<!-- /.col-lg-4 -->
        	<%for(Test test:listTest){ %>
	        	<div class="col-md-3 test-box text-center">
		          	<div class="test-box-border">
		            <img class="rounded-circle" src="images/cntt<%=test.getId()%12+5%>.jpg" alt="Generic placeholder image" width="140" height="140">
		            <h5><%= SubjectDAO.getSubjectByTestId(test.getId()).getName() %></h5>
		            <p><%=test.getName() %></p>
		            <div>Ngày bắt đầu: <%=test.getDateOpen()+" "+test.getTimeOpen() %></div>
		            <div>Ngày kết thúc: <%=test.getDateClose()+" "+test.getTimeClose() %></div>
		            <div>Thời lượng: <%=test.getTimeLimit() %> phút</div><br>
		            <p><a class="btn btn-secondary" href="/ThiTracNghiem/exam?command=do&id=<%=test.getId() %>" role="button">Thi ngay &raquo;</a></p>
		          	</div>
	          	</div>
            <%} %>
        </div> <!-- /.row -->
		

        <!-- SHOW LIST SUBJECT -->
		<%for(Subject subject : listSubject){ %>
        <hr class="featurette-divider">

        <div class="row featurette">
          <div class="col-md-7">
            <h2 class="featurette-heading"><%=subject.getName() %><span class="text-muted"></span></h2>
            <p class="lead"><%=subject.getDescription() %></p>
          </div>
          <div class="col-md-5">
            <img class="featurette-image img-fluid mx-auto" src="images/cntt<%=subject.getId() %>.jpg" alt="Generic placeholder image">
          </div>
        </div>
        <%} %>
        <!-- /END THE FEATURETTES -->

      </div><!-- /.container -->
	
		<jsp:include page="footer.jsp"></jsp:include>
	
    </main>
  </body>
</html>