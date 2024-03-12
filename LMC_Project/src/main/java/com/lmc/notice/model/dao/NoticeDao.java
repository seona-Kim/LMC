package com.lmc.notice.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.lmc.board.model.vo.Board;
import com.lmc.common.JDBCTemplate;
import com.lmc.common.model.vo.PageInfo;
import com.lmc.notice.model.vo.Notice;

public class NoticeDao {
	
	// 쿼리문들을 키 + 밸류 세트로 담은 Properties 객체 셋팅
	private Properties prop = new Properties();
	
	public NoticeDao() {
		
		String fileName = NoticeDao.class.getResource("/sql/notice/notice-mapper.xml").getPath();
	
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	// 공지사항 전체 게시글 수 확인용 메소드
	public int selectListCount(Connection conn) {

		// 필요한 변수들 먼저 셋팅
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
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
		
		return listCount;
	}
	
	// 공지사항 리스트 조회용 메소드 (페이징 처리 포함)
	public ArrayList<Notice> selectList(Connection conn, PageInfo pi) {

		// 필요한 변수들 먼저 셋팅
		ArrayList<Notice> list = new ArrayList<>();
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
			
			// rset으로 부터 받은 결과 가공하기 (ArrayList<Notice>에 담음)
			
			while(rset.next()) {
				
				list.add(new Notice(rset.getInt("NOTICE_NO"),
									rset.getString("NOTICE_TITLE"),
									rset.getString("NOTICE_CONTENT"),
									rset.getInt("VIEWS"),
									rset.getDate("CREATE_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다쓴 JDBC용 자원 반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		// Service 단으로 조회 결과 반납
		return list;
	}

	public int deleteNotice(Connection conn, String deleteNNo) {
		
		// UPDATE 문 => 처리된 행의 개수 (int)
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE"
				+ "		FROM NOTICE"
				+ "	   WHERE NOTICE_NO IN ("+ deleteNNo +")";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertNotice(Connection conn, Notice n) {
		
		// INSERT문 => 처리된 행의 갯수 (int)
		
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int increaseCount(Connection conn, int noticeNo) {
		
		// UPDATE 문 => 처리된 행의 개수 (int)
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Notice selectNotice(Connection conn, int noticeNo) {
		
		// SELECT 문 => ResultSet 객체 => Notice 객체 (단일행)
		
		Notice n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				n = new Notice(rset.getInt("NOTICE_NO")
							 , rset.getString("NOTICE_TITLE")
							 , rset.getString("NOTICE_CONTENT")
							 , rset.getInt("VIEWS")
							 , rset.getDate("CREATE_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return n;
		
		
	}
	
	// 최근 공지 noticeLimit개 리턴해주는 메소드
	public ArrayList<Notice> closeNoticeList(Connection conn, int noticeLimit) {
		
		ArrayList<Notice> noticeList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("closeNoticeList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeLimit);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				
				noticeList.add(new Notice(rset.getInt("NOTICE_NO")
										, rset.getString("NOTICE_TITLE")
										, rset.getString("NOTICE_CONTENT")
										, rset.getInt("VIEWS")
										, rset.getDate("CREATE_DATE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return noticeList;
	}

}
