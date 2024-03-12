package com.lmc.admin.controller;

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
import com.lmc.member.model.vo.Member;

/**
 * Servlet implementation class AdminSearchStopBoardController
 */
@WebServlet("/searchStopBoardList.bo")
public class AdminSearchStopBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchStopBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		//System.out.println("Controller 단에서 검색 키워드 정상 입력 여부 확인:" + keyword);
			
			Member m = new Member();
			
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
			String searchCategory; // 검색 시 선택한 카테고리
			
			
			searchCategory = request.getParameter("searchCategory");
			listCount = new BoardService().searchAllBoardListCount(keyword);
			
			// System.out.println(listCount);
			
			// * currentPage : 현재페이지 (즉, 사용자가 요청한 페이지)
			// System.out.println(request.getParameter("currentPage"));
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			//System.out.println("요청한 페이지(컨트롤러단) : " + currentPage );
			//System.out.println("선택 카테고리 잘 옴? : " + searchCategory );
			
			
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
			
			PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, 
					   boardLimit, maxPage, startPage, endPage);
			
			ArrayList<Board> searchList 
			= new BoardService().searchAllBoardList(keyword,pi);
		
		if(searchList == null) { //실패
			
			request.setAttribute("errorMsg", "검색에 실패했습니다.");
						
			// 응답 페이지를 지정하면서 RequestDispatcher 객체 생성
			RequestDispatcher view 
				= request.getRequestDispatcher("views/common/errorPage.jsp");
			
		}else { //성공
			
			
			request.setAttribute("list", searchList);
			request.setAttribute("keyword", keyword);
			request.setAttribute("pi", pi);
			
			request.getRequestDispatcher("views/admin/adminPage_board.jsp").
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
