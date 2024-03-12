<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.lmc.board.model.vo.Board,
                 com.lmc.notice.model.vo.Notice,
                 java.util.ArrayList" %>
<%
ArrayList<Board> freeBoardList = (ArrayList<Board>)request.getAttribute("freeBoardList");
ArrayList<Board> shearBoardList = (ArrayList<Board>)request.getAttribute("shearBoardList");
ArrayList<Board> reviewBoardList = (ArrayList<Board>)request.getAttribute("reviewBoardList");
ArrayList<Notice> noticeList = (ArrayList<Notice>)request.getAttribute("noticeList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMC</title>

<style>
	.content {
        display: flex;
        flex-direction: column; /* 세로 정렬 */
        align-items: center;
        justify-content: center;
        height: 1000px;
        padding: 20px;
    }

    .best-posts {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin: auto;
    }

	.best-posts li a {
		text-decoration: none;
	}

    .best-post-group {
        display: flex;
    }

    .best-post {
        width: 450px;
        background-color: #ececec;
        padding: 40px;
        margin: 40px 50px;
        border-radius: 5px;
    }

    .best-post h4 {
        color: #333;
        font-family: 'LINESeedKR-Bd';
        margin-bottom: 30px;
    }

    .best-post i {
        margin-left: 15px;
    }

    .best-post p {
        color: #666;
    }

    .best-post-list {
        list-style: none;
        padding: 0;
    }

    .best-post-list-item {
        margin-bottom: 10px;
    }

    .best-post-list-item a {
        text-decoration: none;
        color: #333;
        font-weight: bold;
    }

</style>

</head>
<body>
	<%@ include file = "views/common/menubar.jsp" %>
    <div class="wrap">
		<div class="content">
			<div class="best-posts">
				<div class="best-post-group">
					<div class="best-post">
						<a href="<%= contextPath %>/list.bo?category=10&currentPage=1" style="text-decoration: none;">
							<h4 align="center">자유게시판<i class="fas fa-caret-right"></i></h4>
						</a>
						<ul class="best-post-list">
						<% if(freeBoardList.isEmpty()) { %>
                            <li>
                                조회된 글이 없습니다.
                            </li>
                        <% } else { %>
                            <% for(Board b : freeBoardList) { %>
                                <li class="best-post-list-item"><a href="<%= contextPath %>/boardDetailView.bo?bno=<%= b.getBoardNo() %>"><%= b.getBoardTitle() %></a></li>
                            <% } %>
                        <% } %>
						</ul>
					</div>
					<div class="best-post">
						<a href="<%= contextPath %>/list.bo?category=20&currentPage=1" style="text-decoration: none;">
							<h4 align="center">나눔게시판<i class="fas fa-caret-right"></i></h4>
						</a>
						<ul class="best-post-list">
							<% if(shearBoardList.isEmpty()) { %>
                            <li>
                                조회된 글이 없습니다.
                            </li>
	                        <% } else { %>
	                            <% for(Board b : shearBoardList) { %>
	                                <li class="best-post-list-item"><a href="<%= contextPath %>/boardDetailView.bo?bno=<%= b.getBoardNo() %>"><%= b.getBoardTitle() %></a></li>
	                            <% } %>
	                        <% } %>
						</ul>
					</div>
				</div>
				<div class="best-post-group">
					<div class="best-post">
						<a href="<%= contextPath %>/list.bo?category=30&currentPage=1" style="text-decoration: none;">
							<h4 align="center">평론게시판<i class="fas fa-caret-right"></i></h4>
						</a>
						<ul class="best-post-list">
							<% if(reviewBoardList.isEmpty()) { %>
                            <li>
                                조회된 글이 없습니다.
                            </li>
	                        <% } else { %>
	                            <% for(Board b : reviewBoardList) { %>
	                                <li class="best-post-list-item"><a href="<%= contextPath %>/boardDetailView.bo?bno=<%= b.getBoardNo() %>"><%= b.getBoardTitle() %></a></li>
	                            <% } %>
	                        <% } %>
						</ul>
					</div>
					<div class="best-post">
						<a href="<%= contextPath %>/list.no?currentPage=1" style="text-decoration: none;">
							<h4 align="center">공지사항<i class="fas fa-caret-right"></i></h4>
						</a>
						<ul class="best-post-list">
							<% if(noticeList.isEmpty()) { %>
                            <li>
                                조회된 공지사항이 없습니다.
                            </li>
	                        <% } else { %>
	                            <% for(Notice n : noticeList) { %>
	                                <li class="best-post-list-item"><a href="<%= contextPath %>/detail.no?nno=<%= n.getNoticeNo() %>"><%= n.getNoticeTitle() %></a></li>
	                            <% } %>
	                        <% } %>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file = "views/common/footer.jsp" %>

</body>
</html>