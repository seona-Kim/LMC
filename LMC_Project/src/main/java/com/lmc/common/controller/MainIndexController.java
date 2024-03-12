package com.lmc.common.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmc.board.model.service.BoardService;
import com.lmc.board.model.vo.Board;
import com.lmc.notice.model.service.NoticeService;
import com.lmc.notice.model.vo.Notice;

/**
 * Servlet implementation class MainIndexController
 */
@WebServlet("/index.main")
public class MainIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainIndexController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int listCount = 5; // 메인페이지에서 표시될 글 개수
		
		int category = 0;
		
		category = 10;
		ArrayList<Board> freeBoardList = new BoardService().bestBoardList(listCount, category);
		category = 20;
		ArrayList<Board> shearBoardList = new BoardService().bestBoardList(listCount, category);
		category = 30;
		ArrayList<Board> reviewBoardList = new BoardService().bestBoardList(listCount, category);
		
		ArrayList<Notice> noticeList = new NoticeService().closeNoticeList(listCount);
		
		request.setAttribute("freeBoardList", freeBoardList);
		request.setAttribute("shearBoardList", shearBoardList);
		request.setAttribute("reviewBoardList", reviewBoardList);
		request.setAttribute("noticeList", noticeList);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
