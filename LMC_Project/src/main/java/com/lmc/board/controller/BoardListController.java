package com.lmc.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmc.board.model.service.BoardService;
import com.lmc.board.model.vo.Board;
import com.lmc.common.model.vo.PageInfo;
import com.lmc.notice.model.service.NoticeService;
import com.lmc.notice.model.vo.Notice;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ------ 페이징 처리 ------
		int listCount; 	// 현재 총 게시글의 개수
		int currentPage;// 현재 페이지 (즉, 사용자가 요청한 페이지)
		int pageLimit; 	// 페이지 하단에 보여질 페이징바의 페이지 최대 개수
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수 (몇개 단위씩)
		
		int maxPage; 	// 가장 마지막 페이지가 몇번 페이지인지 (총 페이지 수)
		int startPage; 	// 페이지 하단에 보여질 페이징바의 시작수
		int endPage; 	// 페이지 하단에 보여질 페이징바의 끝수
		
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int category = Integer.parseInt(request.getParameter("category"));
		String categoryName = new BoardService().searchCategoryName(category);
		
		listCount = new BoardService().selectCategoryListCount(category);
		
		pageLimit = 10;
		
		boardLimit = 15;
		
		int noticeLimit = 3; // 게시판 페이지 위쪽에 최근 공지 몇개 표시할건지
		
		maxPage = (int)Math.ceil((double)listCount / (boardLimit-noticeLimit));
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit-noticeLimit, maxPage, startPage, endPage);
		
		ArrayList<Board> list = new BoardService().selectCategoryBoardList(pi, category);
		
		//--- 최근 공지 리스트 불러오기
		ArrayList<Notice> noticeList = new NoticeService().closeNoticeList(noticeLimit);
		
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		request.setAttribute("category", category);
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("noticeList", noticeList);
		
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
