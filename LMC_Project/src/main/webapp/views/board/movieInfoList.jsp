<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>LMC</title>

</head>

<style>

@font-face {
    font-family: 'KimjungchulGothic';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302_01@1.0/KimjungchulGothic-Bold.woff2') format('woff2');
    font-style: normal;
    }
    @font-face {
    font-family: 'Dokrip';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Dokrip.woff') format('woff');
    font-weight: normal;
    font-style: normal;
    }

    .wrap {
        background-color: white;
        width: 80%;
        margin: auto;
        font-family: 'KimjungchulGothic'
    }

    body {
        background-color: #e0e0e0;
        margin: 0;
        padding: 0;
    }

    .header {
        display: flex;
        align-items: center;
        margin-left: 80px;
    }

    .menu-bar {
        background-color: #eeeeee;
        height: 65px;
        width: 60%;
        margin: auto;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-radius: 20px;
    }

    .menu-bar ul {
        list-style: none;
        display: flex;
    }

    .menu-bar li {
        margin-right: 60px;
    }

    .menu-bar nav ul li:last-child {
        margin-right: 0;
    }

    .menu-bar a {
        text-decoration: none;
        color: #000;
    }

    

    .content {
        display: flex;
        flex-direction: column;
         align-items: center;
         justify-content: center;
        height: 100vh;
    }

   
    #back{
        height: 90%;
        width: 85%;
        background-color: #eeeeee;
        border-radius: 40px;
        
    }
   
    .bang:hover{
        
        -webkit-filter: blur(3px);
	    filter: blur(3px);
        
    }

    .choong:hover{
        -webkit-filter: blur(3px);
	    filter: blur(3px);
        
    }
    .lala:hover{
        -webkit-filter: blur(3px);
	    filter: blur(3px);
    }
    .about:hover{
        -webkit-filter: blur(3px);
	    filter: blur(3px);

    }
</style>
</head>
<body>

<%@ include file="../common/menubar.jsp" %>
<div class="wrap">
    <div class="content">
        
        
	  
        
	    
	    
        <div id="back">
           
            <h3> &nbsp; &nbsp; &nbsp; &nbsp; <br>
                &nbsp;ìíì ë³´</h3>
            
            <div id=movie-detail>
            <center>
               <!--  -->
            </center>
            </div>
        
        
        </div>
    
    </div>
	<a href="<%= contextPath %>/movieInsert.me" class="btn btn-secondary btn-sm">글작성</a>

</div>
	<%@ include file="../common/footer.jsp" %>



</body>
</html>