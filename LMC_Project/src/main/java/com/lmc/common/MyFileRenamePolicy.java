package com.lmc.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// FileRenamePolicy 인터페이스 구현 (implements 키워드 이용)
// 인터페이스 : 모든 필드들이 상수필드, 모든 메소드들이 추상메소드인 추상 클래스
public class MyFileRenamePolicy implements FileRenamePolicy {

	// 반드시 미완성된 rename 메소드를 오버라이딩 해서 구현해야함
	// 기존의 파일을 전달받아서 파일명 수정작업 후에 수정된 파일
	@Override
	public File rename(File originFile) {

		// 파일명 수정 규칙
		// 파일업로드된시간(년월일시분초) + 5자리랜덤값(10000 ~ 99999) + 확장자명
		// (최대한 수정파일명이 겹치지 않게)
		
		// 원본파일명 : aaa.jpg
		// 수정파일명 : 2023100413423512345.jpg
		
		// 1) 원본파일명 얻어내기
		String originName = originFile.getName();
		
		// 2) 파일 업로드된 시간 얻어내기
		// (년월일시분초)
		String currentTime = 
				// simpleDateForMat 객체를 이용해서 Date()로 뽑아지는 시간을 원하는 형식으로 포멧하기
				new SimpleDateFormat("yyyyMMDDHHmmss").format(new Date());
		
		// 3) 5자리 랜덤값 얻어내기
		// (10000 ~ 99999 사이의 아무 숫자 하나)
		int ranNum = (int)(Math.random() * 90000 + 100000);
										  //랜덤값   시작수
		
		// 4) 원본파일명으로부터 확장자를 얻어내기
		// (.jpg, .png, .txt, ...)
		// 단, 파일명 중간에 . 이 들어가는 경우도 있음
		// 예) a.a.jpg
		// => 원본파일명에 가장 맨 마지막의 . 의 인덱스를 기준으로 (lastIndexOf)
		//    문자열을 추출해서(substring) 파일명과 확장자를 나눔
		String ext = originName.substring(originName.lastIndexOf("."));
		
		// 5) 2 + 3 + 4 문자열 합치기
		String changeName = currentTime + ranNum + ext;
		
		// 6) 원본파일 (originFile) 을 수정된 파일명으로 적용시켜서
		//    파일객체로 변환시키면서 리턴
		
		
		return new File(originFile.getParent(),changeName);
	}
	
	
}
