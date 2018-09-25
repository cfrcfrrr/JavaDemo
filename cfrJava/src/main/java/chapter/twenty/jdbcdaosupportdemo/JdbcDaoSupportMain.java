package chapter.twenty.jdbcdaosupportdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JdbcDaoSupportMain {
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbcDaoSupportContext.xml");
        JdbcDaoSupportIDAO dao = ctx.getBean("jdbcDaoSupportDAOImpl", JdbcDaoSupportDAOImpl.class);
        JdbcDaoSupportHero vo = new JdbcDaoSupportHero();
        vo.setName("1223");
        vo.setDynasty("11");
        vo.setMaster(null);
        System.out.println(dao.doCreate(vo));
    }
}
