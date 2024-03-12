<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lmc.member.model.vo.Member" %>
   <% String contextPath = request.getContextPath();
   
	// 로그인한 회원의 정보를 session 으로부터 뽑아내기
	// session.getAttribute("키값") : Object (밸류값)
	Member loginUser = (Member)session.getAttribute("loginUser");
	// session 에 담아둔 1회성 알람문구를 변수로 담아두기
	String alertMsg = (String)session.getAttribute("alertMsg");
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LMC Menu Bar</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <!-- jQuery 라이브러리 연동 (온라인 방식) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

    <!-- 아이콘 연동 -->
    <script src="https://kit.fontawesome.com/53a8c415f1.js" crossorigin="anonymous"></script> 


    <style>
    /* 글꼴 */
    @font-face {
        font-family: 'KimjungchulGothic';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302_01@1.0/KimjungchulGothic-Bold.woff2') format('woff2');
        font-style: normal;
    }

    @font-face {
        font-family: 'Dokrip';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Dokrip.woff') format('woff');
        font-weight: normal;
        font-style: normal;
    }

    @font-face {
        font-family: 'LINESeedKR-Bd';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2') format('woff2');
        font-weight: 200;
        font-style: normal;
    }

    @font-face {
        font-family: 'NanumBarunGothic';
        font-style: normal;
        font-weight: 900;
        src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.eot');
        src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.eot?#iefix') format('embedded-opentype'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.woff') format('woff'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.ttf') format('truetype');
        }
    
    body {
        margin: 0;
        padding: 0;
        background-color: #e0e0e0;
    }

    .wrap {
        width: 1200px;
        margin: auto;
    }

    .top-bar {
        background-color: #eeeeee;
        display: flex;
        justify-content: space-between; /* 간격을 최대로 설정하여 양 끝에 정렬 */
        align-items: center;
        padding: 10px 20px;
        font-family: 'LINESeedKR-Bd'; 
    }

    .logo {
        display: flex;
        align-items: center;
    }

    .logo img {
        width: 60px;
        margin-right: 10px;
    }

    .logo a {
        font-family: 'Dokrip';
        text-decoration: none;
        color: black;
        font-size: 30px;
    }

    .menu {
        list-style: none;
        margin: 0;
        padding: 0;
        display: flex;
    }

    .menu li {
        margin-right: 50px;
    }

    .menu a {
        position: relative;
        text-decoration: none;
        color: black;
        font-size: 18px;
        font-weight: bold;
    }

    .menu a::before {
        content: "";
        position: absolute;
        width: 100%;
        height: 1.3px;
        bottom: 0;
        left: 0;
        background-color: black;
        transform: scaleX(0); /* 처음에는 밑줄이 안 보이도록 설정 */
        transition: all 0.3s ease-in-out; /* 애니메이션 적용 */
    }

    .menu a:hover::before {
        visibility: visible;
        transform: scaleX(1); /* 호버됐을 때 밑줄 */
    }


    .search-box {
        display: flex;
        align-items: center;
        background-color: #cfcfcf;
        border-radius: 5px;
        padding: 5px 10px;
        height: 40px;
    }

    .search-box input[type="text"] {
        border: none;
        background-color: transparent; /* 검색 상자 배경 투명 */
        color: black;
        font-size: 14px;
        padding: 5px;
        outline: none;
    }

    .search-box button {
        background-color: transparent; /* 검색 아이콘 배경 투명 */
        border: none;
        color: #333;
        border-radius: 5px;
        margin-left: 5px;
        cursor: pointer;
    }

    .login {
        font-size: 11px;
        margin-right: 15px;
        color: gray;
    }

    .login a {
        text-decoration: none;
        color: gray;
        padding: 7px;
    }
    
    /*회원가입폼 */

	.outerInsert {
		width : 1000px;
		margin : auto;
		margin-top : 50px;
		border : 1px dotted lightgray;
	}

	#enroll-form table { margin : auto; }
	#enroll-form input { margin : 5px; 
        border : 1px solid lightgray;
                         border-radius: 5px;
                   color: #585858;
                   margin-left: 30px;       
    }

    #enroll-form #checked1, #enroll-form #checked2{

        margin-left: 200px;
    }

    #user table{
        font-size: 20px;
    }
    #user b{
        color : rgb(255, 123, 0)
    }
    #user button{
        width: 100px;
        height : 45px;
        border-radius: 5px;
        border : 1px solid lightgray;
    }
    

    #user hr{
        margin-top: 20px;
    }

    #user hr{
        margin-top: 20px;
    }
  
    .modal-body input {
        background-color: rgb(240, 239, 239);
        border-style : none;
    }

    #login{
        float:left;
    }
  
    #footerLogin>button{
        width: 40%;
        height: 50px;
        border-radius: 25px;
    }

</style>
</head>
<body>

<script>
		// script 태그 내에서도 스크립틀릿과 같은 jsp 요소들 사용 가능!!
		
		let msg = "<%= alertMsg %>";
		// "null" / "성공적으로 로그인이 되었습니다."
		
		if(msg != "null") {
			
			alert(msg);
			// 알람창을 띄워준 후 session 에 담긴 해당 메세지는
			// 항상 지워줘야 함!!
			// => 안그러면 menubar.jsp 가 로딩될때마다
			//	  매번 alert 가 뜨기 때문
			
			<%
				session.removeAttribute("alertMsg");
			%>
		}	
	</script>

    <div class="wrap">
        <div class="top-bar">
            <div class="logo">
                <a href="<%= contextPath %>"><img src="<%= contextPath %>/resources/images/Logo.png" alt="로고">LMC</a>
            </div>
            <ul class="menu">
                <li><a href="<%= contextPath %>/list.bo?category=10&currentPage=1">자유게시판</a></li>
                <li><a href="<%= contextPath %>/movieList.me">영화 정보</a></li>
                <li><a href="<%= contextPath %>/list.bo?category=20&currentPage=1">나눔</a></li>
            </ul>
            <div class="search-box">
                <form action="<%= contextPath %>/searchListView.bo?currentPage=1;" method="get">
              	<input type="text" placeholder="Search" name="keyword">
                <button type="submit" name="currentPage" value="1"><i class="fas fa-search"></i></button>
            </form>
            </div>

            <div class="login">
           		 <!-- 로그인 전 -->
            	<%if(loginUser == null){ %>
	                <a href="<%= contextPath %>" data-toggle="modal" data-target="#loginForm">로그인</a> |
	                <a href="<%= contextPath %>/enrollForm.me">회원가입</a>
                <% } else if(loginUser.getMemberId().equals("admin")) { %>
                	<b><%= loginUser.getMemberNick() %>님</b><br>
                	<a href="<%= contextPath %>/menu.ad">관리자페이지</a> | 
					<a href="<%= contextPath %>/logout.me">로그아웃</a>
                <!-- 로그인 후 -->
                <% } else { %>
					<b><%= loginUser.getMemberNick() %>님</b> 환영합니다. <br>
					<a href="<%= contextPath %>/myPageInfo.me">마이페이지</a> | 
					<a href="<%= contextPath %>/logout.me">로그아웃</a>
				<% } %>
            </div>
        </div>
    </div>

<!-- Button to Open the Modal -->
      <!-- 
        data-toggle="modal" : 부트스트랩에서 제공하는 속성, 
                              모달창을 띄울려면 반드시 필요함
        data-target="#myModal" : 부트스트랩에서 제공하는 속성, 
                    내가 띄우고자 하는 모달창을 연결해주는 속성
      -->
       <!--
        
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginForm">
            Open Login Form

        </button>-->
        
        <!-- The Modal -->
        <div class="modal" id="loginForm" style="margin-top: 10%; ">
            <div class="modal-dialog">
                <button type="button" class="close" data-dismiss="modal" style="margin-right: 217px;
                margin-bottom:20px; border-style: none; font-size: 450%; ">&times;</button>
            <div class="modal-content"  style="border-radius: 30px;">
        
                <!-- Modal Header -->
              
                <div class="modal-header" style="background-color: rgb(240,240,240); border-radius: 30px 30px 0px 0px;">
                <h4 class="modal-title" style="margin-left: 200px;">로그인</h4>
                
                </div>
        
                <!-- Modal body -->
                <div class="modal-body">
                
                    <form action="<%= contextPath %>/login.me" method="post">
                        <div class="form-group">
                          
                          <input type="text" class="form-control" placeholder="아이디" 
                                        id="memberId" name="memberId">
                        </div>
                        <div class="form-group">
                          
                          <input type="password" class="form-control" placeholder="비밀번호" 
                                        id="pwd" name="memberPwd">
                        </div>
                        <div class="form-group form-check">

                            <input type="checkbox" id="saveId"
                            name="saveId" value="y">
                            <label for="saveId">
                                아이디 저장
                            </label>

                          
                           <a href="<%= contextPath %>/memberFindAccount.me" style="color : black; float: right;">ID/PW찾기</a>

                        </div>

                        <div id="footerLogin"  style="margin-left: 75px;">
                            <button type="button" id="login" class="btn btn-light" style="margin-right: 10px;" onclick="location.href='<%= contextPath %>/enrollForm.me'">회원가입</button>
                            <button type="submit" id="loginUser" class="btn btn-primary btn-block" style="margin-left: 10px;">로그인</button>
                        </div>
                        
                    </form> 

                </div>
        
            </div>
            </div>
        </div>

</body>
</html>