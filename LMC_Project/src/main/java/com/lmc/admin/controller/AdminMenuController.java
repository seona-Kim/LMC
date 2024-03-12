package com.lmc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminMenuController
 */
@WebServlet("/menu.ad")
public class AdminMenuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMenuController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 로그인 안된 상태에서 url 접속 시도 가능
		// 로그인 여부 검사 (session 이용)
		HttpSession session = request.getSession();
		// 메인메뉴.jsp 에서 로그인 정보 받아두기(loginUser 키 + 밸류로 구성)
		
		
		if(session.getAttribute("loginUser") == null) {
			// 로그아웃 상태 >> loginUser 값 null
			
			// 1회성 알람 문구 활용
			session.setAttribute("alertMsg", "관리자만 이용 가능한 페이지입니다.");
			
			// 메인페이지로 url 재용청
			response.sendRedirect(request.getContextPath());
	
		} else {
			// 로그인 상태 >> 정상적으로 관리자 페이지가 노출되도록 포워딩
			// session에 관리자 계정 정보가 담겨있기 때문에 관리자 페이지로 포워딩
			request.getRequestDispatcher("views/admin/adminPage.jsp").forward(request, response);
			
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
