package chapter.twenty.jdbctemplatedemo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class JDBCTemplateMain {
    public static void main(String[] args) throws SQLException {
        DriverManagerDataSource dmds = new DriverManagerDataSource();
        dmds.setDriverClassName("org.gjt.mm.mysql.Driver"); // jar包版本要正确，否则会报错，目前用的是5.1.46，最开始用了更新的版本不行
        dmds.setUrl("jdbc:mysql://localhost:3306/mysql");
        dmds.setUsername("root");
        dmds.setPassword("root");
        Connection conn = dmds.getConnection();
        System.out.println(conn);
        JdbcTemplate jt = new JdbcTemplate(dmds); // 或 JdbcTemplate jt = new JdbcTemplate();jt.setDataSource(dmds);
        // 插入数据
        String sql = "INSERT INTO hero(name,dynasty,master) VALUES(?,?,?)";
        System.out.println("更新行数：" + jt.update(sql,"赵云","三国",1));
        // 获得ID
        KeyHolder kh = new GeneratedKeyHolder();
        final String sql2 = "INSERT INTO hero(name,dynasty,master) VALUES(?,?,?)";
        int count = jt.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,"张飞");
                ps.setString(2,"三国");
                ps.setInt(3,1);
                return ps;
            }},kh);
        System.out.println("更新行数：" + count + "当前ID：" + kh.getKey());
        // 查询
        final String sql3 = "SELECT id,name,dynasty,master FROM hero WHERE name LIKE ?";
        List<JDBCTemplateHero> li =  jt.query(sql3, new RowMapper<JDBCTemplateHero>() {
            @Override
            public JDBCTemplateHero mapRow(ResultSet resultSet, int i) throws SQLException {
                System.out.println("返回的数据行号：" + i);
                JDBCTemplateHero vo = new JDBCTemplateHero();
                vo.setId(resultSet.getInt(1));
                vo.setName(resultSet.getString(2));
                vo.setDynasty(resultSet.getString(3));
                vo.setMaster(resultSet.getInt(4));
                return vo;
            }
        },"%云%");
        System.out.println(li);
        // 查询单列
        String sql4 = "SELECT name FROM hero WHERE name LIKE ?";
        List<String> li4 = jt.queryForList(sql4,new Object[]{"%%"},String.class);
        System.out.println(li4);
        // 统计数据量
        String sql5 = "SELECT COUNT(name) FROM hero WHERE name LIKE ?";
        Integer count5 = jt.queryForObject(sql5,new Object[]{"%%"},Integer.class);
        System.out.println(count5);

        dmds.getConnection().close();
    }
}
