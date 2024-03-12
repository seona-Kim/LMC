package com.lmc.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.lmc.board.model.service.BoardService;
import com.lmc.board.model.vo.Attachment;
import com.lmc.board.model.vo.Board;
import com.lmc.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) POST 방식이므로 인코딩 설정 먼저하기
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// enctype 이 multipart/form-data 로 요청이 들어왔을 때
			// 기존 request 객체를 MultipartRequest 타입으로 변환
			// (HttpServletRequest -> MultipartRequest)
			
			// MultipartRequest multiRequest 
			// = new MultipartRequest(request, 저장할파일경로, 용량제한, 인코딩, 파일명수정객체);
			
			// 1. MultipartRequest 객체 생성 시 필요한 정보 얻기
			// 1_1. 전송파일 용량 제한 (int maxSize)
			// 10Mbyte 로 지정 (byte 단위로 셋팅하기)
			int maxSize = 10 * 1024 * 1024;
			
			// 1_2. 전달된 파일을 저장시킬 서버의 경로 (String savePath)
			// application scope 객체로부터 알아내기
			// 배포되는 webapp 폴더를 기준으로 경로를 제시해서 알아내기
			String savePath 
				= request.getSession().getServletContext()
						.getRealPath("/resources/images/"); 
			
			// 2. MultipartRequest 객체 생성하기
			MultipartRequest multiRequest 
				= new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			// 위의 구문 한줄 만으로
			// request 객체가 MultipartRequest 타입으로 이관
			// 전달된 파일명이 수정됨
			// 해당 경로에 전달된 파일이 업로드까지 일어남
			
			// 이 시점 기준으로 새롭게 넘어온 첨부파일이 서버에 업로드 되어있음
			
			// 3. 본격적으로 SQL문 실행 시 필요한 데이터들을 VO 로 가공하기
			// (요청 시 전달값 뽑기)
			
			// case 0. 기존 첨부파일 X, 새 첨부파일 X
			// => BOARD 테이블에 UPDATE 만
			
			// case 1. 기존 첨부파일 O, 새 첨부파일 O
			// => BOARD 테이블에 UPDATE 후 ATTACHMENT 테이블에 UPDATE
			
			// case 2. 기존 첨부파일 X, 새 첨부파일 O
			// => BOARD 테이블에 UPDATE 후 ATTACHMENT 테이블에 INSERT
			
			// - 공통적으로 수행 : BOARD 테이블에 UPDATE
			// Board VO 로 가공하기
			int boardNo 
				= Integer.parseInt(multiRequest.getParameter("boardNo"));
			String category = multiRequest.getParameter("category");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			
			
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setCategory(category); 
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			
			
			// - 새로이 첨부파일이 전달되었을 경우
			// (case 1, case 2 공통)
			Attachment at = null;
			
			// 첨부파일이 새롭게 넘어왔을 경우만 객체 생성
			// multiRequest.getOriginalFileName("키값")
			// => 넘어온 파일에 대한 원본파일명을 알 수 있음
			//    (넘어온 파일이 없다면 null 을 리턴)
			if(multiRequest.getOriginalFileName("reUpfile") != null) {
				
				at = new Attachment();
				
				// case 1 일 경우 필요한 데이터
				// 새로 넘어온 파일의 원본명, 수정명, 저장경로,
				// 기존 첨부파일의 파일번호가 필요함
				
				// case 2 일 경우 필요한 데이터
				// 게시글 번호, 
				// 새로 넘어온 파일의 원본명, 수정명, 저장경로가 필요함
				
				// - 공통적으로 필요한 데이터 먼저 셋팅
				// (새로 넘어온 파일의 원본명, 수정명, 저장경로)
				String originName 
					= multiRequest.getOriginalFileName("reUpfile");
				String changeName
					= multiRequest.getFilesystemName("reUpfile");
				String filePath = "resources/images/";
				
				at.setOriginName(originName);
				at.setChangeName(changeName);
				at.setFilePath(filePath);
				
				if(multiRequest.getParameter("originFileNo") != null) {
					// - case 1 인 경우 추가적으로 필요한 필드 셋팅
					// (기존 첨부파일이 있을 경우)
					// (기존 첨부파일의 파일번호)
					int fileNo 
						= Integer.parseInt(multiRequest.getParameter("originFileNo"));
					
					at.setFileNo(fileNo);
					
					// + 기존 첨부파일의 수정명도 같이 넘겼었음!!
					// => 기존 첨부파일을 서버로부터 삭제 (용량만 차지)
					String originFileName 
						= multiRequest.getParameter("originFileName");
					
					new File(savePath + originFileName).delete();
					
				} else {
					// - case 2 인 경우 추가적으로 필요한 필드 셋팅
					// (기존 첨부파일이 없을 경우)
					// (게시글 번호)
					
					at.setRefNo(boardNo);
				}
			}
			
			// 이 시점 기준으로
			// case 0, case 1, case 2 든 간에
			// 공통적으로 Board 객체는 셋팅 완료
			
			// case 1, case 2 든 간에
			// Attachment 객체 셋팅 완료
			// (즉, case 0 일 경우만 at == null)
			
			// case 1 일 경우
			// at 에 담겨있는 필드값 
			// 원본파일명, 수정파일명, 경로, 파일번호
			
			// case 2 일 경우
			// at 에 담겨있는 필드값
			// 원본파일명, 수정파일명, 경로, 현재 게시글번호
			
			// 모두 하나의 트랜잭션으로 처리해야함!!
			int result = new BoardService().updateBoard(b, at);
			
			// 결과에 따른 응답뷰 지정
			if(result > 0) { // 성공
				
				// 1회성 알람문구를 담아
				// 해당 게시글의 상세조회 페이지로 url 재요청
				request.getSession()
					.setAttribute("alertMsg", "성공적으로 게시글이 수정되었습니다.");
				
				response.sendRedirect(request.getContextPath() 
										+ "/boardDetailView.bo?bno=" + boardNo);
				
			} else { // 실패
				
				// 에러문구를 담아
				// 에러페이지로 포워딩
				request.setAttribute("errorMsg", "게시글 수정 실패");
				
				request.getRequestDispatcher("views/common/errorPage.jsp")
												.forward(request, response);
			}
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
