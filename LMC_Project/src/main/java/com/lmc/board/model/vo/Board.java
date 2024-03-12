package com.lmc.board.model.vo;

import java.sql.Date;

public class Board {
	
	private int commendNo;			// 	COMMEND_NO 	NUMBER 추천 식별용 번호
	private int boardNo;			//	BOARD_NO	NUMBER
	private String category;		//	CATEGORY_NO	NUMBER
	private String boardTitle;		//	BOARD_TITLE	VARCHAR2(100 BYTE)
	private String boardContent;	//	BOARD_CONTENT	VARCHAR2(4000 BYTE)
	private String boardWriter;		//	BOARD_WRITER	NUMBER
	private int views;				//	VIEWS	NUMBER
	private Date createDate;		//	CREATE_DATE	DATE
	private Date updateDate;		//	UPDATE_DATE	DATE
	private String status;			//	STATUS	VARCHAR2(1 BYTE)
	private int commendCount;		//	Count
	
	public Board() {}
	
	public Board(int commendNo, int boardNo, String category, String boardTitle, String boardContent,
			String boardWriter, int views, Date createDate, Date updateDate, String status) {
		super();
		this.commendNo = commendNo;
		this.boardNo = boardNo;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.views = views;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
	}
	public Board(int boardNo, String category, String boardTitle, String boardContent, String boardWriter, int views,
			Date createDate, Date updateDate, String status) {
		super();
		this.boardNo = boardNo;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.views = views;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
	}
	
	// 일반 게시글 전체 조회용 생성자
	public Board(int boardNo, String category, String boardTitle, String boardContent, String boardWriter, int views,
			Date createDate, Date updateDate) {
		super();
		this.boardNo = boardNo;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.views = views;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	
	
	
	public Board(int boardNo, String category, String boardTitle, String boardContent, String boardWriter, int views,
			Date createDate, Date updateDate, int commendCount) {
		super();
		this.boardNo = boardNo;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.views = views;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.commendCount = commendCount;
	}

	public Board(int boardNo, String boardTitle) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
	}
	
	// 추천 식별, 조회용 생성자
	public Board(int commendNo, int boardNo, String category, String boardTitle, String boardWriter, int views,
			Date createDate) {
		super();
		this.commendNo = commendNo;
		this.boardNo = boardNo;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.views = views;
		this.createDate = createDate;
	}
	
	public Board(int boardNo, String category, String boardTitle, String boardContent, String boardWriter, int views,
			Date createDate) {
		super();
		this.boardNo = boardNo;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.views = views;
		this.createDate = createDate;
	}

	public Board(int boardNo, String category, String boardTitle, String boardContent, String boardWriter,
			Date createDate) {
		super();
		this.boardNo = boardNo;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.createDate = createDate;
	}

	public Board(int boardNo, String boardTitle, String boardWriter, int views, Date createDate) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.views = views;
		this.createDate = createDate;
	}
	
	
	

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getCommendNo() {
		return commendNo;
	}

	public void setCommendNo(int commendNo) {
		this.commendNo = commendNo;
	}
	public int getCommendCount() {
		return commendCount;
	}

	public void setCommendCount(int commendCount) {
		this.commendCount = commendCount;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", category=" + category + ", boardTitle=" + boardTitle + ", boardContent="
				+ boardContent + ", boardWriter=" + boardWriter + ", views=" + views + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", status=" + status + "]";
	}
	
}
