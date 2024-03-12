package com.lmc.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// JDBC 과정 중 공통적으로 일어날 코드들을 메소드 단위로 정의
// => 모두 static 메소드로 정의 : "싱글톤 패턴"
public class JDBCTemplate {
	
	// 1. Connection 객체 생성 (DB 접속) 한 후
	//	  해당 Connection 을 반환해주는 메소드
	public static Connection getConnection() {
		
		Properties prop = new Properties();	// Map 계열 컬렉션 (key + value)
		// .properties 파일에 담겨있는 정보들을 받을 수 있는 properties 객체 생성
		
		// 읽어들이고자 하는 driver.properties 파일의 물리적인 위치 지정
		String fileName = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();
		// JDBCTemplate.class(JDBCTemplate.java 가 컴플릿 된 파일이 위치한 곳을 의미하기 위해)
		// ==> 맨 처음의 / 는 classes 폴더를 의미함!!
		
		// fileName 변수에 담기는 경로값
		// C:\06_Web-workspace2\JSP_Project\src\main\webapp\WEB-INF\classes\sql\driver\driver.properties
		
		try {
			prop.load(new FileInputStream(fileName));
			// prop 객체에서 파일로부터 key, vale 값을 불러드리는 스트림 작업
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Connection conn = null;
		
		try {
			// 1) JDBC Driver 등록
			Class.forName(prop.getProperty("driver"));
			
			// 2) DB 와접속된 Connection 객체 생성
			conn = DriverManager.getConnection(prop.getProperty("url"),
											   prop.getProperty("username"),
											   prop.getProperty("password"));
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			return conn;
		}

	// 2. 전달받은 Connection 객체를 가지고 commit 해주는 메소드
	public static void commit(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3. 전달받은 Connection 객체를 가지고 rollback 해주는 메소드
	public static void rollback(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 4. 전달받은 Connection 객체를 자원 반납시켜 주는 메소드
	public static void close(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 5. 전달받은 Statement 객체를 자원 반납해주는 메소드
	public static void close(Statement stmt) {	// 오버로딩, 다형성
		
		try {
			if(stmt != null && !stmt.isClosed()) {
				
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 6. 전달받은 ResultSet 객체를 자원반납시켜주는 메소드
	public static void close(ResultSet rset) {
		
		try {
			if(rset != null && !rset.isClosed()) {
				
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
