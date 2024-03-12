package com.lmc.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lmc.admin.model.vo.AdminPageInfo;
import com.lmc.board.model.service.BoardService;
import com.lmc.member.model.service.MemberService;

/**
 * Servlet implementation class AdminPageController
 */
@WebServlet("/adminPage.ad")
public class AdminPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 받아와야할 값들
		// 1. 총 회원수
		// 2. 금일 작성글 수
		// 3. 누적(총) 작성글 수
		// 4. 자유/나눔/정보/공지 게시판 글수
		int totalMember;  // 총 회원수
		int todayBoardCount;	  // 금일 전체 게시판 작성글 수
		
		int todayFreeBoardCount; 		// 금일 자유 게시판 작성글 수
		int todayShareBoardCount; 		// 금일 나눔 게시판 작성글 수
		int todayMovieInfoBoardCount;	// 금일 영화정보 게시판 작성글 수
		int todayNoticeBoardCount;		// 금일 공지 게시판 작성글 수
		
		int totalWrite;	 	// 총 작성글 수 (공지 + 전체 일반 게시글스(listCount 빼오기))
		int totalNotice; 	// 전체 공지 게시글 수
		int listCount;		// 전체 일반 게시글의 수
		
		int freeBoard;   	// 자유 게시판 글 수
		int shareBoard;	  	// 나눔 게시판 글 수
		int movieInfoBoard; // 영화정보 게시글 수  
		int noticeBoard;  	// 공지 게시판 글 수
		
		// 총 회원 수 + 금일 작성된 전체 일반 게시글의 수
		totalMember = new MemberService().selectListCount();
		todayBoardCount = new BoardService().todayBoardCount();
		
		// 일일 작성된 게시글 수 구하기
		todayFreeBoardCount = new BoardService().todayFreeBoardCount();
		todayShareBoardCount = new BoardService().todayShareBoardCount();
		todayMovieInfoBoardCount = new BoardService().todayMovieInfoBoardCount();
		todayNoticeBoardCount = new BoardService().todayNoticeBoardCount();
		
		// 작성된 전체 게시글의 수 구하기
		freeBoard = new BoardService().freeBoard();
		shareBoard = new BoardService().shareBoard();
		movieInfoBoard = new BoardService().movieInfoBoard();
		noticeBoard = new BoardService().noticeBoard();
		
		// 총 게시글 수 (공지포함)
		totalWrite = freeBoard + shareBoard + shareBoard + movieInfoBoard + noticeBoard;
		
		// 여러 통계 정보를 VO 객체에 담아서 넘기기
		AdminPageInfo api = new AdminPageInfo( totalMember,  todayBoardCount, todayFreeBoardCount,
				todayShareBoardCount, todayMovieInfoBoardCount, todayNoticeBoardCount,
				totalWrite,	freeBoard, shareBoard, movieInfoBoard, noticeBoard);
		
		// 게시판 현황 확인을 위해 어드민 페이지 정보 AJAX 처리
		response.setContentType("application/json; cahrset=UTF-8");
		new Gson().toJson(api, response.getWriter());
		
		// request.getRequestDispatcher("views/admin/adminPage.jsp").forward(request, response);
		
		
	}

	/**	
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
