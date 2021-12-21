package SQLConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamSelect03 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null; //statement �� ��Ӱ���
		ResultSet rs = null;
		String user = "c##tester";
		String password = "1234";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Ŭ���� �ε� ����!");
			
			StringBuffer sql = new StringBuffer();
			sql.append("select \"NAME\", \"NUM\", \"BIRTH\" , \"ADDRESS\"  from \"MYDB\"");
			sql.append(" where \"NAME\"=?");//���ε� ����
			
			String searchName = "�赿��";
			
			conn = DriverManager.getConnection(
					url, 
					user, 
					password);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, searchName); //���ε� ������ set��.
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getLong(1));
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
