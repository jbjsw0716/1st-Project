package com.keduit.SangWook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class UserSelect {
	Connection conn;

	protected UserSelect() throws Exception {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/movie";
		String userid = "root";
		String pwd = "1234";
		
		Class.forName(driver);
		
		conn = DriverManager.getConnection(url, userid, pwd);
		
		System.out.println("|_User Select link completed!_|");
	}
	
	protected void join() throws Exception{
		String sql = "select Movie_Num, B.Movie_Title, C.GenreName, RunningTime, D.CountryName, ReleaseDate, B.Name, E.ViewingRating, F.ProductionCompanyName from movie A"
				+ " join director B on A.Title = B.Director_ID"
				+ "	join genre C on A.Genre_ID = C.Genre_ID"
				+ " join country D on A.Country_ID = D.Country_ID"
				+ " join rating E on A.Rating_ID = E.Rating_ID"
				+ " join productioncompany F on A.ProductionCompany_ID = F.ProductionCompany_ID order by Movie_Num asc;";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();

        while (rs.next()) {
            for(int i =1; i <= columnCount; i++) {
                System.out.print("\t" + rs.getObject(i));
            }
            System.out.println();
        }
		
		rs.close();
		stmt.close();
	}
	
	protected void joinTitle(String Title) throws Exception{
		String sql = "select Movie_Num, B.Movie_Title, C.GenreName, RunningTime, D.CountryName, ReleaseDate, B.Name, E.ViewingRating, F.ProductionCompanyName from movie A"
				+ "	join director B on A.Title = B.Director_ID"
				+ "	join genre C on A.Genre_ID = C.Genre_ID"
				+ " join country D on A.Country_ID = D.Country_ID"
				+ " join rating E on A.Rating_ID = E.Rating_ID"
				+ " join productioncompany F on A.ProductionCompany_ID = F.ProductionCompany_ID where B.Movie_Title = ?;";
		
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, Title);

		ResultSet rs = pstmt.executeQuery();
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		
		while (rs.next()) {
            for(int i =1; i <= columnCount; i++) {
                System.out.print("\t" + rs.getObject(i));
            }
            System.out.println();
        }
		
		rs.close();
		pstmt.close();
	}
	
	protected void joinGenre(String Genre) throws Exception{
		String sql = "select Movie_Num, B.Movie_Title, C.GenreName, RunningTime, D.CountryName, ReleaseDate, B.Name, E.ViewingRating, F.ProductionCompanyName from movie A"
				+ "	join director B on A.Title = B.Director_ID"
				+ "	join genre C on A.Genre_ID = C.Genre_ID"
				+ " join country D on A.Country_ID = D.Country_ID"
				+ " join rating E on A.Rating_ID = E.Rating_ID"
				+ " join productioncompany F on A.ProductionCompany_ID = F.ProductionCompany_ID where C.GenreName = ?;";
		
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, Genre);

		ResultSet rs = pstmt.executeQuery();
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		
		while (rs.next()) {
            for(int i =1; i <= columnCount; i++) {
                System.out.print("\t" + rs.getObject(i));
            }
            System.out.println();
        }
		
		rs.close();
		pstmt.close();
	}
	
	protected void joinCountry(String Country) throws Exception{
		String sql = "select Movie_Num, B.Movie_Title, C.GenreName, RunningTime, D.CountryName, ReleaseDate, B.Name, E.ViewingRating, F.ProductionCompanyName from movie A"
				+ "	join director B on A.Title = B.Director_ID"
				+ "	join genre C on A.Genre_ID = C.Genre_ID"
				+ " join country D on A.Country_ID = D.Country_ID"
				+ " join rating E on A.Rating_ID = E.Rating_ID"
				+ " join productioncompany F on A.ProductionCompany_ID = F.ProductionCompany_ID where D.CountryName = ?;";
		
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, Country);

		ResultSet rs = pstmt.executeQuery();
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		
		while (rs.next()) {
            for(int i =1; i <= columnCount; i++) {
                System.out.print("\t" + rs.getObject(i));
            }
            System.out.println();
        }
		
		rs.close();
		pstmt.close();
	}
	
	protected void joinName(String Name) throws Exception{
		String sql = "select Movie_Num, B.Movie_Title, C.GenreName, RunningTime, D.CountryName, ReleaseDate, B.Name, E.ViewingRating, F.ProductionCompanyName from movie A"
				+ "	join director B on A.Title = B.Director_ID"
				+ "	join genre C on A.Genre_ID = C.Genre_ID"
				+ " join country D on A.Country_ID = D.Country_ID"
				+ " join rating E on A.Rating_ID = E.Rating_ID"
				+ " join productioncompany F on A.ProductionCompany_ID = F.ProductionCompany_ID where B.Name = ?;";
		
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, Name);

		ResultSet rs = pstmt.executeQuery();
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		
		while (rs.next()) {
            for(int i =1; i <= columnCount; i++) {
                System.out.print("\t" + rs.getObject(i));
            }
            System.out.println();
        }
		
		rs.close();
		pstmt.close();
	}
	
	protected void joinNum(String Num) throws Exception{
		String sql = "select Movie_Num, B.Movie_Title, C.GenreName, RunningTime, D.CountryName, ReleaseDate, B.Name, E.ViewingRating, F.ProductionCompanyName from movie A"
				+ "	join director B on A.Title = B.Director_ID"
				+ "	join genre C on A.Genre_ID = C.Genre_ID"
				+ " join country D on A.Country_ID = D.Country_ID"
				+ " join rating E on A.Rating_ID = E.Rating_ID"
				+ " join productioncompany F on A.ProductionCompany_ID = F.ProductionCompany_ID where Movie_Num = ?;";
		
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, Num);
		
		ResultSet rs = pstmt.executeQuery();
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		
		while (rs.next()) {
			for(int i =1; i <= columnCount; i++) {
				System.out.print("\t" + rs.getObject(i));
			}
			System.out.println();
		}
		
		rs.close();
		pstmt.close();
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		DirectorTable dt = new DirectorTable();
		GenreTable gt = new GenreTable();
		CountryTable ct = new CountryTable();
		UserSelect us = new UserSelect();
		
		System.out.println();
		String message1 = "영화를 사랑하는 영화인이라면 누구든 환영하는 AMIGO에 오신 것을 환영합니다!";
        char[] chars1 = message1.toCharArray(); //문자열을 char 배열로 변환
        
        // 배열의 각 요소를 순회하면서 출력
        for (char c : chars1) {
            System.out.print(c);
            try {
                // 한 글자씩 출력 후 잠시 대기
                Thread.sleep(100); // 0.5초 대기
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println();
        System.out.println("	  /\\_/\\             /\\_/\\");
        System.out.println("	 ( o.o )	   ( o.o )");
        System.out.println("	  > ^ <             > ^ <");
        System.out.println("	 /_/ \\_\\	   /_/ \\_\\");
        
		while (true) {
			System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("보기의 숫자를 입력하세요.\n1.영화 전체 조회 | 2.부분 조회 | 3.종료");
			System.out.print("입력란: ");
			int i = scan.nextInt();
			
			if (i==1) {
				System.out.println("--------------------------------------------------------영화 전체 조회 현황------------------------------------------------------------------");
				us.join();
			} else if (i==2) {
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("부분 조회가 필요한 옵션의 숫자를 입력하세요.\n1.영화 제목 | 2.장르 | 3.제작 국가 | 4.감독 이름 | 5.영화 번호");
				System.out.print("입력란: ");
				int z = scan.nextInt();
				
				scan.nextLine();
				if (z==1) {
					System.out.println("----------영화 제목 조회--------------------------------------------------------------------------------------------------------------------");
					dt.selectTitle();
					System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
					System.out.print("조회된 영화 제목 중 원하시는 영화 제목을 입력하세요.\n입력란: ");
					String x = scan.nextLine();
					
					us.joinTitle(x);
				}  else if (z==2) {
					System.out.println("--------장르 조회-------------------------------------------------------------------------------------------------------------------------");
					gt.select();
					System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
					System.out.print("조회된 장르 중 원하시는 영화 장르를 입력하세요.\n입력란: ");
					String c = scan.nextLine();
					
					us.joinGenre(c);
				} else if (z==3) {
					System.out.println("-----제작 국가 조회-------------------------------------------------------------------------------------------------------------------------");
					ct.select();
					System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
					System.out.print("조회된 제작 국가 중 원하시는 제작 국가를 입력하세요.\n입력란: ");
					String v = scan.nextLine();
					
					us.joinCountry(v);
				} else if (z==4) {
					System.out.println("--------감독 이름 조회----------------------------------------------------------------------------------------------------------------------");
					dt.selectName();
					System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
					System.out.print("조회된 감독 이름 중 원하시는 감독 이름을 입력하세요.\n입력란: ");
					String b = scan.nextLine();
					
					us.joinName(b);
				} else if (z==5) {
					System.out.println("--------------------------------------------------------영화 전체 조회 현황------------------------------------------------------------------");
					us.join();
					System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
					System.out.print("영화 번호를 입력하세요.(제일 왼쪽에 해당하는 숫자가 영화 번호입니다.)\n입력란: ");
					String n = scan.nextLine();
					
					us.joinNum(n);
				}
			} else if (i==3) {
				System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
				String message2 = "✧٩(`·ω·´)و✧ AMIGO를 찾아와 주셔서 감사합니다. 다음에 또 뵙겠습니다! 안녕히 가세요! ✧٩(`·ω·´)و✧";
				char[] chars2 = message2.toCharArray();
			        
			    for (char c : chars2) {
			    	System.out.print(c);
			    	try {
			    		Thread.sleep(100);
			    	} catch (InterruptedException e) {
			    		e.printStackTrace();
			    	}
			    }
				
				break;
			}
		}
		
		scan.close();
	} {try {
		if (conn != null) {
			conn.close();
			System.out.println("데이터베이스 연결이 닫혔습니다.");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
