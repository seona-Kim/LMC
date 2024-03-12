package com.lmc.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmc.board.model.service.BoardService;
import com.lmc.board.model.vo.Commend;

/**
 * Servlet implementation class BoardCommendController
 */
@WebServlet("/commend.bo")
public class BoardCommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommendController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		int memberNo = Integer.parseInt(request.getParameter("mno"));
		
		Commend c = new Commend();
		
		//.out.println(boardNo);
		
		//System.out.println(memberNo);
		
		int result = new BoardService().selectcommnedCount(boardNo,memberNo);
		
		//System.out.println(result);
		//int result = new BoardService().intsertCommend(boardNo);
		
		if(result == 1) { //이 아이디로 이 게시물  이미추천되어있음
			
			//request.getSession()
			//.setAttribute("alertMsg", "이미 추천을 했습니다.");
			
			int result1 = new BoardService().deleteCommend(boardNo,memberNo);
			//System.out.println(result1);	//추천취소 함!
			
			
			int count = new BoardService().CountCommend(boardNo,memberNo);
			System.out.println("추천 수 잘 나옴: " + count);
			
			response.setContentType("text/html; charset=UTF-8");	
			
			PrintWriter out = response.getWriter();
			
			out.print(count);
			
			
		}else{//이 아이디로 이 게시물 추천이안되어있음 즉, 추천 가능
			
			int result1 = new BoardService().intsertCommend(boardNo,memberNo);
			//System.out.println(result1);	//추천 함!
			
			
			
			int count = new BoardService().CountCommend(boardNo,memberNo);
			System.out.println("추천 수 잘 나옴: " + count);
			
			//request.setAttribute("count", count);
			
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			
			out.print(count);
			
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
