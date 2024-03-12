package com.lmc.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmc.member.model.service.MemberService;
import com.lmc.member.model.vo.Member;

/**
 * Servlet implementation class MemberListController
 */
@WebServlet("/member.no")
public class AdminMemberSelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberSelectController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// 회원 번호 먼저 뽑기 

    int memberNo = Integer.parseInt(request.getParameter("mno"));
    
    // 회원 번호를 이용해서 회원의 정보를
    // (MemberService를 통해 불러올수 있도록 Member 객체로 가공) 
    Member m = new Member();
	m.setMemberNo(memberNo);

    // 회원 번호를 이용해서 해당 회원의 정보 뽑아내기
    MemberService mService = new MemberService();
    
    // m에는 회원의 정보만 담겼음
    Member selectedM = mService.selectMember(m);
    
    request.setAttribute("selectedM", selectedM);
    RequestDispatcher view = request.getRequestDispatcher("views/admin/adminPage_selectMember.jsp");
	view.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
