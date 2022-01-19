package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;
@Repository
public class GuestbookDao {
	
	//필드

	//공통영역
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int count = 0;
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			// System.out.println("접속성공");

			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
	
	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	//공통영역
	
	//리스트 영역	
	public List<GuestbookVo> getList(){
		
		List<GuestbookVo> guestbookList = new ArrayList<GuestbookVo>();
	
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " SELECT no, ";
			query += " name, ";
			query += " password, ";
			query += " content, ";
			query += " TO_CHAR(reg_date, 'YYYY-MM-DD hh:mi:ss') reg_date ";
			query += " FROM guestbook ";
			query += " ORDER BY reg_date desc ";
			//System.out.println(query);

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			//바인딩 생략? 없음

			rs = pstmt.executeQuery(); // 쿼리문 실행

			// 4.결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");
				
				GuestbookVo guestbookVo = new GuestbookVo(no, name, password, content, reg_date);
				guestbookList.add(guestbookVo);
			}
			// System.out.println("[" + count + "건 추가되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return guestbookList;
	}   
	//리스트 영역	
	
	// 등록 영역
	public int guestInsert(GuestbookVo guestbookVo) {

		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " INSERT INTO guestbook ";
			query += " VALUES (seq_guestbook_no.nextval, ?, ?, ?, sysdate) ";
			// System.out.println(query);

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기

			pstmt.setString(1, guestbookVo.getName()); // ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, guestbookVo.getPassword()); // ?(물음표) 중 2번째, 순서중요
			pstmt.setString(3, guestbookVo.getContent()); // ?(물음표) 중 3번째, 순서중요

			 count = pstmt.executeUpdate();  // 쿼리문 실행

			// 4.결과처리
			System.out.println("[" + count + "건 추가되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		return count;
	}
	// 등록 영역
	
	//삭제 영역
	public void guestbookDelete(GuestbookVo guestBookVo) {
		
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = ""; // 쿼리문 문자열만들기, ? 주의
			query += " delete from guestbook ";
			query += " where no = ? ";
			query += " and password = ? ";

			pstmt = conn.prepareStatement(query); // 쿼리로 만들기
			
			//바운딩
			pstmt.setInt(1, guestBookVo.getNo());// ?(물음표) 중 1번째, 순서중요
			pstmt.setString(2, guestBookVo.getPassword());// ?(물음표) 중 1번째, 순서중요
			count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.println(count + "건 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

	}
	
}


