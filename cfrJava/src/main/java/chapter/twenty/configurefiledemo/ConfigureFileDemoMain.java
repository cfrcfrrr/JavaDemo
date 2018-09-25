package chapter.twenty.configurefiledemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Spring的核心里面就是利用字符串解决一切的程序开发问题
 * @author Administrator
 *
 */
public class ConfigureFileDemoMain {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/configureFileDemoContext.xml");
//		String str = ctx.getBean("str", String.class);
//		System.out.println(str);
		ConfigureFileDemoBean bean = ctx.getBean("bean", ConfigureFileDemoBean.class);
		System.out.println(bean.getInfo()); // {#str.subString(5, 10) + 'cfr'}
	}
}
