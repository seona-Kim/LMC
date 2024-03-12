package com.lmc.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmc.board.model.service.BoardService;
import com.lmc.member.model.vo.Member;

/**
 * Servlet implementation class DeleteMyBoardController
 */
@WebServlet("/deleteMyBoard.bo")
public class DeleteMyBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMyBoardController() {
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
		String[] deleteBNoArr = request.getParameterValues("deleteBNo");
		String deleteBNo = "";
		
		if(deleteBNoArr != null) {
			deleteBNo = String.join(", ", deleteBNoArr);
		}
		
		int result = new BoardService().deleteMyBoard(memberNo, deleteBNo);
		
		if(result > 0) { 
			session.setAttribute("alertMsg", "게시글 삭제에 성공했습니다.");
			
			response.sendRedirect(request.getContextPath() + "/myPageBoard.me?currentPage=1");
		} else {
			request.setAttribute("errorMsg", "게시글 삭제에 실패했습니다.");
			
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
