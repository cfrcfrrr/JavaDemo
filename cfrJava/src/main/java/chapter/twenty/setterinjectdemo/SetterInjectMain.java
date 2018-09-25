package chapter.twenty.setterinjectdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterInjectMain {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/setterInjectContext.xml");
		SetterInjectDept dept = ctx.getBean("dept",SetterInjectDept.class);
		System.out.println(dept.getDeptno());
		System.out.println(dept.getDname());
		System.out.println(dept.isFlag());
		SetterInjectEmp emp = ctx.getBean("emp",SetterInjectEmp.class);
		System.out.println(emp.getDept().getDname());
	}
}
