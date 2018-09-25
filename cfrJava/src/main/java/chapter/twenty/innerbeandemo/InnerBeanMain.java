package chapter.twenty.innerbeandemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InnerBeanMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/innerBeanContext.xml");
		System.out.println(ctx.getBean("emp",InnerBeanEmp.class).getDept().getDeptno());
	}

}
