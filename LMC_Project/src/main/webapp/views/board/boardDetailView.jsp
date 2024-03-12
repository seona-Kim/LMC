<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.lmc.board.model.vo.Board,
				com.lmc.board.model.vo.Attachment,
				com.lmc.member.model.vo.Member"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 조회</title>
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
	display: inline-block;
}

.c-etc .far {
	margin-left: 15px;
}

.c-content {
	border: 1px solid lightgray;
	height: auto;
	width: 850px;
	padding: 20px;
	border-radius: 15px;
	margin: 20px 0px;
}

#com-icon {
	font-size: 60px;
	color: rgb(101, 101, 101);
}

.commend {
	text-align: center;
}

.commend div {
	margin: 15px 0px;
}

.c-reply {
	border: 1px solid lightgray;
	width: 850px;
	padding: 20px;
	margin: 20px 0px;
}

.r-info, .r-userpf {
	display: flex;
	align-items: center;
	align-content: center;
	justify-content: space-between;
}

.r-info {
	margin-bottom: 10px;
}

.r-u-nickname {
	margin-left: 10px;
}

.r-c-time {
	color: gray;
	font-size: 12px;
}

.r-content {
	width: 720px;
	height: auto;
	align-items: center;
	background-color: #f5f5f5;
	border-radius: 5px;
	border-style: none;
	resize: none;
	padding: 10px;
}

.c-button {
	width: 70px;
	height: 40px;
	background-color: rgb(168, 168, 168);
	margin-left: 10px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.c-button:hover {
	background-color: rgb(122, 122, 122);
}

#u-pfimg>img {
	margin-right: 5px;
}

.title {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.title>#title-category {
	font-size: 13px;
}

.update-btn {
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

.update-btn:hover {
	background-color: #8dc231;
}

.delete-btn {
	margin-top: 20px;
	margin-left: 15px;
	background-color: #ff4d4d;
	color: white;
	padding: 10px 15px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	float: right;
}

.delete-btn:hover {
	background-color: #ff1a1a;
}

#dm-send {
	margin-left: 5px;
	color: rgb(59, 59, 59);
	font-size: 15px;
}

#c-delete {
	margin-left: 620px;
	font-size: 15px;
	background-color: transparent;
	border: none;
	color: rgb(66, 66, 66);
}

</style>

<body>

	<%@ include file="../common/menubar.jsp"%>

	<%
	Board b = (Board) request.getAttribute("b");
	Attachment at = (Attachment) request.getAttribute("at");
	Member profile = (Member) request.getAttribute("profile");
	int c = (int) request.getAttribute("c");
	int c1 = (request.getAttribute("c1") == null) ? 0 : (int) request.getAttribute("c1");
	String memberImg = (profile.getMemberImg() == null) ? "/resources/images/pngwing.com.png" : profile.getMemberImg(); 
	int memberNo = (request.getAttribute("memberNo") == null) ? 0 : (int)request.getAttribute("memberNo");
	int replyCount = (int) request.getAttribute("replyCount");
	//if(loginUser != null){	
	//	String memberNick =  (loginUser.getMemberNick() == null) ?
	//			"asd" : loginUser.getMemberNick(); 
	//}
	
	
	%>
	
	
	<div class="wrap">

		<div class="content">

			<div class="content-form">

				<br>
				<div class="title">
					<%
					if (b.getCategory().equals("자유")) {
					%>
					<p id="title-category">
						<i class="far fa-list-alt"></i>
						<%=b.getCategory()%></p>
					<!-- 자유 -->
					<%
					}
					%>
					<%
					if (b.getCategory().equals("나눔")) {
					%>
					<p id="title-category">
						<i class="far fa-handshake"></i>
						<%=b.getCategory()%></p>
					<!-- 나눔 -->
					<%
					}
					%>
					<%
					if (b.getCategory().equals("공지")) {
					%>
					<p id="title-category">
						<i class="fas fa-exclamation-circle"></i>
						<%=b.getCategory()%></p>
					<!-- 공지 -->
					<%
					}
					%>
					<h2 align="center" style="font-family: 'LINESeedKR-Bd';">
						<%=b.getBoardTitle()%></h2>

				</div>
				<br> <span class="c-header"> <span class="userpf">
						<span id="u-pfimg" id="userProfile" data-username=""> <!-- 사용자 프로필 이미지 -->
							<img src="<%=contextPath%><%=memberImg%>" width="30" height="30"
							style="border-radius: 50%;">
					</span> 
					<span id="u-nickname"> <%=profile.getMemberNick()%>
					</span>
					 <input type="hidden" name="dmRecive" value="">
                     <a href="<%= contextPath %>/enrollForm.mes?dmRecive=<%= profile.getMemberNo() %>" type="button" id="dm-send"><i class="far fa-envelope"></i></a>
				</span>
				<span class="c-time"> <%=b.getCreateDate()%>
				</span> <span class="c-etc"> 
						<i class="far fa-thumbs-up" id="commendNum"> <%=c%></i> <!-- 추천수 -->
						<i class="far fa-comment-dots" id="replyCount2"> 0</i> <!-- 댓글수 --> 
						<i class="far fa-eye"> <%=b.getViews()%></i> <!-- 조회수 -->
				</span>
				</span>

				<div class="c-content">
					<%=b.getBoardContent()%>

					<%
					if (at != null) {
					%>
					<div>
						<img
							src="<%=contextPath%>/<%=at.getFilePath() + at.getChangeName()%>">
					</div>
					<%
					}
					%>

				</div>
				<%
				if (loginUser != null && loginUser.getMemberId().equals(b.getBoardWriter())) {
				%>

				<span style="float: right; width: 850px;">
					<button type="button" class="delete-btn"
						onclick="location.href = '<%=contextPath%>/delete.bo?bno=<%=b.getBoardNo()%>';">삭제하기</button>
					<button type="button" class="update-btn"
						onclick="location.href = '<%=contextPath%>/updateForm.bo?bno=<%=b.getBoardNo()%>';">수정하기</button>

					<%
					}
					%>
				</span>

				<div class="commend">
					<%
					if (loginUser != null) {
					%>
					<div>

						<%
						if (c1 == 0) {
						%>
						<i class="far fa-thumbs-up 123" id="com-icon"></i>

						<%
						} else {
						%>
						<i class="fas fa-thumbs-up 123" id="com-icon"></i>
						<%
						}
						%>
						<%
						} else {
						%>

						<i class="far fa-thumbs-up 1234" id="com-icon"></i> <br>
						<%
						}
						%>
					</div>
					<span style="font-family: 'LINESeedKR-Bd';">추천하기</span>

				</div>

				<script>
                
                $(".123").click(function() {
                	if($("#com-icon").hasClass("fas") === true) {
                		$("#com-icon").attr("class", "far fa-thumbs-up");
                		} else {
                		$("#com-icon").attr("class", "fas fa-thumbs-up");
                		}
                	if(loginUser != null){
                	$.ajax({
        				url : "commend.bo",
        				type : "get",
        				data : {bno : <%=b.getBoardNo()%>,
        						mno : <%=memberNo%>},
        				success : function(result) {
        						//let resultStr = "회원번호 : " +  result.memberNo + "<br>"
        						//+ "이름 : " + result.memberName + "<br>"
        						//+ "나이 : " + result.age + "<br>"
        						//+ "성별 : " + result.gender + "<br>";
        					$("#commendNum").html(result);
        					if(result == 0){
        					$("#commendNum").html(result);
        					}
        				},
        				error : function() {
        					console.log("ajax 통신 실패!");
        				}
        			});
              	  }
                });
                $(".1234").click(function() {
                	alert("로그인 후 이용하실 수 있습니다.")
                });
                </script>

				<%
				if (loginUser != null) {
				%>

				<div class="c-reply">

					<div class="r-info">
						<span class="r-userpf"> <span id="u-pfimg" id="userProfile"
							data-username=""> <!-- 사용자 프로필 이미지 --> <img
								src="<%=contextPath%><%=loginUser.getMemberImg()%>" width="30"
								height="30" style="border-radius: 50%;">
						</span> <span id="u-nickname"> <%=loginUser.getMemberNick()%>
						</span>
						</span> <span class="r-c-time"></span>
					</div>

					<span
						style="display: flex; align-items: center; align-content: space-between;">
						<textarea class="r-content" id="replyContent"
							placeholder="댓글을 입력해 주세요."></textarea>
						<button class="c-button" onclick="insertReply();">작성</button>
					</span>

				</div>
				<%
				} else {
				%>
				<div class="c-reply">

					<div class="r-info">
						<span class="r-userpf"> <span id="u-pfimg" id="userProfile"
							data-username=""> <!-- 사용자 프로필 이미지 --> <img
								src="<%=contextPath%>/resources/images/pngwing.com.png"
								width="30" height="30" style="border-radius: 50%;">
						</span> <span id="u-nickname"> 로그인하세요 </span>
						</span> <span class="r-c-time"></span>
					</div>

					<span
						style="display: flex; align-items: center; align-content: space-between;">
						<textarea class="r-content" id="replyContent"
							placeholder="댓글을 입력해 주세요." readonly></textarea>
						<button class="c-button" onclick="insertReply();" disabled>작성</button>
					</span>

				</div>
				<%
				}
				%>
				<div id="commendList"></div>

				<script>
            
            $(function() {
        		
        		//댓글 리스트 조회용 함수 호출 (화면이 보여지는 순간 최초 1회)
        		selectReplyList();
        		showReplyCount();
        		// 추가적으로 1초 간격마다 selectReplyList 함수 실행
        		setInterval(showReplyCount, 3000);
        		
       	 	});
            
 		       	function insertReply() {
 		       	
 		    		$.ajax({
 		    			url : "rinsert.bo",
 		    			type : "post",
 		    			data : { 
 		    				content : $("#replyContent").val(),
 		    				bno : <%=b.getBoardNo()%>
 		    			},
 		    			success : function(result) {
 		    				
 		    				//result 에는
 		    				//댓글 등록 성공시 1이 담겨있음!
 		    				//댓글 등록 실패시 0이 담겨있음!
 		    				// (처리된 행의 갯수를 그대로 응답데이터로 넘겼음)
 		    				if(result > 0){//등록 성공시
 		    					
 		    					//갱신된 댓글 리스트를 다시 조회해올 것
 		    					// => selectReplyList 함수 호출
 		    					selectReplyList();
 		    					showReplyCount();
 		    					
 		    					//textarea 를 초기화
 		    					$("#replyContent").val("");
 		    					
 		    				}else{ //등록 실패시
 		    					
 		    					
 		    				}
 		    				
 		    			},
 		    			error : function() {
 		    				
 		    				console.log("댓글 작성용 ajax 통신 실패!");
 		    			}
 		    			
 		    			
 		    		});
 		    	}
 		       	
 		       function selectReplyList() {
					
            		let contextPath = "<%= contextPath %>";
            		
            		$.ajax({
            			url : "rlist.bo",
            			type : "get",
            			data : { bno : <%= b.getBoardNo()%>	 },
            			success : function(list) { // list 는 자바스크립트 변수
            				
            				
            				
            				console.log(list); // [{}, {}, {}]
            				
            				let resultStr = "";
            				
            				for(let i in list){
            					
            					let replyImg = (list[i].replyImg == null) ?
                						"/resources/images/pngwing.com.png" : list[i].replyImg;
            				
            					// i = 0, 1, 2	
            					resultStr += '<div class="c-reply">'
				                              +  '<div class="r-info">'
				                               +	 '<span class="r-userpf">' 
				                                +    '<span id="u-pfimg" id="userProfile" data-username="">' <!-- 사용자 프로필 이미지 -->
				                                 +       '<img src="'+ contextPath + replyImg +'" width="30" height="30" style="border-radius : 50%;">'
				                                  +  '</span>'
				                                   + '<span id="u-nickname">'
				                                    +    list[i].replyWriter
				                                   + '</span>'
				                               + '</span>';
				                         //if ((loginUser != null) && (list[i].replyWriter == memberNick)) {
				                        	//resultStr += '<button></button>';
				                        // }   
				                     
				                         <% if(loginUser != null) { %>
				             
				                         if("<%= loginUser.getMemberNick() %>" == list[i].replyWriter) {
												
				                        	
				                        	
	 				                         resultStr += '<button id="c-delete" onclick="deleteReply(' + list[i].replyNo + ');"><i class="far fa-trash-alt"></i></button>';
	 				                  
				                         }
				                         
				                         <% } %>
				                    	resultStr +=
				                         '</span>'
				                           + '<span class="r-c-time">'
				                               +  list[i].createDate
				                               + '</span>'
				                           + '</div>'
				                          +  '<table>'
				                             +   '<tr>'
				                                +    '<td width=800>'
				                           
				                                   +     '<textarea class="r-content" readonly style="width: 800px;">'
				                                   + list[i].replyContent
				                                   + 	'</textarea>'
				                                   
				                                 +   '</td>'
				                              +  '</tr>'
				                           + '</table>'
				                         + '</div>';
				                         
				                       
            				}
            				
            				
            				
            				//화면에 출력
            				$("#commendList").html(resultStr);
            				
            				
            			},
            			
            			error : function() {
            				console.log("댓글리스트 조회용 ajax 통신 실패!");
            			}
            			
            		});
            	}
 		       
 		       
	 		  	function deleteReply(num) {
			       		//console.log(num);
			       		alert("댓글이 삭제되었습니다.")
			       		
			       		$.ajax({
			       		
			       			url : "rdelete.bo",
			       			type : "get",
			       			data : { 
		    				
		    				bno : <%= b.getBoardNo()%>,
			       				rno : num
		    			},
		    			
						success : function(result) {
			    				
			    				//result 에는
			    				//댓글 삭제 성공시 1이 담겨있음!
			    				//댓글 삭제 실패시 0이 담겨있음!
			    				// (처리된 행의 갯수를 그대로 응답데이터로 넘겼음)
			    				if(result > 0){//삭제 성공시
			    					
			    					//갱신된 댓글 리스트를 다시 조회해올 것
			    					// => selectReplyList 함수 호출
			    					selectReplyList();
			    					
			    		
			    					
			    				}else{ //삭제 실패시
			    					
			    					
			    				}
			    				
			    			},
			    			error : function() {
			    				
			    				console.log("댓글 삭제용 ajax 통신 실패!");
			    			}
			    			
			       			
			       		});
		       		}
 		       
 				
 		       	// 아래 펑션은 선아님 것 
 				// 댓글수 출력용 ajax
 				function showReplyCount(){
 					
 					$.ajax({
        				url : "rCount.bo",
        				type : "get",
        				data : {bno : <%=b.getBoardNo()%>},
        				success : function(result) {
        						
        					
        					$("#replyCount2").html(" " + result);
        			
        				},
        				error : function() {
        					console.log("ajax 통신 실패!");
        				}
 					});
 				}
            </script>
			</div>
		</div>
	</div>

	<%@ include file="../common/footer.jsp"%>


</body>
</html>