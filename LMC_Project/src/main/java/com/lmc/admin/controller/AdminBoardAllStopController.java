package com.lmc.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmc.board.model.service.BoardService;
import com.lmc.member.model.service.MemberService;

/**
 * Servlet implementation class AdminBoardAllStopController
 */
@WebServlet("/allStopBoard.bo")
public class AdminBoardAllStopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBoardAllStopController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String currentPage = request.getParameter("currentPage");
		String[] checkNo = request.getParameterValues("bordercheck");
		
		//System.out.println("관리자 번호 잘 넘어옴?" +currentPage);
		//System.out.println("복구/정지 잘 넘어옴?" + result);
		//System.out.println("체크박스 잘 넘어옴?" + checkNo[1]);
		
		// SQL 문 Where 문에 Member_no IN ('값', '값', '값') 식으로 찎어야함
		// checkNo 를 , 로 이어지는 문자열로 가공
		String totalCheckNo = "";
		if(checkNo != null) {
			totalCheckNo = String.join(", ", checkNo);
		}
		
		int result = new BoardService().boardAllStop(totalCheckNo);
		
						
		if(result > 0) {
			request.setAttribute("alertMsg", result + "개 삭제 성공");
		} else {
			request.setAttribute("errorMsg", result + "개 삭제 실패했습니다");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
		response.sendRedirect(request.getContextPath()+ "/boardList.bo?currentPage="+currentPage);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
