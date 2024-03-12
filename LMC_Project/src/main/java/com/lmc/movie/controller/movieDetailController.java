package com.lmc.movie.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.lmc.movie.model.service.MovieService;
import com.lmc.movie.model.vo.Movie;

/**
 * Servlet implementation class movieDetailController
 */
@WebServlet("/detail.th")
public class movieDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public movieDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int movieNo = Integer.parseInt(request.getParameter("bno"));
		
		MovieService mService = new MovieService();
			
		
			
			Movie m = mService.selectBoard(movieNo);
			
			
			
			
		if(m != null ) {
			request.setAttribute("m", m);
		
			request.getRequestDispatcher("views/movie/movieDetail.jsp")
				.forward(request, response);
		}else {

			
			request.setAttribute("errorMsg", "사진게시글 조회 실패");
			
			request.getRequestDispatcher("views/common/errorPage.jsp")
											.forward(request, response);
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
