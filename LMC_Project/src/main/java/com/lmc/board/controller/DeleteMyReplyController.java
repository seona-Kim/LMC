package com.lmc.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmc.board.model.service.BoardService;
import com.lmc.member.model.vo.Member;

/**
 * Servlet implementation class DeleteMyReplyController
 */
@WebServlet("/deleteMyReply.bo")
public class DeleteMyReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMyReplyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int memberNo = ((Member)session.getAttribute("loginUser")).getMemberNo();
		String[] deleteRNoArr = request.getParameterValues("deleteRNo");
		String deleteRNo = "";
		
		if(deleteRNoArr != null) {
			deleteRNo = String.join(", ", deleteRNoArr);
		}
		
		int result = new BoardService().deleteMyReply(memberNo, deleteRNo);
		
		if(result > 0) { 
			session.setAttribute("alertMsg", "댓글 삭제에 성공했습니다.");
			
			response.sendRedirect(request.getContextPath() + "/myPageReply.me?currentPage=1");
		} else {
			request.setAttribute("errorMsg", "댓글 삭제에 실패했습니다.");
			
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
