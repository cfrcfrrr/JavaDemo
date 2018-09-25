package chapter.twenty.c3p0demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class C3P0Main {
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("c3P0Context.xml");
        C3P0IDAO dao = ctx.getBean("c3P0DAOImpl", C3P0IDAO.class);
        C3P0Hero vo = new C3P0Hero();
        vo.setName("黄忠");
        vo.setDynasty("三国");
        vo.setMaster(1);
        System.out.println(dao.doCreate(vo));
        
    }
}
