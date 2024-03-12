package com.lmc.admin.model.vo;

public class AdminPageInfo {

	private int totalMember;  // 총 회원수
	private int todayBoardCount;	  // 금일 전체 게시판 작성글 수
	
	private int todayFreeBoardCount; 		// 금일 자유 게시판 작성글 수
	private int todayShareBoardCount; 		// 금일 나눔 게시판 작성글 수
	private int todayMovieInfoBoardCount;	// 금일 영화정보 게시판 작성글 수
	private int todayNoticeBoardCount;		// 금일 공지 게시판 작성글 수
	
	private int totalWrite;	 	// 총 작성글 수 (공지 + 전체 일반 게시글스(listCount 빼오기))
	private int totalNotice; 	// 전체 공지 게시글 수
	private int listCount;		// 전체 일반 게시글의 수
	
	private int freeBoard;   	// 자유 게시판 글 수
	private int shareBoard;	  	// 나눔 게시판 글 수
	private int movieInfoBoard; // 영화정보 게시글 수  
	private int noticeBoard;  	// 공지 게시판 글 수
	
	// 생성자
	public AdminPageInfo() {}

	public AdminPageInfo(int totalMember, int todayBoardCount, int todayFreeBoardCount, int todayShareBoardCount,
			int todayMovieInfoBoardCount, int todayNoticeBoardCount, int totalWrite, int totalNotice, int listCount,
			int freeBoard, int shareBoard, int movieInfoBoard, int noticeBoard) {
		super();
		this.totalMember = totalMember;
		this.todayBoardCount = todayBoardCount;
		this.todayFreeBoardCount = todayFreeBoardCount;
		this.todayShareBoardCount = todayShareBoardCount;
		this.todayMovieInfoBoardCount = todayMovieInfoBoardCount;
		this.todayNoticeBoardCount = todayNoticeBoardCount;
		this.totalWrite = totalWrite;
		this.totalNotice = totalNotice;
		this.listCount = listCount;
		this.freeBoard = freeBoard;
		this.shareBoard = shareBoard;
		this.movieInfoBoard = movieInfoBoard;
		this.noticeBoard = noticeBoard;
	}

	

	public AdminPageInfo(int totalMember, int todayBoardCount, int todayFreeBoardCount, int todayShareBoardCount,
			int todayMovieInfoBoardCount, int todayNoticeBoardCount, int totalWrite, int freeBoard, int shareBoard,
			int movieInfoBoard, int noticeBoard) {
		super();
		this.totalMember = totalMember;
		this.todayBoardCount = todayBoardCount;
		this.todayFreeBoardCount = todayFreeBoardCount;
		this.todayShareBoardCount = todayShareBoardCount;
		this.todayMovieInfoBoardCount = todayMovieInfoBoardCount;
		this.todayNoticeBoardCount = todayNoticeBoardCount;
		this.totalWrite = totalWrite;
		this.freeBoard = freeBoard;
		this.shareBoard = shareBoard;
		this.movieInfoBoard = movieInfoBoard;
		this.noticeBoard = noticeBoard;
	}

	// 메소드부
	public int getTotalMember() {
		return totalMember;
	}

	public void setTotalMember(int totalMember) {
		this.totalMember = totalMember;
	}

	public int getTodayBoardCount() {
		return todayBoardCount;
	}

	public void setTodayBoardCount(int todayBoardCount) {
		this.todayBoardCount = todayBoardCount;
	}

	public int getTodayFreeBoardCount() {
		return todayFreeBoardCount;
	}

	public void setTodayFreeBoardCount(int todayFreeBoardCount) {
		this.todayFreeBoardCount = todayFreeBoardCount;
	}

	public int getTodayShareBoardCount() {
		return todayShareBoardCount;
	}

	public void setTodayShareBoardCount(int todayShareBoardCount) {
		this.todayShareBoardCount = todayShareBoardCount;
	}

	public int getTodayMovieInfoBoardCount() {
		return todayMovieInfoBoardCount;
	}

	public void setTodayMovieInfoBoardCount(int todayMovieInfoBoardCount) {
		this.todayMovieInfoBoardCount = todayMovieInfoBoardCount;
	}

	public int getTodayNoticeBoardCount() {
		return todayNoticeBoardCount;
	}

	public void setTodayNoticeBoardCount(int todayNoticeBoardCount) {
		this.todayNoticeBoardCount = todayNoticeBoardCount;
	}

	public int getTotalWrite() {
		return totalWrite;
	}

	public void setTotalWrite(int totalWrite) {
		this.totalWrite = totalWrite;
	}

	public int getTotalNotice() {
		return totalNotice;
	}

	public void setTotalNotice(int totalNotice) {
		this.totalNotice = totalNotice;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getFreeBoard() {
		return freeBoard;
	}

	public void setFreeBoard(int freeBoard) {
		this.freeBoard = freeBoard;
	}

	public int getShareBoard() {
		return shareBoard;
	}

	public void setShareBoard(int shareBoard) {
		this.shareBoard = shareBoard;
	}

	public int getMovieInfoBoard() {
		return movieInfoBoard;
	}

	public void setMovieInfoBoard(int movieInfoBoard) {
		this.movieInfoBoard = movieInfoBoard;
	}

	public int getNoticeBoard() {
		return noticeBoard;
	}

	public void setNoticeBoard(int noticeBoard) {
		this.noticeBoard = noticeBoard;
	}

	@Override
	public String toString() {
		return "AdminPageInfo [totalMember=" + totalMember + ", todayBoardCount=" + todayBoardCount
				+ ", todayFreeBoardCount=" + todayFreeBoardCount + ", todayShareBoardCount=" + todayShareBoardCount
				+ ", todayMovieInfoBoardCount=" + todayMovieInfoBoardCount + ", todayNoticeBoardCount="
				+ todayNoticeBoardCount + ", totalWrite=" + totalWrite + ", totalNotice=" + totalNotice + ", listCount="
				+ listCount + ", freeBoard=" + freeBoard + ", shareBoard=" + shareBoard + ", movieInfoBoard="
				+ movieInfoBoard + ", noticeBoard=" + noticeBoard + "]";
	}
	
	
	
}
