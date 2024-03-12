package com.lmc.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.lmc.common.MyFileRenamePolicy;
import com.lmc.member.model.service.MemberService;
import com.lmc.member.model.vo.Member;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) POST 방식이므로 인코딩 변경 먼저 진행
		request.setCharacterEncoding("UTF-8");
		
		
			// 2) 요청 시 전달값을 뽑아서 변수 및 객체에 기록하기
			
			//
			String[] checkBox1 
			= request.getParameterValues("check1");
			
			String[] checkBox2 
			= request.getParameterValues("check2");
			
			// 아이디 : userId (필수입력)
			String userId = request.getParameter("userId");
			
			// 비밀번호 : userPwd (필수입력)
			String userPwd = request.getParameter("userPwd");
			
			// 이름 : userName (필수입력)
			String userName = request.getParameter("userName");
			
			// 닉네임 : Nname (필수입력)
			String Nname = request.getParameter("Nname");
			
			// 이메일주소 : email (필수입력)
			String email = request.getParameter("email1");
			
			
			
			// Member VO 로 가공하기
				
				Member m = new Member(userId, userPwd, userName,
						Nname, email);

			
			// 3) Service 단으로 요청 후 결과 받기
			int result = new MemberService().insertMember(m);
			
			// 4) 처리 결과를 가지고 사용자가 보게 될 응답뷰 지정
			if(result > 0) { // 회원가입 성공 => 메인페이지로 url 이동

				// session 에 1회성 알람 문구 담기
				HttpSession session = request.getSession();
				session.setAttribute("alertMsg", "회원가입에 성공했습니다.");
				
				// url 재요청방식
				response.sendRedirect(request.getContextPath());
				
			} else { // 회원가입 실패 => errorPage.jsp 포워딩
				
				// 에러문구 먼저 담기
				request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
				
				// RequestDispatcher 객체 생성
				RequestDispatcher view 
					= request.getRequestDispatcher("views/common/errorPage.jsp");
				
				// 포워딩
				view.forward(request, response);
			}
			
		  
			
		

		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
