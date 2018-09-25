package chapter.twenty.c3p0demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class C3P0DAOImpl implements C3P0IDAO {
    private JdbcTemplate jt;

    @Autowired
    public void setJt(JdbcTemplate jt) {
        this.jt = jt;
    }

    @Override
    public boolean doCreate(C3P0Hero vo) {
        String sql = "INSERT INTO hero(name,dynasty,master) VALUES(?,?,?)";
        int count = jt.update(sql,vo.getName(),vo.getDynasty(),vo.getMaster());
        return count > 0;
    }
}
