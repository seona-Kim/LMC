<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lmc.common.model.vo.PageInfo, java.util.ArrayList, com.lmc.board.model.vo.Board" %>
<% 
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPageBoard</title>
 	<style>
        .wrapBody {
            width: 1200px;
            height: 800px;
            margin: auto;
            background-color: #ffffff;
        }
        .wrapBody>div {
            width: 100%;
        }
        #body { 
            height: 100%; 
        }
        #body>div {
            height: 100%;
            float: left;
        }
        #body_1 { width: 25%; }
        #body_2 { width: 75%; }
        #body_1 a { 
            text-decoration: none;
            color: black;
        }

        .outer {
            width: 100%;
            margin: auto;
            border: 1px dotted lightgray;
            margin-top: 50px;
            padding: 50px;
        }
        .list-area {
        	height: 100%;
            border-spacing: 0px;
            border: 1px solid lightgray;
            text-align: center;
        }

        .list-area>tbody>tr:hover { 
            background-color: lightgray; 
            cursor: pointer;
        }
        .page-outer>ul{
         justify-content : center;
        }
        
    </style>
</head>
<body>
	<%@ include file = "../common/menubar.jsp" %>
	<%
		String memberId 	=  loginUser.getMemberId();
		String memberName 	=  loginUser.getMemberName();
		String memberNick 	= (loginUser.getMemberNick() == null) 	? "" : loginUser.getMemberNick();
		String email 		= (loginUser.getEmail() == null) 	? "" : loginUser.getEmail();
		String enrollDate 	= (loginUser.getEnrollDate() == null) 	? "" : loginUser.getEnrollDate().toString();
		String memberImg 	= (loginUser.getMemberImg() == null) ? "/resources/images/pngwing.com.png" : loginUser.getMemberImg();
		
	%>
	<div class="wrapBody">
          <div id="body">
          	<div id="body_1">
              <%@ include file = "myPage.jsp" %>
              </div>
              <div id="body_2">
                   <div class="outer">
			        <br>
			        <h2 align="center">내가 쓴 게시글</h2>
			        <br>
					<form action="<%= contextPath %>/deleteMyBoard.bo" method="post">
			        <table class="list-area" align="center" border="1">
			            <thead>
			                <tr>
			                	<th width="30"><input type="checkbox" name="deleteAll"></th>
			                    <th width="70">글번호</th>
			                    <th width="70">카테고리</th>
			                    <th width="300">제목</th>
			                    <th width="100">작성자</th>
			                    <th width="70">조회수</th>
			                    <th width="100">작성일</th>
			                </tr>
			            </thead>
			            <tbody>
			            	<% if(list.isEmpty()) { %>
					         <tr>
		                		<td colspan="7">
		                			조회된 리스트가 없습니다.
		                		</td>
		                	</tr>
		                	<% } else { %>
			            		<% for(Board b : list) { %>
		                			<tr>
		                				<td class="notSelect"><input type="checkbox" name="deleteBNo" value="<%= b.getBoardNo() %>"></td>
		                				<td><%= b.getBoardNo() %></td>
		                				<td><%= b.getCategory() %></td>
		                				<td><%= b.getBoardTitle() %></td>
		                				<td><%= b.getBoardWriter() %></td>
		                				<td><%= b.getViews() %></td>
		                				<td><%= b.getCreateDate() %></td>
		                			</tr>
		                		<% } %>
			                <% } %>
			            </tbody>
			        </table>
			        <br>
			        <div style="width:770px;" align="right">
						<button type="submit" class="btn btn-warning btn-sm" name="deleteMyBNo">삭제하기</button>
						<br><br>
					</div>
			        <br>
					</form>
			        <script type="text/javascript">
			        	$(function(){
			        		$("input[name='deleteAll']").change(function(){
			        			$("input[name='deleteBNo']").prop('checked', $("input[name='deleteAll']").is(":checked"));
			        		});
			        		
			        		$("input[name='deleteBNo']").click(function() {
			        			if($("input[name='deleteBNo']:checked").length != $("input[name='deleteBNo']").length){
			        				$("input[name='deleteAll']").prop('checked', false);
			        			} else {
			        				$("input[name='deleteAll']").prop('checked', true);
			        			}
			        		});
			        	
			        		$(".list-area>tbody>tr").children().not(".notSelect").click(function() {
								let bno = $(this).parent().children().eq(1).text();
								location.href = "<%= contextPath %>/detail.bo?bno=" + bno;
							});
			        		
			        		$("button[name='deleteMyBNo']").click(function() {
			        			var result = confirm($(".list-area>tbody input[name='deleteBNo']:checked").length + "개의 게시글을 삭제하시겠습니까?");
								return result;
			        		});
			        	});
					</script>
			        
			        <!-- 페이징바 -->
			        <div class="page-outer">
					  <ul class="pagination">
					  	<% if(currentPage != 1) { %>
					    <li class="page-item">
					      <a class="page-link" href="<%= contextPath %>/myPageBoard.me?currentPage=<%= currentPage - 1 %>" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    <% } %>
					    <% for(int p = startPage; p <= endPage; p++ ) { %>
		            		<% if(p != currentPage) { %>
		            			<li class="page-item"><a class="page-link" href="<%= contextPath %>/myPageBoard.me?currentPage=<%= p %>"><%= p %></a></li>
		            		<% } else { %>
		            			<li class="page-item active" aria-current="page"><a class="page-link"><%= p %></a></li>
		            		<% } %>
		            	<% } %>
					    <% if(currentPage != maxPage) { %>
					    <li class="page-item">
					      <a class="page-link" href="<%= contextPath %>/myPageBoard.me?currentPage=<%= currentPage + 1 %>" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					    <% } %>
					  </ul>
					</div>
			    </div>
              </div>
          </div>
      </div>
      <%@ include file = "../common/footer.jsp" %>
</body>
</html>