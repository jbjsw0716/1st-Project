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

public class MovieTable {
	Connection conn;
	
	protected MovieTable() throws Exception {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/movie";
		String userid = "root";
		String pwd = "1234";
		
		Class.forName(driver);
		
		conn = DriverManager.getConnection(url, userid, pwd);
		
		System.out.println("|_Movie Table link completed!_|");
	}
	
	protected void selectAll() throws Exception{
		String sql = "select * from movie order by Movie_Num asc";
		
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
	
	protected void insert(int Title, int Genre_ID, String RunningTime, int Country_ID, String ReleaseDate, int Director_ID, int Rating_ID, int ProductionCompany_ID) throws Exception {
		Movie m = new Movie(Title, Genre_ID, RunningTime, Country_ID, ReleaseDate, Director_ID, Rating_ID, ProductionCompany_ID);
		String sql = "insert into movie(Title, Genre_ID, RunningTime, Country_ID, ReleaseDate, Director_ID, Rating_ID, ProductionCompany_ID)"
				+ " values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, m.getTitle());
		pstmt.setObject(2, m.getGenre_id());
		pstmt.setObject(3, m.getRunningTime());
		pstmt.setObject(4, m.getCountry_id());
		pstmt.setObject(5, m.getReleaseDate());
		pstmt.setObject(6, m.getDirector_id());
		pstmt.setObject(7, m.getRating_id());
		pstmt.setObject(8, m.getAdditionalinfo_id());
	
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	protected void delete(int Movie_Num) throws Exception {
		Movie m = new Movie(Movie_Num);
		String sql = "delete from movie where Movie_Num = ?";
		
		PreparedStatement pstmt;
		pstmt = conn.prepareStatement(sql);
		pstmt.setObject(1, m.getMovie_num());

		int rs = pstmt.executeUpdate();
		
		if (rs == 1) {
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println(m.getMovie_num()+ "번 삭제 성공");
		} else {
			System.out.println("-----------------------------------------------------------------------------------");
			System.out.println(m.getMovie_num() + "번 삭제 실패");
		}
	
		pstmt.close();
	}
	
	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);
		MovieTable mt = new MovieTable();
		DirectorTable dt = new DirectorTable();
		GenreTable gt = new GenreTable();
		CountryTable ct = new CountryTable();
		RatingTable rt = new RatingTable();
		ProductionCompanyTable pct = new ProductionCompanyTable();
		
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
			System.out.println("---------------------------------------현재 테이블 현황---------------------------------");
			mt.selectAll();
			System.out.println("-----------------------------------------------------------------------------------");
			
			System.out.println("보기의 숫자를 입력하세요.\n1.입력 | 2.삭제 | 3.종료");
			System.out.println("-----------------------------------------------------------------------------------");
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
						
						//초반 파싱 작업
						JSONParser paser = new JSONParser();
						JSONObject jsonObj = (JSONObject) paser.parse(result);
						JSONObject movieInfoResult = (JSONObject) jsonObj.get("movieInfoResult");
						JSONObject movieInfo = (JSONObject) movieInfoResult.get("movieInfo");
						
						//영화명 파싱(join을 활용하기 위해 int 타입으로 설정)
						String movieNm = (String) movieInfo.get("movieNm");
						int movieID = dt.getDirectorId1(movieNm);
						
						//장르 파싱(join을 활용하기 위해 int 타입으로 설정)
						JSONArray genres = (JSONArray) movieInfo.get("genres");
						JSONObject genreNm = (JSONObject) genres.get(0);
						String genreNm1 = (String) genreNm.get("genreNm");
						int genreId = gt.getGenreId(genreNm1);
						
						//러닝타임 파싱
						String showTm = (String) movieInfo.get("showTm");
						
						//제작국명 파싱(join을 활용하기 위해 int 타입으로 설정)
						JSONArray nations = (JSONArray) movieInfo.get("nations");
						JSONObject nationNm = (JSONObject) nations.get(0);
						String nationNm1 = (String) nationNm.get("nationNm");
						int countryID = ct.getCountryId(nationNm1);
						
						//개봉일 파싱
						String openDt = (String) movieInfo.get("openDt");
						
						//감독 파싱(join을 활용하기 위해 int 타입으로 설정)
						JSONArray directors = (JSONArray) movieInfo.get("directors");
						
						//감독 파싱 중에 감독이 공공데이터에 없을 경우 Unknown이라는 문자열을 대체로 출력
						String peopleNm1 = "";
						if (directors != null && !directors.isEmpty()) {
							JSONObject peopleNm = (JSONObject) directors.get(0);
							
							if (peopleNm != null && peopleNm.containsKey("peopleNm")) {
								peopleNm1 = (String) peopleNm.get("peopleNm");
							}
						} else {
							peopleNm1 = "Unknown";
						}
						int directorID = dt.getDirectorId2(peopleNm1);
						
						//관람등급 파싱(join을 활용하기 위해 int 타입으로 설정)
						JSONArray audits = (JSONArray) movieInfo.get("audits");
						JSONObject watchGradeNm = (JSONObject) audits.get(0);
						String watchGradeNm1 = (String) watchGradeNm.get("watchGradeNm");
						int ratingID = rt.getRatingId(watchGradeNm1);
						
						//제작사 파싱(join을 활용하기 위해 int 타입으로 설정)
						JSONArray companys = (JSONArray) movieInfo.get("companys");
						JSONObject companyNm = (JSONObject) companys.get(0);
						String companyNm1 = (String) companyNm.get("companyNm");
						int companyID = pct.getProductionCompanyId(companyNm1);
						
						System.out.println("-----------------------------------------------------------------------------------");
						System.out.println("영화명: " + movieNm);
						System.out.println("장르: " + genreNm1);
						System.out.println("러닝타임: " + showTm);
						System.out.println("제작국명: " + nationNm1);
						System.out.println("개봉일: " + openDt);
						System.out.println("감독: " + peopleNm1);
						System.out.println("관람등급: " + watchGradeNm1);
						System.out.println("제작사: " + companyNm1);
						
						mt.insert(movieID, genreId, showTm, countryID, openDt, directorID, ratingID, companyID);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("예기치 못한 오류가 발생하였습니다.");
					}
				}
				
			//데이터 삭제하기
			} else if (i==2) {
				System.out.println("-----------------------------------------------------------------------------------");
				System.out.print("삭제할 Movie_Num을 입력하세요: ");
				int s = scan.nextInt();
				
				mt.delete(s);
			} else if (i==3) {
				System.out.println("-----------------------------------------------------------------------------------");
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
