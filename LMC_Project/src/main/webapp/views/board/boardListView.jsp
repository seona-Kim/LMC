<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lmc.board.model.vo.Board,
                 com.lmc.common.model.vo.PageInfo,
                 com.lmc.notice.model.vo.Notice,
                 java.util.ArrayList" %>
                 
<%
PageInfo pi = (PageInfo)request.getAttribute("pi");
ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
ArrayList<Notice> noticeList = (ArrayList<Notice>)request.getAttribute("noticeList");
int category = (int)request.getAttribute("category");
String categoryName = (String)request.getAttribute("categoryName");

int currentPage = pi.getCurrentPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
int maxPage = pi.getMaxPage();
%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%= categoryName %>게시판</title>
</head>

<style>

    .content {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: auto;
        padding: 20px;
    }

    .table-area {
        width: 850px;
        height: auto;
        margin: auto;
    }

    .table>tbody>tr:hover{
        cursor: pointer;
        background-color: rgb(244, 244, 244);
    }

    .btn-submit {
            background-color: yellowgreen;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            float: right;
            color: black;
    }

    .btn-submit:hover {
        background-color: #8dc231;
    }


</style>

<body>

    <%@ include file="../common/menubar.jsp" %>

    <div class="wrap">

        <div class="content">

            <br>
            <h2 align="center" style="font-family: 'LINESeedKR-Bd';">
                <i class="far fa-list-alt"></i> <%= categoryName %>게시판
            </h2>
            <br><br>
            <div class="table-area">
                <table class="table" align="center" style="border-spacing : 0px;">
                    <thead>
	                    <tr>
	                        <th width="60" style="text-align:center;">No.</th>
	                        <th width="400" style="text-align:center;">제목</th>
	                        <th width="120" style="text-align:center;">작성자</th>
	                        <th style="text-align:center;">조회수</th>
	                        <th style="text-align:center;">추천수</th>
	                        <th style="text-align:center;">날짜</th>
	                    </tr>
                    </thead>
                    <tbody>
                    	<% if(noticeList.isEmpty()) { %>
                            <tr>
                                <td colspan="5">
                                    조회된 공지가 없습니다.
                                </td>
                            </tr>
                        <% } else { %>
                            <% for(Notice n : noticeList) { %>
                                <tr style="background-color: rgb(255, 255, 177);">
			                    	<td hidden><%= n.getNoticeNo() %></td>
			                        <td style="text-align:center; font-weight: bold;">공지</td>
			                        <td style="text-align:center; font-weight: bold;"><%= n.getNoticeTitle() %></td>
			                        <td style="text-align:center; font-weight: bold;">관리자</td>
			                        <td style="text-align:center; font-weight: bold;"><%= n.getViews() %></td>
			                        <td></td>
			                        <td style="text-align:center; font-weight: bold;"><%= n.getCreateDate() %></td>
			                    </tr>
                            <% } %>
                        <% } %>
	                    	<tr>
                                <td colspan="6" style="text-align:center;">
                                    전체 공지 조회하기 >>
                                </td>
                            </tr>
                    
                   		<% if(list.isEmpty()) { %>
                            <tr>
                                <td colspan="5">
                                    조회된 리스트가 없습니다.
                                </td>
                            </tr>
                        <% } else { %>
                            <% for(Board b : list) { %>
                                <tr>
                                    <td style="text-align:center;"><%= b.getBoardNo() %></td>
                                    <td hidden><%= b.getCategory() %></td>
                                    <td style="text-align:center;"><%= b.getBoardTitle() %></td>
                                    <td style="text-align:center;"><%= b.getBoardWriter() %></td>
                                    <td style="text-align:center;"><%= b.getViews() %></td>
                                    <td style="text-align:center;"><%= b.getCommendCount() %></td>
                                    <td style="text-align:center;"><%= b.getCreateDate() %></td>
                                </tr>
                            <% } %>
                        <% } %>
                    </tbody>
                </table>
                <% if(loginUser != null) { %>
                <button type="button" class="btn-submit" onclick="location.href='<%= contextPath %>/enrollForm.bo?category=<%= category %>'" ><i class="fas fa-pen"></i> 쓰기</button>
                <% } else { %>
                <br> <br>
                <% } %>
            </div>
            
            <script type="text/javascript">
            	$(function() {
            		$(".table-area>.table>tbody>tr").click(function() {
						let bno = $(this).children().eq(0).text();
						if($(this).children().eq(1).text()=="공지"){
							location.href = "<%= contextPath %>/detail.no?nno=" + bno;
						} else if($(this).children().eq(1).text()==""){
							location.href = "<%= contextPath %>/list.no?currentPage=1";
						} else {
							location.href = "<%= contextPath %>/boardDetailView.bo?bno=" + bno;
						}
					});
				});
            </script>
			
			<!-- 페이징바 -->
	        <div class="page-outer">
			  <ul class="pagination">
			  	<% if(currentPage != 1) { %>
			    <li class="page-item">
			      <a class="page-link" href="<%= contextPath %>/list.bo?currentPage=<%= currentPage - 1 %>&category=<%= category %>" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <% } %>
			    <% for(int p = startPage; p <= endPage; p++ ) { %>
            		<% if(p != currentPage) { %>
            			<li class="page-item"><a class="page-link" href="<%= contextPath %>/list.bo?currentPage=<%= p %>&category=<%= category %>"><%= p %></a></li>
            		<% } else { %>
            			<li class="page-item active" aria-current="page"><a class="page-link"><%= p %></a></li>
            		<% } %>
            	<% } %>
			    <% if(currentPage != maxPage) { %>
			    <li class="page-item">
			      <a class="page-link" href="<%= contextPath %>/list.bo?currentPage=<%= currentPage + 1 %>&category=<%= category %>" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			    <% } %>
			  </ul>
			</div>
        </div>

    </div>

    <%@ include file="../common/footer.jsp" %>


</body>
</html>