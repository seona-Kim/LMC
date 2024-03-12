<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Website</title>
    
    <!-- 부트스트랩 연동 코드 (온라인 방식) : 순서 주의!! -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <!-- jQuery 라이브러리 연동 (온라인 방식) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        $('.editable').each(function(){
    this.contentEditable = true;
    });
    </script>
    <style>
        

div.editable {
    height: 300px;
    overflow-y: auto;
    width : 70%;
        border : 1px solid lightgray;
       border-radius: 5px;
       background-color: white;
       color: rgb(90, 90, 90);
       margin : auto;
       font-size: 15px;
       
}

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

        .wrap {
            background-color: white;
            width: 80%;
            margin: auto;
            font-family: 'KimjungchulGothic';
        }

        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #e0e0e0;
        }

        .top-bar {
            background-color: #eeeeee;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
        }

        .logo {
            display: flex;
            align-items: center;
        }

        .logo img {
            width: 60px;
            margin-right: 10px;
        }

        .footer_logo img {
            width: 90px;
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
            text-decoration: none;
            color: black;
            font-size: 18px;
            font-weight: bold;
        }

        .search-box {
            display: flex;
            align-items: center;
            background-color: #979797;
            border-radius: 5px;
            padding: 5px 10px;
        }

        .search-box input[type="text"] {
            border: none;
            background-color: transparent;
            color: black;
            font-size: 16px;
            padding: 5px;
            outline: none;
        }

        .search-box button {
            background-color: #f8f8f8;
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

        footer {
            background-color: black;
            padding: 20px;
            text-align: center;
        }

        footer p {
            font-size: 15px;
            font-weight: 500;
            color: white;
        }

        footer a {
            text-decoration: none;
            color: white;
            padding: 5px;
        }

        .content {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 110vh;
            padding: 20px;
        }

        .shape-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
        }

        .shape {
            width: 200px;
            height: 200px;
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 20px;
            margin: 10px;
        }

       #content_1 {

        /*border : 1px solid red;*/
        border-radius: 30px;
        width : 90%;
        height : 76.8%;
        background-color : rgb(228, 228, 228);
       }


/*회원가입폼 */

.outer {
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
    #user>#user_1,#user_2{

        
        margin : auto;
        width : 50%;
        height : 500px;
        margin-bottom : 50px;
        border-radius : 60px;
        background-color : white;
    
    }

    /* 버튼관련스타일 */
    #user_1 button, #user_2 button{
        border-radius: 20px;
        margin-top: 50px;
        width : 20%;
        height : 50px;
        border-style : none;
    }

    #user_1_head, #user_2_head{
        margin-top : 50px;
        padding-top: 50px;
        font-size : 30px;
    }
   
    #user_1_content, #user_2_content {
        margin-top : 40px;
        padding-left: 20px;
        padding-right : 30px;
        font-size : 18px;
        color : rgb(90, 90, 90);
    }
    
    #user_2_content{

        margin-top : 60px;
    }

    #user_1_footer input,#user_2_footer input{
        width : 40%;
        height: 50px;
        margin-top: 40px;
        background-color: rgb(240,240,240);
    }
    </style>
</head>
<body>

		<%@ include file="../common/menubar.jsp" %>

    <div class="wrap">
       
        <div class="content">
           <div id="content_1">

        
                <div id="user">
                    <div style="margin-top: 5%;"><span style="margin-left: 5%;">ID/PW 찾기</span> 
                    <button style="border-radius: 100%; width : 3.5%" onclick="location.href ='<%=contextPath %>';"><</button></div>

                    <div id="user_1" align="center">
                        <div id="user_1_head">
                            이메일 주소로 계정 찾기
                        </div>

                        <div id="user_1_content">
                            회원정보에 등록된 메일 주소로 아이디/비밀번호를 알려드립니다.
                             메일 주소를 입력하고 "ID/PW 찾기" 버튼을 클릭해 주세요.
                        </div>

                        <div id="user_1_footer">
                            <div class="form-group">
                               
                                <input type="email" class="form-control" id="usr" placeholder="이메일 주소">
                            </div>

                            <hr>
                                <div style="margin-left: 20%;">
                                    <ul>
                                        <li align="left" >&nbsp;&nbsp;&nbsp;아이디 : <span id="idFind" style="color : red"></span></li>
                                        <li align="left">비밀번호 : <span id="pwdFind" style="color : red"></span></li>
                                    </ul>
                                </div>
                            <hr>

                            <button class="btn btn-primary btn-block" id="idPwd">ID/PW 찾기</button>
                        </div>
                    </div>
					
					<script>
					$(function() {
						
						$("#idPwd").click(function(){
							
							// 비동기식 요청 : 페이지가 전환되지 않고 그대로 유지
							$.ajax({
								url : "memberFindAccountInsert.me",
								type : "get",
								data : { email : $("#usr").val() }, 
								success : function(result) {
									
									//console.log(result);
									let id = result.memberId + " 입니다.";
									let pwd = result.memberPwd + " 입니다.";
									
									
									$("#idFind").html(id);
									$("#pwdFind").html(pwd);
									
								},
								error : function() {
									console.log("ajax 통신 실패~");
								},
								
							});
							
						});
						
					});
					</script>
                   
          
                        
                </div>

           </div>

        </div>

        <footer>
            <div class="footer_logo">
                <img src="resources/images/Logo_w.png" alt="로고">
            </div>
            <p>© 2023. LMC. all rights reserved.</p>
            <div>
                <a href="#">공지사항</a>
                <a href="#">개인정보취급방침</a>
            </div>
        </footer>
    </div>
</body>
</html>