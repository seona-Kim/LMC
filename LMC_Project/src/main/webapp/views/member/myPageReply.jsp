<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lmc.common.model.vo.PageInfo, java.util.ArrayList, com.lmc.board.model.vo.Reply" %>
<% 
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Reply> list = (ArrayList<Reply>)request.getAttribute("list");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPageReply</title>
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
		// session 에 담겨있는 loginUser 값을 뽑기
		// System.out.println(loginUser);
		String memberId 	=  loginUser.getMemberId();
		String memberName 	=  loginUser.getMemberName();
		String memberNick 	= (loginUser.getMemberNick() == null) 	? "" : loginUser.getMemberNick();
		String email 		= (loginUser.getEmail() == null) 	? "" : loginUser.getEmail();
		String enrollDate 	= (loginUser.getEnrollDate() == null) 	? "" : loginUser.getEnrollDate().toString();
		String memberImg 	= (loginUser.getMemberImg() == null) ? "/resources/images/pngwing.com.png" : loginUser.getMemberImg();
		
		// 삼항연산자 이용 : DB에 없는 값을 불러오면 null 을 불러오므로
		// null 일 경우는 "" (빈 문자열) 로 대체
	%>
	<div class="wrapBody">
          <div id="body">
          	<div id="body_1">
              <%@ include file = "myPage.jsp" %>
              </div>
              <div id="body_2">
                   <div class="outer">
			        <br>
			        <h2 align="center">내가 쓴 댓글</h2>
			        <br>
			        <form action="<%= contextPath %>/deleteMyReply.bo" method="post">
			        <table class="list-area" align="center" border="1">
			            <thead>
			                <tr>
			                	<th width="30"><input type="checkbox" name="deleteAll"></th>
			                    <th width="280">글제목</th>
			                    <th width="290">댓글내용</th>
			                    <th width="140">작성일</th>
			                </tr>
			            </thead>
			            <tbody>
			            	<% if(list.isEmpty()) { %>
					         <tr>
		                		<td colspan="4">
		                			조회된 리스트가 없습니다.
		                		</td>
		                	</tr>
		                	<% } else { %>
			            		<% for(Reply r : list) { %>
		                			<tr>
		                				<td class="notSelect"><input type="checkbox" name="deleteRNo" value="<%= r.getReplyNo() %>"></td>
		                				<td hidden><%= r.getRefBno() %></td> <!-- 글번호 -->
		                				<td><%= r.getRefBTitle() %></td>
					                    <td><%= r.getReplyContent() %></td>
					                    <td><%= r.getCreateDate() %></td>
		                			</tr>
		                		<% } %>
			                <% } %>
			            </tbody>
			        </table>
			        <br>
			        <div style="width:770px;" align="right">
						<button type="submit" class="btn btn-warning btn-sm" name="deleteMyRNo">삭제하기</button>
						<br><br>
					</div>
			        <br>
			        </form>
			        <script type="text/javascript">
			        	$(function(){
			        		$("input[name='deleteAll']").change(function(){
			        			$("input[name='deleteRNo']").prop('checked', $("input[name='deleteAll']").is(":checked"));
			        		});
			        		
			        		$("input[name='deleteRNo']").click(function() {
			        			if($("input[name='deleteRNo']:checked").length != $("input[name='deleteRNo']").length){
			        				$("input[name='deleteAll']").prop('checked', false);
			        			} else {
			        				$("input[name='deleteAll']").prop('checked', true);
			        			}
			        		});
			        		
			        		$(".list-area>tbody>tr").children().not(".notSelect").click(function() {
								let bno = $(this).parent().children().eq(1).text();
								location.href = "<%= contextPath %>/detail.bo?bno=" + bno;
							});
			        		
			        		$("button[name='deleteMyRNo']").click(function() {
			        			var result = confirm($(".list-area>tbody input[name='deleteRNo']:checked").length + "개의 댓글을 삭제하시겠습니까?");
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