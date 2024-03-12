<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.lmc.common.model.vo.PageInfo, 
    				java.util.ArrayList,
				 com.lmc.board.model.vo.Board" %>
  <%
  PageInfo pi = (PageInfo)request.getAttribute("pi");
  ArrayList<Board> searchList = (ArrayList<Board>)request.getAttribute("searchList");
  String keyword = (String)request.getAttribute("keyword");	
  
	//자주 쓰일법한 변수만 먼저 셋팅
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 결과</title>
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
	
	.table>tbody>tr td{
		text-align : center;
    }
    .paging-area button:disabled {
        background-color : black;
        color : white;
    }
    
    .page-outer>ul{
     justify-content : center;
    }
    
</style>

<body>

    <%@ include file="../common/menubar.jsp" %>

    <div class="wrap">

        <div class="content">

            <br>
            <h2 align="center" style="font-family: 'LINESeedKR-Bd';">
                <i class="fas fa-search"></i> '<%= keyword %>' 검색 결과
            </h2>
            <br><br>
            <div class="table-area">
                <table class="table" align="center" style="border-spacing : 0px;">
                    <thead>
                    <tr>
                        <th width="60" style="text-align:center;">No.</th>
                        <th width="450" style="text-align:center;">제목</th>
                        <th width="120" style="text-align:center;">작성자</th>
                        <th style="text-align:center;">날짜</th>
                        <th style="text-align:center;">조회수</th>
                    </tr>
                    </thead>
                    <tbody>
                     <% if(searchList.isEmpty() || keyword == "") { %>
                
                	<tr>
                		<td colspan="6" onclick='event.cancelBubble=true;'>
                			조회된 리스트가 없습니다.
                		</td>
                	</tr>
                
                <% } else { %>
                
                	<% for(Board b : searchList) { %>
                	
                		<tr>
                			<td><%= b.getBoardNo() %></td>
                			<td><%= b.getBoardTitle() %></td>
                			<td><%= b.getBoardWriter() %></td>
                			<td><%= b.getCreateDate() %></td>
                			<td><%= b.getViews() %></td>
                		</tr>
                	
                	<% } %>
                	
                <% } %>
                <form action="/boardDetailView.bo" method="get">
	                <input type="hidden" name="hiddenKeyword" value="<%=keyword%>">
	                <button id="test" type="submit" style=" visibility : hidden"></button>
                </form>
                    </tbody>
                </table>
                
                 <script>
        	$(function() {
        		
        		$(".table>tbody>tr").click(function() {
        		
        			$("#test").trigger('click');
        			
        			let bno = $(this).children().eq(0).text();
        		
        			location.href = "<%= contextPath %>/boardDetailView.bo?bno=" + bno;
        			
        		});
        	});
        </script>
                
                <br><br>
                
            <% if(keyword != ""){ %>
            		<!-- 페이징바 -->
			        <div class="page-outer">
					  <ul class="pagination">
					  	<% if(currentPage != 1) { %>
					    <li class="page-item">
					      <a class="page-link" href="<%= contextPath %>/searchListView.bo?currentPage=<%= currentPage - 1 %>&keyword=<%= keyword %>" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    <% } %>
					    <% for(int p = startPage; p <= endPage; p++ ) { %>
		            		<% if(p != currentPage) { %>
		            			<li class="page-item"><a class="page-link" href="<%= contextPath %>/searchListView.bo?currentPage=<%= p %>&keyword=<%= keyword %>"><%= p %></a></li>
		            		<% } else { %>
		            			<li class="page-item active" aria-current="page"><a class="page-link"><%= p %></a></li>
		            		<% } %>
		            	<% } %>
					    <% if(currentPage != maxPage) { %>
					    <li class="page-item">
					      <a class="page-link" href="<%= contextPath %>/searchListView.bo?currentPage=<%= currentPage + 1 %>&keyword=<%= keyword %>" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					    <% } %>
					  </ul>
					</div>
            <% } %>
        </div>
    </div>
    <%@ include file="../common/footer.jsp" %>


</body>
</html>