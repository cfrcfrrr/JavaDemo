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
//		// 经验：如果sql语句太长，要拆成多个字符串连接，记住每个字符串两端都加上空格
//		String sql = "INSERT INTO myemp(EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) VALUES (9999,'zsf','ws',6666,SYSDATE,0,3000,20)";
//		int len = stmt.executeUpdate(sql);
//		System.out.println(len);
//		// 标准开发中SELECT不能再使用*查询
//		String sql2 = "SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM myemp";
//		ResultSet rs = stmt.executeQuery(sql2);
//		while(rs.next()) {
//			// 标准开发中使用序号，如下，而不使用字符串，例int empno = rs.getInt("empno")
//			// 取出数据时，按顺序取，且每个数据只取一次
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
//		 数据更新
		int empno = 8888;
		String ename = "Mr'Smith";
		String job = "foreign";
		int mgr = 9999;
		Date hiredate = new Date(); // 只能新建util.Date，后面需转换
		int sal = 0;
		int comm = 1000;
		int deptno = 20;
		String sql = "INSERT INTO myemp(EMPNO,ENAME,JOB,MGR,HIREDATE,SAL,COMM,DEPTNO) VALUES (?,?,?,?,?,?,?,?)"; //用问号即可，不需要自己加引号
		PreparedStatement ps1 = conn.prepareStatement(sql);
		ps1.setInt(1, empno); // 索引从1开始，不是从0开始
		ps1.setString(2, ename);
		ps1.setString(3, job);
		ps1.setInt(4, mgr);
		ps1.setDate(5, new java.sql.Date(hiredate.getTime())); // java.util.Date转至java.sql.Date
		ps1.setInt(6, sal);
		ps1.setInt(7, comm);
		ps1.setInt(8, deptno);
		int len = ps1.executeUpdate();
		System.out.println(len);
		// 数据查询
		String sql2 = "SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM myemp";
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		ResultSet rs2 = ps2.executeQuery();
		while(rs2.next()) {
			System.out.println(rs2.getInt(1) + "," + rs2.getString(2) + "," + rs2.getString(3) + "," + rs2.getInt(4) + "," + rs2.getDate(5) + "," 
					+ rs2.getInt(6) + "," + rs2.getInt(7) + "," + rs2.getInt(8)); //getDate()返回的结果不需要处理，会自动转换，能够正常输出
		}
		System.out.println("----------------------------------------------------------------");
		// 模糊查询
		String keyword = "K";
		String sql3 = "SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM myemp WHERE ename LIKE ?";
//		错误：String sql3 = "SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM myemp WHERE ename LIKE '%?%'";不能这么设置，需要把整个字符串作为一个整体设置值
		PreparedStatement ps3 = conn.prepareStatement(sql3);
		ps3.setString(1, "%" + keyword + "%");
//		ps3.setString(1, keyword);
		ResultSet rs3 = ps3.executeQuery();
		while(rs3.next()) {
			System.out.println(rs3.getInt(1) + "," + rs3.getString(2) + "," + rs3.getString(3) + "," + rs3.getInt(4) + "," + rs3.getDate(5) + "," 
					+ rs3.getInt(6) + "," + rs3.getInt(7) + "," + rs3.getInt(8));
		}
		System.out.println("----------------------------------------------------------------");
		// 分页显示
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
		// 统计数据量
		String sql5 = "SELECT COUNT(sal) FROM myemp";
		PreparedStatement ps5 = conn.prepareStatement(sql5);
		ResultSet rs5 = ps5.executeQuery();
		while(rs5.next()) {
			System.out.println(rs5.getInt(1));
		}
		// 手工处理事务
		conn.setAutoCommit(false);
		Statement stmt2 = conn.createStatement();
		try {
			stmt2.addBatch("INSERT INTO myemp(empno,ename) VALUES (9983,'张辽')");
			stmt2.addBatch("INSERT INTO myemp(empno,ename) VALUES (9982,'李典')");
			stmt2.addBatch("INSERT INTO myemp(empno,ename) VALUES (9981,'徐晃')");
			stmt2.addBatch("INSERT INTO myemp(empno,ename) VALUES (9980,'于禁')");
			stmt2.addBatch("INSERT INTO myemp(empno,ename) VALUES (9979,'张郃')");
			int result [] = stmt2.executeBatch();
			System.out.println(Arrays.toString(result));
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback(); // 可以在添加数据时在名称内加个单引号，手动创造错误观察
		}
		conn.close();
	}
}
