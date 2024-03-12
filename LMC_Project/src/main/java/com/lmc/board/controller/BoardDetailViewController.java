package com.lmc.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lmc.board.model.service.BoardService;
import com.lmc.board.model.vo.Attachment;
import com.lmc.board.model.vo.Board;
import com.lmc.member.model.vo.Member;

/**
 * Servlet implementation class BoardDetailViewController
 */
@WebServlet("/boardDetailView.bo")
public class BoardDetailViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Board a = new Board();
		
		int boardNo =Integer.parseInt(request.getParameter("bno")); // 게시글번호뽑기
		
		
		HttpSession session = request.getSession();
		
		
		
		
		String hiddenKeyword = request.getParameter("hiddenKeyword");

		//System.out.println(hiddenKeyword);
		// BoardService 객체를 생성해두기
		BoardService bService = new BoardService();
		
		// 글번호를 가지고 조회수 증가
		int result = bService.increaseCount(boardNo);
		
		
		if(result > 0) { //조회수가 증가함
			
			// 게시글조회
			Board b = bService.selectBoard(boardNo);
			
			// 첨부파일조회
			Attachment at = bService.selectAttachment(boardNo);
			
			// 댓글 카운트 조회
			int replyCount = bService.selectBoardReplyCount(boardNo);
			
			//추천수 조회
			int c = bService.selectBoardCommend(boardNo);
			
			if(session.getAttribute("loginUser") != null) {
				int memberNo = ((Member)session.getAttribute("loginUser")).getMemberNo(); //게시글 유저번호
				
				//내가 추천한게시물이면 따봉색이 다르게끔 미리설정
				int c1 = bService.selectcommnedCount(boardNo,memberNo);
				request.setAttribute("c1",c1);
				request.setAttribute("memberNo",memberNo);
			}
			//작성자회원번호로 프로필사진 불러오기 프사가 있으면 불러오고 없으면 NULL
			Member profile = bService.selectProfile(boardNo);
			
			// 잘 조회되었나 테스트
			// System.out.println(b);
			// System.out.println(at);
			// System.out.println(profile);
			// 응답 데이터로 각각 넘기고 포워딩
			request.setAttribute("b", b);
			request.setAttribute("at", at);
			request.setAttribute("profile", profile);
			request.setAttribute("c", c);
			request.setAttribute("replyCount", replyCount);
			
			
			request.getRequestDispatcher("views/board/boardDetailView.jsp").
			forward(request, response);
			
		}else {// 증가하지않음
			
			// 에러문구를 담아서 에러페이지로 포워딩 (실패 페이지로 포워딩)
			request.setAttribute("errorMsg", "게시글 상세조회 실패");
			
			request.getRequestDispatcher("views/commmon/errorPage.jsp").forward(request, response);
			
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
