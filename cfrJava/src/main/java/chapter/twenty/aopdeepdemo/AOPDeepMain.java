package chapter.twenty.aopdeepdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPDeepMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/aopDeepContext.xml");
		AOPDeepIService dis = ctx.getBean("AOPDeepServiceImpl",AOPDeepIService.class);
		AOPDeepVO vo2 = new AOPDeepVO();
		vo2.setMid("11");
		vo2.setName("aaa");
		System.out.println("main:" + dis.insert(vo2));
	}
}
