<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<% response.setStatus(200); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>500</title>
<style type="text/css">
	body{margin-top:20px;}
	.error-page {
	    text-align: center;
		background: #fff;
		border-top: 1px solid #eee;
	}
	.error-page .error-inner {
		display: inline-block;
	}
	.error-page .error-inner h1 {
		font-size: 140px;
		text-shadow: 3px 5px 2px #3333;
		color: #006DFE;
		font-weight: 700;
	}
	.error-page .error-inner h1 span {
		display: block;
		font-size: 25px;
		color: #333;
		font-weight: 600;
		text-shadow: none;
	}
	.error-page .error-inner p {
		padding: 20px 15px;
	}
	.error-page .search-form {
		width: 100%;
		position: relative;
	}
	.error-page .search-form input {
		width: 400px;
		height: 50px;
		padding: 0px 78px 0 30px;
		border: none;
		background: #f6f6f6;
		border-radius: 5px;
		display: inline-block;
		margin-right: 10px;
		font-weight:400;
		font-size:14px;
	}
	.error-page .search-form input:hover{
		padding-left:35px;
	}
	.error-page .search-form .btn {
		width: 80px;
		height: 50px;
		border-radius: 5px;
		cursor: pointer;
		background: #006DFE;
		display: inline-block;
		position: relative;
		top: -2px;
	}
	.error-page .search-form .btn i{
		font-size:16px;
	}
	.error-page .search-form .btn:hover{
		background:#333;
	}
</style>
</head>
<body>
	
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
	<section class="error-page section">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 offset-lg-3 col-12">
					<!-- Error Inner -->
					<div class="error-inner">
						<h1>500<span>업데이트 될 예정인 페이지입니다!</span></h1>
						<p>찾으시는 글이 있다면 밑에서 검색을 해보시는건 어떠신가요? <br> 아니면 <a href="<%= request.getContextPath() %>">메인페이지</a>로 갈수도 있습니다!</p>
						<form class="search-form" action="<%= request.getContextPath() %>/searchListView.bo?currentPage=1;" method="get">
							<input placeholder="검색어를 입력하세요." type="text" name="keyword">
							<button class="btn" type="submit" name="currentPage" value="1"><i class="fa fa-search"></i></button>
						</form>
					</div>
					<!--/ End Error Inner -->
				</div>
			</div>
		</div>
	</section>
	
</body>
</html>