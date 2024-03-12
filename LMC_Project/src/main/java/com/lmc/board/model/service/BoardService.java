package com.lmc.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.lmc.board.model.dao.BoardDao;
import com.lmc.board.model.vo.Attachment;
import com.lmc.board.model.vo.Board;
import com.lmc.board.model.vo.Category;
import com.lmc.board.model.vo.Reply;
import com.lmc.common.JDBCTemplate;
import com.lmc.common.model.vo.PageInfo;
import com.lmc.member.model.dao.MemberDao;
import com.lmc.member.model.vo.Member;

public class BoardService {

	// 일반 게시글의 총 갯수를 확인할 용도의 메소드
	public int selectListCount() {
		
		// 1) Connection 객세 채성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 객체와 전달값 넘기면서 DAO로 요청 + 결과 받기
		int listCount = new BoardDao().selectListCount(conn);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}
	// 검색 확인할 페이징 용도의 메소드
	public int searchListCount(Board b) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을
		// DAO 로 넘기면서 요청 후 결과 받기
		int listCount = new BoardDao().searchListCount(conn, b);
		
		// 3) 트랜잭션 처리 (select문이므로 패스)
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 리턴
		return listCount;
	}

		
		
	// 일반 게시글 목록을 받을 용도의 메소드
	public ArrayList<Board> searchListView(Board b ,PageInfo pi) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을
		// DAO 로 넘기면서 요청 후 결과 받기
		ArrayList<Board> searchList = new BoardDao().searchListView(conn, b, pi);
		
		// 3) 트랜잭션 처리 (select문이므로 패스)
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 리턴
		return searchList;
		
	}

	public int increaseCount(int boardNo) {
			
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		int result = new BoardDao().increaseCount(conn, boardNo);
		
		// 3) 트랜잭션 처리
		if(result > 0) { // 성공 (커밋)
			
			JDBCTemplate.commit(conn);
			
		} else { // 실패 (롤백)
			
			JDBCTemplate.rollback(conn);
		}
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 리턴
		return result;
	}

	public Board selectBoard(int boardNo) {
			
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을
		// DAO 로 넘기면서 요청 후 결과 받기
		Board b = new BoardDao().selectBoard(conn, boardNo);
		
		// 3) 트랜잭션 처리 (select문이므로 패스)
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 리턴
		return b;

	}

	public Attachment selectAttachment(int boardNo) {
			
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		Attachment at = new BoardDao().selectAttachment(conn, boardNo);
		
		// 3) 트랜잭션 처리
		// select문이므로 패스
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return at;
	}

	public Member selectProfile(int boardNo) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		Member profile = new BoardDao().selectProfile(conn, boardNo);
		
		// 3) 트랜잭션 처리
		// select문이므로 패스
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return profile;
	}
	
	
	// 일반 게시글 목록을 받을 용도의 메소드
	public ArrayList<Board> selectList(PageInfo pi) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 객체와 전달값 넘기면서 DAO로 요청 + 결과 받기
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		// 3) 트랜젝션 처리 => select문 이므로 패스
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 반납
		return list;

	}


	public int selectMyListCount(int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new BoardDao().selectMyListCount(conn, memberNo);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}


	public ArrayList<Board> selectMyList(PageInfo pi, int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> list = new BoardDao().selectMyList(conn, pi, memberNo);
		
		JDBCTemplate.close(conn);
		
		return list;
	}


	public int deleteMyBoard(int memberNo, String deleteBNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().deleteMyBoard(conn, memberNo, deleteBNo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}


	public int selectMyReplyCount(int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new BoardDao().selectMyReplyCount(conn, memberNo);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}


	public ArrayList<Reply> selectMyReplyList(PageInfo pi, int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Reply> list = new BoardDao().selectMyReplyList(conn, pi, memberNo);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
//  금일 작성 글 수 확인용 메소드
	public int todayBoardCount() {
		
		Connection conn = JDBCTemplate.getConnection();

		int todayBoardCount = new BoardDao().todayBoardCount(conn);
		
		JDBCTemplate.close(conn);
				
		return todayBoardCount;
	}
	
	// 금일 자유 게시판 작성 글 수 확인 확인용 메소드
	public int todayFreeBoardCount() {
		
		Connection conn = JDBCTemplate.getConnection();

		int freeBoardCount = new BoardDao().todayFreeBoardCount(conn);
		
		JDBCTemplate.close(conn);
				
		return freeBoardCount;
	}
	

	// 금일 나눔 게시판 작성 글 수 확인용 메소드
	public int todayShareBoardCount() {

		Connection conn = JDBCTemplate.getConnection();

		int todayShareBoardCount = new BoardDao().todayShareBoardCount(conn);
		
		JDBCTemplate.close(conn);
				
		return todayShareBoardCount;
		
	}

	// 금일 영화 정보 게시판 작성 글 수 확인용 메소드
	public int todayMovieInfoBoardCount() {
		
		Connection conn = JDBCTemplate.getConnection();

		int movieInfoBoardCount = new BoardDao().todayMovieInfoBoardCount(conn);
		
		JDBCTemplate.close(conn);
				
		return movieInfoBoardCount;

	}

	// 금일 공지 사항 작성 글 수 확인용 메소드
	public int todayNoticeBoardCount() {

		Connection conn = JDBCTemplate.getConnection();

		int todayNoticeBoardCount = new BoardDao().todayNoticeBoardCount(conn);
		
		JDBCTemplate.close(conn);
				
		return todayNoticeBoardCount;
		
	}

	// 총 자유 게시판 글 수 구하기 
	public int freeBoard() {

		Connection conn = JDBCTemplate.getConnection();

		int freeBoard = new BoardDao().freeBoard(conn);
		
		JDBCTemplate.close(conn);
				
		return freeBoard;

	}


	// 총 나눔 게시판 글 수 구하기
	public int shareBoard() {
		
		Connection conn = JDBCTemplate.getConnection();

		int shareBoard = new BoardDao().shareBoard(conn);
		
		JDBCTemplate.close(conn);
				
		return shareBoard;
	}

	// 총 영화 정보 게시판 글 수 구하기
	public int movieInfoBoard() {

		Connection conn = JDBCTemplate.getConnection();

		int movieInfoBoard = new BoardDao().movieInfoBoard(conn);
		
		JDBCTemplate.close(conn);
		
		return movieInfoBoard;
	}

	// 전체 공지글 수 확인용 메소드
	public int noticeBoard() {
		
		Connection conn = JDBCTemplate.getConnection();

		int noticeBoard = new BoardDao().noticeBoard(conn);
		
		JDBCTemplate.close(conn);
		
		return noticeBoard;

	}
	
//	메인 페이지 베스트 게시글
	public ArrayList<Board> bestBoardList(int listCount, int category) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> list = new BoardDao().bestBoardList(conn, listCount, category);
		
		JDBCTemplate.close(conn);
		
		return list;
	}


	public int deleteMyReply(int memberNo, String deleteRNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().deleteMyReply(conn, memberNo, deleteRNo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}


	public int selectMyCommendCount(int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new BoardDao().selectMyCommendCount(conn, memberNo);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}


	public ArrayList<Board> selectMyCommend(PageInfo pi, int memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> list = new BoardDao().selectMyCommend(conn, pi, memberNo);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public int deleteMyCommend(int memberNo, String deleteCNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().deleteMyCommend(conn, memberNo, deleteCNo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Category> selectCategoryList() {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		ArrayList<Category> list 
			= new BoardDao().selectCategoryList(conn);
		
		// 3) 트랜잭션 처리
		// => select문 이므로 패스
		
		// 4) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 리턴
		return list;
	}
	
	public int deleteBoard(int boardNo) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		int result = new BoardDao().deleteBoard(conn, boardNo);
		
		// 3) 트랜잭션 처리
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
			
		} else {
			
			JDBCTemplate.rollback(conn);
		}
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return result;
	}
	
	public int updateBoard(Board b, Attachment at) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기 - 1 
		// (경우에 따른 공통 쿼리문인 BOARD UPDATE 먼저 요청)
		int result1 = new BoardDao().updateBoard(conn, b);
		
		// 3) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기 - 2
		// (case 1, case 2 인 경우 생각해보기)
		int result2 = 1;
		// case 0 일 경우 result1 만 성공해도 성공처리
		// (로직의 편의성을 위해 1로 셋팅)
		
		if(at != null) { // 새롭게 첨부된 파일이 있을 경우
			
			if(at.getFileNo() != 0) {
				// case 1 일 경우
				// (기존 첨부파일이 있을 경우)
				// => Attachment 테이블 update
				result2 
					= new BoardDao().updateAttachment(conn, at);
				
			} else {
				// case 2 일 경우
				// (기존 첨부파일이 없을 경우)
				// => Attachment 테이블 insert
				result2 
					= new BoardDao().insertNewAttachment(conn, at);
			}
		}
		
		// 이 시점 기준으로 성공, 실패 여부 검사
		// 4) 트랜잭션 처리
		if(result1 > 0 && result2 > 0) {
			// 모두 성공 => 커밋
			JDBCTemplate.commit(conn);
			
		} else {
			// 하나라도 실패 => 롤백
			JDBCTemplate.rollback(conn);
		}
		
		// 5) Connection 반납
		JDBCTemplate.close(conn);
		
		// 6) Controller 단으로 결과 반환
		return result1 * result2;
	}
	public int selectCategoryListCount(int category) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new BoardDao().selectCategoryListCount(conn, category);
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}
	public ArrayList<Board> selectCategoryBoardList(PageInfo pi, int category) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> list = new BoardDao().selectCategoryBoardList(conn, pi, category);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	public String searchCategoryName(int category) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		String categoryName = new BoardDao().searchCategoryName(conn, category);
		
		JDBCTemplate.close(conn);
		
		return categoryName;
	}
	
	public int insertBoard(Board b, Attachment at) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result1 = new BoardDao().insertBoard(conn, b);
		
		int result2 = 1;
		
		
		if(at != null) {
			
			result2 = new BoardDao().insertAttachment(conn, at);
		}
		
		if(result1 > 0 && result2 > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result1 * result2;
	}
	
	public int selectcommnedCount(int boardNo, int memberNo) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
				
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		int result = new BoardDao().selectcommnedCount(conn,boardNo,memberNo);
		
		// 3) 트랜잭션 처리(select이므로 패스)
	
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return result;
		
	}
	
	public int intsertCommend(int boardNo, int memberNo) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
				
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		int result = new BoardDao().intsertCommend(conn,boardNo,memberNo);
		
		// 3) 트랜잭션 처리
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
			
		} else {
			
			JDBCTemplate.rollback(conn);
		}
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return result;
		
	}

	public int deleteCommend(int boardNo, int memberNo) {
	
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
				
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		int result = new BoardDao().deleteCommend(conn,boardNo,memberNo);
		
		// 3) 트랜잭션 처리
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
			
		} else {
			
			JDBCTemplate.rollback(conn);
		}
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return result;
	
	}

	public int CountCommend(int boardNo, int memberNo) {
	
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
				
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		int result = new BoardDao().CountCommend(conn,boardNo,memberNo);
		
		// 3) 트랜잭션 처리
	
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return result;
		
	}

	public int selectBoardCommend(int boardNo) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
				
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		int result = new BoardDao().selectBoardCommend(conn,boardNo);
		
		// 3) 트랜잭션 처리(select이므로 패스)
	
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 로 결과 리턴
		return result;
		
	}

	public int insertReply(Reply r) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서
		// DAO 로 요청 후 결과 받기
		int result = new BoardDao().insertReply(conn, r);
		
		// 3) 트랜잭션 처리
		if(result > 0) {
			
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 반환
		return result;
	}

	public ArrayList<Reply> selectReplyList(int boardNo) {
		
		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을 넘기면서 
		// DAo 로 요청 후 결과 받기
		ArrayList<Reply> list = new BoardDao().selectReplyList(conn, boardNo);
		
		// 3) 트랜잭션 처리
		//select문이므로 패스
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) controller 단으로 결과 리턴
		return list;
	}
	
	// 10월20일+ 게시판 일괄 삭제용 메소드
	public int boardAllStop(String totalCheckNo) {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().boardAllStop(conn, totalCheckNo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	// 관리자 페이지 게시판 검색용 메소드 (수)
	public int searchAllBoardListCount(String keyword) {

		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을
		// DAO 로 넘기면서 요청 후 결과 받기
		int listCount = new BoardDao().searchAllBoardListCount(conn, keyword);
		
		// 3) 트랜잭션 처리 (select문이므로 패스)
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 리턴
		return listCount;	}
	
	// 관리자 페이지 게시판 검색용
	public ArrayList<Board> searchAllBoardList(String keyword, PageInfo pi) {

		// 1) Connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Connection 과 전달값을
		// DAO 로 넘기면서 요청 후 결과 받기
		ArrayList<Board> searchList = new BoardDao().searchAllBoardList(conn, keyword, pi);
		
		// 3) 트랜잭션 처리 (select문이므로 패스)
		
		// 4) Connection 반납
		JDBCTemplate.close(conn);
		
		// 5) Controller 단으로 결과 리턴
		return searchList;
		
	}
	public int selectBoardReplyCount(int boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int replyCount = new BoardDao().selectBoardReplyCount(conn, boardNo);
		
		JDBCTemplate.close(conn);
		
		return replyCount;
	}
	
	public int deleteReply(Reply r) {
			
			// 1) Connection 객체 생성
			Connection conn = JDBCTemplate.getConnection();
			
			// 2) Connection 과 전달값을 넘기면서
			// DAO 로 요청 후 결과 받기
			int result = new BoardDao().deleteReply(conn, r);
			
			// 3) 트랜잭션 처리
			if(result > 0) {
				
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			// 4) Connection 반납
			JDBCTemplate.close(conn);
			
			// 5) Controller 단으로 결과 반환
			return result;
		}
	
}
