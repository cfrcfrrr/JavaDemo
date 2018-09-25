package chapter.twenty.aopdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPMain {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/aopContext.xml");
		AOPIService ims = ctx.getBean("AOPServiceImpl", AOPIService.class); // ������Ϊ�����д��ĸ��ͷʱ���Զ����ɵ����������ĸû�б�Сд����������һ���Զ����ɵ��������
//		AOPMember vo = ctx.getBean("AOPMember", AOPMember.class); // ������Ϊʲôvo��ʹ��annotation�Զ�����bean
		AOPVO vo = new AOPVO();
		vo.setMid("11");
		vo.setName("aaa");
		System.out.println(ims.insert(vo));
	}
}
