package com.lmc.member.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.lmc.common.JDBCTemplate;
import com.lmc.common.model.vo.PageInfo;
import com.lmc.member.model.dao.MemberDao;
import com.lmc.member.model.vo.Member;

/*
 * Service 단의 처리 흐름
 * 1. Connection 객체 생성
 * 2. 만들어진 Connection 과 전달값을 
 * 	  DAO 로 넘기면서 요청 후 결과 받기
 * 3. 결과에 따른 트랜잭션 처리 (Select문일 경우에는 패스)
 * 4. Connection 객체 반납
 * 5. 결과를 Controller 로 리턴
 */
public class MemberService {
	
public Member loginMember(Member m) {
		
		// 1. Connection 객체 생성
		Connection conn 
			= JDBCTemplate.getConnection();
		
		// 2. 만들어진 Connection 과 전달값을
		//	  DAO 로 넘기면서 요청 후 결과 받기
		Member loginUser 
			= new MemberDao().loginMember(conn, m);
	
		// 3. 트랜잭션 처리 (SELECT문일경우 패스)
		
		// 4. Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5. Controller 로 결과 반환
		return loginUser;
	}
	
	public int insertMember(Member m) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서
		//	  DAO 로 요청 후 결과 받기
		int result = new MemberDao().insertMember(conn, m);
		
		System.out.println(result);
		// 3) 트랜잭션 처리
		if(result > 0) { // 회원가입 성공 => 커밋
			
			JDBCTemplate.commit(conn);
			
		} else { // 회원가입 실패 => 롤백
			
			JDBCTemplate.rollback(conn);
		}
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
	
		// 5) Controller 로 결과 반환
		return result;
	}

	public Member updateMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMem = null;
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
			
			updateMem = new MemberDao().selectMember(conn, m.getMemberId());
			
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		return updateMem;
	}

	public Member updatePwdMember(HashMap<String, String> hm) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updatePwdMember(conn, hm);
		
		Member updateMem = null;
		
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
			
			updateMem = new MemberDao().selectMember(conn, hm.get("memberId"));
		} else {
			
			JDBCTemplate.rollback(conn);
		}
		
		return updateMem;
	}
	
	// 총 회원의 숫자 확인용 메소드
	public int selectListCount() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new MemberDao().selectListCount(conn);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}

	// 회원 목록 조회용 메소드
	public ArrayList<Member> selectList(PageInfo pi) {

		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Member> list = new MemberDao().selectList(conn,pi);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public int deleteMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().deleteMember(conn, m);
		
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
			
		} else {
			
			JDBCTemplate.rollback(conn);
			
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	// 10월 16일 추가본
	// 관리자 페이지를 통해 회원의 상세 정보 조회용 메소드
	public Member selectMember(Member m) {
	
		// 1. Connection 객체 생성
		Connection conn 
			= JDBCTemplate.getConnection();
		
		// 2. 만들어진 Connection 과 전달값을
		//	  DAO 로 넘기면서 요청 후 결과 받기
		Member selectedM 
			= new MemberDao().selectMember(conn, m);
	
		// 3. 트랜잭션 처리 (SELECT문일경우 패스)
		
		// 4. Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5. Controller 로 결과 반환
		return selectedM;
	}
	
	// 10월 17일 윤석 추가
	// 전체 카테고리로 검색된 회원의 총 수 확인 용 메소드 
	public int searchAllMemberListCount(String searchCategory, String keyword) {

		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을
		// DAO 로 넘기면서 요청 후 결과 받기
		int listCount = new MemberDao().searchAllMemberListCount(conn, searchCategory, keyword);
		
		// 3) 트랜잭션 처리 (select문이므로 패스)
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 리턴
		return listCount;	}
	
	// 전체 카테고리로 검색된 회원의 리스트를 불러올 용도의 메소드
	public ArrayList<Member> searchAllMemberList(String searchCategory, String keyword, PageInfo pi) {

		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을
		// DAO 로 넘기면서 요청 후 결과 받기
		ArrayList<Member> searchList = new MemberDao().searchAllMemberList(conn, searchCategory, keyword, pi);
		
		// 3) 트랜잭션 처리 (select문이므로 패스)
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 리턴
		return searchList;
	}
	
	//id 찾기용
	public Member idPwdFindMember(Member m) {
			
		// 1. Connection 객체 생성
		Connection conn 
			= JDBCTemplate.getConnection();
		
		// 2. 만들어진 Connection 과 전달값을
		//	  DAO 로 넘기면서 요청 후 결과 받기
		Member idPwd 
			= new MemberDao().idPwdFindMember(conn, m);
	
		// 3. 트랜잭션 처리 (SELECT문일경우 패스)
		
		// 4. Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5. Controller 로 결과 반환
		return idPwd;
		
	}
	
	// 10월 18일 추가
	// 회원 정지용 메소드 ( 단일 클릭 )
	public int memberStop(String mno, String status) {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().memberStop(conn, mno, status);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	// 10월 19일 추가 메소드
		// 회원 단체 복구용 메소드
		public int memberAllRecovery(String totalCheckNo) {

			Connection conn = JDBCTemplate.getConnection();
			
			int result = new MemberDao().memberAllRecovery(conn, totalCheckNo);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			
			return result;
		}

		// 회원 단체 정지용 메소드
		public int memberAllStop(String totalCheckNo) {
			
			Connection conn = JDBCTemplate.getConnection();
					
				int result = new MemberDao().memberAllStop(conn, totalCheckNo);
				
				if(result > 0) {
					JDBCTemplate.commit(conn);
				} else {
					JDBCTemplate.rollback(conn);
				}
				JDBCTemplate.close(conn);
				
				return result;
		}

		// 11월 20일 추가
		// 회원 가입 아이디 중복 검사용 메소드
		public int duplicationCheck(String checkId) {
			
			Connection conn = JDBCTemplate.getConnection();
			
			int result = new MemberDao().duplicationCheck(conn, checkId);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			
			return result;
		}

		// 회원 가입 시 닉네임 중복 여부 확인용
		public int duplicationNickCheck(String checkNick) {
			
			Connection conn = JDBCTemplate.getConnection();
			
			int result = new MemberDao().duplicationNickCheck(conn, checkNick);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			
			return result;
		}
		
		// 회원 가입 시 이메일 중복 여부 확인용	
		public int duplicationEmailCheck(String checkEmail) {
			
			Connection conn = JDBCTemplate.getConnection();
			
			int result = new MemberDao().duplicationEmailCheck(conn, checkEmail);
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
			JDBCTemplate.close(conn);
			
			return result;
		}
	
}





