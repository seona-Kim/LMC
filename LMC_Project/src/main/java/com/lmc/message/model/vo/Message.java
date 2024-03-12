package com.lmc.message.model.vo;

import java.sql.Date;

public class Message {
	
//	필드부
	public int dmNo;				//	DM_NO				NUMBER
	public String dmContent;		//	DM_CONTENT			VARCHAR2(600 BYTE)
	public String dmSendMember;		//	DM_SEND_MEMBER		NUMBER
	public String dmReciveMember;	//	DM_RECIVE_MEMBER	NUMBER
	public Date dmSendDate;			//	DM_SEND_DATE		DATE
	public String status;			//	STATUS				VARCHAR2(1 BYTE)
	
	public String memberImg;
	
//	생성자부
	public Message() { }

	public Message(int dmNo, String dmContent, String dmSendMember, String dmReciveMember, Date dmSendDate,
			String status) {
		super();
		this.dmNo = dmNo;
		this.dmContent = dmContent;
		this.dmSendMember = dmSendMember;
		this.dmReciveMember = dmReciveMember;
		this.dmSendDate = dmSendDate;
		this.status = status;
	}
	
	public Message(int dmNo, String dmContent, String memberImg, String dmSendMember, Date dmSendDate) {
		super();
		this.dmNo = dmNo;
		this.memberImg = memberImg;
		this.dmSendMember = dmSendMember;
		this.dmContent = dmContent;
		this.dmSendDate = dmSendDate;
	}

	//	메소드부
	public int getDmNo() {
		return dmNo;
	}

	public void setDmNo(int dmNo) {
		this.dmNo = dmNo;
	}

	public String getDmContent() {
		return dmContent;
	}

	public void setDmContent(String dmContent) {
		this.dmContent = dmContent;
	}

	public String getDmSendMember() {
		return dmSendMember;
	}

	public void setDmSendMember(String dmSendMember) {
		this.dmSendMember = dmSendMember;
	}

	public String getDmReciveMember() {
		return dmReciveMember;
	}

	public void setDmReciveMember(String memberNo) {
		this.dmReciveMember = memberNo;
	}

	public Date getDmSendDate() {
		return dmSendDate;
	}

	public void setDmSendDate(Date dmSendDate) {
		this.dmSendDate = dmSendDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMemberImg() {
		return memberImg;
	}

	public void setMemberImg(String memberImg) {
		this.memberImg = memberImg;
	}

	@Override
	public String toString() {
		return "Message [dmNo=" + dmNo + ", dmContent=" + dmContent + ", dmSendMember=" + dmSendMember
				+ ", dmReciveMember=" + dmReciveMember + ", dmSendDate=" + dmSendDate + ", status=" + status + "]";
	}
	
	

}
