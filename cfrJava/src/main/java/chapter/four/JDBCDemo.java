package chapter.four;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;

public class JDBCDemo {
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String USER = "scott";
	private static final String PASSWORD = "tiger";
	public static void main(String[] args) throws Exception {
		Class.forName(DBDRIVER);
		Connection conn = DriverManager.getConnection(DBURL, USER, PASSWORD);
		System.out.println(conn);
//		 statement
//		Statement stmt = conn.createStatement();
//		// ���飺���sql���̫����Ҫ��ɶ���ַ������ӣ���סÿ���ַ������˶����Ͽո�
//		String sql = "INSERT INTO myemp(EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) VALUES (9999,'zsf','ws',6666,SYSDATE,0,3000,20)";
//		int len = stmt.executeUpdate(sql);
//		System.out.println(len);
//		// ��׼������SELECT������ʹ��*��ѯ
//		String sql2 = "SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM myemp";
//		ResultSet rs = stmt.executeQuery(sql2);
//		while(rs.next()) {
//			// ��׼������ʹ����ţ����£�����ʹ���ַ�������int empno = rs.getInt("empno")
//			// ȡ������ʱ����˳��ȡ����ÿ������ֻȡһ��
//			int empno = rs.getInt(1);
//			String ename = rs.getString(2);
//			String job = rs.getString(3);
//			int mgr = rs.getInt(4);
//			Date hiredate = rs.getDate(5);
//			int sal = rs.getInt(6);
//			int comm = rs.getInt(7);
//			int deptno = rs.getInt(8);
//			System.out.println(empno + "," + ename + "," + job + "," + mgr + "," + hiredate + "," + sal + "," + comm + "," + deptno);
//		}
		
//		 preparedStatement
//		 ���ݸ���
		int empno = 8888;
		String ename = "Mr'Smith";
		String job = "foreign";
		int mgr = 9999;
		Date hiredate = new Date(); // ֻ���½�util.Date��������ת��
		int sal = 0;
		int comm = 1000;
		int deptno = 20;
		String sql = "INSERT INTO myemp(EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) VALUES (?,?,?,?,?,?,?,?)"; //���ʺż��ɣ�����Ҫ�Լ�������
		PreparedStatement ps1 = conn.prepareStatement(sql);
		ps1.setInt(1, empno); // ������1��ʼ�����Ǵ�0��ʼ
		ps1.setString(2, ename);
		ps1.setString(3, job);
		ps1.setInt(4, mgr);
		ps1.setDate(5, new java.sql.Date(hiredate.getTime())); // java.util.Dateת��java.sql.Date
		ps1.setInt(6, sal);
		ps1.setInt(7, comm);
		ps1.setInt(8, deptno);
		int len = ps1.executeUpdate();
		System.out.println(len);
		// ���ݲ�ѯ
		String sql2 = "SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM myemp";
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		ResultSet rs2 = ps2.executeQuery();
		while(rs2.next()) {
			System.out.println(rs2.getInt(1) + "," + rs2.getString(2) + "," + rs2.getString(3) + "," + rs2.getInt(4) + "," + rs2.getDate(5) + "," 
					+ rs2.getInt(6) + "," + rs2.getInt(7) + "," + rs2.getInt(8)); //getDate()���صĽ������Ҫ�������Զ�ת�����ܹ��������
		}
		System.out.println("----------------------------------------------------------------");
		// ģ����ѯ
		String keyword = "K";
		String sql3 = "SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM myemp WHERE ename LIKE ?";
//		����String sql3 = "SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM myemp WHERE ename LIKE '%?%'";������ô���ã���Ҫ�������ַ�����Ϊһ����������ֵ
		PreparedStatement ps3 = conn.prepareStatement(sql3);
		ps3.setString(1, "%" + keyword + "%");
//		ps3.setString(1, keyword);
		ResultSet rs3 = ps3.executeQuery();
		while(rs3.next()) {
			System.out.println(rs3.getInt(1) + "," + rs3.getString(2) + "," + rs3.getString(3) + "," + rs3.getInt(4) + "," + rs3.getDate(5) + "," 
					+ rs3.getInt(6) + "," + rs3.getInt(7) + "," + rs3.getInt(8));
		}
		System.out.println("----------------------------------------------------------------");
		// ��ҳ��ʾ
		int currentPage = 4;
		int lineSize = 5;
		String sql4 = "SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM (SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno,ROWNUM rn FROM myemp WHERE ROWNUM <= ?) tmp WHERE tmp.rn > ?";
		PreparedStatement ps4 = conn.prepareStatement(sql4);
		ps4.setInt(1, currentPage * lineSize);
		ps4.setInt(2, (currentPage - 1) * lineSize);
		ResultSet rs4 = ps4.executeQuery();
		while(rs4.next()) {
			System.out.println(rs4.getInt(1) + "," + rs4.getString(2) + "," + rs4.getString(3) + "," + rs4.getInt(4) + "," + rs4.getDate(5) + "," 
					+ rs4.getInt(6) + "," + rs4.getInt(7) + "," + rs4.getInt(8));
		}
		System.out.println("----------------------------------------------------------------");
		// ͳ��������
		String sql5 = "SELECT COUNT(sal) FROM myemp";
		PreparedStatement ps5 = conn.prepareStatement(sql5);
		ResultSet rs5 = ps5.executeQuery();
		while(rs5.next()) {
			System.out.println(rs5.getInt(1));
		}
		// �ֹ���������
		conn.setAutoCommit(false);
		Statement stmt2 = conn.createStatement();
		try {
			stmt2.addBatch("INSERT INTO myemp(empno,ename) VALUES (9983,'����')");
			stmt2.addBatch("INSERT INTO myemp(empno,ename) VALUES (9982,'���')");
			stmt2.addBatch("INSERT INTO myemp(empno,ename) VALUES (9981,'���')");
			stmt2.addBatch("INSERT INTO myemp(empno,ename) VALUES (9980,'�ڽ�')");
			stmt2.addBatch("INSERT INTO myemp(empno,ename) VALUES (9979,'���A')");
			int result [] = stmt2.executeBatch();
			System.out.println(Arrays.toString(result));
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback(); // �������������ʱ�������ڼӸ������ţ��ֶ��������۲�
		}
		conn.close();
	}
}
