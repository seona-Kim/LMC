package com.lmc.message.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

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
 * Servlet implementation class MessageListController
 */
@WebServlet("/list.mes")
public class MessageListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        
        // 세션에서 loginUser 속성 가져오기
        Member loginUser = (Member) session.getAttribute("loginUser");

        // loginUser가 null이 아니라면 로그인된 상태이므로 추가 처리
        if (loginUser != null) {
            String memberNo = "" + loginUser.getMemberNo();
            // 로그인이 된 상태라면 쪽지함에 접근 가능한 상태

            // 요청 시 전달값 
            String status = request.getParameter("status"); // null 또는 "recived" 또는 "sent"
            
            status = (status == null) ? "recived" : status;
            
            if(status.equals("sent")) { // 보낸쪽지함을 요청한 경우 ("sent")
            	
            	// 보낸 쪽지 리스트 조회 결과를 담아서 응답 페이지로 포워딩
                ArrayList<Message> list = new MessageService().selectSendList(memberNo);
                request.setAttribute("list", list);
                request.setAttribute("status", status);

                // 조회된 데이터의 유무에 따라 이동할 뷰 선택
                RequestDispatcher view = request.getRequestDispatcher("views/message/messageListView.jsp");
                view.forward(request, response);
            	
            } else { // 그 이외의 경우 (null 또는 "recived") => 받은쪽지함일경우
            	
            	// 받은 쪽지 리스트 조회 결과를 담아서 응답 페이지로 포워딩
                ArrayList<Message> list = new MessageService().selectReciveList(memberNo);
                request.setAttribute("list", list);
                request.setAttribute("status", status);

                // 조회된 데이터의 유무에 따라 이동할 뷰 선택
                RequestDispatcher view = request.getRequestDispatcher("views/message/messageListView.jsp");
                view.forward(request, response);
            }
            
        } else {
        	
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
