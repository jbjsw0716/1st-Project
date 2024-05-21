package com.keduit.SangWook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CountryTable {
	Connection conn;
	
	protected CountryTable() throws Exception {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/movie";
		String userid = "root";
		String pwd = "1234";
		
		Class.forName(driver);
		
		conn = DriverManager.getConnection(url, userid, pwd);
		
		System.out.println("|_Country Table link completed!_|");
	}
	
	protected void selectAll() throws Exception{
		String sql = "select * from country order by Country_ID asc";
		
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
	
	protected void select() throws Exception{
		String sql = "select CountryName from country";
		
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
	
	protected void insert(String CountryName) throws Exception {
		Country c = new Country(CountryName);
		String sql = "insert into country(CountryName) " 
				+ "values(?)";
		
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, c.getCountryName());

		pstmt.executeUpdate();
		pstmt.close();
	}
	
	protected void delete(int Country_ID) throws Exception {
		Country c = new Country(Country_ID);
		String sql = "delete from country where Country_ID = ?";
		
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, c.getCountry_id());

		int rs = pstmt.executeUpdate();
		
		if (rs == 1) {
			System.out.println("---------------------------");
			System.out.println(c.getCountry_id() + "번 삭제 성공");
		} else {
			System.out.println("---------------------------");
			System.out.println(c.getCountry_id() + "번 삭제 실패");
		}
	
		pstmt.close();
	}
	
	protected int getCountryId(String CountryName) throws Exception {
		Country c = new Country(CountryName);
	    int countryId = -1; //초기값으로 -1을 설정하거나, 적절한 오류 처리를 위한 값으로 설정
	    String query = "SELECT Country_ID FROM country WHERE CountryName = ?";

	    try (PreparedStatement statement = conn.prepareStatement(query)) {
	        statement.setObject(1, c.getCountryName());

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                countryId = resultSet.getInt("Country_ID");
	            }
	        }
	    }

	    return countryId;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		CountryTable ct = new CountryTable();
		
		String[] code = {"20124079", "20129370", "20090834", "20080396", "20149120",
				"20060151", "20070550", "20210028", "20163181", "20184889",
				"20148048", "20156564", "20162442", "20070316", "20196478",
				"20137048", "20212866", "20183782", "20192240", "20050333",
				"19980231", "20182530", "20127593", "20228819", "20156557",
				"20150976", "20111009", "20197803", "20161872", "20040756",
				"20183867", "20232273", "20172742", "20204548", "20179383",
				"19980035", "20030131", "20181788", "20175262", "20090857",
				"20040613", "20030371", "20100301", "20227762", "20235980",
				"20135428", "20050082", "20040734", "20149629", "20090856"};
		
		String key = "fd88e01386196424fce436d18fec846e";
		String result = "";
		
		System.out.println();
		
		while (true) {
			//테이블 자동 조회
			System.out.println("--------현재 테이블 현황--------");
			ct.selectAll();
			System.out.println("---------------------------");
			
			System.out.println("보기의 숫자를 입력하세요.\n1.입력 | 2.삭제 | 3.종료");
			System.out.println("---------------------------");
			System.out.print("입력란: ");
			int i = scan.nextInt();
			
			//데이터 입력하기
			if (i==1) {
				for (String movieCd : code) {
					try {
						URL url = new URL
								("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key="
										+ key + "&movieCd=" + movieCd);
						BufferedReader bf;
						bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
						result = bf.readLine();
						
						JSONParser paser = new JSONParser();
						JSONObject jsonObj = (JSONObject) paser.parse(result);
						JSONObject movieInfoResult = (JSONObject) jsonObj.get("movieInfoResult");
						JSONObject movieInfo = (JSONObject) movieInfoResult.get("movieInfo");
						
						JSONArray nations = (JSONArray) movieInfo.get("nations");
						JSONObject nationNm = (JSONObject) nations.get(0);
						String nationNm1 = (String) nationNm.get("nationNm");
						
						System.out.println("---------------------------");
						System.out.println("국가: " + nationNm1);
						
						ct.insert(nationNm1);
					} catch (Exception e) {
						System.out.println("중복된 국가입니다.");
					}
				}
				
			//데이터 삭제하기
			} else if (i==2) {
				System.out.println("---------------------------");
				System.out.print("삭제할 Country_ID를 입력하세요: ");
				int s = scan.nextInt();
				
				ct.delete(s);
			} else if (i==3) {
				System.out.println("---------------------------");
				System.out.print("종료합니다.");
				System.out.println();
				
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
