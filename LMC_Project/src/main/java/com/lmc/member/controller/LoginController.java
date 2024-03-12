package com.lmc.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmc.member.model.service.MemberService;
import com.lmc.member.model.vo.Member;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * * HttpServletRequest 객체와 HttpServletResponse 객체
		 * - request : 서버로 요청할 때의 정보들이 담겨있음
		 * 			   (요청 시 전달값, 요청 전송 방식, ip 주소 등)
		 * - response : 요청에 대해 응답할 때 필요한 메소드들이 담겨있음
		 * 
		 * * Get 방식과 Post 방식 차이
		 * - Get 방식 : 사용자가 입력한 값들이 url 주소에 노출
		 * 			   데이터의 길이 제한이 있음
		 * 			   대신 즐겨찾기가 편리함
		 * - Post 방식 : 사용자가 입력한 값들이 url 주소에 노출 X
		 * 				데이터의 길이 제한이 없음
		 * 				대신 Timeout 이 존재
		 */
		
		// 1) Post 방식이므로 전달값을 뽑기 전에 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청 시 전달값을 뽑아서 변수 또는 객체에 기록하기
		// => request 의 parameter 영역으로부터 뽑기
		//	  request.getParameter("키값") : String (밸류값)
		//	  request.getParameterValues("키값") : String[] (밸류값들)
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		
		// System.out.println(memberId);
		// System.out.println(memberPwd);
		
		// Member 타입의 VO 객체로 가공
		Member m = new Member();
		m.setMemberId(memberId);
		m.setMemberPwd(memberPwd);
		
		Member loginUser 
			= new MemberService().loginMember(m);
		
		// 4) 처리된 결과를 가지고 사용자가 보게될 응답뷰 지정
		if(loginUser == null) {
			
			request.setAttribute("errorMsg", "로그인에 실패했습니다.");
			
			// 응답 페이지를 지정하면서 RequestDispatcher 객체 생성
			RequestDispatcher view 
				= request.getRequestDispatcher("views/common/errorPage.jsp");
			
			// 포워딩
			// 해당 경로로 선택된 뷰는 보여지고,
			// url 주소는 절대 변경되지 않는 방법!!
			// (요청 시 url 주소가 그대로 남아있음)
			view.forward(request, response);
			
		} else {
			// 조회된 회원이 있는 경우 => 로그인 성공
			// (메인페이지로 url 이동)
			
			// 로그인한 회원의 정보를 로그아웃 하기 전까지
			// 계속 가져다 써야만 함!! (변수같은곳에 담아둬야 함)
			// 사실 로그인한 회원의 정보도 "응답데이터"
			/*
			 * * 응답 페이지에 전달할 값이 있다면 어딘가에 담아야 함
			 *   (담아줄 수 있는 Servlet Scope 내장객체 4종류)
			 * 1) application : application 에 담은 데이터는 
			 * 				이 웹 애플리케이션 전역에서 다 꺼내다 쓸 수 있음
			 * 2) session : session 에 담은 데이터는
			 * 				이 웹 애플리케이션 전역에서 다 꺼내다 쓸 수 있음
			 * 				단, 한번 담은 데이터는 내가 직접 지우기 전까지,
			 * 								  서버가 종료되기 전까지,
			 * 								  브라우저가 종료되기 전까지만 사용 가능
			 * 3) request : request 에 담은 데이터는
			 * 				해당 request 를 포워딩한 응답 jsp 페이지에서만
			 * 				한번 꺼내쓸 수 있음
			 * 4) page : 한 개의 jsp 페이지에서 값을 담고 꺼내쓸 수 있음
			 * 
			 * => session, request 를 주로 많이 쓴다.
			 * 
			 * 공통적으로 데이터를 담고자 한다면
			 * xxx.setAttribute("키값", 밸류값);
			 * 
			 * 공통적으로 데이터를 꺼내고자 한다면
			 * xxx.getAttribute("키값") : Object (밸류값)
			 * 
			 * 공통적으로 데이터를 지우고자 한다면
			 * xxx.removeAttribute("키값");
			 */
			
			// 로그아웃 하기 전까지 사용 가능해야하므로
			// session 에 담을 예정
			
			// session 객체 얻어내기 (HttpSession 타입)
			// => Servlet 에서 JSP 내장 객체인 session 에 
			//    접근하고자 한다면 request 객체를 통해 접근 가능함
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			// Servlet Scope 내장객체에는
			// 반드시 데이터를 하나만 담으라는 법은 없다!!
			// 또한, session 에는 무조건 로그인한 회원의 정보만
			// 담으라는 법도 없다!!
			session.setAttribute("alertMsg", "성공적으로 로그인이 되었습니다.");
			
			// url 재요청방식 (== sendRedirect 방식)
			// 내부적으로
			// 현재 이 브라우저 창의 url 주소를
			// http://localhost:8888/jsp 로 이동시킴
			// response.sendRedirect("/jsp");
			response.sendRedirect(request.getContextPath());
			// request.getContextPath() : 현재 이 프로젝트의
			//							  context root 를 반환
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
