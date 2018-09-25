package objectmapperdemo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ObjectMapperDemoMain {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// 反序列化
		System.out.println("--------json string to java bean------------");
		// Boolean设置true、\"true\"、false、\"false\"都能识别；
		// Long型不用给出1000L这样的结构，直接给出一个数字即可，在json中数字只有NUMBER型；
		// 数组用[]包含；
		// 时间需要给出如下结构"yyyy-MM-dd'T'HH:mm:ss.SSSZ"、"yyyy-MM-dd'T'HH:mm:ss.SSS"、"EEE, dd MMM yyyy HH:mm:ss zzz"、"yyyy-MM-dd"，或者给出一个合理的数字
		String jsonStr = "{\"id\":1, \"name\":\"aaa\", \"isStudent\":false, \"birthday\":\"2018-12-12\", \"teachers\":[\"张三\", \"李四\"], \"bookSize\":2147483648}"; // 遗留：这里必须要用\"，用'会报错，而JSONObject就可以，为什么？
		ObjectMapperDemoBean bean = new ObjectMapper().readValue(jsonStr, ObjectMapperDemoBean.class); // readValue在java bean类任一属性没有提供setter方法时会报错
		System.out.println(bean);
		
		System.out.println("--------json file to java bean--------------");
		ObjectMapperDemoBean beanC = new ObjectMapper().readValue(new File("src/main/resources/objectMapperDemo/objectMapperDemo.json"), ObjectMapperDemoBean.class);
		System.out.println(beanC);
		
		// 序列化
		System.out.println("--------java bean to json string------------");
		ObjectMapperDemoBean beanB = new ObjectMapperDemoBean();
		beanB.setId(2);
		beanB.setName("bbb");
		beanB.setIsStudent(true);
		beanB.setBirthday(new Date());
		List<String> liB = new ArrayList<String>();
		liB.add("赵五");
		liB.add("钱六");
		beanB.setTeachers(liB);
		beanB.setBookSize(1000L);
		ObjectMapper mapperB = new ObjectMapper();
		mapperB.configure(SerializationFeature.INDENT_OUTPUT, true); // 可以不设置本行。SerializationFeature.INDENT_OUTPUT表示是否缩放排列输出，遗留：但是没看出设置和不设置有什么区别
		String jsonStrB = mapperB.writeValueAsString(beanB); // writeValue在java bean类全部没有提供getter方法时才报错，部分没有提供时，没有提供的不显示（不是显示空）
		System.out.println(jsonStrB);
		// 输出结果：
//		{
//			  "id" : 2,
//			  "name" : "bbb",
//			  "isStudent" : true,
//			  "birthday" : 1528893621576,
//			  "teachers" : [ "赵五", "钱六" ],
//			  "bookSize" : 1000
//		}
	}
}
