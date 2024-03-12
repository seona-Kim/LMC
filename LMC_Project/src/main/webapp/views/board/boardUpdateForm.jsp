<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lmc.board.model.vo.Board,
				com.lmc.board.model.vo.Attachment,
				java.util.ArrayList,
				com.lmc.board.model.vo.Category" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>

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
            padding: 20px;
            border-radius: 10px;
            margin-top: 20px;
            width: 1000px;
            
        }

        .board-title {
            margin-bottom: 10px;
            margin-top: 20px;
            justify-content: space-between;
        }

        #category,
        #title {
            height: 35px;
            align-items: center;
            background-color: #f5f5f5;
            border-radius: 5px;
            border-style: none;
        }

        #category {
            width: 150px;
            margin-right: 10px;
        }

        #category:hover {
            cursor: pointer;
        }

        #title {
            width: 600px;
            padding: 10px;
        }

        .btn-submit {
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

        .btn-submit:hover {
            background-color: #8dc231;
        }

        .btn-reset {
            margin-top: 20px;
            margin-left: 15px;
            background-color: #c7c7c7;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            float: right;
        }

        .btn-reset:hover {
            background-color: #b1b1b1;
        }
        
        .board-content>textarea {
            resize: none;
        	width: 100%;
            height: 600px;
            border-radius: 5px;
            border-color: lightgray;
            padding: 20px;
        }
    </style>

</head>
<body>

    <%@ include file="../common/menubar.jsp" %>
	<% 
		Board b = (Board)request.getAttribute("b");
		Attachment at = (Attachment)request.getAttribute("at");
		ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
	%>
    <div class="wrap">
        <div class="content">
            <form action="<%=contextPath %>/update.bo" method="post" enctype="multipart/form-data" class="content-form"  id="update-form">

						<!-- 게시글 번호도 같이 넘기기 -->
        			 <input type="hidden" name="boardNo" 
            			value="<%= b.getBoardNo() %>">

                <h2 style="font-family: 'LINESeedKR-Bd';">게시글 수정</h2>
                
                <div class="board-title">
                    <select name="category" id="category">
                           <% for(Category c : list) { %>
                                <option value="<%= c.getCategoryNo() %>"><%= c.getCategoryName() %></option>
                            <% } %>
                    </select>
                    
                     <script>
                        	$(function() {
                        		
                        		$("#update-form option").each(function() {
                        			
                        			// each 메소드 내의 $(this) 
                        			// => 현재 반복을 돌리고 있는 요소
                        			if($(this).text() == "<%= b.getCategory() %>") {
                        				
                        				$(this).attr("selected", true);
                        			}
                        		});
                        	});
                        </script>
                    
                    <input type="text" id="title" name="title" value="<%= b.getBoardTitle() %>" placeholder="제목" required>
                </div>

                 <div class="board-content">
                    <textarea name="content" value="<%= b.getBoardContent() %>" id="content" placeholder="내용을 입력해 주세요."><%= b.getBoardContent() %></textarea>          
                </div>              
        
                <div class="file-upload-container">
               			 <% if(at != null) { %>
                    	
	                        <!-- 기존에 첨부파일이 있었다면 원본명 보여주기 -->
	                        <%= at.getOriginName() %>
	                        
	                        <!-- 기존 첨부파일의 첨부파일번호를 넘길 것 -->
	                        <input type="hidden" name="originFileNo" 
	                        				value="<%= at.getFileNo() %>">
	                        
	                        <!-- 기존 첨부파일의 수정명도 같이 넘길 것 (파일 삭제 시 필요) -->
	                        <input type="hidden" name="originFileName"
	                        				value="<%= at.getChangeName() %>">
	                        
	                    <% } %>
                    <input type="file" id="upload-input" name="reUpfile" multiple>
                    
                    <label for="upload-input" class="upload-button">
                    </label>
                    <div class="preview-container" id="preview-container"></div>
                </div>


                <button type="reset" class="btn-reset" onclick="location.href='<%=contextPath %>/boardDetailView.bo?bno=<%=b.getBoardNo() %>';" >취소</button>
                <button type="submit" class="btn-submit">수정</button>

            </form>
        </div>
    </div>

    <%@ include file="../common/footer.jsp" %>


</body>
</html>
