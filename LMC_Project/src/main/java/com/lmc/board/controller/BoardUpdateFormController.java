package com.lmc.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmc.board.model.service.BoardService;
import com.lmc.board.model.vo.Attachment;
import com.lmc.board.model.vo.Board;
import com.lmc.board.model.vo.Category;

/**
 * Servlet implementation class BoardUpdateFormController
 */
@WebServlet("/updateForm.bo")
public class BoardUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 카테고리 정보 불러오기
		BoardService bService = new BoardService();
		
		ArrayList<Category> list 
						= bService.selectCategoryList();
		
		// 게시글 상세정보, 첨부파일 상세정보 불러오기
		// (글번호가 필요함)
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		Board b = bService.selectBoard(boardNo);
		// 글번호, 카테고리명, 제목, 내용, 작성자아이디, 작성일
		
		Attachment at = bService.selectAttachment(boardNo);
		// 첨부파일번호, 원본명, 수정명, 저장경로
		// => 첨부파일이 없는 게시글일 경우 null
		
		request.setAttribute("list", list);
		request.setAttribute("b", b);
		request.setAttribute("at", at);
		
		// 게시글 수정 페이지 포워딩
		request.getRequestDispatcher("views/board/boardUpdateForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
