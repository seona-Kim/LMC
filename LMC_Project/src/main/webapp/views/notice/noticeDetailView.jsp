<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lmc.notice.model.vo.Notice,
				com.lmc.member.model.vo.Member" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나눔 상세 조회</title>
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

    .content-form {
        width: 850px;
        display: flex;
        flex-direction: column; 
        align-items: center;
        justify-content: center;
    }

    .c-header {
        width: 100%;
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .userpf {
        text-align: left;
    }

    .userpf, .c-etc, .c-time {
        width: 200px;
    }

    .c-time {
        color: gray;
        text-align: center;
    }

    .content-form .c-etc {
        text-align: right;
    }

    .c-header>div { 
        display : inline-block;
    }

    .c-etc .far {
        margin-left: 15px;
    }

    .c-content {
        border: 1px solid lightgray;
        height: 500px;
        width: 850px;
        padding: 20px;
        border-radius: 15px;
        margin: 20px 0px;
    }

    .title {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .title>#title-category {
        font-size: 13px;
    }

</style>

<body>

    <%@ include file="../common/menubar.jsp" %>
	
	<% 
		Notice n = (Notice)request.getAttribute("n");
		Member profile = (Member)request.getAttribute("profile");
	%>
	
    <div class="wrap">

        <div class="content">

            <div class="content-form">

                <br>
                <div class="title">
                    <p id="title-category"><i class="fas fa-exclamation-circle"></i> 공지사항</p>
                    <h2 align="center" style="font-family: 'LINESeedKR-Bd';"><%= n.getNoticeTitle() %></h2>
                </div>
                <br>

                <span class="c-header">
                    <span class="userpf" >
                        <span id="u-pfimg" id="userProfile" data-username=""> <!-- 사용자 프로필 이미지 -->
                            <img src="<%= contextPath %>/resources/images/pngwing.com.png" width="30" height="30" style="border-radius : 50%;">
                        </span>
                        <span id="u-nickname">
                            관리자
                        </span>
                    </span>
                    <span class="c-time">
                        <%= n.getCreateDate() %>
                    </span>
                    <span class="c-etc">
                        <i class="far fa-eye"> <%= n.getViews() %></i> <!-- 조회수 -->
                    </span> 
                </span>

                <div class="c-content">
        				<%= n.getNoticeContent() %>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="../common/footer.jsp" %>


</body>
</html>