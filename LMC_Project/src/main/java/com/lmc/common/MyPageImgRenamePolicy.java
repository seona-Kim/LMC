package com.lmc.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// FileRenamePolicy 인터페이스 구현 (implements 키워드 이용)
// 인터페이스 : 모든 필드들이 상수필드, 모든 메소드들이 추상메소드인 추상 클래스
public class MyPageImgRenamePolicy implements FileRenamePolicy {

	// 반드시 미완성된 rename 메소드를 오버라이딩 해서 구현해야함
	// 기존의 파일을 전달받아서 파일명 수정작업 후에 수정된 파일
	@Override
	public File rename(File originFile) {

		// 파일명 수정 규칙
		// memberNo 받아서 유저번호.확장자명
		
		// 원본파일명 : aaa.jpg , 234번 유저 업로드
		// 수정파일명 : 234.jpg
		
		// 1) 원본파일명 얻어내기
		String originName = originFile.getName();
		
		// 업로드 유저번호 알아내기 
		
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
