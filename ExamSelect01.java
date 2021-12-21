package SQLConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExamSelect01 {
	public static void main(String[] args) {
		Connection conn = null; //�����ͺ��̽��� �����ϴ� ����
		Statement stmt = null; //SQL���� �����ְ� ���ִ� ����
		ResultSet rs = null; //sql��� �� ����� ����ش�. select���������� ����.
		String user = "c##tester"; //user name setting
		String password = "1234"; //user password setting
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; //port ����
		try {
			Class.forName("oracle.jdbc.OracleDriver"); //����Ŭ �����ͺ��̽� ����
			System.out.println("Ŭ���� �ε� ����!"); //���� �� Ŭ���� �ε� ���� ����
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * from \"MYDB\"");//sql��ü�� SQL ���� �ֱ�
			conn = DriverManager.getConnection( //Connection ��ü�� url, user, password���� ������� �־��ش�.
					url, 
					user, 
					password);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString()); //resultSet�̶�� ��ü�� executeQuery�� �����͸� ��´�.
			while(rs.next()) { //iterator�� ����� ���� //���� �����Ͱ� �ִ��� next()��� boolean������ �����Ѵ�.
				System.out.println(rs.getLong(1));//Index�˻� ����
				System.out.println(rs.getString("NAME")); //Column �����ε� ����� �� �ִ�.
				System.out.println(rs.getDate(3));
				System.out.println(rs.getString(4));
				System.out.println();
			}
			
		} catch (ClassNotFoundException e) { //class�� ã�� ���Ҷ� ����ó��
			e.printStackTrace();
		} catch(SQLException e) { //SQL���� ���� ����ó��
			e.printStackTrace();
		} finally {
			if(rs != null) { //���� resultSet�� null�� �ƴ϶�� 
				try {
					rs.close(); //resultSet ����
				} catch (SQLException e) { 
					e.printStackTrace(); //SQL ����ó����
				}
			}
			if(stmt != null) { //SQL���� �ν��� �� �ִ� ��ü�� url, username, userpassword ���� ���� ������
				try {
					stmt.close(); //statement ����
				} catch (SQLException e) { //SQL ����ó��
					e.printStackTrace();
				}
			}
			if(conn != null) { //conn ��ü�� url, username, userpassword�� null�� �ƴ϶��
				try {
					conn.close();
				} catch (SQLException e) { //SQL����ó��
					e.printStackTrace();
				}
			}
		}
		
	}
}
