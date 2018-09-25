package chapter.twenty.springjdbctemplatedemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJDBCTemplateMain {
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("springJDBCTemplateContext.xml");
        SpringJDBCTemplateIHeroDAO dao = ctx.getBean("springJDBCTemplateHeroDAOImpl", SpringJDBCTemplateIHeroDAO.class);
        SpringJDBCTemplateHero vo = new SpringJDBCTemplateHero();
//        vo.setId(5); // 设置自动增长列的值没有作用，实际添加进去还是会为自动增长的值，可以直接不设置
        vo.setName("马超");
        vo.setDynasty("三国");
        vo.setMaster(1);
        System.out.println(dao.doCreate(vo));
    }
}
