package com.lmc.board.model.vo;

public class Commend {

  private int commendNo;	//	COMMEND_NO	NUMBER
  private int commendBno;	//	COMMEND_BNO	NUMBER
  private int commendMno;	//	COMMEND_MNO	NUMBER
  
 public Commend() {}
  
public Commend(int commendNo, int commendBno, int commendMno) {
	super();
	this.commendNo = commendNo;
	this.commendBno = commendBno;
	this.commendMno = commendMno;
}


public int getCommendNo() {
	return commendNo;
}


public void setCommendNo(int commendNo) {
	this.commendNo = commendNo;
}


public int getCommendBno() {
	return commendBno;
}


public void setCommendBno(int commendBno) {
	this.commendBno = commendBno;
}


public int getCommendMno() {
	return commendMno;
}


public void setCommendMno(int commendMno) {
	this.commendMno = commendMno;
}


@Override
public String toString() {
	return "Commend [commendNo=" + commendNo + ", commendBno=" + commendBno + ", commendMno=" + commendMno + "]";
}



  
  
}
