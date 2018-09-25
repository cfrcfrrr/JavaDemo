package chapter.twenty.autowiredemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowireMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/autowireContext.xml");
		System.out.println(ctx.getBean("empa",AutowireEmp.class).getDept().getDeptno());
		System.out.println(ctx.getBean("empb",AutowireEmp.class).getDept().getDeptno());
	}
}
