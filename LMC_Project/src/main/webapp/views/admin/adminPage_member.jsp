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
    <title>회원관리</title>

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
                    
                    <h2 align="left">회원 정보 수정</h2>
                    <br>
                    
                    <div id="search" style="width : 800px; display: flex; justify-content: flex-start;">
                    
                            <form action="<%=contextPath%>/searchMemberList.me?currentPage=1" method="post"  accept-charset="UTF-8" >
                                <select name="searchCategory" id="searchCategory">
                                    <option value="all">전체</option>
                                    <option value="MEMBER_NO">회원번호</option>
                                    <option value="MEMBER_ID">아이디</option>
                                    <option value="MEMBER_NAME">이름</option>
                                    <option value="MEMBER_NICK">닉네임</option>
                                    <option value="EMAIL">이메일</option>
                                </select>
                                <div  class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="Search" name="keyword">
                                    <div class="input-group-append">
                                        <button class="btn btn-secondary" type="submit">검색</button>
                                    </div>
                                </div>
                            </form>
                            
                    </div>

                    <br>

                    <table class="list-area" align="center" border="1">
                        <thead>
                            <tr>
                                <th width="90">회원번호</th>
                                <th width="90">닉네임</th>
                                <th width="300">이메일</th>
                                <th width="150">아이디</th>
                                <th width="70">상태</th>
                                <th width="100">가입일</th>
                            </tr>
                        </thead>
                        <tbody>

                            <% if(list.isEmpty()) { %>
                                
                                <tr>
                                    <td colspan="6" onclick='event.cancelBubble=true;'>
                                        조회된 리스트가 없습니다.
                                    </td>
                                </tr>
                                
                                <% } else { %>
                                    
                                    <% for(Member m : list) { %>
                                        
                                        <tr>
                                            <td><%= m.getMemberNo() %></td>
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
                                
                                <!-- 상세보기 처리용 스크립트 -->
			                    <script>
			                        $(function(){
			                        		
				   		        		$(".list-area>tbody>tr").click(function() {
											let mno = $(this).children().eq(0).text();
											
											if($(this).children().eq(4).text() == "Y"){
											location.href = "<%= contextPath %>/member.no?mno=" + mno;
												
											} else {
												
												alert("정지된 회원입니다.")
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
					      <a class="page-link" href="<%= contextPath %>/memberlist.me?currentPage=<%= currentPage - 1 %>" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    <% } %>
					    <% for(int p = startPage; p <= endPage; p++ ) { %>
		            		<% if(p != currentPage) { %>
		            			<li class="page-item"><a class="page-link" href="<%= contextPath %>/memberlist.me?currentPage=<%= p %>"><%= p %></a></li>
		            		<% } else { %>
		            			<li class="page-item active" aria-current="page"><a class="page-link"><%= p %></a></li>
		            		<% } %>
		            	<% } %>
					    <% if(currentPage != maxPage) { %>
					    <li class="page-item">
					      <a class="page-link" href="<%= contextPath %>/memberlist.me?currentPage=<%= currentPage + 1 %>" aria-label="Next">
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