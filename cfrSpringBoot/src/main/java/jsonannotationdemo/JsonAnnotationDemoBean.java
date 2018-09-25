package jsonannotationdemo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonAutoDetect(fieldVisibility = Visibility.ANY) // 设置序列化的范围
@JsonPropertyOrder(alphabetic=true) // 设置序列化时属性排序
//@JsonIgnoreProperties({ "id", "name" }) // 相当于@JsonIgnore的批量配置
@JsonTypeInfo(use=Id.NAME, include=As.PROPERTY, property="@class") // 设置序列化的识别码
@JsonSubTypes( // 设置子类和自定义识别码值的对应关系
	{
		@Type(value=JsonAnnotationDemoSubBeanA.class, name="subA"),
		@Type(value=JsonAnnotationDemoSubBeanB.class, name="subB")
	}
)
public class JsonAnnotationDemoBean {
	private String name;
	@JsonProperty(value = "stu_id") // 表示要被序列化，且可以起别名
	private Integer id;
	@JsonIgnore // 表示此属性不被序列化
	private Date birthday;

	public JsonAnnotationDemoBean() { // 反序列化默认调用无参构造
		System.out.println("---into JsonAnnotationDemoBean()---");
	}

	public JsonAnnotationDemoBean(Integer id, String name, Date birthday) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
	}
	
//	private JsonAnnotationDemoBean(Integer id, String name) {
//		this.id = id;
//		this.name = name;
//	}
	
	// @JsonValue
	@Override
	public String toString() {
		return "JsonAnnotationDemoBean [id=" + id + ", name=" + name + ", birthday=" + birthday + "]";
	}
	
	// 自定义序列化方法应该意义不大
	// @JsonValue
	// public List<String> myJsonValue() {
	// List<String> li = new ArrayList<String>();
	// li.add("aaa");
	// li.add("bbb");
	// return li;
	// }
	
//	@JsonCreator // @JsonCreator使用方法一：自定义传入的参数
//	public JsonAnnotationDemoBean(@JsonProperty("student_id") Integer id) { // 这里只需要给出起别名的参数即可，原名的参数不需给出，会自动识别，给出反而会报错。遗留：反序列化的原理是什么？为什么给出反而要报错？
//		System.out.println("---Into @JsonCreator method1 (rename parameter)---");
//		this.id = id;
//	}
//	
//	@JsonCreator // @JsonCreator使用方法二：授权式构造器
//	public JsonAnnotationDemoBean(Map<String, Object> map) {
//		System.out.println("---Into @JsonCreator method2 (map parameter)---");
//		this.id = (Integer)map.get("map_id");
//		this.name = (String)map.get("name");
//		this.birthday = (Date)map.get("birthday");
//	}
//
//	@JsonCreator // @JsonCreator使用方法三：静态方法
//	// 遗留：方法必须声明为静态，为什么？
//    public static JsonAnnotationDemoBean getInstance(@JsonProperty("ins_id") Integer ins_id, @JsonProperty("name") String name) { // 参数必须要用@JsonProperty声明对应jsonStr的key，就算完全一样也要声明，遗留：为什么？
//		System.out.println("---Into @JsonCreator method3 (getInstance)---");
//		return new JsonAnnotationDemoBean(ins_id, name); // 调用了一个private的构造方法
//    }
}
