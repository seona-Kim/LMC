package com.lmc.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmc.common.model.vo.PageInfo;
import com.lmc.notice.model.service.NoticeService;
import com.lmc.notice.model.vo.Notice;


/**
 * Servlet implementation class AdminNoticeListController
 */
@WebServlet("/noticeList.no")
public class AdminNoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminNoticeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// -- 페이징 처리를 위한 변수 셋팅 --
		int listCount;		// 현재 총 게시글의 갯수
		int currentPage; 	// 현재 페이지 (즉, 사용자가 요청한 페이지)
		int pageLimit; 		// 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		int boardLimit; 	// 한페이지에 보여질 게시글의 최대 갯수 (몇개 단위씩)
		
		int maxPage; 		// 가장 마지막 페이지가 몇번 페이지인지 (즉, 총 페이지 수)
		int startPage; 		// 페이지 하단에 보여질 페이징바의 시작 수
		int endPage;		// 페이지 하단에 보여질 페이징바의 끝 수
		
		// * listCount : 총 게시글의 갯수
		// (단, 삭제된 게시글은 빼고, 일반게시글의 갯수 구해야 함)
		listCount = new NoticeService().selectListCount();
		
		// *currentPage : 현재페이지 (즉, 사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		// *pageLimit : 페이지 하단에 보여질 페이징바의 페이지 최대 갯수
		//				(즉, 페이지 목록들 몇개 단위씩)
		pageLimit = 5;
		
		// *boardLimit : 한 페이지 당 보여질 게시글의 최대 갯수
		// 				 (즉, 게시글 몇개 단위씩)
		boardLimit = 10;
		
		// *maxPage 최대 게시글 페이지 수	
		maxPage = (int)(Math.ceil((double)listCount / boardLimit));
		
		// *stratPage : 페이지 하단에 보여지는 페이징바의 시작 수
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		// * endPage : 페이지 하단에 보여질 페이징바의 끝수
		endPage = startPage + pageLimit - 1;
				
		// 만약 startPage 가 11 이여서 endPage가 20으로 계산되었는데
		// 근데 maxPage 가 고작 13페이지밖에 안된다면?
		// => endPage 를 maxPage 로 변경
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 페이지 정보들을 하나의 공간에 담기
		// 페이지 정보들을 담을 VO 클래스를 하나 만들 것
		// => 공지사항이나 사진게시판 등에서도 사용 가능하기 때문에
		//    common 페이지에 작성할 것
		
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit,
								   boardLimit, maxPage, startPage, endPage);
		
		// PageInfo 객체를 Service 로 넘기면서
		// 요청 후 결과 받기
		ArrayList<Notice> list = new NoticeService().selectList(pi);
		
		// 화면에 뿌려줘야할 데이터
		// 게시글 목록 : list
		// 페이징바 : pi
		
		// 응답데이터를 request 에 담기
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		// 포워딩 처리
		request.getRequestDispatcher("views/admin/adminPage_notice.jsp").forward(request, response);
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
