<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 응답데이터 찾기
	// request.getAttribute("키값") : Object (밸류값)
	String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="menubar.jsp" %>
	
	<br><br>
	
	<h1 align="center" style="color : red;">
		<%= errorMsg %>
	</h1>

</body>
</html>



