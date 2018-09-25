package jsonobjectdemo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonObjectDemoMain {
	public static void main(String[] args) throws IOException {
		// 一、构建json
		System.out.println("-----------String to JSONObject--------");
		String strA = "{'id':1, 'name':'aaa'}"; // 单引号可以换成\"
		JSONObject objA = JSONObject.fromObject(strA);
		System.out.println(objA);
		
		System.out.println("-----------Map to JSONObject-----------");
		Map<String, String> mapB = new HashMap<String, String>();
		mapB.put("id", "2");
		mapB.put("name", "bbb");
		JSONObject objB = JSONObject.fromObject(mapB);
		System.out.println(objB);
		
		System.out.println("-----------List to JSONArray-----------");
		List<String> liG = new ArrayList<String>();
		liG.add("{'id':7, 'name':'ggg'}");
		liG.add("{'id':8, 'name':'hhh'}");
		JSONArray objG = JSONArray.fromObject(liG); // List转JSON结构对象，不能用JSONObject
		System.out.println(objG);
		
		System.out.println("-----------new JSONObject--------------");
		JSONObject objC = new JSONObject();
		objC.put("id", 3); // put()第一个参数必须是字符串，第二个参数可以是任意类型，但是如double、int等类型到json中实际上都是用Number类型保存
		objC.put("name", "ccc");
		System.out.println(objC);
		
		System.out.println("-----------JavaBean to JSONObject------");
		JsonObjectDemoBean bean = new JsonObjectDemoBean(4, "ddd");
		JSONObject objD = JSONObject.fromObject(bean); // 遗留：为什么这里会打印一串debug信息
		System.out.println(objD);
		
		
		System.out.println("-----------Json file to JSONObject-----");
		File jsonFileE = new File("src/main/resources/jsonObjectDemo/jsonObjectDemo.json");
		String fileContentE = FileUtils.readFileToString(jsonFileE);
		JSONObject objE = JSONObject.fromObject(fileContentE);
		System.out.println(objE); // {"hobbies":["hiking","swimming"],"sex":"male","name":"John","is_student":true,"age":22}
		
		// 二、解析json，使用getXxx()或optXxx()
		System.out.println("-----------JSONObject to java object");
		String sex = objE.getString("sex");
		System.out.println("sex is " + sex);
		boolean isStudent = objE.getBoolean("is_student");
		System.out.println("isStudent is " + isStudent);
		int age = objE.getInt("age");
		System.out.println("age is " + age);
		System.out.print("hobbies is ");
		// 遗留：直接用List接收也可以，这样有什么差别吗？
//		List<String> hobbies = objE.getJSONArray("hobbies");
//		Iterator iter = hobbies.iterator();
//		while(iter.hasNext()) {
//			System.out.print(iter.next() + " ");
//		}
		JSONArray array = objE.getJSONArray("hobbies");
		for (int i = 0; i < array.size(); i++) {
            String str = (String) array.get(i);
            System.out.print(str + " ");
        }
		System.out.println();
		
		// getXxx 和 optXxx
		System.out.println("------ --optXxx get no exist key will return null------");
		System.out.println("sex is " + objE.optString("sex"));
		System.out.println("sexx is " + objE.optString("sexx")); // sexx is 
		System.out.println("---------getXxx get no exist key raise exception--------");
		System.out.println("sex is " + objE.getString("sex"));
//		System.out.println("sexx is " + objE.getString("sexx")); // net.sf.json.JSONException: JSONObject["sexx"] not found.
		
		System.out.println("----------JSONObject to JavaBean------------");
		String strF = "{\"id\":6,\"name\":\"fff\"}";
		JSONObject objF = JSONObject.fromObject(strF);
		JsonObjectDemoBean beanF = (JsonObjectDemoBean) JSONObject.toBean(objF, JsonObjectDemoBean.class); // 遗留：有点奇怪，为什么方法参数已经指定了类，得到的结果还要强制转型？
		System.out.println(beanF);
	}
}
