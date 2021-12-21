package SQLConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamSelect03 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null; //statement 와 상속관계
		ResultSet rs = null;
		String user = "c##tester";
		String password = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("클래스 로딩 성공!");
			
			StringBuffer sql = new StringBuffer();
			sql.append("select \"NUM\", \"NAME\",  \"BIRTH\" , \"ADDRESS\"  from \"MYDB\"");
			sql.append(" where \"NAME\"=?");//바인딩 변수
			
			String searchName = "김동인"; //찾을 이름을 변수에 저장
			
			conn = DriverManager.getConnection(
					url, 
					user, 
					password);
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, searchName); //첫번째 ?에 넣을 값을 입력하는 구문 (indexNum, variable)
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getLong(1)); //index번호로 검색
				System.out.println(rs.getString(2));
				System.out.println(rs.getDate(3));
				System.out.println(rs.getString(4));
				System.out.println();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
