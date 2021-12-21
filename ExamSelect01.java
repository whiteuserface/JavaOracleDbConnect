package SQLConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExamSelect01 {
	public static void main(String[] args) {
		Connection conn = null; //데이터베이스에 연결하는 역할
		Statement stmt = null; //SQL문을 쓸수있게 해주는 역할
		ResultSet rs = null; //sql명령 후 결과를 담아준다. select구문에서만 쓴다.
		String user = "c##tester"; //user name setting
		String password = "1234"; //user password setting
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //port 설정
		try {
			Class.forName("oracle.jdbc.OracleDriver"); //오라클 데이터베이스 연결
			System.out.println("클래스 로딩 성공!"); //성공 시 클래스 로딩 성공 띄우기
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * from \"MYDB\"");//sql객체에 SQL 구문 넣기
			conn = DriverManager.getConnection( //Connection 객체에 url, user, password값을 순서대로 넣어준다.
					url, 
					user, 
					password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString()); //resultSet이라는 객체에 executeQuery로 데이터를 담는다.
			while(rs.next()) { //iterator와 비슷한 개념 //다음 데이터가 있는지 next()라는 boolean값으로 조사한다.
				System.out.println(rs.getLong(1));//Index검색 예시
				System.out.println(rs.getString("NAME")); //Column 명으로도 출력할 수 있다.
				System.out.println(rs.getDate(3));
				System.out.println(rs.getString(4));
				System.out.println();
			}
			
		} catch (ClassNotFoundException e) { //class를 찾지 못할때 예외처리
			e.printStackTrace();
		} catch(SQLException e) { //SQL구문 오류 예외처리
			e.printStackTrace();
		} finally {
			if(rs != null) { //만약 resultSet이 null이 아니라면 
				try {
					rs.close(); //resultSet 종료
				} catch (SQLException e) { 
					e.printStackTrace(); //SQL 예외처리함
				}
			}
			if(stmt != null) { //SQL문을 인식할 수 있는 객체에 url, username, userpassword 값이 없지 않으면
				try {
					stmt.close(); //statement 종료
				} catch (SQLException e) { //SQL 에외처리
					e.printStackTrace();
				}
			}
			if(conn != null) { //conn 객체에 url, username, userpassword가 null이 아니라면
				try {
					conn.close();
				} catch (SQLException e) { //SQL예외처리
					e.printStackTrace();
				}
			}
		}
		
	}
}
