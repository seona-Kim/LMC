<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.lmc.message.model.vo.Message,
				com.lmc.member.model.vo.Member,
				com.lmc.board.model.vo.Board" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>쪽지 보내기</title>

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

        .btn-reset {
            margin-top: 20px;
            margin-left: 15px;
            background-color: #c7c7c7;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            float: right;
        }

        .btn-reset:hover {
            background-color: #b1b1b1;
        }
        
        #message-write {
            resize: none;
            width: 1000px;
            height: 300px;
            border: solid 1px lightgray;
            border-radius: 10px;
            padding: 20px;
        }
    </style>
</head>
<body>

    <%@ include file="../common/menubar.jsp" %>
    
    	<% 
    	
			Message m = (Message)request.getAttribute("m");
    	
			Member profile = (Member)request.getAttribute("profile");
		
			
			String memberImg = (profile.getMemberImg() == null) ?
				"/resources/images/pngwing.com.png" : profile.getMemberImg();
			
			int sendMemberNo = loginUser.getMemberNo();
			int reciveMemberNo = (int)request.getAttribute("dmRecive");
			
			System.out.println(sendMemberNo);
			System.out.println(reciveMemberNo);			
		%>

    <div class="wrap">
        <div class="content">
            <form action="<%= contextPath %>/insert.mes" method="post">
            
            	<input type="hidden" name="reciveMember" value="<%= profile.getMemberNo() %>">
            	<%--<input type="hidden" name="dmSend" value="<%= m.getDmSendMember() %>"> --%>
                
                <h2 style="font-family: 'LINESeedKR-Bd';">답장하기</h2> <br>

                <label for="recipient" style="margin-left: 10px;">
                    <span class="userpf" style="align-items: center;">
                        To. &nbsp;
                        <span id="u-pfimg" id="userProfile" data-username=""> <!-- 사용자 프로필 이미지 -->
                            <img src="<%=contextPath%><%=memberImg%>" width="30" height="30" style="border-radius : 50%;">
                        </span>
                        <span id="u-nickname">
                            <%= profile.getMemberNick() %>
                        </span>
                    </span>	
                </label>

                <br>

                <div class="m-content">
                    <textarea name="content" id="message-write" placeholder="내용을 입력해 주세요."></textarea>
                </div>

                <button type="reset" class="btn-reset">취소</button>
                <button type="submit" class="btn-submit">보내기</button>

            </form>
        </div>
    </div>

    <%@ include file="../common/footer.jsp" %>


</body>
</html>
