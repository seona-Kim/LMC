package com.lmc.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmc.board.model.service.BoardService;
import com.lmc.board.model.vo.Board;
import com.lmc.common.model.vo.PageInfo;
import com.lmc.member.model.vo.Member;

/**
 * Servlet implementation class MyPageBoardController
 */
@WebServlet("/myPageBoard.me")
public class MyPageBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") == null) {
			
			session.setAttribute("alertMsg", "로그인 후 이용 가능한 서비스입니다.");
			response.sendRedirect(request.getContextPath());
		} else {
			// RequestDispatcher view = request.getRequestDispatcher("views/member/myPageBoard.jsp");
			// view.forward(request, response);
			
			// ------ 페이징 처리 ------
			int listCount; 	// 현재 총 게시글의 개수
			int currentPage;// 현재 페이지 (즉, 사용자가 요청한 페이지)
			int pageLimit; 	// 페이지 하단에 보여질 페이징바의 페이지 최대 개수
			int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수 (몇개 단위씩)
			
			int maxPage; 	// 가장 마지막 페이지가 몇번 페이지인지 (총 페이지 수)
			int startPage; 	// 페이지 하단에 보여질 페이징바의 시작수
			int endPage; 	// 페이지 하단에 보여질 페이징바의 끝수
			
			int memberNo = ((Member)session.getAttribute("loginUser")).getMemberNo();
			
			
			listCount = new BoardService().selectMyListCount(memberNo);
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			
			pageLimit = 3;
			
			boardLimit = 10;
			
			maxPage = (int)Math.ceil((double)listCount / boardLimit);
			
			startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
			
			endPage = startPage + pageLimit - 1;
			
			if(endPage > maxPage) {
				endPage = maxPage;
			}
			
			PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
			
			ArrayList<Board> list = new BoardService().selectMyList(pi, memberNo);
			
			request.setAttribute("pi", pi);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("views/member/myPageBoard.jsp").forward(request, response);
			
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