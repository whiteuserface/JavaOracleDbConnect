package SQLConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamSelect05 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String user = "c##tester";
		String password = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("클래스 로딩 성공!");
			
			StringBuffer sql = new StringBuffer();
			sql.append("select \"NUM\", \"NAME\", \"BIRTH\", \"ADDRESS\" from \"MYDB\"");
			sql.append(" where \"NAME\"=? or \"NUM\"=?");
			
			String searchName = "김동인";
			int searchNum = 1;
			
			conn = DriverManager.getConnection(
					url, 
					user, 
					password);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, searchName);
			pstmt.setInt(2, searchNum);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("NAME"));
				System.out.println(rs.getLong("NUM"));
				System.out.println(rs.getDate("BIRTH"));
				System.out.println(rs.getString("ADDRESS"));
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
