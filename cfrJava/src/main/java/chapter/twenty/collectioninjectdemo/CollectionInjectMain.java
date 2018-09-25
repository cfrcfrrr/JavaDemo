package chapter.twenty.collectioninjectdemo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CollectionInjectMain {

	@SuppressWarnings({ "resource", "rawtypes" })
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/collectionInjectContext.xml");
		CollectionInjectClass col = ctx.getBean("col",CollectionInjectClass.class);
		List<Integer> li = col.getLi();
		
		Iterator ili = li.iterator();
		while(ili.hasNext()) {
			System.out.println(ili.next());
		}
		List<String> ls = col.getLs();
		Iterator ils = ls.iterator();
		while(ils.hasNext()) {
			System.out.println(ils.next());
		}
		Set<Double> sd = col.getSd();
		Iterator isd = sd.iterator();
		while(isd.hasNext()) {
			System.out.println(isd.next());
		}
		Map<Integer,String> mis = col.getMis();
		Set<Map.Entry<Integer, String>> smis = mis.entrySet();
		Iterator<Map.Entry<Integer, String>> imis = smis.iterator();
		while(imis.hasNext()) {
			Map.Entry<Integer, String> temp = imis.next();
			System.out.println(temp.getKey() + "-" + temp.getValue());
		}
		
		Properties pro = col.getPro();
		System.out.println(pro);
	}
}
