package foreachdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {
	public static void main(String[] args) {
		String [] strArray = new String[] {"arrayA", "arrayB", "arrayC"};
		for(String str: strArray) {
			System.out.println(str);
		}
		
		List<String> list = new ArrayList<>();
		list.add("listA");
		list.add("listB");
		list.add("listC");
		for(String str: list) {
			System.out.println(str);
		}
		
		Set<String> set = new HashSet<>();
		set.add("setA");
		set.add("setB");
		set.add("setC");
		for(String str: set) {
			System.out.println(str);
		}
		
		Map<String, String> map = new HashMap<>();
		map.put("mapA", "AAA");
		map.put("mapB", "BBB");
		map.put("mapC", "CCC");
		for(Map.Entry<String, String> entry: map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}
