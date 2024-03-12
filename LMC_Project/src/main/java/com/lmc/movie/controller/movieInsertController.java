package com.lmc.movie.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.lmc.board.model.vo.Attachment;
import com.lmc.common.MyFileRenamePolicy;
import com.lmc.movie.model.service.MovieService;
import com.lmc.movie.model.vo.Movie;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class movieInsertController
 */
@WebServlet("/movieInsert.me")
public class movieInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public movieInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			
			
			int maxSize = 10 * 1024 * 1024;
			
			
			String savePath 
				= request.getServletContext()
					.getRealPath("/resources/movie_info/");
			
			// 3_2) MultipartRequest 객체 생성 (생성자 구문)
			// MultipartRequest 타입으로 이관, 파일명 수정, 파일 업로드가
			// 동시에 일어나게 됨!!
			MultipartRequest multiRequest 
					= new MultipartRequest(request, savePath, maxSize
										 , "UTF-8", new MyFileRenamePolicy());
			
			// 이 시점 기준으로 파일 업로드는 완료된 상황임
			
			// 4) multiRequest 로 부터 요청 시 전달값 뽑기
			// 변수 및 객체에 기록
			// - BOARD 테이블에 INSERT 
			
			String movieName = multiRequest.getParameter("name");
			String movieContent = multiRequest.getParameter("content");
			String movieContent2 = multiRequest.getParameter("content2");
			String movieImg = "/resources/movie_info/" + multiRequest.getFilesystemName("titleMovie");
			
			Movie m = new Movie();
			
			m.setMovieName(movieName);
			m.setMovieContent(movieContent);
			m.setMovieContent2(movieContent2);
			m.setMovieImg(movieImg);
			
			// 이 시점 기준으로 넘어온 첨부파일의 갯수 만큼
			// list 에 at 가 담겨있을 것!!
			
			int result = new MovieService().insertMovieBoard(m);
			
			if(result > 0) {
				request.getSession()
					.setAttribute("alertMsg", "성공적으로 영화정보게시글이 등록되었습니다.");
				
				response.sendRedirect(request.getContextPath() + "/movieList.me");
				
			} else { 
				
				request.setAttribute("errorMsg", "영화게시글 업로드 실패");
				
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
