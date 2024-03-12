package com.lmc.message.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmc.board.model.vo.Board;
import com.lmc.member.model.vo.Member;
import com.lmc.message.model.service.MessageService;
import com.lmc.message.model.vo.Message;

/**
 * Servlet implementation class MessageReplyEnrollFormController
 */
@WebServlet("/enrollFormReply.mes")
public class MessageEnrollFormReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageEnrollFormReplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		

		
//      MessageService 객체 생성해두기
        MessageService mService = new MessageService();
        
		int recive = Integer.parseInt(request.getParameter("dmRecive"));
		
		Message m = mService.selectMessage(recive);
		
		Member profile = mService.selectProfile(recive);
		
		
		request.setAttribute("dmRecive",recive);
		
		
		request.setAttribute("m", m);
		request.setAttribute("profile", profile);
		
		RequestDispatcher view = request.getRequestDispatcher("views/message/messageEnrollFormReply.jsp");
		
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
