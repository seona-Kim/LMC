package com.lmc.message.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmc.board.model.service.BoardService;
import com.lmc.board.model.vo.Board;
import com.lmc.member.model.vo.Member;
import com.lmc.message.model.service.MessageService;
import com.lmc.message.model.vo.Message;

/**
 * Servlet implementation class MessageDetailController
 */
@WebServlet("/detail.mes")
public class MessageDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(); // 현재 세션을 가져옵니다.
		
	    Member loginUser = (Member) session.getAttribute("loginUser"); // 세션에서 로그인한 사용자의 정보를 가져옵니다.
	    
	    String memberNo = "" + loginUser.getMemberNo(); // 로그인한 사용자의 회원번호를 문자열로 변환합니다.
	    
	    String status = request.getParameter("status"); // 요청 파라미터에서 쪽지의 상태를 가져옵니다.
        
//      MessageService 객체 생성해두기
        MessageService msService = new MessageService();
        
//      쪽지 번호
		int messageNo = Integer.parseInt(request.getParameter("dno"));
		
//		쪽지 상세조회용 서비스 요청
		Message m = new MessageService().selectMessage(messageNo);
		
		Member profile = msService.selectProfile(messageNo); // MessageService 객체의 selectProfile 메소드를 호출하여, 쪽지의 번호에 해당하는 쪽지의 보낸 사람 또는 받은 사람의 프로필 정보를 가져옵니다. Member 클래스는 회원의 정보를 담는 클래스입니다.
		
		request.setAttribute("m", m); // 요청 객체에 쪽지의 상세 정보를 "m"이라는 이름으로 저장합니다.
		request.setAttribute("profile", profile); // 요청 객체에 프로필 정보를 "profile"이라는 이름으로 저장합니다.
		request.setAttribute("status", status); // 요청 객체에 쪽지의 상태를 "status"라는 이름으로 저장합니다.
		
		RequestDispatcher view = request.getRequestDispatcher("views/message/messageDetailView.jsp"); // RequestDispatcher 객체를 생성하고, 쪽지의 상세 정보를 보여주는 JSP 파일의 경로를 인자로 전달합니다. RequestDispatcher 클래스는 요청을 다른 자원으로 전달하는 역할을 합니다.
		
		view.forward(request, response); // RequestDispatcher 객체의 forward 메소드를 호출하여, 요청과 응답 객체를 JSP 파일로 전달합니다. 이렇게 하면, JSP 파일에서 요청 객체에 저장된 정보를 사용하여 쪽지의 상세 정보를 화면에 출력할 수 있습니다.		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
