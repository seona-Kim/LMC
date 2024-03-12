<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지 메뉴</title>
	
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <!-- jQuery 라이브러리 연동 : 온라인 방식 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

    <!-- 아이콘 -->
    <script src="https://kit.fontawesome.com/22698b3d17.js" crossorigin="anonymous"></script>

    <style>
        
        /* logo 와 menu font 외부로 불러옴 */
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
        
        /*좌측 관리자 프로필 + 메뉴 크기*/
        #content_1 { width: 200px; height: auto; margin-top: 30px; margin-left: 20px;}
       
        #adminProfile_img { width: 200px; height: 200px; border: 1px solid darkgrey}
        #adminProfile { 
            border-left: 1px solid darkgrey;
            border-right: 1px solid darkgrey;
            border-bottom: 1px solid darkgrey;
            padding: 5px;
        }
        
        #adminMenu { 
            height: 400px; 
            margin-top: 15px; 
            border: 1px solid darkgrey; 
            padding: 0px; 
            font-family: 'KimjungchulGothic';
        } 
        
        /* 관리자 메뉴 관련 스타일 */
        #adminMenu>div { 
            /* 관리자 메뉴 상위 제목 */
            width: 100%; 
            font-size: 18px; 
            font-weight: bolder; 
            padding-top: 5px; 
            padding-bottom: 5px;
            padding-left: 10px; 
            background-color: #e0e0e0;
        }
        
        ul { padding-left: 15px; color: dimgrey;} 
        li { margin-top: 10px; list-style: none;}
        li:hover {
            color: black;
            text-decoration: underline;
            text-decoration-color: dimgray;
            text-underline-position : under;
            text-decoration-thickness: 1.5px;
            cursor: pointer;
        }

        li>a { color: dimgray; }
        li>a:hover{ color: black; }
        
        /*관리자 프로필 하단 id, email 스타일 */
        
        #adminProfile>input { 
            border: none;
            outline: none; 
            font-size: 16px;
        }
        
        #adminProfile>#admin_id { font-weight: bolder; width: 99%; font-family: 'KimjungchulGothic';}
        #adminProfile>#admin_email {color: dimgray; width: 99%; font-family: 'KimjungchulGothic';}
        
        #home { 
            height: 45px;
            border: 1px solid darkgray;
            line-height: 45px;
            font-size: 17px;
            font-weight: bolder;
            font-family: 'KimjungchulGothic';
            margin-top: 15px;
            padding-left: 10px;
            color: white;
            background-color: rgb(44, 44, 44);
        }

        #home:hover {
            background-color:   rgba(44, 44, 44, 0.877);
            cursor: pointer;
        }
       
        </style>
</head>

<body>
	 
    <div  id="content_1">
                
        <div class="shadow">
            <div id="adminProfile_img">
                <a href="<%=contextPath%>/myPageInfo.me">
                    <img src="<%=contextPath + memberImg%>" height="100%">
                </a>
            </div>
            <div id="adminProfile" style="padding-left: 10px;"> 
                <input type="text" id="admin_id" value="<%= memberNick %>">
                <input type="text" id="admin_email" value="<%= email %>">                
            </div>
        </div>

        <div class="shadow" id="home" onclick="location.href='<%=contextPath%>/menu.ad';"><i class="fa-solid fa-house"></i>  관리자 페이지 홈</div>
        
        <div class="shadow" id="adminMenu">

            <div >게시글 관리</div>
            
            <ul>
                <li><a href="<%=contextPath%>/noticeList.no?currentPage=1">공지사항 조회</a></li>
                <li><a href="<%=contextPath%>/boardList.bo?currentPage=1">게시글 조회</a></li>
            </ul>

            <div>회원 관리</div>
            <ul>
                <li><a href="<%=contextPath%>/memberlist.me?currentPage=1">회원 정보 수정</a></li>
                <li><a href="<%=contextPath%>/memberstoplist.me?currentPage=1">회원 정지</a></li>
            </ul>

            <div>영화정보 관리</div>
            <ul>
                <li><a href="<%=contextPath%>/movieList.me">영화 정보 작성</a></li>
                <li><a href="<%=contextPath%>/views/common/500.jsp">영화 등록 요청 확인</a></li>
                <li><a href="<%=contextPath%>/views/common/500.jsp">한줄평 관리</a></li>
            </ul>

        </div>

    </div>

</body>
</html>