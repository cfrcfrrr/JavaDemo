package chapter.twenty.springjdbctemplatedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SpringJDBCTemplateHeroDAOImpl implements SpringJDBCTemplateIHeroDAO {
    private JdbcTemplate jt;

    @Autowired // 自动根据匹配类型注入数据
    public void setJt(JdbcTemplate jt) {
        this.jt = jt;
    }

    @Override
    public boolean doCreate(SpringJDBCTemplateHero vo) {
        String sql = "INSERT INTO hero(name,dynasty,master) VALUES(?,?,?)";
        int count = jt.update(sql,vo.getName(),vo.getDynasty(),vo.getMaster());
        return count > 0;
    }
}
