<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LMC</title>


    <style>

    .wrap {
        background-color: white;
        width: 1200px;
        margin: auto;
    }

    footer {
        background-color: rgb(44, 44, 44);
        padding: 20px;
        text-align: center;
        font-family: 'LINESeedKR-Bd';
    }

    footer p {
        font-size: 15px;
        font-weight: 500;
        color: white;
    }

    .footer_text a {
        text-decoration: none;
        color: white;
        padding: 5px;
    }

    .footer_logo img {
        width: 120px;
        margin-bottom: 20px;
    }

    </style>

</head>
<body>
    <div class="wrap">
        
        <footer>
            <div class="footer_logo">
                <img src="<%= request.getContextPath() %>/resources/images/Logo_w.png" alt="로고">
            </div>
            <p>© 2023. LMC. all rights reserved.</p>
            <div class="footer_text">
                <a href="<%= contextPath %>/list.no?currentPage=1">공지사항</a>
                <a href="">개인정보취급방침</a>
            </div>
        </footer>

    </div>
</body>
</html>
