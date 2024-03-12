<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="com.lmc.member.model.vo.Member,
                 com.lmc.common.model.vo.PageInfo,
                 java.util.ArrayList" %>

<%
// AdminNotieListController 의 request 로 부터 응답데이터 뽑기
PageInfo pi = (PageInfo)request.getAttribute("pi");
ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("list");

// 자주 쓰일법한 변수 세팅
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

        div,form {
            box-sizing: border-box;
            border: px solid blue;
        }
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

        #search{
            width: 100%;
           
            display: flex;
        }
        
        #search>div {
            flex:1;
        }
        
        #controllerButton{
           
            margin-left: auto;
            margin: 24.5px;
        }

        #search_div{
            
            margin-right: 100px;
            padding-right: 100px;
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
                    
                    <h2 align="left" style="margin-bottom: 15px;">회원 목록</h2>
                   
                    
                    <div id="search" style="width : 800px; display: flex; justify-content: flex-start;">

                            
                            <form action="<%=contextPath%>/searchStopMemberList.me?currentPage=1" method="post" >
                               
                                <div class="controllBar">
                                    <select name="searchCategory" id="searchCategory">
                                        <option value="all">전체</option>
                                        <option value="MEMBER_NO">회원번호</option>
                                        <option value="MEMBER_ID">아이디</option>
                                        <option value="MEMBER_NAME">이름</option>
                                        <option value="MEMBER_NICK">닉네임</option>
                                        <option value="EMAIL">이메일</option>
                                    </select>
                                </div>

                                <div class="input-group mb-3 controllBar" id="search_div">
                                    <input type="text" class="form-control" name="keyword" placeholder="Search">
                                    <div class="input-group-append">
                                        <button class="btn btn-secondary" type="submit">검색</button>
                                    </div>
                                </div>

                            </form>
                            
                        <form action="<%=contextPath %>/allStopMember.me?currentPage=<%=currentPage%>" method="post">
                    
                            <div class="recoverOrStop controllBar" id="controllerButton" style="margin-left: 5px;">
                            	<input type="hidden" id="recoverOrStop" name="recoverOrStop" value="0">
                                <button type="submit" class="btn btn-info" value="1" name="recover " >복구</button>
                                <button type="submit" class="btn btn-danger" value="2" name="stop" >정지</button>
                            </div>
                            
                    </div> 
                            
                    <script>
                        // 복구 시 => 1을 넘기고 / 정지 선택 시 => 2를 서블릿에게 넘기도록 설계
                        $(function(){
                            
                            $(".recoverOrStop>button").click(function() {
                                $("#recoverOrStop").val($(this).val());
                            });
                            
                        });
                    </script>
                   
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
                                <th width="70">회원번호</th>
                                <th width="80">이름</th>
                                <th width="80">닉네임</th>
                                <th width="220">이메일</th>
                                <th width="130">아이디</th>
                                <th width="70">상태</th>
                                <th width="100">가입일</th>
                            </tr>
                        </thead>
                        <tbody>

                        <input id="currentPage" type="hidden" value="<%=currentPage%>">
                            <% if(list.isEmpty()) { %>
                                
                                <tr>
                                    <td colspan="8" onclick='event.cancelBubble=true;'>
                                        조회된 리스트가 없습니다.
                                    </td>
                                </tr>
                                
                                <% } else { %>
                                    
                                    <% for(Member m : list) { %>
                                        
                                        <tr>
                                        	<td onclick='event.cancelBubble=true;'>
                                        	<input type="checkbox" name="bordercheck" class="checkNo" value=<%=m.getMemberNo()%>>
                                        	</td>
                                            <td><%= m.getMemberNo() %></td>
                                            <td><%= m.getMemberName() %></td>
                                            <td><%= m.getMemberNick() %></td>
                                            <td><%= m.getEmail() %></td>
                                            <td><%= m.getMemberId() %></td>
                                            <td><%= m.getStatus() %></td>
                                            <td><%= m.getEnrollDate() %></td>
                                        </tr>
                                        
                                        <% } %>
                                        
                                        <% } %>

                                    </tbody>
                                </table>
                    </form>
                          
					<script>
								
                    <!-- 회원 목록 클릭 이벤트 ( 상태 변경 ) -->                    
						
	               		$(".list-area>tbody>tr").click(function() {
	               			
						let mno = $(this).children().eq(1).text();
						let status = $(this).children().eq(6).text();
						let currentPage = $('#currentPage').val();
						
						if($(this).children().eq(6).text() == 'Y' ){
							$(this).children().eq(6).text('N');
						} else {
							$(this).children().eq(6	).text('Y');
							
						}
													
						
						location.href = "<%= contextPath %>/stopMember.me?mno="+mno+"&st="+status+"&currentPage="+currentPage;
						
						});
	               		               		        
	               	
		        	 
				</script>                  
                    
            	<br>
            		<!-- 페이징바 -->
			        <div class="page-outer">
					  <ul class="pagination">
					  	<% if(currentPage != 1) { %>
					    <li class="page-item">
					      <a class="page-link" href="<%= contextPath %>/memberstoplist.me?currentPage=<%= currentPage - 1 %>" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    <% } %>
					    <% for(int p = startPage; p <= endPage; p++ ) { %>
		            		<% if(p != currentPage) { %>
		            			<li class="page-item"><a class="page-link" href="<%= contextPath %>/memberstoplist.me?currentPage=<%= p %>"><%= p %></a></li>
		            		<% } else { %>
		            			<li class="page-item active" aria-current="page"><a class="page-link"><%= p %></a></li>
		            		<% } %>
		            	<% } %>
					    <% if(currentPage != maxPage) { %>
					    <li class="page-item">
					      <a class="page-link" href="<%= contextPath %>/memberstoplist.me?currentPage=<%= currentPage + 1 %>" aria-label="Next">
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