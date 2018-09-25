package chapter.twenty.pnamespacedemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PNamespaceMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/pNamespaceContext.xml");
		System.out.println(ctx.getBean("emp",PNamespaceEmp.class).getDept().getDeptno());
	}

}
