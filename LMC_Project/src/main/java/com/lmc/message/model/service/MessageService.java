package com.lmc.message.model.service;

import static com.lmc.common.JDBCTemplate.close;
import static com.lmc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.lmc.board.model.dao.BoardDao;
import com.lmc.common.JDBCTemplate;
import com.lmc.member.model.vo.Member;
import com.lmc.message.model.dao.MessageDao;
import com.lmc.message.model.vo.Message;

public class MessageService {
	
	public ArrayList<Message> selectReciveList(String memberNo) {
		
//		1) Connection 객체 생성
		Connection conn = getConnection();
		
//		2) Connection 과 전달 값을 DAO 로 넘긴 후 결과 받기
		ArrayList<Message> list = new MessageDao().selectReciveList(conn, memberNo);
		
//		3) 트랜잭션 처리 => select 문이므로 pass
		
//		4) Connection 객체 반납
		close(conn);
		
//		5) Controller 로 결과 리턴
		return list;
	}
	
	public ArrayList<Message> selectSendList(String memberNo) {
		
//		1) Connection 객체 생성
		Connection conn = getConnection();
		
//		2) Connection 과 전달 값을 DAO 로 넘긴 후 결과 받기
		ArrayList<Message> list = new MessageDao().selectSendList(conn, memberNo);
		
//		3) 트랜잭션 처리 => select 문이므로 pass
		
//		4) Connection 객체 반납
		close(conn);
		
//		5) Controller 로 결과 리턴
		return list;
	}
	
	public Message selectMessage(int messageNo) {
		
//		1) Connection 객체 생성
		Connection conn = getConnection();
		
//		2) Connection 과 전달 값을 DAO 로 넘긴 후 결과 받기 
		Message m = new MessageDao().selectMessage(conn, messageNo);
		
//		3) 트랜잭션 처리 => select문 이므로 pass
		
//		4) Connection 객체 반납
		close(conn);
		
//		5) Controller 로 결과 리턴
		return m;
	}
	
	public Member selectProfile(int recive) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		Member profile = new BoardDao().selectProfile(conn, recive);
		
		// 3) 트랜잭션 처리
		// select문이므로 패스
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return profile;
	}
	
	public int insertMessage(Message m) {
		
		// 1) Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) Connection 과 전달값을 넘기면서 DAO 로 요청 후 결과 받기
		int result = new MessageDao().insertMessage(conn, m);
		
		// 3) 트랜잭션 처리
		if(result > 0) {
			
			// 성공했을 경우
			commit(conn);
			
		} else {
			
			// 실패했을 경우
			rollback(conn);
		}
		
		// 4) Connection 객체 반납
		close(conn);
		
		// 5) Controller 로 결과 리턴
		return result;
	}

}
