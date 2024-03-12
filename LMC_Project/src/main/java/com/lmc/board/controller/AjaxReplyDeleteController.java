package com.lmc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmc.board.model.service.BoardService;
import com.lmc.board.model.vo.Reply;
import com.lmc.member.model.vo.Member;

/**
 * Servlet implementation class AjaxReplyDeleteController
 */
@WebServlet("/rdelete.bo")
public class AjaxReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxReplyDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			//System.out.println("잘되나?");
		
				//요청 시 전달값 먼저 뽑기
				//String replyContent = request.getParameter("content");
				//int boardNo = Integer.parseInt(request.getParameter("bno"));
				int replyNo = Integer.parseInt(request.getParameter("rno"));
				//System.out.println("넘어옴?"+ replyNo);
				//작성자의 회원 번호
				// => Session 으로부터 얻어내기
				//HttpSession session = request.getSession();
				//int memberNo = ((Member)session.getAttribute("loginUser")).getMemberNo();
				
				//Reply VO 객체로 가공
				Reply r = new Reply();
				//r.setRefBno(boardNo);
				r.setReplyNo(replyNo);
				
				//Service 단으로 요청 후 결과 받기
				int result = new BoardService().deleteReply(r);
				
				//System.out.println(result);
				
				//result 를 그대로 응답데이터에 넘기기
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().print(result);
				
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
