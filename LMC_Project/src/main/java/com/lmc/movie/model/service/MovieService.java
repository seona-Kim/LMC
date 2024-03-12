package com.lmc.movie.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.lmc.board.model.vo.Attachment;
import com.lmc.common.JDBCTemplate;
import com.lmc.common.model.vo.PageInfo;
import com.lmc.movie.model.dao.MovieDao;
import com.lmc.movie.model.vo.Movie;


public class MovieService {

	// 영화 정보 게시글의 총 갯수 조회용 메소드
	public int selectListCount() {

		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new MovieDao().selectListCount(conn);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}

	// 영화 정보 게시글 리스트 조회용 메소드
	public ArrayList<Movie> selectList(PageInfo pi) {

		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Movie> list = new MovieDao().selectList(conn,pi);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	
	public int insertMovieBoard(Movie m) {
		
		// 1) Connection 객체 생성
	Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기 - 1 (Board Insert)
		int result1 = new MovieDao().insertMovieBoard(conn, m);
		
		
		// 4) 트랜잭션 처리
		if(result1 > 0) {
			
			JDBCTemplate.commit(conn);
			
		} else {
			
			JDBCTemplate.rollback(conn);
		}
		
		// 5) Connection 반납
		JDBCTemplate.close(conn);
		
		// 6) Controller 단으로 결과 반환
		return result1;
	}
	
	public ArrayList<Movie> selectMovieimgList() {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		ArrayList<Movie> list 
			= new MovieDao().selectMovieimgList(conn);
		
		// 3) 트랜잭션 처리
		// select문이므로 패스
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 리턴
		return list;
	}

	public Movie selectBoard(int movieNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Movie m = new MovieDao().selectBoard(conn, movieNo);
		
		// 3) 트랜잭션 처리 
		// select문이므로 패스
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return m;
	}
	public Attachment selectAttachment(int movieNo) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		Attachment at = new MovieDao().selectAttachment(conn, movieNo);
		
		// 3) 트랜잭션 처리
		// select문이므로 패스
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return at;
	}
	public ArrayList<Attachment> selectAttachmentList(int movieNo) {
		
		// 1) Connection 객체 생성
	Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		//ArrayList<Attachment> list
		//	= new MovieDao().selectAttachmentList(conn, movieNo);
		
		// 3) 트랜잭션 처리
		// select 문이므로 패스
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 리턴
		return null;
	}

}
