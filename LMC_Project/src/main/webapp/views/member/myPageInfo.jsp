<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--
<meta http-equiv="Cache-Control" content="max-age=0" />
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
<meta http-equiv="Pragma" content="no-cache">
-->
<meta http-equiv="Cache-Control" content="no-cache" />
<title>MyPageInfo</title>
	<style type="text/css">
		
		
		.wrapBody {
            width: 1200px;
            height: 800px;
            margin: auto;
            background-color: #ffffff;
        }
        .wrapBody>div {
            width: 100%;
            box-sizing: border-box;
        }
        .body { 
            height: 100%; 
        }
        .body>div {
            height: 100%;
            float: left;
            box-sizing: border-box;
        }
        .body_1 { width: 300px; }
        .body_2 { width: 900px; }
        

        .outer {
            width: 100%;
            margin: auto;
            border: 1px dotted lightgray;
            margin-top: 50px;
        }
        #mypage-form table { margin: auto; }
        #mypage-form input { margin: 5px; }
        #mypage-form button { margin: 5px; }
	</style>
</head>
<body>
	
	<%@ include file = "../common/menubar.jsp" %>
	
	<%
		// session 에 담겨있는 loginUser 값을 뽑기
		// System.out.println(loginUser);
		int memberNo		=  loginUser.getMemberNo();
		String memberId 	=  loginUser.getMemberId();
		String memberName 	=  loginUser.getMemberName();
		String memberNick 	= (loginUser.getMemberNick() == null) 	? "" : loginUser.getMemberNick();
		String email 		= (loginUser.getEmail() == null) 	? "" : loginUser.getEmail();
		String enrollDate 	= (loginUser.getEnrollDate() == null) 	? "" : loginUser.getEnrollDate().toString();
		String memberImg 	= (loginUser.getMemberImg() == null) ? "/resources/images/pngwing.com.png" : loginUser.getMemberImg();
		
		// 삼항연산자 이용 : DB에 없는 값을 불러오면 null 을 불러오므로
		// null 일 경우는 "" (빈 문자열) 로 대체
	%>

	<div class="wrapBody">
	<div class="body">
		<div class="body_1">
			<%@ include file="myPage.jsp" %>
		</div>
		<div class="body_2">
	<div class="outer">
       <br>
       <h2 align="center">마이페이지</h2>
       <br>
       
       <form id="mypage-form" action="<%= contextPath %>/update.me" method="post" enctype="multipart/form-data">
           <input type="hidden" name="memberNo" value="<%= memberNo %>">
           <!--
               마이페이지에서 보여져야 하는 것들
               아이디, 이름, 닉네임, 이메일, 회원가입일
           -->

           <table>
               <tr>
                   <td>&nbsp;&nbsp;아이디</td>
                   <td colspan="2">
                       <input type="text" name="memberId" maxlength="12" readonly value="<%= memberId %>">
                       <!-- 수정을 막기 위해 readonly 부여 -->
                   </td>
                   <td></td>
               </tr>
               <tr>
                   <td>&nbsp;&nbsp;이름</td>
                   <td colspan="2">
                       <input type="text" name="memberName" maxlength="6" required value="<%= memberName %>">
                   </td>
                   <td></td>
               </tr>
               <tr>
                   <td>&nbsp;&nbsp;닉네임</td>
                   <td colspan="2">
                       <input type="tel" name="memberNick" required value="<%= memberNick %>">
                   </td>
                   <td>
                       <input type="button" class="btn btn-secondary btn-sm" value="중복확인">
                   </td>
               </tr>
               <tr>
                   <td>&nbsp;&nbsp;이메일</td>
                   <td colspan="2">
                       <input type="email" name="email" required value="<%= email %>">
                   </td>
                   <td></td>
               </tr>
               <tr>
                   <td>&nbsp;&nbsp;회원가입일</td>
                   <td colspan="2">
                       <input type="text" name="enrollDate" value="<%= enrollDate %>" readonly>
                   </td>
                   <td></td>
               </tr>
               <tr>
                   <td colspan="4"><br></td>
               </tr>
               <tr>
                   <td rowspan="2" colspan="2" align="right">
                       <img src="<%= contextPath %><%= memberImg %>" alt="" height="100px" id="preview">
                       <input type="hidden" name="memberImg" value="<%= memberImg %>">
                   </td>
                   <td colspan="2" align="center">
                       프로필 이미지 변경
                   </td>
               </tr>
               <tr>
                   <td colspan="2" align="center">
                       <input type="file" name="updateImg" class="btn btn-secondary btn-sm" value="업로드" onchange="readURL(this);">
                   </td>
               </tr>
           </table>

           <br><br>

           <div align="center">
               <button type="submit" class="btn btn-secondary btn-sm">정보변경</button>
               <button type="button" class="btn btn-warning btn-sm" data-toggle="modal" data-target="#updatePwdForm">비밀번호변경</button>
           </div>

           <br><br><br><br>

           <div align="center">
               <button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
           </div>

           <br><br>

       </form>
   	</div>
	</div>
	</div>
	</div>
	
	<script type="text/javascript">
		function readURL(input) {
		  if (input.files && input.files[0]) {
		    var reader = new FileReader();
		    reader.onload = function(e) {
		      document.getElementById('preview').src = e.target.result;
		    };
		    reader.readAsDataURL(input.files[0]);
		  } else {
		    document.getElementById('preview').src = "";
		  }
		}
	</script>
	
	<!-- 필요한 모달창 -->
	<!-- 비밀번호 변경용 모달창 -->
	<div class="modal" id="updatePwdForm">
	       <div class="modal-dialog">
	         <div class="modal-content">
	     
	           <!-- Modal Header -->
	          <div class="modal-header">
	            <h4 class="modal-title">비밀번호 변경</h4>
	            <button type="button" class="close" data-dismiss="modal">&times;</button>
	          </div>
	    
	          <!-- Modal body -->
	          <div class="modal-body">
	            <form action="<%= contextPath %>/updatePwd.me" method="post">
	                
	                <!-- 
	                    비밀번호 변경 시 : 현재 비밀번호, 변경할 비밀번호, 변경할 비밀번호 확인
	                                         , 변경할 아이디
	                 -->
	                 <!--  
	                     비밀번호 변경 요청 시 변경하고자 하는 회원의 아이디값도 같이 form 으로 넘기고자 함
	                     input type="text" 로 아이디를 굳이 입력받지 않고도 넘기고 싶음
	                     => input type="hidden" 로 아이디값을 넘길 것
	                 -->
	                 <input type="hidden" name="memberId" value="<%= memberId %>">
	                 
	                <table>
	                    <tr>
	                        <td>현재 비밀번호</td>
	                        <td>
	                            <input type="password" name="memberPwd" required>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td>변경할 비밀번호</td>
	                        <td>
	                        	<input type="password" name="updatePwd" required>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td>변경할 비밀번호 재입력</td>
	                        <td>
	                            <input type="password" name="checkPwd" required>
	                        </td>
	                    </tr>
	                </table>
	                <br>
	                <button type="submit" class="btn btn-secondary btn-sm" onclick="return validatePwd();">비밀번호 변경</button>
	            </form>
	            
	            <script>
	                function validatePwd() {
	                    
	                    // 변경할 비번 == 변경할 비번 확인 : return true;
	                    // 변경할 비번 != 변경할 비번 확인 : return false;
	                    if($("input[name=updatePwd]").val() != $("input[name=checkPwd]").val()) {
	                        alert("비밀번호가 일치하지 않습니다.");
	                        return false;
	                    }
	                    return true;
	                }
	            </script>
	            
	          </div>
	        </div>
	      </div>
	    </div>
	    
	    <!-- 회원 탈퇴용 모달창 -->
	    <div class="modal" id="deleteForm">
	      <div class="modal-dialog">
	        <div class="modal-content">
	    
	          <!-- Modal Header -->
	          <div class="modal-header">
	            <h4 class="modal-title">회원탈퇴</h4>
	            <button type="button" class="close" data-dismiss="modal">&times;</button>
	          </div>
	    
	          <!-- Modal body -->
	          <div class="modal-body">
	            <b>
	                탈퇴 후 복구가 불가능합니다. <br>
	                정말로 탈퇴하시겠습니까? <br><br>
	            </b>
	            
	            <form action="<%= contextPath %>/delete.me" method="post">
	                <!-- 탈퇴 시 : 비밀번호 확인 -->
	                <table>
	                    <tr>
	                        <td>비밀번호</td>
	                        <td>
	                            <input type="password" name="memberPwd" required>
	                        </td>
	                </table>
	                <br>
	                <button type="submit" class="btn btn-secondary btn-sm">탈퇴하기</button>
	                
	            </form>
	          </div>
	        </div>
	      </div>
	    </div>
	    
	    <%@ include file="../common/footer.jsp" %>
</body>
</html>