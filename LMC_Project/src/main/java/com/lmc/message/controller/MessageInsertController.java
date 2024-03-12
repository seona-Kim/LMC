package com.lmc.message.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmc.member.model.vo.Member;
import com.lmc.message.model.service.MessageService;
import com.lmc.message.model.vo.Message;

/**
 * Servlet implementation class MessageInsertController
 */
@WebServlet("/insert.mes")
public class MessageInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) POST 방식이므로 인코딩 설정 먼저
		request.setCharacterEncoding("UTF-8");
		
		// 2) 요청 시 전달값들을 뽑아서 변수 및 객체에 기록
		
		// 내용 : content
		String dmContent = request.getParameter("content");
		
		// 보낸 사람 : sendMember
		// String sendMemberNo = request.getParameter("sendMember");
		String sendMemberNo = "" + ((Member)(request.getSession().getAttribute("loginUser"))).getMemberNo();
		
		// 받은 사람 : reciveMember
		String reciveMemberNo = request.getParameter("reciveMember"); 
		
//		System.out.println("보낸사람회원번호 : " + sendMemberNo);
//		System.out.println("받는사람회원번호 : " + reciveMemberNo);
		
		// Message VO 로 가공하기
		Message m = new Message();
		m.setDmContent(dmContent);
		m.setDmSendMember(sendMemberNo);
		m.setDmReciveMember(reciveMemberNo);
		
		// 3) Service 로 요청 후 결과 받기
		int result = new MessageService().insertMessage(m);
		
		// 4) 결과에 따른 응답뷰 지정
		if(result > 0) { 
			
//			성공 시 alert 메시지 후 쪽지함으로
			
			HttpSession session = request.getSession();
			
			session.setAttribute("alertMsg", "쪽지가 전송되었습니다.");
			
			response.sendRedirect(request.getContextPath() + "/list.mes");
			
		} else {
			
//			에러페이지로 포워딩
			
			request.setAttribute("errorMsg", "쪽지가 전송되지 않았습니다.");
			
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			
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
