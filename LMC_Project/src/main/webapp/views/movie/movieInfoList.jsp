<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, 
				 com.lmc.movie.model.vo.Movie" %>
<%
	// 조회된 사진게시글 리스트를 뽑기
	ArrayList<Movie> list 
		= (ArrayList<Movie>)request.getAttribute("list");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LMC</title>

</head>

<style>
  .wrap {
      background-color: white;
      width: 80%;
      margin: auto;
      font-family: 'NanumBarunGothic';
  }

  body {
      background-color: #e0e0e0;
      margin: 0;
      padding: 0;
  }

  .content {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      height: auto;
      padding: 20px;
  }

  #back {
      height: auto;
      width: 90%;
      border-radius: 10px;
      border: 2px dotted lightgrey; 
  }

  .movie_img {
      cursor: pointer;
      float: left;
      margin: 25px;
  }
  
  .movie_img:hover img {	
      -webkit-filter: blur(3px);
      filter: blur(3px);
  }

  .btn {
      display:block;
      width:200px; 
      height:40px; 
      line-height:40px; 
      border:1px #3399dd solid;;
      margin:15px auto; 
      background-color:#66aaff;  
      text-align:center; 
      cursor: pointer;  
      color:#333; 
      transition:all 0.9s, color 0.3;  
  }

  .btn:hover {
      color:#fff;
  }

  .hover1:hover {
      box-shadow:200px 0 0 0 rgba(0,0,0,0.5) inset;
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

          <div id="back">
              
              
              <div id="movie-detail">
                  <% if(list.isEmpty()){ %>
                      <br><br><br>
                      <p align="center" style="font-size:30px;">등록된 게시글이 없습니다.</p>
                  <% } else { %>
                      <% for(Movie m : list) { %>
                          <div class="movie_img" align="center;">
                              <input type="hidden" value="<%= m.getMovieNo() %>" name="movieNo">
                              <img src="<%= contextPath %>/<%= m.getMovieImg() %>" width="200px" height="300px">
                              <h5 style="text-align: center; font-size: 15px; font-weight: bold; margin-top: 10px;"><%= m.getMovieName() %></h5>
                          </div>
                      <% } %>
                  <% } %>
              </div>
          </div>
  
          <div align="center"> 
              <% if(loginUser.getMemberId().equals("admin")){ %>
                  <a href="<%= contextPath %>/enrollForm.th" class="btn hover1">작성하기</a>	
              <% }%>
          </div>
      </div>
  </div>
  
  <%@ include file="../common/footer.jsp" %>
  
  <script>
      $(function() {	
          $(".movie_img").click(function() {
              let bno = $(this).children().eq(0).val();
              location.href = "<%= contextPath %>/detail.th?bno=" + bno;
          });
      });
  </script>

</body>
</html>