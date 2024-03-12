package com.lmc.notice.model.vo;

import java.sql.Date;

public class Notice {

	private int noticeNo;			//	NOTICE_NO	NUMBER
	private String noticeTitle;		//	NOTICE_TITLE	VARCHAR2(100 BYTE)
	private String noticeContent;	//	NOTICE_CONTENT	VARCHAR2(4000 BYTE)
	private int views;				//	VIEWS	NUMBER
	private Date createDate;		//	CREATE_DATE	DATE
	private String status; 			//	STATUS	VARCHAR2(1 BYTE)
	
	// 생성자부
	public Notice() {}
	
	public Notice(int noticeNo, String noticeTitle, String noticeContent, int views, Date createDate, String status) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.views = views;
		this.createDate = createDate;
		this.status = status;
	}
	
	public Notice(int noticeNo, String noticeTitle, String noticeContent, int views, Date createDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.views = views;
		this.createDate = createDate;
	}
	
	public Notice(int noticeNo, String noticeTitle, int views, Date createDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.views = views;
		this.createDate = createDate;
	}

	// 메소드부
	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", views=" + views + ", createDate=" + createDate + ", status=" + status + "]";
	}
	
}
