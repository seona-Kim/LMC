package com.lmc.member.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmc.member.model.service.MemberService;
import com.lmc.member.model.vo.Member;

/**
 * Servlet implementation class MemberPwdUpdateController
 */
@WebServlet("/updatePwd.me")
public class MemberPwdUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPwdUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		HashMap<String, String> hm = new HashMap<>();
		
		hm.put("memberId", memberId);
		hm.put("memberPwd", memberPwd);
		hm.put("updatePwd", updatePwd);
		
		Member updateMem = new MemberService().updatePwdMember(hm);
		
		HttpSession session = request.getSession();
		
		if(updateMem == null) {
			
			session.setAttribute("alertMsg", "비밀번호 변경에 실패했습니다.");
		} else {
			session.setAttribute("alertMsg", "성공적으로 비밀번호가 변경됬습니다.");
			
			session.setAttribute("loginUser", updateMem);
		}
		
		// myPage.me 로 url 재요청 
		response.sendRedirect(request.getContextPath() + "/myPageInfo.me");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
