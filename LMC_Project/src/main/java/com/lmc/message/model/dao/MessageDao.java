package com.lmc.message.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.lmc.common.JDBCTemplate;
import com.lmc.member.model.vo.Member;
import com.lmc.message.model.vo.Message;

public class MessageDao {
	
	private Properties prop = new Properties();
	
	// 공통코드를 담아둘 기본생성자 작성
	// (message-mapper.xml 파일로부터 쿼리문을 읽어오기)
	public MessageDao() {
		
		String fileName = MessageDao.class
							.getResource("/sql/message/message-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Message> selectReciveList(Connection conn, String memberNo) {
		
//		SELECT 문 => ResultSet 객체 => ArrayList<Message> (여러행 조회)
		
//		필요한 변수들 먼저 세팅
		ArrayList<Message> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
//		실행할 sql 문
		String sql = prop.getProperty("selectReciveList");
		
		try {
			
//			PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
//			미완성된 SQL 문 완성
			pstmt.setInt(1, Integer.parseInt(memberNo));
			
//			SQL 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
//			rset 에서 조회된 데이터를 ArrayList 에 담기
			while(rset.next()) {
				Message m = new Message();
				
				m.setDmNo(rset.getInt("DM_NO"));
				m.setDmContent(rset.getString("DM_CONTENT"));
				m.setDmSendMember(rset.getString("MEMBER_NICK"));
				m.setDmSendDate(rset.getDate("DM_SEND_DATE"));
				
				list.add(m);
			}
			
//			이 시점 기준으로
//			데이터 o => list.isEmpty() == false
//			데이터 x => list.isEmpty() == true
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
//		Service 단으로 결과 리턴
		return list;
		
	}
	
	public ArrayList<Message> selectSendList(Connection conn, String memberNo) {
		
//		SELECT 문 => ResultSet 객체 => ArrayList<Message> (여러행 조회)
		
//		필요한 변수들 먼저 세팅
		ArrayList<Message> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
//		실행할 sql 문
		String sql = prop.getProperty("selectSendList");
		
		try {
			
//			PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
//			미완성된 SQL 문 완성
			pstmt.setInt(1, Integer.parseInt(memberNo));
			
//			SQL 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
//			rset 에서 조회된 데이터를 ArrayList 에 담기
			while(rset.next()) {
				Message m = new Message();
				
				m.setDmNo(rset.getInt("DM_NO"));
				m.setDmContent(rset.getString("DM_CONTENT"));
				m.setDmSendMember(rset.getString("MEMBER_NICK"));
				m.setDmSendDate(rset.getDate("DM_SEND_DATE"));
				
				list.add(m);
			}
			
//			이 시점 기준으로
//			데이터 o => list.isEmpty() == false
//			데이터 x => list.isEmpty() == true
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
//		Service 단으로 결과 리턴
		return list;		
	}
	
	public Message selectMessage(Connection conn, int messageNo) {
		
//		SELECT 문 => ResultSet 객체 => Message 객체 (단일행)
		
//		필요한 변수들 먼저 세팅
		Message m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
//		실행할 sql문
		String sql = prop.getProperty("selectMessage");
		
		try {
			
//			PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
//			미완성된 sql 문 완성시키기
			pstmt.setInt(1, messageNo);
			
//			SQL문 실행 후 결과 받기 => select 문 => executeQuery() 메소드
			rset = pstmt.executeQuery();
			
//			rset 으로부터 조회된 결과를 VO 로 옮기기
			if(rset.next()) {
				
				m = new Message();
				m.setDmNo(rset.getInt("DM_NO"));
				m.setMemberImg(rset.getString("MEMBER_IMG"));
				m.setDmSendMember(rset.getString("MEMBER_NICK"));
				m.setDmContent(rset.getString("DM_CONTENT"));
				m.setDmSendDate(rset.getDate("DM_SEND_DATE"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 다 쓴 JDBC 용 자원 반납 (역순)
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// Service 단으로 결과 리턴
		return m;
	}

	public Member selectProfile(Connection conn, int recive) {
		
//		SELECT 문 => ResultSet 객체 => 단일행 조회 (Attachment 객체)
		
		// 필요한 변수 먼저 셋팅
		Member profile = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문 
		String sql = prop.getProperty("selectProfile");
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 미완성된 쿼리 완성시키기
			pstmt.setInt(1, recive);
			
			// 쿼리문 실행 후 결과 받기
			// select문 : executeQuery 메소드
			rset = pstmt.executeQuery();
			
			// rset 에 담긴 데이터를 VO 로 담기
			if(rset.next()) {
				profile = new Member();
				profile.setMemberImg(rset.getString("MEMBER_IMG"));
				profile.setMemberNick(rset.getString("MEMBER_NICK"));
				profile.setMemberNo(rset.getInt("MEMBER_NO"));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			// JDBC 용 자원 반납 (역순)
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// Service 단으로 결과 리턴
		return profile;
	}
	
	
	public int insertMessage(Connection conn, Message m ) {
		
//		INSERT 문 => 처리된 행의 갯수 (int)
		
//		필요한 변수들 먼저 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
//		실행할 sql 문
		String sql = prop.getProperty("insertMessage");
		
		try {
			
//			PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getDmContent());
			pstmt.setInt(2, Integer.parseInt(m.getDmSendMember()));
			pstmt.setInt(3, Integer.parseInt(m.getDmReciveMember()));
			
//			sql 문 실행 후 결과 받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {

			e.printStackTrace();
		
		} finally {
			
			JDBCTemplate.close(pstmt);
			
		}
		
		return result;
		
		
	}
	
	
	
}












