package junit;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import entity.HisTest;
import entity.TmsTest;
import service.BaseService;

public class TestService {
	private BaseService service;
	TmsTest tmsTest;
	HisTest hisTest;
	
	@Before
	public void init(){
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		BeanFactory factory = (BeanFactory) context;
		service = factory.getBean("BaseService",BaseService.class);
		
		tmsTest = new TmsTest();
		tmsTest.setName("张三");
		hisTest = new HisTest();
		hisTest.setName("李四李四李四李四李四李四李四李四李四李四");
	}

	@Test
	public void testDataSource() {
		try {
			@SuppressWarnings("unchecked")
			List<TmsTest> tmsList = service.findTms();
			System.out.println("tms============" + tmsList.get(0).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			@SuppressWarnings("unchecked")
			List<HisTest> hisList = service.findHis();
			System.out.println("his=================" + hisList.get(0).getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			service.save(tmsTest, hisTest);
		} catch (Exception e) {
			System.out.println("保存操作失败,事务整体回滚。");
		}
		
	}
}
