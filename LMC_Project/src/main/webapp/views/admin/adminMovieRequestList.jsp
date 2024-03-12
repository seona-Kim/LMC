<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.lmc.movie.model.vo.Movie,
                 com.lmc.common.model.vo.PageInfo,
                 java.util.ArrayList" %>    
                 
<%
// Controller 단에서 정보 받아오기
PageInfo pi = (PageInfo)request.getAttribute("pi");
ArrayList<Movie> list = (ArrayList<Movie>)request.getAttribute("list");

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
    <title>영화정보게시판(임시)</title>

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

                            
                            <form action="" method="get"  >
                                <div  class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="Search">
                                    <div class="input-group-append">
                                        <button class="btn btn-secondary" type="submit">검색</button>
                                    </div>
                                </div>
                            </form>
                            <div style="margin-left: auto;">
                                <button type="button" class="btn btn-danger"; onclick="체크한게시글삭제상태변경">삭제</button>
                            </div>
                     
                    </div>
                   
                    <input type="checkbox" name="bordercheck" style="margin-bottom: 15px;" value="selectall" onclick="selectAll(this)"> 전체 선택

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
                                <th width="350">제목</th>
                                <th width="100">작성자</th>
                                <th width="70">조회수</th>
                                <th width="100">작성일</th>
                            </tr>
                        </thead>
                        <tbody>

                            <% if(list.isEmpty()) { %>
                                
                                <tr>
                                    <td colspan="6">
                                        조회된 리스트가 없습니다.
                                    </td>
                                </tr>
                                
                                <% } else { %>
                                    
                                    <% for(Movie m : list) { %>
                                        
                                        <tr>
                                      		<td><input type="checkbox" name="bordercheck"></td>
                                            <td><%= m.getMovieNo() %></td>
                              
                                        </tr>
                                        
                                        <% } %>
                                        
                                        <% } %>
                                    </tbody>
                                </table>
                                                                
                                <br>
                            
            <!-- 페이징바 -->
	        <div class="paging-area" align="center">
	        
	        	<% if(currentPage !=1) { %>
	        	<!--  1페이지가 아닌 경우에만 이전페이지로 이동 가능하게끔 -->
	        	<button onclick="location.href = '<%=contextPath%>/movierequest.mo?currentPage=<%= currentPage - 1%>';">
	        		&lt;
	        	</button>
	        	<% } %>
	        	
	        	
	            <% for(int p = startPage; p <= endPage; p++ ) { %>
	            
	            	<% if(p != currentPage) { %>
	            	
	            		<button onclick="location.href='<%=contextPath%>/movierequest.mo?currentPage=<%=p%>';"><%=p%></button>
	            	
	            	<% } else { %>
	            	
	            		<!-- 현재 내가 보고있는 페이지일 경우 클릭 안되게끔 -->
	            		<button disabled><%= p %></button>
	            	
	            	<% } %>
	            	
	            <% } %>
	            
	            <% if(currentPage < maxPage) { %>
	            <!--  마지막 페이지가 아닌 경우에만 다음페이지로 이동하게끔 -->
	            <button onclick="location.href = '<%=contextPath%>/movierequest.mo?currentPage=<%= currentPage + 1%>';">&gt;</button>
	            <% } %>
	        </div>
            
        </div>

        </div>
        
    </div>
    </div>
    <%@ include file="../common/footer.jsp" %>

</body>