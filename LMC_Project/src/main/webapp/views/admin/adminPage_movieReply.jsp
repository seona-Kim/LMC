<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>일반게시글관리</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <!-- jQuery 라이브러리 연동 : 온라인 방식 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

    <!-- 차트 Resources -->
    <script src="https://cdn.amcharts.com/lib/5/index.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/percent.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
    
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
        .outer {
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
                
                <div class="outer">
                    
                    <h2 align="left">한줄평 관리</h2>
                    <br>
                    
                    <div id="search" style="width : 800px; display: flex; justify-content: flex-start;">

                            <form action="" method="get"  >
                                <div  class="input-group mb-3">
                                    <input type="text" class="form-control" placeholder="Search">
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
                                <th width="60">글번호</th>
                                <th width="70">카테고리</th>
                                <th width="400">제목</th>
                                <th width="100">작성자</th>
                                <th width="70">조회수</th>
                                <th width="100">작성일</th>
                            </tr>
                        </thead>
                        <tbody>

                            <%--                     
                            <% if(list.isEmpty()) { %>
                                
                                <tr>
                                    <td colspan="6">
                                        조회된 리스트가 없습니다.
                                    </td>
                                </tr>
                                
                                <% } else { %>
                                    
                                    <% for(Board b : list) { %>
                                        
                                        <tr>
                                            <td><%= b.getBoardNo() %></td>
                                            <td><%= b.getCategory() %></td>
                                            <td><%= b.getBoardTitle() %></td>
                                            <td><%= b.getBoardWriter() %></td>
                                            <td><%= b.getCount() %></td>
                                            <td><%= b.getCreateDate() %></td>
                                        </tr>
                                        
                                        <% } %>
                                        
                                        <% } %>
                                     --%> 

                                    <tr>
                                        <td>1</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>2</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>3</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>4</td>
                                        <td>영화정보</td>
                                        <td>아잉</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>5</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>6</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>7</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>8</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>10</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>11</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>12</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>13</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>14</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>15</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr> 
                                    <tr>
                                        <td>16</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>17</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>18</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>19</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>
                                    <tr>
                                        <td>20</td>
                                        <td>영화정보</td>
                                        <td>으앙</td>
                                        <td>관리자</td>
                                        <td>1</td>
                                        <td>2023-10-03</td>
                                    </tr>

                                    </tbody>
                                </table>
                                                                
                                <br>
                            
                                <!-- 페이징바 -->
                                <div class="paging-area" align="center">
                                                                        
                                        <!-- 1 페이지가 아닌 경우에만 이전페이지로 이동 가능하게끔 -->
                                    <button onclick="location.href = '';">
                                        &lt;
                                    </button>
                            
                                    <button >1</button>   <button >2</button>   <button >3</button>   <button >4</button>   <button >5</button> 
                                                            
                                    <button onclick="location.href = '';">
                                        &gt;
                                    </button>
                  
                                </div>
            
                </div>

            </div>
            
        </div>
        
		<%@ include file="../common/footer.jsp" %>
		
    </div>

</body>