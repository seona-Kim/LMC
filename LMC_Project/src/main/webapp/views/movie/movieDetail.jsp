<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.lmc.movie.model.vo.Movie" %>

<%
    Movie m = (Movie)request.getAttribute("m");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>영화정보</title>
    <!-- jQuery 라이브러리 연동 (온라인 방식) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>

    <style>
        @font-face {
            font-family: 'Pretendard-Regular';
            src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
            font-weight: 400;
            font-style: normal;
        }

        @font-face {
            font-family: 'NPSfontBold';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2310@1.0/NPSfontBold.woff2') format('woff2');
            font-weight: 700;
            font-style: normal;
        }

        * {
            font-family: 'Pretendard-Regular';
        }

        body {
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        .wrap {
            background-color: #ffffff;
            width: 80%;
            margin: auto;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .content {
            display: flex;
            flex-direction: column;
            padding: 20px;
        }

        .m-content {
            border: 1px solid lightgray;
            padding: 20px;
            border-radius: 15px;
        }

        .btn {
            display: block;
            width: 200px;
            height: 40px;
            line-height: 40px;
            border: 1px #3399dd solid;
            margin: 15px auto;
            background-color: #66aaff;
            text-align: center;
            cursor: pointer;
            color: #ffffff;
            border-radius: 4px;
            transition: all 0.3s;
        }

        .btn:hover {
            background-color: #3388cc;
        }
    </style>
</head>

<body>
    <%@ include file="../common/menubar.jsp" %>
    <div class="wrap">
        <div class="content">
    
            <br>
            <h2 align="center" style="font-family: 'LINESeedKR-Bd';">
            <i class="fas fa-film"></i> 영화정보
            </h2><br>
    
            <div class="movie-info" style="width: 900px; margin: 0 auto; display: flex; align-items: center">
                <div style="flex: 0 0 300px;">
                    <img src="<%= contextPath %>/<%= m.getMovieImg() %>" 
                         alt="<%= m.getMovieName() %>" 
                         class="content_thumbnail"
                         style="width: 100%;
                                border-radius: 5px;
                                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                                vertical-align: bottom;">
                </div>
                <div style="flex: 1; padding-left: 30px; height: 300px;">
                        <h3 style="font-family: 'NPSfontBold'; font-size: 40px; margin-bottom: 10px;">
                            <%= m.getMovieName() %>
                        </h3>
                        <br>
                        <div class="m-content">
                            <h3 style="margin-bottom: 20px;">줄거리</h3>
                            <%= m.getMovieContent() %>
                    </div>
                </div>
            </div>

            <br><br><br><br>
    
            <div class="m-preview" style="text-align: center;">

                <h4 style="margin-bottom: 10px;">예고편</h4>
                
                <iframe width="560" height="315" src="<%= m.getMovieContent2() %>" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

            </div>
            
        </div>
    
        <div align="center">
            <a href="<%= contextPath %>/movieList.me" class="btn">돌아가기</a>
        </div>
        <br>
    </div>
    </div>

    <%@ include file="../common/footer.jsp" %>

</body>

</html>