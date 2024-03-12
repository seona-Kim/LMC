<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPageCommend</title>
 	<style>
        .wrapBody {
            width: 1200px;
            height: 800px;
            margin: auto;
            background-color: #ffffff;
        }
        .wrapBody>div {
            width: 100%;
        }
        #body { 
            height: 100%; 
        }
        #body>div {
            height: 100%;
            float: left;
        }
        #body_1 { width: 25%; }
        #body_2 { width: 75%; }
        #body_1 a { 
            text-decoration: none;
            color: black;
        }

        .outer {
            width: 100%;
            margin: auto;
            border: 1px dotted lightgray;
            margin-top: 50px;
            padding: 50px;
        }
        .list-area {
        	height: 100%;
            border-spacing: 0px;
            border: 1px solid lightgray;
            text-align: center;
        }

        .list-area>tbody>tr:hover { 
            background-color: lightgray; 
            cursor: pointer;
        }

        .page-outer {
        	width: 100%;
        	margin: auto;
        }
        .pagination {
        	width:200px;
			margin-left: auto;
			margin-right: auto;
        }
    </style>
</head>
<body>
	
	<%@ include file = "../common/menubar.jsp" %>
	
	<div class="wrapBody">
          <div id="body">
              <div id="body_1">
                  <br><br><br>
                  <div align="center"><img src="<%= contextPath %>/resources/images/pngwing.com.png" height="150px"></div>
                  <br>
                  <div align="center"><h4>홍길동 님</h4></div>
                  <br><br><br>
                  <div align="center"><h4><a href="<%= contextPath %>/myPage.me">내 정보 수정</a></h4></div>
                  <hr>
                  <div align="center"><h4><a href="<%= contextPath %>/myPageBoard.me">내가 쓴 게시글 조회</a></h4></div>
                  <hr>
                  <div align="center"><h4><a href="<%= contextPath %>/myPageReply.me">내가 쓴 댓글 조회</a></h4></div>
                  <hr>
                  <div align="center"><h4><a href="<%= contextPath %>/myPageCommend.me">추천한 게시글 조회</h4></div>
                  <hr>
                  <div align="center"><h4><a href="<%= contextPath %>/myPageDmList.me">쪽지함</a></h4></div>
              </div>
              <div id="body_2">
                   <div class="outer">
			        <br>
			        <h2 align="center">쪽지함</h2>
			        
			        <br>
			        <table  align="center">
				      	 <tr>
		            		<td width="70">
			            		 <select>
						        	<option>받은 쪽지함</option>
						        	<option>보낸 쪽지함</option>
					        	</select>
		        			</td>
		        			<td colspan="5" width="600"></td>
				          </tr>
			        </table>
			        
			        <br>
			       
			        <table class="list-area" align="center" border="1">
			            <thead> 
			                <tr>
			                    <th width="70"></th>
			                    <th width="70">카테고리</th>
			                    <th width="300">제목</th>
			                    <th width="100">작성자</th>
			                    <th width="70">조회수</th>
			                    <th width="100">작성일</th>
			                </tr>
			            </thead>
			            <tbody>
			                <tr>
			                    <td>98</td>
			                    <td>자유</td>
			                    <td>내가 작성한 글1</td>
			                    <td>user01</td>
			                    <td>56</td>
			                    <td>2023-09-27</td>
			                </tr>
			                <tr>
			                    <td>99</td>
			                    <td>자유</td>
			                    <td>내가 작성한 글2</td>
			                    <td>user01</td>
			                    <td>56</td>
			                    <td>2023-09-27</td>
			                </tr>
			                <tr>
			                    <td>100</td>
			                    <td>자유</td>
			                    <td>내가 작성한 글3</td>
			                    <td>user01</td>
			                    <td>56</td>
			                    <td>2023-09-27</td>
			                </tr>
			                <tr>
			                    <td>101</td>
			                    <td>자유</td>
			                    <td>내가 작성한 글4</td>
			                    <td>user01</td>
			                    <td>56</td>
			                    <td>2023-09-27</td>
			                </tr>
			                <tr>
			                    <td>102</td>
			                    <td>자유</td>
			                    <td>내가 작성한 글5</td>
			                    <td>user01</td>
			                    <td>56</td>
			                    <td>2023-09-27</td>
			                </tr>
			            </tbody>
			        </table>
			        <br><br>
			        
			        <!-- 페이징바 -->
			        <div class="page-outer" >
					  <ul class="pagination">
					    <li class="page-item">
					      <a class="page-link" href="#" aria-label="Previous">
					        <span aria-hidden="true">&laquo;</span>
					      </a>
					    </li>
					    <li class="page-item active" aria-current="page"><a class="page-link" href="#">1</a></li>
					    <li class="page-item"><a class="page-link" href="#">2</a></li>
					    <li class="page-item"><a class="page-link" href="#">3</a></li>
					    <li class="page-item">
					      <a class="page-link" href="#" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
					    </li>
					  </ul>
					</div>
			    </div>
              </div>
          </div>
      </div>
      <%@ include file = "../common/footer.jsp" %>
</body>
</html>