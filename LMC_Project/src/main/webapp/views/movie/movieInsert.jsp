<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://kit.fontawesome.com/53a8c415f1.js" crossorigin="anonymous"></script>

 <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <!-- jQuery 라이브러리 연동 (온라인 방식) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


    <meta charset="UTF-8">
<title>Insert title here</title>
<style>
    @font-face {
            font-family: 'Pretendard-Regular';
            src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
            font-weight: 400;
            font-style: normal;
        }
        * { font-family: 'Pretendard-Regular'; }
    
    .outer {
        width : 900px;
        height: 700px;
        margin : auto;
        margin-top : 30px;
        border : 1px dotted lightgray;
        box-sizing: border-box;
    }

    
   
    #movie_insert_1{
        width: 100%;
        height: 30%;
        
    }
    
    #movie_insert_1{
        margin: 20px;
    }
    
.info_content {width: 40%; height: 40%; box-sizing: border-box; float: left;}

#movie_mid{
        width: 100%;
        height: 30%;       
        box-sizing: border-box;
    }

    .movie_content { width: 50%; height: 50%; box-sizing: border-box; float: left;}

    #link{
        float: center;
    }

    .btn {
        display:block;
        width:200px; 
        height:40px; 
        line-height:40px; 
        border:1px #3399dd solid;;
        margin:15px auto; 
        background-color:#66aaff;  
        text-align:center; 
        cursor: pointer;  

        color:#333; 
        transition:all 0.9s, color 0.3;  
    }
    .btn:hover{color:#fff;}
    
    .hover1:hover{
        box-shadow:200px 0 0 0 rgba(0,0,0,0.5) inset;
    }
   
    
</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>

    <div class="outer">

        <br>
        <h2 align="center">영화정보 등록</h2>
        <br> 
        <form action="<%= contextPath %>/movieInsert.me" id="enroll-form" 
                method="post" enctype="multipart/form-data">
               
                <div id="movie_insert_1">
                
                    <div class="info_content" id="movie_name">
                    
                        영화제목 : <input type="text" name="name" required style="width: 120px;"> 
                    
                        <div>
                        영화 이미지 :  
                        </div>
                        &nbsp;  &nbsp; &nbsp;  &nbsp;
                        &nbsp;  &nbsp; &nbsp;  &nbsp;
                        &nbsp;  &nbsp; &nbsp;  &nbsp;
                        <img src="" id="titleImg" width="250" height="300">    
                        
                        <!-- 이미지 불러오는 input file 버튼 -->
                        <label class="input-file-button" for="titlemovie" >
                             
                              <!-- <i class="fas fa-camera">   </i> -->
                        
                        </label>
                       
                        <input type="file" id="titleMovie" name="titleMovie"  style="display: none;" onchange="loadImg(this);" required>

                    </div>

               </div>
               
             
             
               <div id="movie_mid">

                
                    <div class="movie_content">
                        &nbsp;  &nbsp; &nbsp;  &nbsp; 
                        &nbsp;  &nbsp; &nbsp;  &nbsp;
                        &nbsp;  &nbsp; &nbsp;  &nbsp;
                        &nbsp;  &nbsp; &nbsp;  &nbsp;
                        &nbsp;  &nbsp; &nbsp;  &nbsp;
                        &nbsp;  &nbsp; &nbsp;  &nbsp;
                        
                        
                        내 &nbsp;용 
                        <textarea name="content" rows="10" cols="50" 
                                    style="resize : none;" required></textarea>
                    </div>

                    <div class="movie_content">
                        &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp;
                        &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp;
                        &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp;                 
                        동영상 링크  
                       
                        <input type="text" name="content2"style="width: 350px;" id="link">
                    <div><br><br><br>
                        
                        <button type="submit" class="btn hover1" id="btn">
                            등록하기
                        </button>      
                    </div>   
                        
                    </div>
               </div>
 
           
            </form>
    
    </div>
    

        

       	<%@ include file="../common/footer.jsp" %>



           <script>
           $(function(){

             $("#titleImg").click(function() {
                     $("#titleMovie").click();

           });     
           });
         

         function loadImg(inputFile) {

             
             if(inputFile.files.length == 1) {
              

                
                 let reader = new FileReader();

                 
                 reader.readAsDataURL(inputFile.files[0]);

                
                 reader.onload = function(e) {

                    $("#titleImg").attr("src", e.target.result);
                        
                 };

             } else {
                 
                 $("#titleImg").attr("src", null);
                  
             }
            
         }
         
           </script>

</body>
</html>