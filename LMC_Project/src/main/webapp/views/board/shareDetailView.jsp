<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나눔 게시글 상세 조회</title>
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
        display : inline-block;
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

</style>

<body>

    <%@ include file="../common/menubar.jsp" %>

    <div class="wrap">

        <div class="content">

            <div class="content-form">

                <br>
                <div class="title">
                    <p id="title-category"><i class="far fa-handshake"></i> 나눔</p>
                    <h2 align="center" style="font-family: 'LINESeedKR-Bd';">무대인사 양도합니다!</h2>
                </div>
                <br>

                <span class="c-header">
                    <span class="userpf" >
                        <span id="u-pfimg" id="userProfile" data-username=""> <!-- 사용자 프로필 이미지 -->
                            <img src="data:image/jpeg;base64,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" width="30" height="30" style="border-radius : 50%;">
                        </span>
                        <span id="u-nickname">
                            주먹밥선아
                        </span>
                    </span>
                    <span class="c-time">
                        2023-10-16 15:21
                    </span>
                    <span class="c-etc">
                        <i class="far fa-thumbs-up"> 5</i> <!-- 추천수 -->
                        <i class="far fa-comment-dots"> 24</i> <!-- 댓글수 -->
                        <i class="far fa-eye"> 152</i> <!-- 조회수 -->
                    </span> 
                </span>

                <div class="c-content">
        
                </div>

                <span style="float: right; width: 850px;">
                    <button type="button" class="delete-btn">삭제하기</button>
                    <button type="button" class="update-btn">수정하기</button>

                </span>

                <div class="commend">
                    <div>
                        <i class="far fa-thumbs-up" id="com-icon"></i>
                        <!-- <i class="fas fa-thumbs-up"></i> 클릭 시 변환될 아이콘 -->
                    </div>
                    <span style="font-family: 'LINESeedKR-Bd';">추천하기</span>
                </div>

                <div class="c-reply">
                    <div class="r-info">
                        <span class="r-userpf">
                            <span id="u-pfimg" id="userProfile" data-username=""> <!-- 사용자 프로필 이미지 -->
                                <img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzAzMTdfMSAg%2FMDAxNjc5MDMwMTYwMzcz.xCY_nfQPkZRUN708CtQ6nvNLiwEdhAoRWikTufCRqvYg.qEHqVInqUjvyX_NYbowxLNIMfZDFhrVgJviVc-cFYJgg.PNG.gyubinhhj%2F%25B1%25D7%25B8%25B21.png&type=a340" width="30" height="30" style="border-radius : 50%;">
                            </span>
                            <span id="u-nickname">
                                보노선아
                            </span>
                        </span>
                        <span class="r-c-time">댓글 시간</span>
                    </div>

                    <span style="display: flex; align-items: center; align-content: space-between;">
                        <textarea class="r-content" placeholder="댓글을 입력해 주세요."></textarea>
                        <button type="submit" class="c-button">작성</button>
                    </span>
                    
                </div>

                <div class="c-reply">
                    <div class="r-info">
                        <span class="r-userpf">
                            <span id="u-pfimg" id="userProfile" data-username=""> <!-- 사용자 프로필 이미지 -->
                                <img src="data:image/jpeg;base64,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" width="30" height="30" style="border-radius : 50%;">
                            </span>
                            <span id="u-nickname">
                                단비
                            </span>
                        </span>
                        <span class="r-c-time">댓글 시간</span>
                    </div>
                    <table >
                        <tr>
                            <td width=800>
                                <textarea class="r-content" readonly style="width: 800px;">저용ㅇㅇ요오우우양아ㅓ</textarea>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="../common/footer.jsp" %>


</body>
</html>