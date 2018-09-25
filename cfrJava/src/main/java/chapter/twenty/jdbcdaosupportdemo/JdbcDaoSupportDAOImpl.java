package chapter.twenty.jdbcdaosupportdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

@Component
public class JdbcDaoSupportDAOImpl extends JdbcDaoSupport implements JdbcDaoSupportIDAO {
    @Autowired
    public JdbcDaoSupportDAOImpl(JdbcTemplate jt) {
        super.setJdbcTemplate(jt);
    }

    @Override
    public boolean doCreate(JdbcDaoSupportHero vo) {
        String sql = "INSERT INTO hero(name,dynasty,master) VALUES(?,?,?)";
        int count = super.getJdbcTemplate().update(sql,vo.getName(),vo.getDynasty(),vo.getMaster());
        return count > 0;
    }
}
