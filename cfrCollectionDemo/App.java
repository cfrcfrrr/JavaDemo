package collectiondemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

/**
 * 类集：动态对象数组
 * 		与链表类似的工具类：Vector
 * 		几个核心接口：Collection、List、Set、Map、Iterator、Enumeration
 * @author cfr
 *
 */
public class App {
	public static void main(String[] args) throws FileNotFoundException, IOException {
//		List
		System.out.println("---List---");
		List<String> list = new ArrayList<>();
		list.add("aaa");
		list.add("bbb");
		list.add("bbb");
		for(int i = 0; i < list.size(); i ++) {
			System.out.println(list.get(i));
		}
//		Iterator用法与Set类似
//		ListIterator可双向迭代
		ListIterator<String> listIterator = list.listIterator();
		System.out.println("---listIterator next---");
		while(listIterator.hasNext()) {
			System.out.println(listIterator.next());
		}
		//	但由后向前输出之前，必须先发生由前向后输出，所以没什么用
		System.out.println("---listIterator previous---");
		while(listIterator.hasPrevious()) {
			System.out.println(listIterator.previous());
		}
		
//		Set
		System.out.println("---Set---");
		Set<String> set = new HashSet<String>();
		set.add("aaa");
		set.add("bbb");
		set.add("bbb");
		System.out.println(set);
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
//		Vector、Enumberation
		System.out.println("---Vector、Enumberation---");
		Vector<String> vector = new Vector<>();
		vector.addElement("aaa");
		vector.addElement("bbb");
		vector.addElement("bbb");
		Enumeration<String> enumeration = vector.elements();
		while(enumeration.hasMoreElements()) {
			System.out.println(enumeration.nextElement());
		}
		
//		Map
		System.out.println("---Map---");
		Map<String, Integer> map = new HashMap<>();
		map.put("一", 1);
		map.put("二", null);
		map.put(null, 0);
		System.out.println(map.get("一"));
		System.out.println(map.get("二")); // null
		System.out.println(map.get("六")); // null, key不存在返回null
		System.out.println(map.get(null)); // 0 
		map.put(null, null);
		System.out.println(map.get(null)); // null
		
		Set<Map.Entry<String, Integer>> mapEntrySet = map.entrySet();
		Iterator<Map.Entry<String, Integer>> mapIterator = mapEntrySet.iterator();
		while(mapIterator.hasNext()) {
			Map.Entry<String, Integer> mapEntry = mapIterator.next();
			System.out.println(mapEntry.getKey() + "-" + mapEntry.getValue());
		}
		
//		Stack
		System.out.println("---stack---");
		Stack<String> stack = new Stack<>();
		stack.push("aaa");
		stack.push("bbb");
		stack.push("ccc");
		System.out.println(stack.pop()); // ccc
		System.out.println(stack.pop()); // bbb
		System.out.println(stack.pop()); // aaa
//		System.out.println(stack.pop()); // 报错
		
//		Properties
		System.out.println("---Properties---");
		Properties properties = new Properties();
		properties.setProperty("info", "你好");
		System.out.println(properties.getProperty("info"));
		File file = new File("E:" + File.separator + "collectionDemo.properties");
//		遗留问题；怎么使用resources下的文件？
//		File file = new File("classpath:collectionDemo.properties");
//		遗留问题：怎么让存储时是追加，而不是覆盖？
		properties.store(new FileOutputStream(file), "collection demo");
		Properties loadProperties = new Properties();
		loadProperties.load(new FileInputStream(file));
		System.out.println(loadProperties.getProperty("info"));
//		遗留问题：怎么让文件中原本就存在的数据能取出到？
		System.out.println(loadProperties.getProperty("info1"));
		System.out.println(loadProperties.getProperty("info2"));
		
//		工具类
		System.out.println("---Collections---");
		List<String> list2 = new ArrayList<>();
		Collections.addAll(list2, "ddd", "eee");
		Collections.reverse(list2);
		System.out.println(list2); // [eee, ddd]
		
	}
}
