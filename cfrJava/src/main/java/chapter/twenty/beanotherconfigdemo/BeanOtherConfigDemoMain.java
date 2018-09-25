package chapter.twenty.beanotherconfigdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanOtherConfigDemoMain {

	public static void main(String[] args) {
		System.out.println("-----------start applicationContext init");
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/beanOtherConfigDemoContext.xml");
		System.out.println("-----------applicationContext init over");
		System.out.println("-----------start getBean");
		BeanOtherConfigDemoBean bean = ctx.getBean("beanOtherConfigDemoBean",BeanOtherConfigDemoBean.class); // console：bean create，bean的初始化被延迟到这里，初始化方法调用
		System.out.println("-----------getBean Over");
		System.out.println("-----------start destroy applicaionContext");
		ctx.registerShutdownHook();
		System.out.println("-----------applicaionContext destroy over");
		// Console：bean destroy，bean的销毁方法调用
	}

}
