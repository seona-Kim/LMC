/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.80
 * Generated at: 2023-11-06 08:58:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views.movie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.lmc.member.model.vo.Member;

public final class movieInsert_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/views/movie/../common/menubar.jsp", Long.valueOf(1698080520456L));
    _jspx_dependants.put("/views/movie/../common/footer.jsp", Long.valueOf(1697763428000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.lmc.member.model.vo.Member");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<script src=\"https://kit.fontawesome.com/53a8c415f1.js\" crossorigin=\"anonymous\"></script>\r\n");
      out.write("\r\n");
      out.write(" <!-- Latest compiled and minified CSS -->\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\">\r\n");
      out.write("    <!-- jQuery 라이브러리 연동 (온라인 방식) -->\r\n");
      out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js\"></script>\r\n");
      out.write("    <!-- Popper JS -->\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\"></script>\r\n");
      out.write("    <!-- Latest compiled JavaScript -->\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\">\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js\"></script>\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\"></script>\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<style>\r\n");
      out.write("    @font-face {\r\n");
      out.write("            font-family: 'Pretendard-Regular';\r\n");
      out.write("            src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');\r\n");
      out.write("            font-weight: 400;\r\n");
      out.write("            font-style: normal;\r\n");
      out.write("        }\r\n");
      out.write("        * { font-family: 'Pretendard-Regular'; }\r\n");
      out.write("    \r\n");
      out.write("    .outer {\r\n");
      out.write("        width : 900px;\r\n");
      out.write("        height: 700px;\r\n");
      out.write("        margin : auto;\r\n");
      out.write("        margin-top : 30px;\r\n");
      out.write("        border : 1px dotted lightgray;\r\n");
      out.write("        box-sizing: border-box;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write("   \r\n");
      out.write("    #movie_insert_1{\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        height: 30%;\r\n");
      out.write("        \r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    #movie_insert_1{\r\n");
      out.write("        margin: 20px;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write(".info_content {width: 40%; height: 40%; box-sizing: border-box; float: left;}\r\n");
      out.write("\r\n");
      out.write("#movie_mid{\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        height: 30%;       \r\n");
      out.write("        box-sizing: border-box;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .movie_content { width: 50%; height: 50%; box-sizing: border-box; float: left;}\r\n");
      out.write("\r\n");
      out.write("    #link{\r\n");
      out.write("        float: center;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .btn {\r\n");
      out.write("        display:block;\r\n");
      out.write("        width:200px; \r\n");
      out.write("        height:40px; \r\n");
      out.write("        line-height:40px; \r\n");
      out.write("        border:1px #3399dd solid;;\r\n");
      out.write("        margin:15px auto; \r\n");
      out.write("        background-color:#66aaff;  \r\n");
      out.write("        text-align:center; \r\n");
      out.write("        cursor: pointer;  \r\n");
      out.write("\r\n");
      out.write("        color:#333; \r\n");
      out.write("        transition:all 0.9s, color 0.3;  \r\n");
      out.write("    }\r\n");
      out.write("    .btn:hover{color:#fff;}\r\n");
      out.write("    \r\n");
      out.write("    .hover1:hover{\r\n");
      out.write("        box-shadow:200px 0 0 0 rgba(0,0,0,0.5) inset;\r\n");
      out.write("    }\r\n");
      out.write("   \r\n");
      out.write("    \r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("	");
      out.write("\r\n");
      out.write("\r\n");
      out.write("   ");
 String contextPath = request.getContextPath();
   
	// 로그인한 회원의 정보를 session 으로부터 뽑아내기
	// session.getAttribute("키값") : Object (밸류값)
	Member loginUser = (Member)session.getAttribute("loginUser");
	// session 에 담아둔 1회성 알람문구를 변수로 담아두기
	String alertMsg = (String)session.getAttribute("alertMsg");
   
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>LMC Menu Bar</title>\r\n");
      out.write("\r\n");
      out.write("    <!-- Latest compiled and minified CSS -->\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\">\r\n");
      out.write("\r\n");
      out.write("    <!-- jQuery 라이브러리 연동 (온라인 방식) -->\r\n");
      out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <!-- Popper JS -->\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <!-- Latest compiled JavaScript -->\r\n");
      out.write("    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <!-- 아이콘 연동 -->\r\n");
      out.write("    <script src=\"https://kit.fontawesome.com/53a8c415f1.js\" crossorigin=\"anonymous\"></script> \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <style>\r\n");
      out.write("    /* 글꼴 */\r\n");
      out.write("    @font-face {\r\n");
      out.write("        font-family: 'KimjungchulGothic';\r\n");
      out.write("        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302_01@1.0/KimjungchulGothic-Bold.woff2') format('woff2');\r\n");
      out.write("        font-style: normal;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    @font-face {\r\n");
      out.write("        font-family: 'Dokrip';\r\n");
      out.write("        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.1/Dokrip.woff') format('woff');\r\n");
      out.write("        font-weight: normal;\r\n");
      out.write("        font-style: normal;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    @font-face {\r\n");
      out.write("        font-family: 'LINESeedKR-Bd';\r\n");
      out.write("        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2') format('woff2');\r\n");
      out.write("        font-weight: 200;\r\n");
      out.write("        font-style: normal;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    @font-face {\r\n");
      out.write("        font-family: 'NanumBarunGothic';\r\n");
      out.write("        font-style: normal;\r\n");
      out.write("        font-weight: 900;\r\n");
      out.write("        src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.eot');\r\n");
      out.write("        src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.eot?#iefix') format('embedded-opentype'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.woff') format('woff'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.ttf') format('truetype');\r\n");
      out.write("        }\r\n");
      out.write("    \r\n");
      out.write("    body {\r\n");
      out.write("        margin: 0;\r\n");
      out.write("        padding: 0;\r\n");
      out.write("        background-color: #e0e0e0;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .wrap {\r\n");
      out.write("        width: 1200px;\r\n");
      out.write("        margin: auto;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .top-bar {\r\n");
      out.write("        background-color: #eeeeee;\r\n");
      out.write("        display: flex;\r\n");
      out.write("        justify-content: space-between; /* 간격을 최대로 설정하여 양 끝에 정렬 */\r\n");
      out.write("        align-items: center;\r\n");
      out.write("        padding: 10px 20px;\r\n");
      out.write("        font-family: 'LINESeedKR-Bd'; \r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .logo {\r\n");
      out.write("        display: flex;\r\n");
      out.write("        align-items: center;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .logo img {\r\n");
      out.write("        width: 60px;\r\n");
      out.write("        margin-right: 10px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .logo a {\r\n");
      out.write("        font-family: 'Dokrip';\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("        color: black;\r\n");
      out.write("        font-size: 30px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .menu {\r\n");
      out.write("        list-style: none;\r\n");
      out.write("        margin: 0;\r\n");
      out.write("        padding: 0;\r\n");
      out.write("        display: flex;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .menu li {\r\n");
      out.write("        margin-right: 50px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .menu a {\r\n");
      out.write("        position: relative;\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("        color: black;\r\n");
      out.write("        font-size: 18px;\r\n");
      out.write("        font-weight: bold;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .menu a::before {\r\n");
      out.write("        content: \"\";\r\n");
      out.write("        position: absolute;\r\n");
      out.write("        width: 100%;\r\n");
      out.write("        height: 1.3px;\r\n");
      out.write("        bottom: 0;\r\n");
      out.write("        left: 0;\r\n");
      out.write("        background-color: black;\r\n");
      out.write("        transform: scaleX(0); /* 처음에는 밑줄이 안 보이도록 설정 */\r\n");
      out.write("        transition: all 0.3s ease-in-out; /* 애니메이션 적용 */\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .menu a:hover::before {\r\n");
      out.write("        visibility: visible;\r\n");
      out.write("        transform: scaleX(1); /* 호버됐을 때 밑줄 */\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    .search-box {\r\n");
      out.write("        display: flex;\r\n");
      out.write("        align-items: center;\r\n");
      out.write("        background-color: #cfcfcf;\r\n");
      out.write("        border-radius: 5px;\r\n");
      out.write("        padding: 5px 10px;\r\n");
      out.write("        height: 40px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .search-box input[type=\"text\"] {\r\n");
      out.write("        border: none;\r\n");
      out.write("        background-color: transparent; /* 검색 상자 배경 투명 */\r\n");
      out.write("        color: black;\r\n");
      out.write("        font-size: 14px;\r\n");
      out.write("        padding: 5px;\r\n");
      out.write("        outline: none;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .search-box button {\r\n");
      out.write("        background-color: transparent; /* 검색 아이콘 배경 투명 */\r\n");
      out.write("        border: none;\r\n");
      out.write("        color: #333;\r\n");
      out.write("        border-radius: 5px;\r\n");
      out.write("        margin-left: 5px;\r\n");
      out.write("        cursor: pointer;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .login {\r\n");
      out.write("        font-size: 11px;\r\n");
      out.write("        margin-right: 15px;\r\n");
      out.write("        color: gray;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .login a {\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("        color: gray;\r\n");
      out.write("        padding: 7px;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("    /*회원가입폼 */\r\n");
      out.write("\r\n");
      out.write("	.outerInsert {\r\n");
      out.write("		width : 1000px;\r\n");
      out.write("		margin : auto;\r\n");
      out.write("		margin-top : 50px;\r\n");
      out.write("		border : 1px dotted lightgray;\r\n");
      out.write("	}\r\n");
      out.write("\r\n");
      out.write("	#enroll-form table { margin : auto; }\r\n");
      out.write("	#enroll-form input { margin : 5px; \r\n");
      out.write("        border : 1px solid lightgray;\r\n");
      out.write("                         border-radius: 5px;\r\n");
      out.write("                   color: #585858;\r\n");
      out.write("                   margin-left: 30px;       \r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    #enroll-form #checked1, #enroll-form #checked2{\r\n");
      out.write("\r\n");
      out.write("        margin-left: 200px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    #user table{\r\n");
      out.write("        font-size: 20px;\r\n");
      out.write("    }\r\n");
      out.write("    #user b{\r\n");
      out.write("        color : rgb(255, 123, 0)\r\n");
      out.write("    }\r\n");
      out.write("    #user button{\r\n");
      out.write("        width: 100px;\r\n");
      out.write("        height : 45px;\r\n");
      out.write("        border-radius: 5px;\r\n");
      out.write("        border : 1px solid lightgray;\r\n");
      out.write("    }\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("    #user hr{\r\n");
      out.write("        margin-top: 20px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    #user hr{\r\n");
      out.write("        margin-top: 20px;\r\n");
      out.write("    }\r\n");
      out.write("  \r\n");
      out.write("    .modal-body input {\r\n");
      out.write("        background-color: rgb(240, 239, 239);\r\n");
      out.write("        border-style : none;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    #login{\r\n");
      out.write("        float:left;\r\n");
      out.write("    }\r\n");
      out.write("  \r\n");
      out.write("    #footerLogin>button{\r\n");
      out.write("        width: 40%;\r\n");
      out.write("        height: 50px;\r\n");
      out.write("        border-radius: 25px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("		// script 태그 내에서도 스크립틀릿과 같은 jsp 요소들 사용 가능!!\r\n");
      out.write("		\r\n");
      out.write("		let msg = \"");
      out.print( alertMsg );
      out.write("\";\r\n");
      out.write("		// \"null\" / \"성공적으로 로그인이 되었습니다.\"\r\n");
      out.write("		\r\n");
      out.write("		if(msg != \"null\") {\r\n");
      out.write("			\r\n");
      out.write("			alert(msg);\r\n");
      out.write("			// 알람창을 띄워준 후 session 에 담긴 해당 메세지는\r\n");
      out.write("			// 항상 지워줘야 함!!\r\n");
      out.write("			// => 안그러면 menubar.jsp 가 로딩될때마다\r\n");
      out.write("			//	  매번 alert 가 뜨기 때문\r\n");
      out.write("			\r\n");
      out.write("			");

				session.removeAttribute("alertMsg");
			
      out.write("\r\n");
      out.write("		}	\r\n");
      out.write("	</script>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"wrap\">\r\n");
      out.write("        <div class=\"top-bar\">\r\n");
      out.write("            <div class=\"logo\">\r\n");
      out.write("                <a href=\"");
      out.print( contextPath );
      out.write("\"><img src=\"");
      out.print( contextPath );
      out.write("/resources/images/Logo.png\" alt=\"로고\">LMC</a>\r\n");
      out.write("            </div>\r\n");
      out.write("            <ul class=\"menu\">\r\n");
      out.write("                <li><a href=\"");
      out.print( contextPath );
      out.write("/list.bo?category=10&currentPage=1\">자유게시판</a></li>\r\n");
      out.write("                <li><a href=\"");
      out.print( contextPath );
      out.write("/movieList.me\">영화 정보</a></li>\r\n");
      out.write("                <li><a href=\"");
      out.print( contextPath );
      out.write("/list.bo?category=20&currentPage=1\">나눔</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("            <div class=\"search-box\">\r\n");
      out.write("                <form action=\"");
      out.print( contextPath );
      out.write("/searchListView.bo?currentPage=1;\" method=\"get\">\r\n");
      out.write("              	<input type=\"text\" placeholder=\"Search\" name=\"keyword\">\r\n");
      out.write("                <button type=\"submit\" name=\"currentPage\" value=\"1\"><i class=\"fas fa-search\"></i></button>\r\n");
      out.write("            </form>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"login\">\r\n");
      out.write("           		 <!-- 로그인 전 -->\r\n");
      out.write("            	");
if(loginUser == null){ 
      out.write("\r\n");
      out.write("	                <a href=\"");
      out.print( contextPath );
      out.write("\" data-toggle=\"modal\" data-target=\"#loginForm\">로그인</a> |\r\n");
      out.write("	                <a href=\"");
      out.print( contextPath );
      out.write("/enrollForm.me\">회원가입</a>\r\n");
      out.write("                ");
 } else if(loginUser.getMemberId().equals("admin")) { 
      out.write("\r\n");
      out.write("                	<b>");
      out.print( loginUser.getMemberNick() );
      out.write("님</b><br>\r\n");
      out.write("                	<a href=\"");
      out.print( contextPath );
      out.write("/menu.ad\">관리자페이지</a> | \r\n");
      out.write("					<a href=\"");
      out.print( contextPath );
      out.write("/logout.me\">로그아웃</a>\r\n");
      out.write("                <!-- 로그인 후 -->\r\n");
      out.write("                ");
 } else { 
      out.write("\r\n");
      out.write("					<b>");
      out.print( loginUser.getMemberNick() );
      out.write("님</b> 환영합니다. <br>\r\n");
      out.write("					<a href=\"");
      out.print( contextPath );
      out.write("/myPageInfo.me\">마이페이지</a> | \r\n");
      out.write("					<a href=\"");
      out.print( contextPath );
      out.write("/logout.me\">로그아웃</a>\r\n");
      out.write("				");
 } 
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("<!-- Button to Open the Modal -->\r\n");
      out.write("      <!-- \r\n");
      out.write("        data-toggle=\"modal\" : 부트스트랩에서 제공하는 속성, \r\n");
      out.write("                              모달창을 띄울려면 반드시 필요함\r\n");
      out.write("        data-target=\"#myModal\" : 부트스트랩에서 제공하는 속성, \r\n");
      out.write("                    내가 띄우고자 하는 모달창을 연결해주는 속성\r\n");
      out.write("      -->\r\n");
      out.write("       <!--\r\n");
      out.write("        \r\n");
      out.write("        <button type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#loginForm\">\r\n");
      out.write("            Open Login Form\r\n");
      out.write("\r\n");
      out.write("        </button>-->\r\n");
      out.write("        \r\n");
      out.write("        <!-- The Modal -->\r\n");
      out.write("        <div class=\"modal\" id=\"loginForm\" style=\"margin-top: 10%; \">\r\n");
      out.write("            <div class=\"modal-dialog\">\r\n");
      out.write("                <button type=\"button\" class=\"close\" data-dismiss=\"modal\" style=\"margin-right: 217px;\r\n");
      out.write("                margin-bottom:20px; border-style: none; font-size: 450%; \">&times;</button>\r\n");
      out.write("            <div class=\"modal-content\"  style=\"border-radius: 30px;\">\r\n");
      out.write("        \r\n");
      out.write("                <!-- Modal Header -->\r\n");
      out.write("              \r\n");
      out.write("                <div class=\"modal-header\" style=\"background-color: rgb(240,240,240); border-radius: 30px 30px 0px 0px;\">\r\n");
      out.write("                <h4 class=\"modal-title\" style=\"margin-left: 200px;\">로그인</h4>\r\n");
      out.write("                \r\n");
      out.write("                </div>\r\n");
      out.write("        \r\n");
      out.write("                <!-- Modal body -->\r\n");
      out.write("                <div class=\"modal-body\">\r\n");
      out.write("                \r\n");
      out.write("                    <form action=\"");
      out.print( contextPath );
      out.write("/login.me\" method=\"post\">\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                          \r\n");
      out.write("                          <input type=\"text\" class=\"form-control\" placeholder=\"아이디\" \r\n");
      out.write("                                        id=\"memberId\" name=\"memberId\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                          \r\n");
      out.write("                          <input type=\"password\" class=\"form-control\" placeholder=\"비밀번호\" \r\n");
      out.write("                                        id=\"pwd\" name=\"memberPwd\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group form-check\">\r\n");
      out.write("\r\n");
      out.write("                            <input type=\"checkbox\" id=\"saveId\"\r\n");
      out.write("                            name=\"saveId\" value=\"y\">\r\n");
      out.write("                            <label for=\"saveId\">\r\n");
      out.write("                                아이디 저장\r\n");
      out.write("                            </label>\r\n");
      out.write("\r\n");
      out.write("                          \r\n");
      out.write("                           <a href=\"");
      out.print( contextPath );
      out.write("/memberFindAccount.me\" style=\"color : black; float: right;\">ID/PW찾기</a>\r\n");
      out.write("\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                        <div id=\"footerLogin\"  style=\"margin-left: 75px;\">\r\n");
      out.write("                            <button type=\"button\" id=\"login\" class=\"btn btn-light\" style=\"margin-right: 10px;\" onclick=\"location.href='");
      out.print( contextPath );
      out.write("/enrollForm.me'\">회원가입</button>\r\n");
      out.write("                            <button type=\"submit\" id=\"loginUser\" class=\"btn btn-primary btn-block\" style=\"margin-left: 10px;\">로그인</button>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        \r\n");
      out.write("                    </form> \r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("        \r\n");
      out.write("            </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <div class=\"outer\">\r\n");
      out.write("\r\n");
      out.write("        <br>\r\n");
      out.write("        <h2 align=\"center\">영화정보 등록</h2>\r\n");
      out.write("        <br> \r\n");
      out.write("        <form action=\"");
      out.print( contextPath );
      out.write("/movieInsert.me\" id=\"enroll-form\" \r\n");
      out.write("                method=\"post\" enctype=\"multipart/form-data\">\r\n");
      out.write("               \r\n");
      out.write("                <div id=\"movie_insert_1\">\r\n");
      out.write("                \r\n");
      out.write("                    <div class=\"info_content\" id=\"movie_name\">\r\n");
      out.write("                    \r\n");
      out.write("                        영화제목 : <input type=\"text\" name=\"name\" required style=\"width: 120px;\"> \r\n");
      out.write("                    \r\n");
      out.write("                        <div>\r\n");
      out.write("                        영화 이미지 :  \r\n");
      out.write("                        </div>\r\n");
      out.write("                        &nbsp;  &nbsp; &nbsp;  &nbsp;\r\n");
      out.write("                        &nbsp;  &nbsp; &nbsp;  &nbsp;\r\n");
      out.write("                        &nbsp;  &nbsp; &nbsp;  &nbsp;\r\n");
      out.write("                        <img src=\"\" id=\"titleImg\" width=\"250\" height=\"300\">    \r\n");
      out.write("                        \r\n");
      out.write("                        <!-- 이미지 불러오는 input file 버튼 -->\r\n");
      out.write("                        <label class=\"input-file-button\" for=\"titlemovie\" >\r\n");
      out.write("                             \r\n");
      out.write("                              <!-- <i class=\"fas fa-camera\">   </i> -->\r\n");
      out.write("                        \r\n");
      out.write("                        </label>\r\n");
      out.write("                       \r\n");
      out.write("                        <input type=\"file\" id=\"titleMovie\" name=\"titleMovie\"  style=\"display: none;\" onchange=\"loadImg(this);\" required>\r\n");
      out.write("\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("               </div>\r\n");
      out.write("               \r\n");
      out.write("             \r\n");
      out.write("             \r\n");
      out.write("               <div id=\"movie_mid\">\r\n");
      out.write("\r\n");
      out.write("                \r\n");
      out.write("                    <div class=\"movie_content\">\r\n");
      out.write("                        &nbsp;  &nbsp; &nbsp;  &nbsp; \r\n");
      out.write("                        &nbsp;  &nbsp; &nbsp;  &nbsp;\r\n");
      out.write("                        &nbsp;  &nbsp; &nbsp;  &nbsp;\r\n");
      out.write("                        &nbsp;  &nbsp; &nbsp;  &nbsp;\r\n");
      out.write("                        &nbsp;  &nbsp; &nbsp;  &nbsp;\r\n");
      out.write("                        &nbsp;  &nbsp; &nbsp;  &nbsp;\r\n");
      out.write("                        \r\n");
      out.write("                        \r\n");
      out.write("                        내 &nbsp;용 \r\n");
      out.write("                        <textarea name=\"content\" rows=\"10\" cols=\"50\" \r\n");
      out.write("                                    style=\"resize : none;\" required></textarea>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"movie_content\">\r\n");
      out.write("                        &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp;\r\n");
      out.write("                        &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp;\r\n");
      out.write("                        &nbsp;  &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp;                 \r\n");
      out.write("                        동영상 링크  \r\n");
      out.write("                       \r\n");
      out.write("                        <input type=\"text\" name=\"content2\"style=\"width: 350px;\" id=\"link\">\r\n");
      out.write("                    <div><br><br><br>\r\n");
      out.write("                        \r\n");
      out.write("                        <button type=\"submit\" class=\"btn hover1\" id=\"btn\">\r\n");
      out.write("                            등록하기\r\n");
      out.write("                        </button>      \r\n");
      out.write("                    </div>   \r\n");
      out.write("                        \r\n");
      out.write("                    </div>\r\n");
      out.write("               </div>\r\n");
      out.write(" \r\n");
      out.write("           \r\n");
      out.write("            </form>\r\n");
      out.write("    \r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("        \r\n");
      out.write("\r\n");
      out.write("       	");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <title>LMC</title>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <style>\r\n");
      out.write("\r\n");
      out.write("    .wrap {\r\n");
      out.write("        background-color: white;\r\n");
      out.write("        width: 1200px;\r\n");
      out.write("        margin: auto;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    footer {\r\n");
      out.write("        background-color: rgb(44, 44, 44);\r\n");
      out.write("        padding: 20px;\r\n");
      out.write("        text-align: center;\r\n");
      out.write("        font-family: 'LINESeedKR-Bd';\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    footer p {\r\n");
      out.write("        font-size: 15px;\r\n");
      out.write("        font-weight: 500;\r\n");
      out.write("        color: white;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .footer_text a {\r\n");
      out.write("        text-decoration: none;\r\n");
      out.write("        color: white;\r\n");
      out.write("        padding: 5px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .footer_logo img {\r\n");
      out.write("        width: 120px;\r\n");
      out.write("        margin-bottom: 20px;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    </style>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <div class=\"wrap\">\r\n");
      out.write("        \r\n");
      out.write("        <footer>\r\n");
      out.write("            <div class=\"footer_logo\">\r\n");
      out.write("                <img src=\"");
      out.print( request.getContextPath() );
      out.write("/resources/images/Logo_w.png\" alt=\"로고\">\r\n");
      out.write("            </div>\r\n");
      out.write("            <p>© 2023. LMC. all rights reserved.</p>\r\n");
      out.write("            <div class=\"footer_text\">\r\n");
      out.write("                <a href=\"");
      out.print( contextPath );
      out.write("/list.no?currentPage=1\">공지사항</a>\r\n");
      out.write("                <a href=\"\">개인정보취급방침</a>\r\n");
      out.write("            </div>\r\n");
      out.write("        </footer>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("           <script>\r\n");
      out.write("           $(function(){\r\n");
      out.write("\r\n");
      out.write("             $(\"#titleImg\").click(function() {\r\n");
      out.write("                     $(\"#titleMovie\").click();\r\n");
      out.write("\r\n");
      out.write("           });     \r\n");
      out.write("           });\r\n");
      out.write("         \r\n");
      out.write("\r\n");
      out.write("         function loadImg(inputFile) {\r\n");
      out.write("\r\n");
      out.write("             \r\n");
      out.write("             if(inputFile.files.length == 1) {\r\n");
      out.write("              \r\n");
      out.write("\r\n");
      out.write("                \r\n");
      out.write("                 let reader = new FileReader();\r\n");
      out.write("\r\n");
      out.write("                 \r\n");
      out.write("                 reader.readAsDataURL(inputFile.files[0]);\r\n");
      out.write("\r\n");
      out.write("                \r\n");
      out.write("                 reader.onload = function(e) {\r\n");
      out.write("\r\n");
      out.write("                    $(\"#titleImg\").attr(\"src\", e.target.result);\r\n");
      out.write("                        \r\n");
      out.write("                 };\r\n");
      out.write("\r\n");
      out.write("             } else {\r\n");
      out.write("                 \r\n");
      out.write("                 $(\"#titleImg\").attr(\"src\", null);\r\n");
      out.write("                  \r\n");
      out.write("             }\r\n");
      out.write("            \r\n");
      out.write("         }\r\n");
      out.write("         \r\n");
      out.write("           </script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
