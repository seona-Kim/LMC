package com.lmc.member.model.vo;

import java.sql.Date;

// VO : 테이블의 컬럼 정보와 유사하게 작업
public class Member {
	 private int memberNo;			//	  MEMBER_NO NUMBER                
	 private String memberId;		//	  MEMBER_ID VARCHAR2(30)  
	 private String memberPwd;		//	  MEMBER_PWD VARCHAR2(100)   
	 private String memberName;		//	  MEMBER_NAME VARCHAR2(15) 
	 private String memberNick;		//	  MEMBER_NICK VARCHAR2(255)
	 private String email;			//	  EMAIL VARCHAR2(100),
	 private Date enrollDate;		//	  ENROLL_DATE DATE 
	 private String	memberImg;		//	  MEMBER_IMG VARCHAR2(1000),
	 private String status;			//	  STATUS VARCHAR2(1)
	
	 public Member() {}

	public Member(int memberNo, String memberId, String memberPwd, String memberName, String memberNick, String email,
			Date enrollDate, String memberImg, String status) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberNick = memberNick;
		this.email = email;
		this.enrollDate = enrollDate;
		this.memberImg = memberImg;
		this.status = status;
	}
	
	
	//회원가입용
	public Member(String memberId, String memberPwd, String memberName, String memberNick, String email) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberNick = memberNick;
		this.email = email;
	}
	
	public Member(int memberNo, String memberId, String memberPwd, String memberName, String memberNick, String email,
			Date enrollDate, String memberImg) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.memberNick = memberNick;
		this.email = email;
		this.enrollDate = enrollDate;
		this.memberImg = memberImg;
	}
	public Member(int memberNo, String memberId, String memberName, String memberNick, String email, Date enrollDate,
			String memberImg, String status) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberNick = memberNick;
		this.email = email;
		this.enrollDate = enrollDate;
		this.memberImg = memberImg;
		this.status = status;
	}
	
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getMemberImg() {
		return memberImg;
	}

	public void setMemberImg(String memberImg) {
		this.memberImg = memberImg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName="
				+ memberName + ", memberNick=" + memberNick + ", email=" + email + ", enrollDate=" + enrollDate
				+ ", memberImg=" + memberImg + ", status=" + status + "]";
	}
	 
	 
		
	 
	
	
}






