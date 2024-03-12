package com.lmc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/logout.me")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 로그아웃 요청 처리
				// => 로그인한 회원의 정보는 session 에 담겨있음
				
				HttpSession session = request.getSession();
				
				// * 로그아웃 구현 방법
				// 1. session.removeAttribute("loginUser");
				session.removeAttribute("loginUser");
				
				// 2. session 을 무효화시키기
				//session.invalidate();
				// => session 에 로그인한 사용자의 데이터
				//	  뿐만 아니라 다른 중요한 데이터도 같이 들어있을경우
				//	  사용하면 안됨
				
				// 메인페이지로 url 재요청
				response.sendRedirect(request.getContextPath());
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
