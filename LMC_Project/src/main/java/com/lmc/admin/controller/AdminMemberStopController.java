package com.lmc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmc.member.model.service.MemberService;

/**
 * Servlet implementation class AdminMemberStop
 */
@WebServlet("/stopMember.me")
public class AdminMemberStopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberStopController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mno = request.getParameter("mno");
		String status = request.getParameter("st");
		String currentPage = request.getParameter("currentPage");
	
		//System.out.println("서비스단 선택 번호 잘 넘김? :" +mno);
		//System.out.println("서비스단 선택 상태 잘 넘김? :" +status);
		int result = new MemberService().memberStop(mno, status);
		
		response.sendRedirect("memberstoplist.me?currentPage="+currentPage);
		//request.getRequestDispatcher("views/admin/adminPage_member_stop.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
