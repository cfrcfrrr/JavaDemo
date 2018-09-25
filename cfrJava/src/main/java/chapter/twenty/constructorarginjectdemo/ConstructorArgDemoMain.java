package chapter.twenty.constructorarginjectdemo;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConstructorArgDemoMain {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/constructorArgInjectDemoContext.xml");
		ConstructorArgInjectDemoBean bean = ctx.getBean("constructorArgInjectDemoBean", ConstructorArgInjectDemoBean.class);
		System.out.println(bean);
	}
}