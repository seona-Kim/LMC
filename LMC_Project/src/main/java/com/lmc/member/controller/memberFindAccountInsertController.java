package com.lmc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lmc.member.model.service.MemberService;
import com.lmc.member.model.vo.Member;

/**
 * Servlet implementation class memberFindAccountInsertController
 */
@WebServlet("/memberFindAccountInsert.me")
public class memberFindAccountInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public memberFindAccountInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		
		// 회원 한명 조회 기능이라면?
				// Service -> Dao -> member-mapper.xml
				// 회원 한명의 정보를 Member 타입의 VO 로 받아낼 수 있음
		
		Member m = new Member(email);
		
		Member idPwd = new MemberService().idPwdFindMember(m); 
		
		//System.out.println(idPwd);
		
		if(idPwd == null) { //실패
			
			
			
			
		}else { //성공
			
			response.setContentType("application/json; charset=UTF-8");
			
			new Gson().toJson(idPwd , response.getWriter());
			
			
			
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
