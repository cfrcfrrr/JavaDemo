package chapter.twenty.helloworlddemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/helloworldContext.xml"); // 在这一步已经实例化完了。ApplicationContext类：应用上下文，xml文件中定义的信息需要依靠此类获取
		HelloWorld hw = ctx.getBean("helloWorld",HelloWorld.class); // 返回指定名称的bean对象，并设置泛型为指定的bean类型，避免向下转型
		System.out.println(hw.sayHi());
	}
}