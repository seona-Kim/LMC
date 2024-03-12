<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.lmc.message.model.vo.Message,
				com.lmc.member.model.vo.Member" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>받은 쪽지</title>

    

    <style>
        
        .content {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100%;
            padding: 20px;
        }

        form {
            padding: 20px;
            border-radius: 10px;
            margin-top: 20px;
            
        }

        .m-header {
            width: 500px;
            display: flex; align-items: center; justify-content: space-between;
        }

        .board-title {
            margin-bottom: 10px;
            margin-top: 20px;
            justify-content: space-between;
        }

        .btn-submit {
            margin-top: 20px;
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
        
        #message-write {
            resize: none;
            width: 1000px;
            height: 300px;
            border: solid 1px lightgray;
            border-radius: 10px;
            padding: 20px;
        }

        .c-time {
            color: gray;
            float: right;
            margin-left: 12px;
            font-size: 12px;
        }
    </style>
</head>
<body>	

    <%@ include file="../common/menubar.jsp" %>
    
    <% 
		Message m = (Message)request.getAttribute("m");
		Member profile = (Member)request.getAttribute("profile");

		String status = (String)request.getAttribute("status");
		
		String memberImg = (profile.getMemberImg() == null) ?
			"/resources/images/pngwing.com.png" : profile.getMemberImg(); 
			
	
	%>

    <div class="wrap">
        <div class="content">
            <form action="<%= contextPath %>/enrollFormReply.mes" method="post">
            
            <input type="hidden" name="dmRecive" value="<%= profile.getMemberNo() %>">
                
                <% if(status.equals("recived")) { %>
                	<h2 style="font-family: 'LINESeedKR-Bd';">받은 쪽지</h2> <br>
                <% } else { %>
                	<h2 style="font-family: 'LINESeedKR-Bd';">보낸 쪽지</h2> <br>
                <% } %>

                <label for="receive" style="margin-left: 10px;">
                    <span class="userpf" style="align-items: center;">
                        <% if(status.equals("recived")) { %>
		                	<span>From. &nbsp;</span>
		                <% } else { %>
		                	<span>To. &nbsp;</span>
		                <% } %>
                        
                        <span id="u-pfimg" id="userProfile" data-username=""> <!-- 사용자 프로필 이미지 -->
                            <img src="<%=contextPath%><%=memberImg%>" width="30" height="30" style="border-radius : 50%;">
                        </span>
                        <span id="u-nickname">
                            <%= profile.getMemberNick() %>
                        </span>
                    </span>
                    <span class="c-time" style="margin-top: 3px;">
                        <%= m.getDmSendDate() %>
                    </span>
                </label>

                <br>

                <div class="m-content">
                    <textarea name="message-write" id="message-write" readonly><%= m.getDmContent() %></textarea>
                </div>

                <button type="submit" class="btn-submit">답장하기</button>

            </form>
        </div>
    </div>

    <%@ include file="../common/footer.jsp" %>


</body>
</html>
