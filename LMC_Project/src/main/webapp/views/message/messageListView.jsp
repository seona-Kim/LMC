<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.lmc.message.model.vo.Message" %>
<%
	// 조회된 받은 쪽지 리스트를 변수에 담기
	ArrayList<Message> list = (ArrayList<Message>)request.getAttribute("list");

	String status = (String)request.getAttribute("status"); // "recived" 또는 "sent"
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쪽지함</title>
</head>

<style>
    .content {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 100%;
        padding: 20px;
    }

    .message-area {
        width: 850px;
        height: auto;
        margin: auto;
        padding: 20px;
        border-radius: 15px;
        background-color: rgb(243, 243, 243);
    }

    .message-area select {
        height: 35px;
        align-items: center;
        background-color: transparent;
        border-radius: 5px;
        border-style: none;
        margin-bottom: 10px;
    }

    .message-area select:hover {
        cursor: pointer;
    }

    .table>tbody>tr:hover{
        cursor: pointer;
        background-color: rgb(237, 237, 237);
    }
</style>

<body>

	<%@ include file="../common/menubar.jsp" %>

    <div class="wrap">

        <div class="content">

            <br>
            <h2 style="font-family: 'LINESeedKR-Bd'; float: left;"><i class="far fa-envelope"></i> 쪽지함</h2>  
            <br>             

                <div class="message-area">
				    <select name="ms-select" id="ms-select">
				        <option value="recived">받은 쪽지</option>
				        <option value="sent">보낸 쪽지</option>
				    </select>
				
				    <table class="table" align="center" style="border-spacing: 0px;">
					    <thead>
					        <tr>
					           	<th width="60" style="text-align:center;">No.</th>
					            <th width="450" style="text-align:center;">내용</th>
					            <th width="120" style="text-align:center;" id="status">보낸 사람</th>
					            <th style="text-align:center;">날짜</th>
					        </tr>
					    </thead>
					    <tbody>
					        <% if (list.isEmpty()) { %>
					            <tr>
					                <td colspan="4" style="text-align:center;">
					                    받은 쪽지가 없습니다.
					                </td>
					            </tr>
					        <% } else { %>
					            <% for (Message m : list) { %>
					                <tr>
					                    <td style="text-align:center;"><%= m.getDmNo() %></td>
					                    <td><%= m.getDmContent() %></td>
					                    <td style="text-align:center;"><%= m.getDmSendMember() %></td>
					                    <td style="text-align:center;"><%= m.getDmSendDate() %></td>
					                </tr>
					            <% } %>
					        <% } %>
					    </tbody>
					</table>
				</div>
				
            <br>
        </div>
        
        <script>
        	$(function() {
        		
        		$("#ms-select").change(function() {

					let status = $("#ms-select").val();
					// alert(status);
        			
        			location.href = "list.mes?status=" + status;
        		});
        		
        	});
        	
        	$(function() {
        		
        		$("#ms-select>option").each(function(){
        			
        			if($(this).val() == "<%= status %>") {
        				$(this).attr("selected", true);
        			}
        		});
        		
        	});
        	
        	$(function() {
        		
        		if("<%= status %>" == "recived") {
        			
        			$("#status").text("보낸 사람");
        			
        		} else {
        			
        			$("#status").text("받은 사람");
        		}
        		
        	});
        	
        	$(function() {
        		
        		$(".message-area>table>tbody>tr").click(function() {
        			
        			// console.log("잘 클릭됬나..?");
        			
        			// 클릭했을 때 자손요소 탐색을 통해
        			// 해당 게시글의 공지사항 번호를 추출해서 넘겨야 함
        			// => 해당 tr 요소의 자손 중에서 첫번째 td 영역의 내용이 필요
        			let dno = $(this).children().eq(0).text();
        			
        			// console.log(mno);
        			
        			// 요청할url주소?키=밸류&키=밸류&..
        			// ? 뒤의 내용들을 "쿼리스트링" 이라고 한다.
        			// get 방식의 경우 쿼리스트링이 노출되는 방식임!!
        			// location.href 속성으로 요청하면 get 방식임!!
        			// => 대놓고 쿼리스트링을 직접 만들어서 넘기기 가능
        					
        			location.href = "<%= contextPath %>/detail.mes?dno=" + dno + "&status=<%= status %>";
        			
        		});
        		
        	});
        </script>

    </div>

	<%@ include file="../common/footer.jsp" %>

</body>
</html>