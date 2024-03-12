<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lmc.board.model.vo.Board,
				com.lmc.board.model.vo.Attachment,
				java.util.ArrayList,
				com.lmc.board.model.vo.Category" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>

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
		ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("list");
		int memberNo = loginUser.getMemberNo();
	%>
    <div class="wrap">
        <div class="content">
            <form action="<%=contextPath %>/insert.bo" method="post" class="content-form" enctype="multipart/form-data">
				<input type="hidden" name="MemberNo" value="<%= memberNo %>">
                <br><h2 style="font-family: 'LINESeedKR-Bd';">게시글 작성</h2>
                
                <div class="board-title">
                    <select name="category" id="category">
                            <% for(Category c : list) { %>
                                <option value="<%= c.getCategoryNo() %>"><%= c.getCategoryName() %></option>
                            <% } %>
                    </select>
                    
                    <input type="text" id="title" name="title" placeholder="제목" required>
                </div>
                
                <div class="board-content">
                    <textarea name="content" id="content" placeholder="내용을 입력해 주세요."></textarea>          
                </div>
                
                <div class="file-upload-container">

                    <input type="file" id="upload-input" name="upfile" multiple>
                    
                    <label for="upload-input" class="upload-button">
                    </label>
                    <div class="preview-container" id="preview-container"></div>
                </div>
                

                <button type="button" class="btn-reset" onclick="history.back();">목록으로</button>
                <button type="submit" class="btn-submit">작성</button>

            </form>
        </div>
    </div>

    <%@ include file="../common/footer.jsp" %>


</body>
</html>
