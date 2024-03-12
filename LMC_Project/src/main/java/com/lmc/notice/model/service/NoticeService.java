package com.lmc.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.lmc.board.model.dao.BoardDao;
import com.lmc.board.model.vo.Board;
import com.lmc.common.JDBCTemplate;
import com.lmc.common.model.vo.PageInfo;
import com.lmc.notice.model.dao.NoticeDao;
import com.lmc.notice.model.vo.Notice;

public class NoticeService {

	// 공지사항의 총 수를 확인하기 위한 메소드
	public int selectListCount() {

		// 1) Connection 객세 채성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 객체와 전달값 넘기면서 DAO로 요청 + 결과 받기
		int listCount = new NoticeDao().selectListCount(conn);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}

	// 공지사항 리스트 조회용 메소드 (페이징 처리 포함)
	public ArrayList<Notice> selectList(PageInfo pi) {

		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 객체와 전달값 넘기면서 DAO로 요청 + 결과 받기
		ArrayList<Notice> list = new NoticeDao().selectList(conn, pi);
		
		// 3) 트랜젝션 처리 => select문 이므로 패스
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 반납
		return list;
	}

	public int deleteNotice(String deleteNNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().deleteNotice(conn, deleteNNo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int insertNotice(Notice n) {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().insertNotice(conn, n);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int increaseCount(int noticeNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new NoticeDao().increaseCount(conn, noticeNo);
		
		if(result > 0) { 
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public Notice selectNotice(int noticeNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Notice n = new NoticeDao().selectNotice(conn, noticeNo);
		
		JDBCTemplate.close(conn);
		
		return n;
	}
	
	// 최근 공지 noticeLimit개 가져오기
	public ArrayList<Notice> closeNoticeList(int noticeLimit) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Notice> noticeList = new NoticeDao().closeNoticeList(conn, noticeLimit);
		
		JDBCTemplate.close(conn);
		
		return noticeList;
	}

}
