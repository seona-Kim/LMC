<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage</title>
 	<style>
        #myMenu a { 
            text-decoration: none;
            color: black;
        }
    </style>
</head>
<body>

    <div id="myMenu">
        <br><br><br>
        <div align="center"><img src="<%= contextPath + memberImg %>" height="150px"></div>
        <br>
        <div align="center"><h4><%= memberNick %> 님</h4></div>
        <br><br><br>
        <div align="center"><h4><a href="<%= contextPath %>/myPageInfo.me">내 정보 수정</a></h4></div>
        <hr>
        <div align="center"><h4><a href="<%= contextPath %>/myPageBoard.me?currentPage=1">내가 쓴 게시글 조회</a></h4></div>
        <hr>
        <div align="center"><h4><a href="<%= contextPath %>/myPageReply.me?currentPage=1">내가 쓴 댓글 조회</a></h4></div>
        <hr>
        <div align="center"><h4><a href="<%= contextPath %>/myPageCommend.me?currentPage=1">추천한 게시글 조회</a></h4></div>
        <hr>
        <div align="center"><h4><a href="<%= contextPath %>/list.mes">쪽지함</a></h4></div>
    </div>

	
</body>
</html>