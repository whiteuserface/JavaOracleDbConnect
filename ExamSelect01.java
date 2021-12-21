package SQLConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExamSelect01 {
	public static void main(String[] args) {
		Connection conn = null; //�����ͺ��̽��� �����ϴ� ����
		Statement stmt = null; //
		ResultSet rs = null; //sql��� �� ����� ����ش�.
		String user = "c##tester";
		String password = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Ŭ���� �ε� ����!");
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * from \"MYDB\"");
			conn = DriverManager.getConnection(
					url, 
					user, 
					password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()) { //iterator�� ����� ����
				System.out.println(rs.getLong(1));
				System.out.println(rs.getString("NAME"));
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
			if(stmt != null) {
				try {
					stmt.close();
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
