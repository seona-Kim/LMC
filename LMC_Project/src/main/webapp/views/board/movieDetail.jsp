<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LMC</title>
      <!-- jQuery 라이브러리 연동 (온라인 방식) -->
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js">

      </script>

</head>

<style>
    .underline-btn{
	display: inline-block;
  	padding: 1em 0;
  	border-radius: 0;
	color: #b2876f;
  	margin-top:2rem;
  	font-weight: bold;
  	font-size: 0.678rem;
  	letter-spacing: 2px;
  	text-transform: uppercase;
  	text-decoration: none;
	position: relative;
	&:before,
	&:after{
		content: '';
    	display: block;
    	position: absolute;
    	height: 1px;
    	width: 0;

	}
	&:before{
		transition: width 0s ease,background .4s ease;
		left: 0;
		right: 0;
    	bottom: 6px;
	}
	&:after{
		right: 2.2%;
    	bottom: 6px;
    	background: #b2876f;
		transition: width .4s ease;
	}
	
	&:hover{
		&:before{
			width: 97.8%;
			background: #b2876f;
    		transition: width .4s ease;
		}
		&:after{
			width: 97.8%;
    		background: 0 0;
			transition: all 0s ease;
		}
	}
}

@font-face {
            font-family: 'Pretendard-Regular';
            src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
            font-weight: 400;
            font-style: normal;
        }
        * { font-family: 'Pretendard-Regular'; }

    .wrap {
        background-color:  #e0e0e0;
        width: 80%;
        margin: auto;
        font-family: 'KimjungchulGothic'
    }
    .wrap div {

        box-sizing: border-box;

    }

    body {
        background-color: #e0e0e0;
        margin: 0;
        padding: 0;
    }

   

    .footer_logo {
        margin: auto;
    }

    .content {
        display: flex;
        flex-direction: column;
    
        height: 100vh;
    }

    footer {
        background-color: black;
        padding: 20px;
        text-align: center;
    }

    footer p {
        font-size: 15px;
        font-weight: 500;
        color: white;
    }

    footer a {
        text-decoration: none;
        color: white;
        padding: 5px;
    }

    .login {
        font-size: 11px;
        margin-right: 15px;
        color: gray;
    }

    .login a {
        text-decoration: none;
        color: gray;
        padding: 7px;
    }
    .content_thumbnail{
        width: 100%;
        height: 250px;
        border: 1px solid red;
    }
    .content_detail_1{
       
        width: 50%;
        border: 1px solid red;
        float: left;
        
    }

    
    #content_main_1{

        width: 50%;
        
        float: left;

    }
  
    #content_main_2{
        width: 50%;
        
        float: right;
        
    }
    #movie_coment{
        

        height: 25%;
        background-color: white;
        border-radius: 20px;
        
    }

   
   
</style>
</head>
<body>
<div class="wrap">
     
    <div class="content">
        <div action="" method="">
        <br><br>
        
        
        <div class="content_thumbnail">

            <img src="resources/images/about.jpg" alt="" style="width: 200px; height: 250px;">
            썸네일 및 화면
        </div>
        
        <div class="content_main">
            
            
                <div id="content_main_1">

                    &nbsp;  &nbsp;<p id="movie_a"class="underline-btn" href="#" style="font-size: 15px;">영화내용</p>
                    <br><br>
                    <textarea  id="movie_a_1" class="moving_1" cols="50" rows="10">


                            안녕안녕안녕

                    </textarea>
                  
                    
                </div>
            
           
                <div id="content_main_2">

                    
                    &nbsp;  &nbsp;<p id="movie_b"class="underline-btn" href="#" style="font-size: 15px;">예고편</p>
                    <br><br>
                    <textarea id="move_b_2" class="moving_2" cols="50" rows="10">
                            하이하이하이

                    </textarea>
                
                
                </div>
                    
        </div>
       
</div>
           <br><br>     
        <div id="movie_coment">
            <ul>
                <li>
                   김말숙 ~~~~
                </li>
                <li>
                    김말똥 ~~~~
                </li>
                <li>
                    고길동 ~~~~
                </li>
            </ul>
            
        
        
        </div>

    </div>
</form>
</div>

<footer>
    <div class="footer_logo">
        <img src="resources/images/Logo_w.png" alt="로고">
    </div>
    <p>© 2023. LMC. all rights reserved.</p>
    <div>
        <a href="#">공지사항</a>
        <a href="#">개인정보취급방침</a>
    </div>
</footer>
<script>
    $(document).ready(function() {
        
        $(".movie_a").hover(function() {
            $(".movie_b_2").hide();
            $(".movie_a_1").css("width", "600px");
        
        }, function() {
            $(".movie_a_1").css("width", "auto");
            $(".movie_b_2").show();
        
        });
    });
    </script>
</body>
</html>