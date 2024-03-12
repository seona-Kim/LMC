package com.lmc.board.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.lmc.board.model.vo.Attachment;
import com.lmc.board.model.vo.Board;
import com.lmc.board.model.vo.Category;
import com.lmc.board.model.vo.Reply;
import com.lmc.common.JDBCTemplate;
import com.lmc.common.model.vo.PageInfo;
import com.lmc.member.model.vo.Member;

public class BoardDao {

	private Properties prop = new Properties();
	
	public BoardDao() {
		
		String fileName = BoardDao.class.getResource("/sql/board/board-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 일반 게시글의 총 숫자 확인용 메소드
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
	
	// 검색 확인할 페이징 용도의 메소드
	public int searchListCount(Connection conn, Board b) {
			
			// SELECT 문 => ResultSet 객체 => int
			
			// 필요한 변수들 먼저 셋팅
			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			// 실행할 SQL문
			String sql = prop.getProperty("searchListCount");
			
			try {
				
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
			
				pstmt.setString(1, "%" + b.getBoardTitle() + "%");
				
				// SQL문 실행 후 결과 받기
				// select문 : executeQuery 메소드
				rset = pstmt.executeQuery();
				
				// rset 으로부터 조회된 결과를 뽑기
				// COUNT 라는 그룹함수를 이용했기 때문에
				// 단일행 조회임 (n개 행 -> 1개 행)
				if(rset.next()) {
					
					listCount = rset.getInt("COUNT");
					// 별칭을 제시해서 뽑음
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				// JDBC 용 자원 반납 (역순)
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			// Service 단으로 결과 리턴
			return listCount;
		}
		
		public ArrayList<Board> searchListView(Connection conn, Board b , PageInfo pi) {
		
		// SELECT 문 => ResultSet 객체 => board객체 하나
		
				// 필요한 변수들 먼저 셋팅
				ArrayList<Board> searchList = new ArrayList<>();
				PreparedStatement pstmt = null;
				ResultSet rset = null;
				
				//실행할 SQL문
				String sql = prop.getProperty("searchListView");
				
				// PreparedStatement 객체 생성
				try {
					pstmt = conn.prepareStatement(sql);
					
					int startRow = (pi.getCurrentPage() - 1) 
							  * pi.getBoardLimit() + 1;
					int endRow = startRow 
								 + pi.getBoardLimit() - 1;
					
					
					pstmt.setString(1, "%" + b.getBoardTitle() + "%");
					pstmt.setInt(2, startRow);
					pstmt.setInt(3, endRow);
					
					
					
					rset = pstmt.executeQuery();
					
					// rset 으로부터 조회된 데이터들을 뽑아서
					// VO 에 차곡차곡 담기
					while(rset.next()) {
						
						searchList.add(new Board(rset.getInt("BOARD_NO"),
											  rset.getString("BOARD_TITLE"),
											  rset.getString("MEMBER_ID"),
											  rset.getInt("VIEWS"),
											  rset.getDate("CREATE_DATE")));
											 
					}
					
				} catch (SQLException e) {
				
					e.printStackTrace();
				}finally {
					
					JDBCTemplate.close(rset);
					JDBCTemplate.close(pstmt);
				}
				return searchList;
				
		}

		public int increaseCount(Connection conn, int boardNo) {
			
			// UPDATE 문 => 처리된 행의 갯수 (int)
			
			// 필요한 변수들 먼저 셋팅
			int result = 0;
			PreparedStatement pstmt = null;
			
			// 실행할 SQL문
			String sql = prop.getProperty("increaseCount");
			
			try {
				
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
			
				// 미완성된 쿼리문 완성시키기
				pstmt.setInt(1, boardNo);
				
				// 쿼리문 실행 후 결과 받기
				// update문 : executeUpdate메소드
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

		public Board selectBoard(Connection conn, int boardNo) {
			
			// SELECT 문 => ResultSet 객체
			// => 단일행 조회 (Board 객체)
			
			// 필요한 변수 먼저 셋팅
			Board b = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			// 실행할 SQL문
			String sql = prop.getProperty("selectBoard");
			
			try {
				
				// PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
			
				// 미완성된 쿼리문 완성시키기
				pstmt.setInt(1, boardNo);
				
				// 쿼리문 실행 후 결과 받기
				// select문 : executeQuery 메소드
				rset = pstmt.executeQuery();
				
				// rset 에 담긴 데이터를 VO로 가공
				if(rset.next()) {
					
					b = new Board(rset.getInt("BOARD_NO"),
								  rset.getString("CATEGORY_NAME"),
								  rset.getString("BOARD_TITLE"),
								  rset.getString("BOARD_CONTENT"),
								  rset.getString("MEMBER_ID"),
								  rset.getInt("VIEWS"),
								  rset.getDate("CREATE_DATE"));
								 
					
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				// JDBC 용 자원 반납 (역순)
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			// Service 단으로 결과 리턴
			return b;
		}

		public Attachment selectAttachment(Connection conn, int boardNo) {
		
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
			pstmt.setInt(1, boardNo);
			
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
		
		public Member selectProfile(Connection conn, int boardNo) {
			
			// SELECT 문 => ResultSet 객체
			// => 단일행 조회 (Attachment 객체)
			
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
				pstmt.setInt(1, boardNo);
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
	
	// 일반 게시글 목록 조회용 메소드
		public ArrayList<Board> selectList(Connection conn, PageInfo pi) {

			// 필요한 변수들 먼저 셋팅
			ArrayList<Board> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			// 실행할 SQL문
			String sql = prop.getProperty("selectList_Admin");
			
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
					
					list.add(new Board(rset.getInt("BOARD_NO"),
										rset.getString("CATEGORY_NO"),
										rset.getString("BOARD_TITLE"),
										rset.getString("BOARD_CONTENT"),
										rset.getString("MEMBER_NICK"),
										rset.getInt("VIEWS"),
										rset.getDate("CREATE_DATE"),
										rset.getDate("UPDATE_DATE"),
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

	public int selectMyListCount(Connection conn, int memberNo) {
		
		// SELECT 문 => ResultSet 객체 => int
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMyListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
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

	public ArrayList<Board> selectMyList(Connection conn, PageInfo pi, int memberNo) {
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("selectMyList");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// 미완성된 SQL문 완성시키기
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Board(rset.getInt("BOARD_NO"),
									rset.getString("CATEGORY_NO"),
									rset.getString("BOARD_TITLE"),
									rset.getString("BOARD_CONTENT"),
									rset.getString("MEMBER_NICK"),
									rset.getInt("VIEWS"),
									rset.getDate("CREATE_DATE"),
									rset.getDate("UPDATE_DATE")));
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

	public int deleteMyBoard(Connection conn, int memberNo, String deleteBNo) {
		
		// UPDATE 문 => 처리된 행의 개수 (int)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE BOARD"
				+ "		 SET STATUS = 'N'"
				+ "	   WHERE BOARD_NO IN ("+ deleteBNo +")"
				+ "		 AND BOARD_WRITER = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int selectMyReplyCount(Connection conn, int memberNo) {
		
		// SELECT 문 => ResultSet 객체 => int
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMyReplyCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
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

	public ArrayList<Reply> selectMyReplyList(Connection conn, PageInfo pi, int memberNo) {
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<Reply> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("selectMyReplyList");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);

			// 미완성된 SQL문 완성시키기
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Reply(rset.getInt("REPLY_NO"),
									rset.getString("REPLY_CONTENT"),
									rset.getInt("REF_BNO"),
									rset.getString("REF_BTITLE"),
									rset.getString("REPLY_WRITER"),
									rset.getDate("CREATE_DATE"),
									rset.getDate("UPDATE_DATE")));
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
	
	// 금일 전체 일반 게시판(공지 제외) 작성 글 수 확인용 메소드
	public int todayBoardCount(Connection conn) {

		// 필요한 변수들 먼저 셋팅
		int todayBoardCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("todayBoardWriteCount");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 미완성 SQL문 완성시키기 (이미 완성된 SELECT 문)
			// 쿼리문 실행하고 결과 받기
			rset = pstmt.executeQuery();
			
			// rset으로 부터 결과 뽑기 > COUNT 그룹함수 이용했기 때문에 단일행 조회
			if(rset.next()) {
				
				todayBoardCount = rset.getInt("COUNT"); // SQL문 별칭으로 뽑음
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return todayBoardCount;
		
	}
	
	// 금일 자유 게시판 작성 글 수 확인용 메소드
	// 10월 16일 sql문 수정
	public int todayFreeBoardCount(Connection conn) {
		// 필요한 변수들 먼저 셋팅
		int freeBoardCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("todayfreeBoardCount");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 미완성 SQL문 완성시키기 (이미 완성된 SELECT 문)
			// 쿼리문 실행하고 결과 받기
			rset = pstmt.executeQuery();
			
			// rset으로 부터 결과 뽑기 > COUNT 그룹함수 이용했기 때문에 단일행 조회
			if(rset.next()) {
				
				freeBoardCount = rset.getInt("COUNT"); // SQL문 별칭으로 뽑음
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return freeBoardCount;
	}
	
	// 금일 나눔 게시판 작성 글 수 확인용 메소드
	public int todayShareBoardCount(Connection conn) {

		// 필요한 변수들 먼저 셋팅
		int todayShareBoardCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("todayShareBoardCount");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 미완성 SQL문 완성시키기 (이미 완성된 SELECT 문)
			// 쿼리문 실행하고 결과 받기
			rset = pstmt.executeQuery();
			
			// rset으로 부터 결과 뽑기 > COUNT 그룹함수 이용했기 때문에 단일행 조회
			if(rset.next()) {
				
				todayShareBoardCount = rset.getInt("COUNT"); // SQL문 별칭으로 뽑음
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return todayShareBoardCount;
				
	}

	// 금일 작성 영화 정보 게시글 수 확인 용 메소드 
	public int todayMovieInfoBoardCount(Connection conn) {

		// 필요한 변수들 먼저 셋팅
		int movieInfoBoardCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("movieInfoBoardCount");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 미완성 SQL문 완성시키기 (이미 완성된 SELECT 문)
			// 쿼리문 실행하고 결과 받기
			rset = pstmt.executeQuery();
			
			// rset으로 부터 결과 뽑기 > COUNT 그룹함수 이용했기 때문에 단일행 조회
			if(rset.next()) {
				
				movieInfoBoardCount = rset.getInt("COUNT"); // SQL문 별칭으로 뽑음
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return movieInfoBoardCount;
		
	}
	
	// 금일 공지 사항 작성글 수 확인용 메소드
	public int todayNoticeBoardCount(Connection conn) {

		// 필요한 변수들 먼저 셋팅
		int todayNoticeBoardCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("todayNoticeBoardCount");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 미완성 SQL문 완성시키기 (이미 완성된 SELECT 문)
			// 쿼리문 실행하고 결과 받기
			rset = pstmt.executeQuery();
			
			// rset으로 부터 결과 뽑기 > COUNT 그룹함수 이용했기 때문에 단일행 조회
			if(rset.next()) {
				
				todayNoticeBoardCount = rset.getInt("COUNT"); // SQL문 별칭으로 뽑음
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return todayNoticeBoardCount;
		
	}

	// 총 자유 게시판의 글 수 구하기
	public int freeBoard(Connection conn) {

		// 필요한 변수들 먼저 셋팅
		int freeBoard = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("freeBoard");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 미완성 SQL문 완성시키기 (이미 완성된 SELECT 문)
			// 쿼리문 실행하고 결과 받기
			rset = pstmt.executeQuery();
			
			// rset으로 부터 결과 뽑기 > COUNT 그룹함수 이용했기 때문에 단일행 조회
			if(rset.next()) {
				
				freeBoard = rset.getInt("COUNT"); // SQL문 별칭으로 뽑음
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return freeBoard;
	}

	// 총 나눔 게시판 글 수 구하기 메소드
	public int shareBoard(Connection conn) {
		
		// 필요한 변수들 먼저 셋팅
		int shareBoard = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("shareBoard");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 미완성 SQL문 완성시키기 (이미 완성된 SELECT 문)
			// 쿼리문 실행하고 결과 받기
			rset = pstmt.executeQuery();
			
			// rset으로 부터 결과 뽑기 > COUNT 그룹함수 이용했기 때문에 단일행 조회
			if(rset.next()) {
				
				shareBoard = rset.getInt("COUNT"); // SQL문 별칭으로 뽑음
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return shareBoard;
	}

	// 총 영화 정보 게시글 수 구하기 
	public int movieInfoBoard(Connection conn) {

		// 필요한 변수들 먼저 셋팅
		int movieInfoBoard = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("movieInfoBoard");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 미완성 SQL문 완성시키기 (이미 완성된 SELECT 문)
			// 쿼리문 실행하고 결과 받기
			rset = pstmt.executeQuery();
			
			// rset으로 부터 결과 뽑기 > COUNT 그룹함수 이용했기 때문에 단일행 조회
			if(rset.next()) {
				
				movieInfoBoard = rset.getInt("COUNT"); // SQL문 별칭으로 뽑음
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return movieInfoBoard;
	}

	// 전체 공지글 수 확인용 메소드
	public int noticeBoard(Connection conn) {

		// 필요한 변수들 먼저 셋팅
		int noticeBoard = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("noticeBoard");
		
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 미완성 SQL문 완성시키기 (이미 완성된 SELECT 문)
			// 쿼리문 실행하고 결과 받기
			rset = pstmt.executeQuery();
			
			// rset으로 부터 결과 뽑기 > COUNT 그룹함수 이용했기 때문에 단일행 조회
			if(rset.next()) {
				
				noticeBoard = rset.getInt("COUNT"); // SQL문 별칭으로 뽑음
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return noticeBoard;
	}
	
	public ArrayList<Board> bestBoardList(Connection conn, int listCount, int category) {
		
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("bestBoardList");
		
		try {
			
//			PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, category);
			pstmt.setInt(2, listCount);
			
//			쿼리문 실행 후 결과 받기
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Board(rset.getInt("BOARD_NO"),
								   rset.getString("BOARD_TITLE")));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public int deleteMyReply(Connection conn, int memberNo, String deleteRNo) {
	
		// UPDATE 문 => 처리된 행의 개수 (int)
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE REPLY"
				+ "		 SET STATUS = 'N'"
				+ "	   WHERE REPLY_NO IN ("+ deleteRNo +")"
				+ "		 AND REPLY_WRITER = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int selectMyCommendCount(Connection conn, int memberNo) {
		
		// SELECT 문 => ResultSet 객체 => int
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMyCommendCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
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
		// System.out.println(listCount);
		
		return listCount;
	}

	public ArrayList<Board> selectMyCommend(Connection conn, PageInfo pi, int memberNo) {
		
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMyCommend");
		
		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Board(rset.getInt("COMMEND_NO"),
									rset.getInt("COMMEND_BNO"),
									rset.getString("CATEGORY_NO"),
									rset.getString("BOARD_TITLE"),
									rset.getString("MEMBER_NICK"),
									rset.getInt("VIEWS"),
									rset.getDate("CREATE_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int deleteMyCommend(Connection conn, int memberNo, String deleteCNo) {
		
		// DELETE 문 => 처리된 행의 개수 => int
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE"
				+ "     FROM COMMEND"
				+ "	   WHERE COMMEND_NO IN ("+ deleteCNo +")"
				+ "		 AND COMMEND_MNO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Category> selectCategoryList(Connection conn) {
		
		// SELECT 문 => ResultSet 객체
		// => ArrayList<Category> 객체 (여러행)
		
		// 필요한 변수들 먼저 셋팅
		ArrayList<Category> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("selectCategoryList");
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
		
			// 미완성된 SQL문 완성시키기
			// => 완성된 쿼리문이므로 패스
			
			// SQL문 실행 후 결과 받기
			// select문 : executeQuery 메소드
			rset = pstmt.executeQuery();
			
			// rset 으로부터 조회된 데이터들을 뽑아서
			// VO 에 차곡차곡 담기
			while(rset.next()) {
				
				list.add(new Category(rset.getInt("CATEGORY_NO"),
									  rset.getString("CATEGORY_NAME")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// JDBC 용 자원 반납 (역순)
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// Service 단으로 결과 리턴
		return list;
	}
	public int deleteBoard(Connection conn, int boardNo) {
		
		// UPDATE 문 => 처리된 행의 갯수 (int)
		
		// 필요한 변수 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("deleteBoard");
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 미완성된 쿼리문 완성시키기
			pstmt.setInt(1, boardNo);
		
			// 쿼리문 실행 후 결과 받기
			// update문 : executeUpdate 메소드
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
	
	public int updateBoard(Connection conn, Board b) {
		
		// UPDATE 문 => 처리된 행의 갯수 (int)
		
		// 필요한 변수 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 쿼리문
		String sql = prop.getProperty("updateBoard");
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
		
			// 미완성된 쿼리문 완성시키기
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4, b.getBoardNo());
		
			// 쿼리문 실행 후 결과 받기
			// update문 : executeUpdate 메소드
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
	
	public int updateAttachment(Connection conn, Attachment at) {
		
		// UPDATE 문 => 처리된 행의 갯수 (int)
		
		// 필요한 변수 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("updateAttachment");

		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
		
			// 미완성된 쿼리문 완성시키기
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			
			// 쿼리문 실행 후 결과 받기
			// update문 : executeUpdate 메소드
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

	public int insertNewAttachment(Connection conn, Attachment at) {
		
		// INSERT 문 => 처리된 행의 갯수 (int)
		
		// 필요한 변수 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("insertNewAttachment");
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
		
			// 미완성된 쿼리문 완성
			pstmt.setInt(1, at.getRefNo());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			// 쿼리문 실행 후 결과 받기
			// insert문 : executeUpdate 메소드
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// JDBC 용 자원 반납
			JDBCTemplate.close(pstmt);
		}
		
		// Service 단으로 결과 반환
		return result;
	}

	public int selectCategoryListCount(Connection conn, int category) {
		
		// 필요한 변수들 먼저 셋팅
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("selectCategoryListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, category);
			
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

	public ArrayList<Board> selectCategoryBoardList(Connection conn, PageInfo pi, int category) {
		
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectCategoryBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);

			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, category);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Board(rset.getInt("BOARD_NO"),
								   rset.getString("CATEGORY_NO"),
								   rset.getString("BOARD_TITLE"),
								   rset.getString("BOARD_CONTENT"),
								   rset.getString("MEMBER_NICK"),
								   rset.getInt("VIEWS"),
								   rset.getDate("CREATE_DATE"),
								   rset.getDate("UPDATE_DATE"),
								   rset.getInt("COMMEND_COUNT")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public String searchCategoryName(Connection conn, int category) {
		
		// 필요한 변수들 먼저 셋팅
		String categoryName = "";
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문
		String sql = prop.getProperty("searchCategoryName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, category);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				categoryName = rset.getString("CATEGORY_NAME");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return categoryName;
	}

	public int insertBoard(Connection conn, Board b) {
		
		// INSERT 문 => 처리된 행의 개수 (int)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(b.getCategory()));
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setInt(4, Integer.parseInt(b.getBoardWriter()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertAttachment(Connection conn, Attachment at) {
		
		// INSERT 문 => 처리된 행의 갯수 (int)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertAttachment");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int selectcommnedCount(Connection conn, int boardNo, int memberNo) {
		
	
		
		// 필요한 변수 먼저 셋팅
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// 실행할 SQL문
		String sql = prop.getProperty("selectcommnedCount");
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, memberNo);
			
			// select문 : executeQuery 메소드
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				result = rset.getInt("COUNT");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// JDBC 용 자원 반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// Service 단으로 결과 리턴
		return result;
	}
	
	public int intsertCommend(Connection conn, int boardNo, int memberNo) {
		
		
		
		// 필요한 변수 먼저 셋팅
		int result = 0;
		
		PreparedStatement pstmt = null;
	
		// 실행할 SQL문
		String sql = prop.getProperty("intsertCommend");
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, memberNo);
			
			
			 
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
	
	public int deleteCommend(Connection conn, int boardNo, int memberNo) {
		
		
		
		// 필요한 변수 먼저 셋팅
		int result = 0;
		
		PreparedStatement pstmt = null;
	
		// 실행할 SQL문
		String sql = prop.getProperty("deleteCommend");
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, memberNo);
			
			
			 
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
	
	public int CountCommend(Connection conn, int boardNo, int memberNo) {
		
		
		
		// 필요한 변수 먼저 셋팅
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// 실행할 SQL문
		String sql = prop.getProperty("CountCommend");
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			// select문 : executeQuery 메소드
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				result = rset.getInt("COUNT");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// JDBC 용 자원 반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// Service 단으로 결과 리턴
		return result;
	}
	
	public int selectBoardCommend(Connection conn, int boardNo) {
		
		
		
		// 필요한 변수 먼저 셋팅
		int result = 0;
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// 실행할 SQL문
		String sql = prop.getProperty("selectBoardCommend");
		
		try {
			
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			// select문 : executeQuery 메소드
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				result = rset.getInt("COUNT");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// JDBC 용 자원 반납
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// Service 단으로 결과 리턴
		return result;
	}
	
	public int insertReply(Connection conn, Reply r) {
		
		// INSERT문 => 처리된 행의 갯수 (int)
		
		//필요한 변수들 먼저 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		//실행할 sql문
		String sql = prop.getProperty("insertReply");
		
		//PreparedStatement 객체생성
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getReplyContent());
			pstmt.setInt(2, r.getRefBno());
			pstmt.setInt(3, Integer.parseInt(r.getReplyWriter()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Reply> selectReplyList(Connection conn, int boardNo){
		
		//SELECT문 => ResultSet객체 => 여러행조회 (ArrayList)
		
	
		//필요한 변수 셋팅
		ArrayList<Reply> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Reply r = null;
		//실행할 SQL문
		String sql = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,  boardNo);
			
			rset = pstmt.executeQuery();
			
			//rset 으로부터 데이터를 뽑아서 VO 로 가공
			while(rset.next()) {
				r = new Reply();
				
				r.setReplyNo(rset.getInt("REPLY_NO"));
				r.setReplyContent(rset.getString("REPLY_CONTENT"));
				r.setReplyWriter(rset.getString("MEMBER_NICK"));
				r.setCreateDate(rset.getDate("CREATE_DATE"));
				r.setReplyImg(rset.getString("MEMBER_IMG"));
				list.add(r);
			}
			
			// 이 시점 기준으로 
			//조회된 댓글이 단 하나라도 있다면
			// list.isEmpty() == false
		
			
		} catch (SQLException e) {
	
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	// 10월 20일 + 게시판 일괄 정지용 메소드
	public int boardAllStop(Connection conn, String totalCheckNo) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = null;
		
		
		sql = "UPDATE BOARD " +
				 "SET STATUS = 'N' "+
			   "WHERE BOARD_NO IN (" +totalCheckNo+ ")";
		
		try {
			pstmt= conn.prepareStatement(sql);
				
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	// 정지 게시판 검색 후 페이지 수 확인 용 메소드
	public int searchAllBoardListCount(Connection conn,String keyword) {
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql= null;
		Board b = null;
		
		sql = prop.getProperty("searchAllBoardListCount");
			
		try {
			// PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setString(3, "%" + keyword + "%");
			pstmt.setString(4, "%" + keyword + "%");

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

	// 회원 검색 결과 리스트 확인용 메소드
	public ArrayList<Board> searchAllBoardList(Connection conn, String keyword, PageInfo pi) {
		
		ArrayList<Board> searchList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = null;

		sql = prop.getProperty("searchAllBoardList");
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
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Board b = new Board();
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setBoardWriter(rset.getString("BOARD_WRITER"));
				b.setViews(rset.getInt("VIEWS"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				b.setUpdateDate(rset.getDate("UPDATE_DATE"));
				b.setStatus(rset.getString("STATUS"));
						
				searchList.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return searchList;
	}

	public int selectBoardReplyCount(Connection conn, int boardNo) {
		
		// SELECT 문 => ResultSet 객체 => int
		
		int replyCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBoardReplyCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				replyCount = rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return replyCount;
	}
	
	
	public int deleteReply(Connection conn, Reply r) {
			
			// UPDATE문 => 처리된 행의 갯수 (int)
			
			//필요한 변수들 먼저 셋팅
			int result = 0;
			PreparedStatement pstmt = null;
			
			//실행할 sql문
			String sql = prop.getProperty("deleteReply");
			
			//PreparedStatement 객체생성
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, r.getReplyNo());
	
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
			
				e.printStackTrace();
			} finally {
				
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}
}
