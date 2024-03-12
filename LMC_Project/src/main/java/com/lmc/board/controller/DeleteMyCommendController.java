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
 * Servlet implementation class DeleteMyCommendController
 */
@WebServlet("/deleteMyCommend.bo")
public class DeleteMyCommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMyCommendController() {
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
		String[] deleteCNoArr = request.getParameterValues("deleteCNo");
		String deleteCNo = "";
		
		if(deleteCNoArr != null) {
			deleteCNo = String.join(", ", deleteCNoArr);
		}
		
		int result = new BoardService().deleteMyCommend(memberNo, deleteCNo);
		
		if(result > 0) { 
			session.setAttribute("alertMsg", "추천 삭제에 성공했습니다.");
			
			response.sendRedirect(request.getContextPath() + "/myPageCommend.me?currentPage=1");
		} else {
			request.setAttribute("errorMsg", "추천 삭제에 실패했습니다.");
			
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
