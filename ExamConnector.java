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
			Class.forName("oracle.jdbc.OracleDriver"); //����Ŭ ����̹� �ε�
			System.out.println("Ŭ���� �ε� ����");
			
			Connection con = DriverManager.getConnection(url, user,password); //Ŀ�ؼ� ���� �� ���� ����Ȯ��
			System.out.println("������ ���̽� ���� ����!");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
