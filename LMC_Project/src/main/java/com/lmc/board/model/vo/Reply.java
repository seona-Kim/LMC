package com.lmc.board.model.vo;

import java.sql.Date;

public class Reply {
	
	private int replyNo;			//	REPLY_NO	NUMBER
	private String replyContent;	//	REPLY_CONTENT	VARCHAR2(400 BYTE)
	private int refBno;				//	REF_BNO	NUMBER
	private String refBTitle;		// 	댓글 조회용 보드타이틀 REF_BTITLE 별칭 달기 
	private String replyWriter; 	//	REPLY_WRITER	NUMBER
	private Date createDate;		//	CREATE_DATE	DATE
	private Date updateDate; 		//	UPDATE_DATE	DATE
	private String status;			//	STATUS	VARCHAR2(1 BYTE)
	private String replyImg;

	public Reply() {}

	public Reply(int replyNo, String replyContent, int refBno, String replyWriter, Date createDate, Date updateDate,
			String status) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.refBno = refBno;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.status = status;
	}
	
	// 댓글 리스트 조회용 생성자
	public Reply(int replyNo, String replyContent, int refBno, String refBTitle, String replyWriter, Date createDate,
			Date updateDate) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.refBno = refBno;
		this.refBTitle = refBTitle;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public int getRefBno() {
		return refBno;
	}

	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}
	
	public String getRefBTitle() {
		return refBTitle;
	}

	public void setRefBTitle(String refBTitle) {
		this.refBTitle = refBTitle;
	}
	
	public String getReplyWriter() {
		return replyWriter;
	}

	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
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
	
	public String getReplyImg() {
		return replyImg;
	}

	public void setReplyImg(String replyImg) {
		this.replyImg = replyImg;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", refBno=" + refBno + ", replyWriter="
				+ replyWriter + ", createDate=" + createDate + ", updateDate=" + updateDate + ", status=" + status
				+ "]";
	}
	
}
