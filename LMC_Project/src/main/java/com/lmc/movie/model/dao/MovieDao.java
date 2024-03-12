package com.lmc.movie.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.lmc.board.model.vo.Attachment;
import com.lmc.common.JDBCTemplate;
import com.lmc.common.model.vo.PageInfo;
import com.lmc.movie.model.vo.Movie;

public class MovieDao {

	Properties prop = new Properties();
	
	// MovieDao 호출시 Properties 객체 사용이 가능하도록 기본 생성자 만들기
	public MovieDao() {
		
		String fileName = MovieDao.class.getResource("/sql/movie/movie-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	// 영화 정보 게시글 총 갯수 조회용 메소드
	public int selectListCount(Connection conn) {

		// 필요 변수 셋팅
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// sql문 셋팅
		String sql = prop.getProperty("selectListCount");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 미완성 SQL문 완성시키기 (이미 완성된 SELECT 문)
			// 쿼리문 실행하고 결과 받기
			rset = pstmt.executeQuery();
			
			// rset으로 부터 결과 뽑기 > COUNT 그룹함수 이용했기 때문에 단일행 조회
			if(rset.next()) {
				
				listCount = rset.getInt("COUNT"); // SQL문 별칭으로 뽑음
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		System.out.println(listCount);
		return listCount;
	}

	// 영화 게시판 리스트 조회용 메소드
	public ArrayList<Movie> selectList(Connection conn, PageInfo pi) {
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<Movie> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("selectList");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// 미완성된 SQL문 완성시키기
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			// SQL문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			// rset으로 부터 받은 결과 가공하기 (ArrayList<Movie>에 담음)
			
			while(rset.next()) {
				
				list.add(new Movie(rset.getInt("MOVIE_NO"),
									rset.getString("MOVIE_NAME"),
									rset.getString("MOVIE_CONTENT"),
									rset.getString("MOVIE_CONTENT2"),
									rset.getString("MOVIE_IMG"),
									rset.getString("STATUS")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다쓴 JDBC용 자원 반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		System.out.println(list);
		// Service 단으로 조회 결과 반납
		return list;
	}
	
	public ArrayList<Movie> selectMovieimgList(Connection conn) {
		
		// SELECT 문 => ResultSet 객체
		// => 여러행 조회 (ArrayList)
		
		// 필요한 변수 먼저 셋팅
		ArrayList<Movie> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("selectMovieimgList");
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
		
			// 미완성된 SQL문 완성시키기
			// => 완성된 형태이므로 패스
			
			// SQL문 실행 후 결과 받기
			// select문 : executeQuery 메소드
			rset = pstmt.executeQuery();
			
			// rset 으로부터 데이터를 뽑아서 VO 에 담기
			while(rset.next()) {
				
				Movie m = new Movie();
				
				m.setMovieNo(rset.getInt("MOVIE_NO"));
				m.setMovieName(rset.getString("MOVIE_NAME"));
				m.setMovieContent(rset.getString("MOVIE_CONTENT"));
				m.setMovieContent2(rset.getString("MOVIE_CONTENT2"));
				m.setMovieImg(rset.getString("MOVIE_IMG"));
				
				list.add(m);
			}
		
			// 조회된 데이터가 있다면
			// list.isEmpty() == false
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// JDBC 용 자원 반납 (역순)
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// Service 단으로 결과 반환
		return list;
	
	}
		
	public int insertMovieBoard(Connection conn, Movie m) {
			
			// INSERT 문 => 처리된 행의 갯수 (int)
			
			// 필요한 변수 먼저 셋팅
			int result = 0;
			PreparedStatement pstmt = null;
			
			// 실행할 SQL문		
			String sql = prop.getProperty("insertMovieBoard");
			
			try {
				
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				
				// 미완성된 쿼리문 완성시키기
				pstmt.setString(1, m.getMovieName());
				pstmt.setString(2, m.getMovieContent());
				pstmt.setString(3, m.getMovieContent2());
				pstmt.setString(4, m.getMovieImg());


				// 쿼리문 실행 후 결과 받기
				// insert문 : executeUpdate 메소드
				result = pstmt.executeUpdate();
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				// JDBC 용 자원 반납
				JDBCTemplate.close(pstmt);
			}
	
			// Service 단으로 결과 리턴
			return result;
		}
	
	public int insertAttachmentList(Connection conn, ArrayList<Attachment> list) {
		
		// INSERT 문 여러번 => 각각 처리된 행의 갯수
		
		// 필요한 변수 먼저 셋팅
		int result = 1;
		// insert 를 반복해서 진행 => 하나라도 실패할 경우 실패처리!!
		// result 를 애초에 1로 셋팅해두고 누적 곱을 구할 예정
		
		PreparedStatement pstmt = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("insertAttachmentList");
		
		try {
		
			// pstmt 객체 1개 
			// == 쿼리문 1개 돌릴 때 필요
			// 반복문이 돌 때 마다 
			// 미완성된 SQL문을 담은 pstmt 생성
			for(Attachment at : list) {
				
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				
				// 미완성된 쿼리문 완성시키기
				
				pstmt.setString(1, at.getOriginName());
				pstmt.setString(2, at.getChangeName());
				pstmt.setString(3, at.getFilePath());
				
				// 쿼리문 실행 후 결과 받기
				// insert문 : executeUpdate 메소드
				result *= pstmt.executeUpdate();
				// 누적 곱 구하기!!
				// => 하나라도 insert 에 실패하면 result == 0
			}
		
			// 이 시점 기준으로
			// 모두 성공했을 경우 result == 1
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			
			// JDBC 용 자원 반납
			JDBCTemplate.close(pstmt);
		}
		
		// Service 단으로 결과 리턴
		return result;
		}
	
	public Movie selectBoard(Connection conn, int movieNo) {
		
		
		Movie m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBoard");
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
		
			// 미완성된 쿼리문 완성시키기
			pstmt.setInt(1, movieNo);
			
			// 쿼리문 실행 후 결과 받기
			// select문 : executeQuery 메소드
			rset = pstmt.executeQuery();
			
			// rset 에 담긴 데이터를 VO로 가공
			if(rset.next()) {
				
				m = new Movie(rset.getInt("MOVIE_NO"),
							  rset.getString("MOVIE_NAME"),
							  rset.getString("MOVIE_CONTENT"),
							  rset.getString("MOVIE_CONTENT2"),
							  rset.getString("MOVIE_IMG") );
				
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return m;
	}
		
	
	
	
	public Attachment selectAttachment(Connection conn, int movieNo) {
			
			// SELECT 문 => ResultSet 객체
			// => 단일행 조회 (Attachment 객체)
			
			// 필요한 변수 먼저 셋팅
			Attachment at = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			// 실행할 SQL문 
			String sql = prop.getProperty("selectAttachment");
			
			try {
				
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
			
				// 미완성된 쿼리 완성시키기
				pstmt.setInt(1, movieNo);
				
				// 쿼리문 실행 후 결과 받기
				// select문 : executeQuery 메소드
				rset = pstmt.executeQuery();
				
				// rset 에 담긴 데이터를 VO 로 담기
				if(rset.next()) {
					
					at = new Attachment();
					
					at.setFileNo(rset.getInt("FILE_NO"));
					at.setOriginName(rset.getString("ORIGIN_NAME"));
					at.setChangeName(rset.getString("CHANGE_NAME"));
					at.setFilePath(rset.getString("FILE_PATH"));
				}
			
				// 이 시점 기준으로
				// 해당 게시글에 딸린 첨부파일이 없었다면
				// at == null
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				// JDBC 용 자원 반납 (역순)
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			// Service 단으로 결과 리턴
			return at;
		}
	
	
	}



