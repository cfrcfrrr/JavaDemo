package chapter.twenty.aopannotationdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPAnnotationMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/aopAnnotationContext.xml");
		AOPAnnotationIService dis = ctx.getBean("AOPAnnotationServiceImpl",AOPAnnotationIService.class);
		AOPAnnotationVO vo = new AOPAnnotationVO();
		vo.setMid("11");
		vo.setName("aaa");
		System.out.println("main:" + dis.insert(vo));
	}
}
