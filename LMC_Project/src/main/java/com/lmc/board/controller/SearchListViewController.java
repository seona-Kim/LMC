package com.lmc.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmc.board.model.service.BoardService;
import com.lmc.board.model.vo.Board;
import com.lmc.common.model.vo.PageInfo;

/**
 * Servlet implementation class SearchListViewController
 */
@WebServlet("/searchListView.bo")
public class SearchListViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchListViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("keyword");
		
		Board b = new Board();
		
		b.setBoardTitle(keyword);
		
		int listCount;	 // 현재 총 게시글의 갯수
		
		//이거를 검색할때마다 게시글수가 다르게나오게해야함
		//키워드가 포함된제목 갯수 만큼
		//키워드를 넘겨서 쿼리문에 웨어절로 키워드가포함됫나 짜야뎀
		
		int currentPage; // 현재 페이지 (즉, 사용자가 요청한 페이지)
		int pageLimit;	 // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		int boardLimit;	 // 한 페이지에 보여질 게시글의 최대 갯수 (몇개 단위씩)
		
		int maxPage;	 // 가장 마지막 페이지가 몇번 페이지인지 (즉, 총 페이지 수)
		int startPage;	 // 페이지 하단에 보여질 페이징바의 시작수
		int endPage;	 // 페이지 하단에 보여질 페이징바의 끝수
		
		// * listCount : 총 게시글의 갯수
		// (단, 삭제된 게시글은 빼고, 일반게시글의 갯수 구해야함)
		listCount = new BoardService().searchListCount(b);
		
		// System.out.println(listCount);
		
		// * currentPage : 현재페이지 (즉, 사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		// System.out.println(currentPage);
		
		// * pageLimit : 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		//				 (즉, 페이지 목록들 몇개 단위씩)
		pageLimit = 10;
		
		// * boardLimit : 한 페이지 당 보여질 게시글의 최대 갯수
		//				  (즉, 게시글 몇개 단위씩)
		boardLimit = 10;
		
		// * maxPage : 가장 마지막 페이지가 몇번 페이지인지
				//			   (즉, 총 페이지의 수)
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		// * startPage : 페이지 하단에 보여지는 페이징바의 시작수
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		// * endPage : 페이지 하단에 보여질 페이징바의 끝수
		endPage = startPage + pageLimit - 1;
		
		if(endPage > maxPage) {
					
					endPage = maxPage;
		}
		
		// 페이지 정보들을 하나의 공간에 담기
		// 페이지 정보들을 담을 VO 클래스를 하나 만들 것
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, 
				   boardLimit, maxPage, startPage, endPage);
		
		
		
		//System.out.println(keyword);
		
		ArrayList<Board> searchList 
		= new BoardService().searchListView(b,pi);
		
		//System.out.println(searchList);
		
		//System.out.println(startPage);
		//System.out.println(endPage);
		
		if(searchList == null) { //실패
			
			request.setAttribute("errorMsg", "검색에 실패했습니다.");
						
			// 응답 페이지를 지정하면서 RequestDispatcher 객체 생성
			RequestDispatcher view 
				= request.getRequestDispatcher("views/common/errorPage.jsp");
			
		}else { //성공
			
			
			request.setAttribute("searchList", searchList);
			request.setAttribute("keyword", keyword);
			request.setAttribute("pi", pi);
			
			request.getRequestDispatcher("views/board/searchListView.jsp").
			forward(request, response);
		
			
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
