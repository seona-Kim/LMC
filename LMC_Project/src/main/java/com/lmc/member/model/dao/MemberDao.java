package com.lmc.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import com.lmc.common.JDBCTemplate;
import com.lmc.common.model.vo.PageInfo;
import com.lmc.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		
		String fileName = MemberDao.class
				.getResource("/sql/member/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(
					new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
public Member loginMember(Connection conn, Member m) {
		
		// SELECT문 => ResultSet 객체 => Member 객체
		
		// 필요한 변수들 먼저 셋팅
		Member loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문 셋팅
		String sql = prop.getProperty("loginMember");
		
		// System.out.println(sql);
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
		
			// 미완성된 쿼리문 완성시키기
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			
			// 실행 후 결과 받기
			// SELECT문 => executeQuery 메소드
			rset = pstmt.executeQuery();
			
			// rset 에 담긴 결과를 VO 객체로 가공
			if(rset.next()) {
				
				loginUser = new Member(rset.getInt("MEMBER_NO"),
									   rset.getString("MEMBER_ID"),
									   rset.getString("MEMBER_PWD"),
									   rset.getString("MEMBER_NAME"),
									   rset.getString("MEMBER_NICK"),
									   rset.getString("EMAIL"),
									   rset.getDate("ENROLL_DATE"),
									   rset.getString("MEMBER_IMG"),
									   rset.getString("STATUS"));
				
			}
			
			// 이 시점 기준으로
			// 해당 회원이 조회되지 않는다면
			// loginUser == null
			
			// System.out.println(loginUser);
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 다 쓴 JDBC 용 자원 반납 (생성 역순)
			// => DAO 에서 Connection 은 반납하면 안됨!!
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// Service 로 결과 반환
		return loginUser;
}

	public int insertMember(Connection conn, Member m) {
		
		// INSERT문 => 처리된 행의 갯수 (int)
		
		// 필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;

		// 실행할 SQL문
		String sql = prop.getProperty("insertMember");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
		
			// 미완성된 SQL문을 완성시키기
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getMemberNick());
			pstmt.setString(5, m.getEmail());
			
			// SQL문 실행 후 결과 받기
		// insert문 => pstmt.executeUpdate()
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 다 쓴 JDBC 용 자원 반납 (생성 역순)
			JDBCTemplate.close(pstmt);
		}
		
		// Service 로 결과 반환
		return result; // 1(성공) 또는 0(실패)
	}

	public int updateMember(Connection conn, Member m) {
		
		// UPDATE 문 => 처리된 행의 개수(int)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberName());
			pstmt.setString(2, m.getMemberNick());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getMemberImg());
			pstmt.setString(5, m.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Member selectMember(Connection conn, String memberId) {
		
		// SELECT문 => ResultSet 객체 => Member 객체
		
		// 필요한 변수들 먼저 세팅
		Member updateMem = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("selectMember");
		
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 미완성된 SQL문 완성시키기
			pstmt.setString(1, memberId);
			
			// SQL문 실행 후 결과 받기
			// select문 : executeQuery 메소드
			rset = pstmt.executeQuery();
			
			// rset 에 담긴 결과를 VO 로 가공
			if(rset.next()) {
				
				updateMem = new Member(rset.getInt("MEMBER_NO"),
									   rset.getString("MEMBER_ID"),
									   rset.getString("MEMBER_PWD"),
									   rset.getString("MEMBER_NAME"),
									   rset.getString("MEMBER_NICK"),
									   rset.getString("EMAIL"),
									   rset.getDate("ENROLL_DATE"),
									   rset.getString("MEMBER_IMG"),
									   rset.getString("STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 다 쓴 JDBC 용 자원 반납 (역순)
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// Service 로 결과 리턴
		return updateMem;
	}

	public int updatePwdMember(Connection conn, HashMap<String, String> hm) {
		
		// UPDATE문 => 처리된 행의 개수 (int)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updatePwdMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, hm.get("updatePwd"));
			pstmt.setString(2, hm.get("memberId"));
			pstmt.setString(3, hm.get("memberPwd"));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
		
	// 회원의 총 숫자 확인용 메소드
	public int selectListCount(Connection conn) {
		
		// 필요한 변수 셋팅
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// SQL문 셋팅
		String sql = prop.getProperty("selectListCount");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return listCount;
	}

	// 회원 목록 조회용 메소드
	public ArrayList<Member> selectList(Connection conn, PageInfo pi) {
		// 필요한 변수들 먼저 셋팅
		ArrayList<Member> list = new ArrayList<>();
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
				
				list.add(new Member(rset.getInt("MEMBER_NO"),
									rset.getString("MEMBER_ID"),
									rset.getString("MEMBER_PWD"),
									rset.getString("MEMBER_NAME"),
									rset.getString("MEMBER_NICK"),
									rset.getString("EMAIL"),
									rset.getDate("ENROLL_DATE"),
									rset.getString("MEMBER_IMG"),
									rset.getString("STATUS")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 다쓴 JDBC용 자원 반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public int deleteMember(Connection conn, Member m) {
		
		// UPDATE 문 => 처리된 행의 개수 (int)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 10월 16일 추가본
	// 관리자 페이지를 통해 회원 상세 정보 조회용 메소드
	public Member selectMember(Connection conn, Member m) {
		
		// SELECT문 => ResultSet 객체 => Member 객체
		
		// 필요한 변수들 먼저 셋팅
		Member selectedM = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문 셋팅
		String sql = prop.getProperty("adSelectMember");
		
		// System.out.println(sql);
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
		
			// 미완성된 쿼리문 완성시키기
			pstmt.setInt(1, m.getMemberNo());
		
			// 실행 후 결과 받기
			// SELECT문 => executeQuery 메소드
			rset = pstmt.executeQuery();
			
			// rset 에 담긴 결과를 VO 객체로 가공
			if(rset.next()) {
				
				selectedM = new Member(rset.getInt("MEMBER_NO"),
									   rset.getString("MEMBER_ID"),
									   rset.getString("MEMBER_PWD"),
									   rset.getString("MEMBER_NAME"),
									   rset.getString("MEMBER_NICK"),
									   rset.getString("EMAIL"),
									   rset.getDate("ENROLL_DATE"),
									   rset.getString("MEMBER_IMG"),
									   rset.getString("STATUS"));
				
			}
			
			// 이 시점 기준으로
			// 해당 회원이 조회되지 않는다면
			// loginUser == null
			
			// System.out.println(loginUser);
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 다 쓴 JDBC 용 자원 반납 (생성 역순)
			// => DAO 에서 Connection 은 반납하면 안됨!!
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// Service 로 결과 반환
		return selectedM;
	}
	
	// 10월 17일 추가
	// 전체 카테고리로 검색된 회원의 총 인원수를 구하는 용도의 메소드
	public int searchAllMemberListCount(Connection conn, String searchCategory, String keyword) {
	
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql= null;
		switch(searchCategory) {
		case "all" : 
			sql = prop.getProperty("searchAllMemberListCount");
			try {
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setString(2, "%" + keyword + "%");
				pstmt.setString(3, "%" + keyword + "%");
				pstmt.setString(4, "%" + keyword + "%");
				pstmt.setString(5, "%" + keyword + "%");
				rset = pstmt.executeQuery();
				if(rset.next()) {
					listCount = rset.getInt("COUNT");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return listCount;
		case "MEMBER_NO" :
			sql = prop.getProperty("search_MemberNo_Count");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+ keyword + "%");
				rset = pstmt.executeQuery();
				if(rset.next()) {
					listCount = rset.getInt("COUNT");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return listCount;
		case "MEMBER_ID" :
			sql = prop.getProperty("search_MemberId_Count");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+ keyword + "%");
				rset = pstmt.executeQuery();
				if(rset.next()) {
					listCount = rset.getInt("COUNT");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return listCount;
		case "MEMBER_NAME" :
			sql = prop.getProperty("search_MemberName_Count");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+ keyword + "%");
				rset = pstmt.executeQuery();
				if(rset.next()) {
					listCount = rset.getInt("COUNT");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return listCount;
		case "MEMBER_NICK" :
			sql = prop.getProperty("search_MemberNick_Count");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+ keyword + "%");
				rset = pstmt.executeQuery();
				if(rset.next()) {
					listCount = rset.getInt("COUNT");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return listCount;
		default : // EMAIL
			sql = prop.getProperty("search_Email_Count");
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+ keyword + "%");
				rset = pstmt.executeQuery();
				if(rset.next()) {
					listCount = rset.getInt("COUNT");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return listCount;
		}
	}
	// 전체 카테고리로 검색된 회원의 리스트를 불러올 용도의 메소드
	public ArrayList<Member> searchAllMemberList(Connection conn, String searchCategory, String keyword, PageInfo pi) {
		// SELECT 문 => ResultSet 객체 => board객체 하나
		// 필요한 변수들 먼저 셋팅
		ArrayList<Member> searchList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = null;
		switch(searchCategory) {
		case "all" : 
			sql = prop.getProperty("searchAllMemberList");
			try {
				pstmt = conn.prepareStatement(sql);
				int startRow = (pi.getCurrentPage() - 1) 
						* pi.getBoardLimit() + 1;
				int endRow = startRow 
						+ pi.getBoardLimit() - 1;
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setString(2, "%" + keyword + "%");
				pstmt.setString(3, "%" + keyword + "%");
				pstmt.setString(4, "%" + keyword + "%");
				pstmt.setString(5, "%" + keyword + "%");
				pstmt.setInt(6, startRow);
				pstmt.setInt(7, endRow);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					searchList.add(new Member(rset.getInt("MEMBER_NO"),
							rset.getString("MEMBER_ID"),
							rset.getString("MEMBER_NAME"),
							rset.getString("MEMBER_NICK"),
							rset.getString("EMAIL"),
							rset.getDate("ENROLL_DATE"),
							rset.getString("MEMBER_IMG"),
							rset.getString("STATUS")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return searchList;
		case "MEMBER_NO" :
			sql = prop.getProperty("search_MemberNo_MemberList");
			try {
				pstmt = conn.prepareStatement(sql);
				int startRow = (pi.getCurrentPage() - 1) 
						* pi.getBoardLimit() + 1;
				int endRow = startRow 
						+ pi.getBoardLimit() - 1;
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					searchList.add(new Member(rset.getInt("MEMBER_NO"),
							rset.getString("MEMBER_ID"),
							rset.getString("MEMBER_NAME"),
							rset.getString("MEMBER_NICK"),
							rset.getString("EMAIL"),
							rset.getDate("ENROLL_DATE"),
							rset.getString("MEMBER_IMG"),
							rset.getString("STATUS")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return searchList;
		case "MEMBER_ID" :
			sql = prop.getProperty("search_MemberId_MemberList");
			try {
				pstmt = conn.prepareStatement(sql);
				int startRow = (pi.getCurrentPage() - 1) 
						* pi.getBoardLimit() + 1;
				int endRow = startRow 
						+ pi.getBoardLimit() - 1;
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					searchList.add(new Member(rset.getInt("MEMBER_NO"),
							rset.getString("MEMBER_ID"),
							rset.getString("MEMBER_NAME"),
							rset.getString("MEMBER_NICK"),
							rset.getString("EMAIL"),
							rset.getDate("ENROLL_DATE"),
							rset.getString("MEMBER_IMG"),
							rset.getString("STATUS")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return searchList;
		case "MEMBER_NAME" :
			sql = prop.getProperty("search_MemberName_MemberList");
			try {
				pstmt = conn.prepareStatement(sql);
				int startRow = (pi.getCurrentPage() - 1) 
						* pi.getBoardLimit() + 1;
				int endRow = startRow 
						+ pi.getBoardLimit() - 1;
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					searchList.add(new Member(rset.getInt("MEMBER_NO"),
							rset.getString("MEMBER_ID"),
							rset.getString("MEMBER_NAME"),
							rset.getString("MEMBER_NICK"),
							rset.getString("EMAIL"),
							rset.getDate("ENROLL_DATE"),
							rset.getString("MEMBER_IMG"),
							rset.getString("STATUS")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return searchList;
		case "MEMBER_NICK" :
		sql = prop.getProperty("search_MemberNick_MemberList");
		try {
			pstmt = conn.prepareStatement(sql);
			int startRow = (pi.getCurrentPage() - 1) 
					* pi.getBoardLimit() + 1;
			int endRow = startRow 
					+ pi.getBoardLimit() - 1;
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				searchList.add(new Member(rset.getInt("MEMBER_NO"),
						rset.getString("MEMBER_ID"),
						rset.getString("MEMBER_NAME"),
						rset.getString("MEMBER_NICK"),
						rset.getString("EMAIL"),
						rset.getDate("ENROLL_DATE"),
						rset.getString("MEMBER_IMG"),
						rset.getString("STATUS")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return searchList;
		default : //EMAIL
			sql = prop.getProperty("search_Email_MemberList");
			try {
				pstmt = conn.prepareStatement(sql);
				int startRow = (pi.getCurrentPage() - 1) 
						* pi.getBoardLimit() + 1;
				int endRow = startRow 
						+ pi.getBoardLimit() - 1;
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rset = pstmt.executeQuery();
				while(rset.next()) {
					searchList.add(new Member(rset.getInt("MEMBER_NO"),
							rset.getString("MEMBER_ID"),
							rset.getString("MEMBER_NAME"),
							rset.getString("MEMBER_NICK"),
							rset.getString("EMAIL"),
							rset.getDate("ENROLL_DATE"),
							rset.getString("MEMBER_IMG"),
							rset.getString("STATUS")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return searchList;
		} 
	}
	
	public Member idPwdFindMember(Connection conn, Member m) {
		
		// SELECT문 => ResultSet 객체 => Member 객체
			
		// 필요한 변수들 먼저 셋팅
		Member idPwd = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문 셋팅
		String sql = prop.getProperty("idPwdFindMember");
		
		// System.out.println(sql);
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
		
			// 미완성된 쿼리문 완성시키기
			pstmt.setString(1, m.getEmail());
			
			// 실행 후 결과 받기
			// SELECT문 => executeQuery 메소드
			rset = pstmt.executeQuery();
			
			// rset 에 담긴 결과를 VO 객체로 가공
			if(rset.next()) {
				
				idPwd = new Member(rset.getInt("MEMBER_NO"),
									   rset.getString("MEMBER_ID"),
									   rset.getString("MEMBER_PWD"),
									   rset.getString("MEMBER_NAME"),
									   rset.getString("MEMBER_NICK"),
									   rset.getString("EMAIL"),
									   rset.getDate("ENROLL_DATE"),
									   rset.getString("MEMBER_IMG"),
									   rset.getString("STATUS"));
				
			}
			
			// 이 시점 기준으로
			// 해당 회원이 조회되지 않는다면
			// idPwd == null
			
			// System.out.println(idPwd);
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 다 쓴 JDBC 용 자원 반납 (생성 역순)
			// => DAO 에서 Connection 은 반납하면 안됨!!
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// Service 로 결과 반환
		return idPwd;
		
	}
	
	// 10월 18일 추가
	// 회원 정지용 메소드 (단일 클릭)
	public int memberStop(Connection conn, String mno, String status) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql = null;
		
		String changeStatus = null;
		
		if(status.equals("Y")) {
			
			changeStatus = "N";
		} else {
			
			changeStatus = "Y";
		}
		
		sql = prop.getProperty("memberStop");
		
		try {
			pstmt= conn.prepareStatement(sql);
			
			pstmt.setString(1, changeStatus);
			pstmt.setString(2, mno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 10월 19일 추가 메소드
		// 회원 단체 복구용 메소드
		public int memberAllRecovery(Connection conn, String totalCheckNo) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = null;
						
			sql = "UPDATE MEMBER " +
					 "SET STATUS = 'Y' "+
				   "WHERE MEMBER_NO IN (" +totalCheckNo+ ")";
			
			try {
				pstmt= conn.prepareStatement(sql);
					
				//pstmt.setString(1, totalCheckNo);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}
		
		// 회원 단체 정지용 메소드
		public int memberAllStop(Connection conn, String totalCheckNo) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = null;
			
			
			sql = "UPDATE MEMBER " +
					 "SET STATUS = 'N' "+
				   "WHERE MEMBER_NO IN (" +totalCheckNo+ ")";
			
			// sql = prop.getProperty("memberAllStop");
			
			try {
				pstmt= conn.prepareStatement(sql);
					
				// Member_No는 숫자라서 안됨
				//pstmt.setString(1, totalCheckNo);
				
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}

		// 11월 20일 추가 사항
		// 회원 아이디 중복 검사용 메소드
		public int duplicationCheck(Connection conn, String checkId) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = null;
			
			sql = prop.getProperty("duplicationCheck");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, checkId);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					
					result = rset.getInt("MEMBER_NO");
					//System.out.println("결과 dao로 잘 받음?" + result);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}

		// 회원가입 닉네임 중복 여부 확인용 메소드
		public int duplicationNickCheck(Connection conn, String checkNick) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = null;
			
			sql = prop.getProperty("duplicationCheckNick");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, checkNick);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					
					result = rset.getInt("COUNT");
					//System.out.println("결과 dao로 잘 받음?" + result);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}
		
		// 회원 가입 시 이메일 중복 체크용 메소드
		public int duplicationEmailCheck(Connection conn, String checkEmail) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = null;
			
			sql = prop.getProperty("duplicationCheckEmail");
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, checkEmail);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					
					result = rset.getInt("COUNT");
					//System.out.println("결과 dao로 잘 받음?" + result);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}
	
}




