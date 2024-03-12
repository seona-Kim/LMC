<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lmc.common.model.vo.PageInfo,
                 com.lmc.notice.model.vo.Notice,
                 java.util.ArrayList" %>
                 
<%
PageInfo pi = (PageInfo)request.getAttribute("pi");
ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");

int currentPage = pi.getCurrentPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
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
                <i class="fas fa-exclamation-circle"></i> 공지사항
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
                    	<% if(list.isEmpty()) { %>
                            <tr>
                                <td colspan="5">
                                    조회된 공지가 없습니다.
                                </td>
                            </tr>
                        <% } else { %>
                            <% for(Notice n : list) { %>
                                <tr>
			                        <td style="text-align:center; font-weight: bold;"><%= n.getNoticeNo() %></td>
			                        <td style="text-align:center; font-weight: bold;"><%= n.getNoticeTitle() %></td>
			                        <td style="text-align:center; font-weight: bold;">관리자</td>
			                        <td style="text-align:center; font-weight: bold;"><%= n.getCreateDate() %></td>
			                        <td style="text-align:center; font-weight: bold;"><%= n.getViews() %></td>
			                    </tr>
                            <% } %>
                        <% } %>
                    </tbody>
                </table>
            </div>
            
            <script type="text/javascript">
			        	$(function(){
			        		$(".table-area>.table>tbody>tr").children().click(function() {
								let nno = $(this).parent().children().eq(0).text();
								location.href = "<%= contextPath %>/detail.no?nno=" + nno;
							});
			        	});
					</script>
            
            <br><br>
			
			<!-- 페이징바 -->
	        <div class="page-outer">
			  <ul class="pagination">
			  	<% if(currentPage != 1) { %>
			    <li class="page-item">
			      <a class="page-link" href="<%= contextPath %>/list.no?currentPage=<%= currentPage - 1 %>" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <% } %>
			    <% for(int p = startPage; p <= endPage; p++ ) { %>
            		<% if(p != currentPage) { %>
            			<li class="page-item"><a class="page-link" href="<%= contextPath %>/list.no?currentPage=<%= p %>"><%= p %></a></li>
            		<% } else { %>
            			<li class="page-item active" aria-current="page"><a class="page-link"><%= p %></a></li>
            		<% } %>
            	<% } %>
			    <% if(currentPage != maxPage) { %>
			    <li class="page-item">
			      <a class="page-link" href="<%= contextPath %>/list.no?currentPage=<%= currentPage + 1 %>" aria-label="Next">
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