package SQLConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ExamConnector {
	public static void main(String[] args) {
		String user = "c##tester";
		String password = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {			
			Class.forName("oracle.jdbc.OracleDriver"); //오라클 드라이버 로딩
			System.out.println("클래스 로딩 성공");
			
			Connection con = DriverManager.getConnection(url, user,password); //커넥션 생성 후 성공 유무확인
			System.out.println("데이터 베이스 접속 성공!");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
