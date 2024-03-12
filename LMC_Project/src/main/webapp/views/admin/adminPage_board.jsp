<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.lmc.board.model.vo.Board,
                 com.lmc.common.model.vo.PageInfo,
                 java.util.ArrayList" %>    
                 
<%
// Controller 단에서 정보 받아오기
PageInfo pi = (PageInfo)request.getAttribute("pi");
ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");

//자주 쓰일법한 변수 세팅
int currentPage = pi.getCurrentPage();
int startPage = pi.getStartPage();
int endPage = pi.getEndPage();
int maxPage = pi.getMaxPage();
%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일반게시글관리</title>

    <style>

        .wrap {
            width: 1200px;
            min-height: 100%;
            background-color: #e0e0e0;
        }
        
        td { font-family: 'KimjungchulGothic' }

        /* 콘텐츠의 공통속성 */
        #content { 
            width: auto; /*총 너비(1200px)의 80% = 960px */
            margin-left: 5%;
            margin-right: 5%;
            min-height: 800px;
            background-color: rgba(255, 255, 255, 0.829)
        }
        
        #content>div{ float: left;}
        
        /*우측 관리자 메뉴 크기*/
        #content_2 { 
            width: 860px;
            height: 740px; 
            margin-top: 30px;
        }


        /* 게시판 관련 스타일 */
        .admin_outer {
        width : 800px;
        height: 100%;
        margin : auto;
        }

        .list-area {
            border-spacing : 0px;
            border : 1px solid lightgray;
            text-align : center;
        }

        .list-area>tbody>tr:hover {
            background-color : lightgray;
            cursor : pointer;
        }
        
        .paging-area button:disabled {
            background-color : black;
            color : white;
        }
        
        .page-outer>ul{
         justify-content : center;
        }
        
     </style>
</head>

<body>
	
	<%@ include file="../common/menubar.jsp" %>
	<%
		String memberId 	=  loginUser.getMemberId();
		String memberName 	=  loginUser.getMemberName();
		String memberNick 	= (loginUser.getMemberNick() == null) 	? "" : loginUser.getMemberNick();
		String email 		= (loginUser.getEmail() == null) 	? "" : loginUser.getEmail();
		String enrollDate 	= (loginUser.getEnrollDate() == null) 	? "" : loginUser.getEnrollDate().toString();
		String memberImg 	= (loginUser.getMemberImg() == null) ? "/resources/images/pngwing.com.png" : loginUser.getMemberImg();
	%>
    <div class="wrap">
        
        <div id="content">
            
            <%@ include file="../common/adminMenu.jsp" %>
            
            <div id="content_2">
                
                <div class="admin_outer">
                    
                    <h2 align="left" style="margin-bottom: 15px;">일반게시판</h2>
                   
                    
                    <div id="search" style="width : 800px; display: flex; justify-content: flex-start;">

                    	<form action="<%=contextPath%>/allStopBoard.bo?currentPage=<%=currentPage%>" method="post">
                            <div style="margin-left: auto;">
                                <button type="submit" class="btn btn-danger">삭제</button>
                            </div>
                     
                    </div>
                   
                    <input type="checkbox" name="bordercheck" style="margin-bottom: 15px;" value="0" onclick="selectAll(this)"> 전체 선택

                    <script>
                        function selectAll(selectAll){
                            const checkboxes
                                =document.getElementsByName("bordercheck");

                            checkboxes.forEach((checkbox) => {
                                checkbox.checked = selectAll.checked;
                            })
                        }

                    </script>
                    
                    <table class="list-area" align="center" border="1">
                        <thead>
                            <tr>
                                <th width="50">선택</th>
                                <th width="60">글번호</th>
                                <th width="70">카테고리</th>
                                <th width="300">제목</th>
                                <th width="100">작성자</th>
                                <th width="70">조회수</th>
                                <th width="100">작성일</th>
                                <th width="50">상태</th>
                            </tr>
                        </thead>
                        <tbody>

                            <% if(list.isEmpty()) { %>
                                
                                <tr>
                                    <td colspan="8" onclick='event.cancelBubble=true;'>
                                        조회된 리스트가 없습니다.
                                    </td>
                                </tr>
                                
                                <% } else { %>
                                    
                                    <% for(Board b : list) { %>
                                        
                                        <tr>
                                      		<td onclick='event.cancelBubble=true;'>
                                      			<input type="checkbox" name="bordercheck" class="checkNo"  onclick='event.cancelBubble=true;'value=<%=b.getBoardNo()%>>
                                      		</td>
                                            <td><%= b.getBoardNo() %></td>
                                            <td><%= b.getCategory() %></td>
                                            <td><%= b.getBoardTitle() %></td>
                                            <td><%= b.getBoardWriter() %></td>
                                            <td><%= b.getViews() %></td>
                                            <td><%= b.getCreateDate() %></td>
                                            <td><%= b.getStatus() %></td>
                                        </tr>
                                        
                                        <% } %>
                                        
                                        <% } %>
                                    </tbody>
                                </table>
                     		</form>
                     
                    <!-- 상세보기 처리용 스크립트 -->
                    <script>
                        $(function(){
                        		
	   		        		$(".list-area>tbody>tr").click(function() {
								let bno = $(this).children().eq(1).text();
								
								if($(this).children().eq(7).text() == "Y"){
								location.href = "<%=contextPath %>/boardDetailView.bo?bno=" + bno;
									
								} else {
									
									alert("정지된 게시글입니다.")
								}
							});
	   		        		
                        	
			        	});
					</script>
                                                                
                                <br>
                            
                <!-- 페이징바 -->
			        <div class="page-outer">
					  <ul class="pagination">
					  	<% if(currentPage != 1) { %>
					    <li class="page-item">
					      <a class="page-link" href="<%= contextPath %>/boardList.bo?currentPage=<%= currentPage - 1 %>" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    <% } %>
					    <% for(int p = startPage; p <= endPage; p++ ) { %>
		            		<% if(p != currentPage) { %>
		            			<li class="page-item"><a class="page-link" href="<%= contextPath %>/boardList.bo?currentPage=<%= p %>"><%= p %></a></li>
		            		<% } else { %>
		            			<li class="page-item active" aria-current="page"><a class="page-link"><%= p %></a></li>
		            		<% } %>
		            	<% } %>
					    <% if(currentPage != maxPage) { %>
					    <li class="page-item">
					      <a class="page-link" href="<%= contextPath %>/boardList.bo?currentPage=<%= currentPage + 1 %>" aria-label="Next">
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
    <%@ include file="../common/footer.jsp" %>
</body>