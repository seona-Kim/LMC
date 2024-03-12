package com.lmc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lmc.member.model.service.MemberService;

/**
 * Servlet implementation class MemberEmailDuplicationCheckController
 */
@WebServlet("/emailCheck.do")
public class MemberEmailDuplicationCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEmailDuplicationCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String checkEmail = request.getParameter("checkEmail");
		
		int result = new MemberService().duplicationEmailCheck(checkEmail);
		//System.out.println("콘트롤러로 중복 검사 값 잘 오나?" +result);
		//System.out.println("checkId 잘 넘어감? " + checkId);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(result,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
