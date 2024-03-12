<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나눔</title>
</head>

<style>

    .content {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: auto;
        padding: 20px;
    }

    .table-area {
        width: 850px;
        height: auto;
        margin: auto;
    }

    .table>tbody>tr:hover{
        cursor: pointer;
        background-color: rgb(244, 244, 244);
    }

    .btn-submit {
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


</style>

<body>

    <%@ include file="../common/menubar.jsp" %>

    <div class="wrap">

        <div class="content">

            <br>
            <h2 align="center" style="font-family: 'LINESeedKR-Bd';">
                <i class="far fa-handshake"></i> 나눔
            </h2>
            <br><br>
            <div class="table-area">
                <table class="table" align="center" style="border-spacing : 0px;">
                    <thead>
                    <tr>
                        <th width="60" style="text-align:center;">No.</th>
                        <th width="450" style="text-align:center;">제목</th>
                        <th width="120" style="text-align:center;">작성자</th>
                        <th style="text-align:center;">날짜</th>
                        <th style="text-align:center;">조회수</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr style="background-color: rgb(255, 255, 177);">
                        <td style="text-align:center; font-weight: bold;">공지</td>
                        <td style="font-weight: bold;">나눔게시판 이용 안내입니다.</td>
                        <td style="font-weight: bold;">관리자</td>
                        <td style="text-align:center; font-weight: bold;">2022-07-28</td>
                        <td style="text-align:center; font-weight: bold;">575</td>
                    </tr>
                    <tr>
                        <td style="text-align:center;">7</td>
                        <td>나눔합니댱</td>
                        <td>김선아oook</td>
                        <td style="text-align:center;">2023-10-12</td>
                        <td style="text-align:center;">515</td>
                    </tr>
                    <tr>
                        <td style="text-align:center;">7</td>
                        <td>해리포터 굿즈 교환해용</td>
                        <td>김선아oook</td>
                        <td style="text-align:center;">2023-10-12</td>
                        <td style="text-align:center;">515</td>
                    </tr>
                    <tr>
                        <td style="text-align:center;">7</td>
                        <td>무대인사 자리 구합니다</td>
                        <td>김선아oook</td>
                        <td style="text-align:center;">2023-10-12</td>
                        <td style="text-align:center;">515</td>
                    </tr>
                    <tr>
                        <td style="text-align:center;">7</td>
                        <td>양도해요</td>
                        <td>김선아oook</td>
                        <td style="text-align:center;">2023-10-12</td>
                        <td style="text-align:center;">515</td>
                    </tr>
                    <tr>
                        <td style="text-align:center;">6</td>
                        <td>제목6dfsdfadㅇㄻ</td>
                        <td>김선아oook</td>
                        <td style="text-align:center;">2023-10-12</td>
                        <td style="text-align:center;">525</td>
                    </tr>
                    <tr>
                        <td style="text-align:center;">5</td>
                        <td>제목5ㅇㄻㅇㄻㅇㄻ</td>
                        <td>김선아k</td>
                        <td style="text-align:center;">2023-10-12</td>
                        <td style="text-align:center;">55</td>
                    </tr>
                    <tr>
                        <td style="text-align:center;">4</td>
                        <td>제목ㅁㄴㅇㄻㄴㅇㅀㅇㄴㅁㅎ4</td>
                        <td>관리자22222</td>
                        <td style="text-align:center;">2023-10-11</td>
                        <td style="text-align:center;">83</td>
                    </tr>
                    <tr>
                        <td style="text-align:center;">3</td>
                        <td>제목3ㅁㅇㅎㅁㅇㄶㅁ</td>
                        <td>김선아123</td>
                        <td style="text-align:center;">2023-10-10</td>
                        <td style="text-align:center;">58</td>
                    </tr>
                    <tr>
                        <td style="text-align:center;">2</td>
                        <td>제목2ㅁㅇㅎㅁㅎ</td>
                        <td>김선아2</td>
                        <td style="text-align:center;">2023-10-10</td>
                        <td style="text-align:center;">448</td>
                    </tr>
                    <tr>
                        <td style="text-align:center;">1</td>
                        <td>제목ㅁㅇㅎㅁㅇㅎㅁ1</td>
                        <td>김선아2</td>
                        <td style="text-align:center;">2023-10-10</td>
                        <td style="text-align:center;">120</td>
                    </tr>
                    </tbody>
                </table>
                
                <button type="submit" class="btn-submit"><i class="fas fa-pen"></i> 쓰기</button>
            </div>

        </div>

    </div>

    <%@ include file="../common/footer.jsp" %>


</body>
</html>