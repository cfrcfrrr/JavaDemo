package chapter.twenty.annotationdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/annotationContext.xml");
//		ʹ��ע�⣬��Ҫ���������Ĭ������
//		AnnotationIService ser = ctx.getBean("service",AnnotationIService.class);
		AnnotationIService ser = ctx.getBean("annotationServiceImpl",AnnotationIService.class);
		ser.print();
	}
}
