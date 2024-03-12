<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lmc.notice.model.vo.Notice,
                 com.lmc.common.model.vo.PageInfo,
                 java.util.ArrayList" %>

<%
// AdminNotieListController 의 request 로 부터 응답데이터 뽑기
PageInfo pi = (PageInfo)request.getAttribute("pi");
ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");

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
    <title>공지사항 관리</title>

    <style>
        
        td { font-family: 'KimjungchulGothic' }

        /* 콘텐츠의 공통속성 */
        #admin_content { 
            width: auto; /*총 너비(1200px)의 80% = 960px */
            margin-left: 5%;
            margin-right: 5%;
            min-height: 800px;
            background-color: rgba(255, 255, 255, 0.829)
        }
        
        #admin_content>div{ float: left;}
        
        /*우측 관리자 메뉴 크기*/
        #admin_content_2 { 
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
        
        <div id="admin_content">
            
            <%@ include file="../common/adminMenu.jsp" %>
            
            <div id="admin_content_2">
                
                <div class="admin_outer">
                    
                    <h2 align="left">공지게시판</h2>
                    <br>
                    
                    <div id="search" style="width : 800px; display: flex; justify-content: flex-start;">

                            
                            <div style="margin-left: auto;">
                                <a href="<%= contextPath %>/enrollForm.no" class="btn btn-secondary btn-sm">글작성</a>
                            </div>
                     
                    </div>

                    <br>
					<form action="<%= contextPath %>/delete.no" method="post">
                    <table class="list-area" align="center" border="1">
                        <thead>
                            <tr>
                            	<th width="30"><input type="checkbox" name="deleteAll"></th>
                                <th width="60">글번호</th>
                                <th width="70">카테고리</th>
                                <th width="400">제목</th>
                                <th width="100">작성자</th>
                                <th width="70">조회수</th>
                                <th width="100">작성일</th>
                            </tr>
                        </thead>
                        <tbody>

                                                
                            <% if(list.isEmpty()) { %>
                                
                                <tr>
                                    <td colspan="7" onclick='event.cancelBubble=true;'>
                                        조회된 리스트가 없습니다.
                                    </td>
                                </tr>
                                
                            <% } else { %>
                                    
                               <% for(Notice n : list) { %>
                                   
                                   <tr>
                                   	   <td class="notSelect"><input type="checkbox" name="deleteNNo" value="<%= n.getNoticeNo() %>"></td>
                                       <td><%=n.getNoticeNo()%></td>
                                       <td>공지</td>
                                       <td><%=n.getNoticeTitle() %></td>
                                       <td>관리자</td>
                                       <td><%=n.getViews()%></td>
                                       <td><%=n.getCreateDate()%></td>
                                   </tr>
                                   
                               <% } %>
                                        
                            <% } %>
                                    
                         </tbody>
                    </table>
                    <br>
                    <div style="width:800px;" align="right">
						<button type="submit" class="btn btn-warning btn-sm" name="deleteNotice">삭제하기</button>
						<br><br>
					</div>
			        <br>
					</form>
					<script type="text/javascript">
			        	$(function(){
			        		$("input[name='deleteAll']").change(function(){
			        			$("input[name='deleteNNo']").prop('checked', $("input[name='deleteAll']").is(":checked"));
			        		});
			        		
			        		$("input[name='deleteNNo']").click(function() {
			        			if($("input[name='deleteNNo']:checked").length != $("input[name='deleteNNo']").length){
			        				$("input[name='deleteAll']").prop('checked', false);
			        			} else {
			        				$("input[name='deleteAll']").prop('checked', true);
			        			}
			        		});
			        	
			        		$(".list-area>tbody>tr").children().not(".notSelect").click(function() {
								let nno = $(this).parent().children().eq(1).text();
								location.href = "<%= contextPath %>/detail.no?nno=" + nno;
							});
			        		
			        		$("button[name='deleteNotice']").click(function() {
			        			var result = confirm($(".list-area>tbody input[name='deleteNNo']:checked").length + "개의 게시글을 삭제하시겠습니까?");
								return result;
			        		});
			        	});
					</script>
   
                    <br>
                    <!-- 페이징바 -->
			        <div class="page-outer">
					  <ul class="pagination">
					  	<% if(currentPage != 1) { %>
					    <li class="page-item">
					      <a class="page-link" href="<%= contextPath %>/noticeList.no?currentPage=<%= currentPage - 1 %>" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    <% } %>
					    <% for(int p = startPage; p <= endPage; p++ ) { %>
		            		<% if(p != currentPage) { %>
		            			<li class="page-item"><a class="page-link" href="<%= contextPath %>/noticeList.no?currentPage=<%= p %>"><%= p %></a></li>
		            		<% } else { %>
		            			<li class="page-item active" aria-current="page"><a class="page-link"><%= p %></a></li>
		            		<% } %>
		            	<% } %>
					    <% if(currentPage != maxPage) { %>
					    <li class="page-item">
					      <a class="page-link" href="<%= contextPath %>/noticeList.no?currentPage=<%= currentPage + 1 %>" aria-label="Next">
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