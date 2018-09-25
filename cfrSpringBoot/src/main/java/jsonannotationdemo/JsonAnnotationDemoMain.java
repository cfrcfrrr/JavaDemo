package jsonannotationdemo;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonAnnotationDemoMain {
	public static void main(String[] args) throws IOException {
		ObjectMapper om = new ObjectMapper();
		om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); // 设置当没有可序列化的属性时也不报异常
//		om.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true); // 使用MapperFeature配置也可达到下述json annotation的一些配置功能
		om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,true); // 设置序列化时时间格式化输出，否则会输出一串数字
		// 遗留：这些对反序列化没影响吗？
		// 遗留：配置@JsonValue的情况下，怎么反序列化回对象？
		// 总结：@JsonValue > @JsonIgnore、@JsonIgnoreProperties > @JsonProperty > @JsonAutoDetect
		/* 一、@JsonAutoDetect：注解在类上，配置序列化的自动检测
		 * 	fieldVisibility属性：配置类属性的自动检测范围
		 * 		Visibility.ANY:任何级别的字段都可以自动识别
		 *		Visibility.NONE:所有字段都不可以自动识别   
		 *		Visibility.NON_PRIVATE:非private修饰的字段可以自动识别  
		 *		Visibility.PROTECTED_AND_PUBLIC:被protected和public修饰的字段可以被自动识别   
		 *		Visibility.PUBLIC_ONLY:默认值，只有被public修饰的字段才可以被自动识别     
		 *		Visibility.DEFAULT:同PUBLIC_ONLY
		 *	 	示例：
		 * 			1、没有配置@JsonAutoDetect + 没有给出getter -> 序列化为空（输出{}）
		 * 			2、没有配置@JsonAutoDetect + 给出getter -> 可序列化（输出{"id":1,"name":"aaa"}）
		 * 			3、配置@JsonAutoDetect + 不配置fieldVisibility属性(相当于配置Visibility.PUBLIC_ONLY) + 没有给出getter -> 序列化为空
		 * 			4、配置@JsonAutoDetect(fieldVisibility->Visibility.ANY) + 没有给出getter -> 可序列化
		 * 	getterVisibility属性：配置getter方法的自动检测范围
		 * 	setterVisibility属性：配置setter方法的自动检测范围
		 * 	isGetterVisibility属性：配置is方法的自动检测范围
		 * 	creatorVisibility配置：配置构造器方法的自动检测范围
		 * 二、@JsonIgnore：注解在属性、setter或getter方法上，表示该属性不被序列化
		 * 	示例：
		 * 		1、private属性、public setter、public getter + 不配置@JsonIgnore -> 被序列化（输出{"id":1,"name":"aaa","birthday":1528905220127}）
		 * 		2、private属性、public setter、public getter + 任一上配置@JsonIgonre -> 不被序列化（输出{"id":1,"name":"aaa"} ）
		 * 三、@JsonIgnoreProperties：注解在类上，相当于@JsonIgnore的批量配置，注意如果属性用@JsonProperty设置了别名，则需要配置其别名才能屏蔽，配置其原名不能屏蔽
		 * 	示例：
		 * 		1、@JsonIgnoreProperties({ "id", "name" }) + @JsonProperty(value = "stu_id") -> 输出{"stu_id":1}
		 * 		2、@JsonIgnoreProperties({ "stu_id", "name" }) + @JsonProperty(value = "stu_id") -> 输出{}
		 * 四、@JsonProperty：注解在属性上，表示此属性要被序列化，可设置序列化时的别名（设置别名是主要用途）。别名也影响反序列化，反序列化时key需要为别名，设置原名会失败
		 * 	value属性：
		 * 		设置属性序列化时的别名。
		 * 	示例：
		 * 		1、private id + 无getter + 无@JsonAutoDetect注解 + 无@JsonProperty -> id属性不会被序列化
		 * 		2、private id + 无getter + 无@JsonAutoDetect注解 + 在setter上注解@JsonProperty -> id属性不会被序列化（注解在setter上没有用）
		 * 		3、private id + 无getter + 无@JsonAutoDetect注解 + 在id属性上注解@JsonProperty -> id属性被序列化
		 * 		4、private id + 无getter + 无@JsonAutoDetect注解 + 在id属性上注解@JsonProperty(value->"stu_id") -> id属性被序列化，且被重命名为student_id（输出：{"stu_id":1}）
		 * 		5、private id + 无getter + @JsonAutoDetect(fieldVisibility=Visibility.PUBLIC_ONLY) + @JsonProperty -> id属性被序列化（@JsonProperty > @JsonAutoDetect）
		 * 五、@JsonValue：注解在方法上，设置序列化的内容
		 * 	示例：
		 * 		1、没有@JsonValue注解 -> 根据@JsonProperty、@JsonAutoDetect、@JsonIgnore的配置将属性名及值序列，可正常反序列化回对象
		 * 		2、配置@JsonValue注解在toString()方法上 -> 覆盖@JsonProperty、@JsonAutoDetect、@JsonIgnore的配置，以toString()方法返回值为序列化结果（输出"JsonAnnotationDemoBean [id=1, name=aaa, birthday=Thu Jun 14 00:53:51 CST 2018]"），但不能反序列化回对象
		 * 		3、配置@JsonValue注解在自定义方法上 + 方法返回值为String、List等 -> 以自定义方法返回值为序列化内容（例如输出为["aaa","bbb"])
		 * 		4、配置@JsonValue注解在自定义方法上 + 方法无返回值 -> 根据@JsonProperty、@JsonAutoDetect、@JsonIgnore的配置将属性名及值序列
		 * 六、@JsonPropertyOrder：定义属性序列化后的顺序
		 * 	举例：
		 * 		1、name属性配置在id属性前 + 不注解 -> 生成的序列化为 {"@class":"JsonAnnotationDemoBean","name":"aaa","id":1}
		 * 		2、name属性配置在id属性前 + 注解 -> 生成的序列化为 {"@class":"JsonAnnotationDemoBean","id":1,"name":"aaa"}
		 * 	遗留：但如果id属性重命名为stu_id了，则不管注解不注解，name属性都在stu_id之前；如果有List类型，就算名称是a，也会排在最后；而子类就算name配置在id属性之前且不注解，序列化也是id在name之前；这里面的规则貌似有点乱
		 */
		JsonAnnotationDemoBean beanA = new JsonAnnotationDemoBean(1, "aaa", new Date());
		String strA = om.writeValueAsString(beanA);
		System.out.println(strA);
//		strA = "{\"name\":\"aaa\",\"id\":1}"; // 设置原名序列化会失败，需要设置别名
		JsonAnnotationDemoBean beanADeserialize = om.readValue(strA, JsonAnnotationDemoBean.class); // 遗留：没有定义无参构造会失败，反序列化会报错，为什么？
		System.out.println(beanADeserialize.toString());

		/*
		 * 七、@JsonCreator：用于指定反序列化时调用此构造方法，不使用标准的构造方法格式
		 * 	用途一：自定义传入的参数名
		 *  用途二：授权式构造器
		 * 	用途三：自定义其他方法，如一个静态工厂方法上，，可用于创建单例等，例如
		 * 		public enum Algorithm {
		 * 			LSTAR("LSTAR"),
		 * 			TTT("TTT");
		 * 			private String value;
		 * 			Algotirhm(String value){
		 * 				this.value = value;
		 * 			}
		 * 			@JsonCreator
		 * 			public static Algorithm formValue(String text) {
		 * 				for(Algorithm b : Algorithm.values(){
		 * 					if(String.valueOf(b.value).equals(text){
		 * 						return b;
		 * 					}
		 * 				}
		 * 				return null;
		 * 			}
		 * 		}
		 */
//		String strC = "{\"student_id\":3, \"name\":\"ccc\"}"; // 但使用stu_id也不会报错，应该是走了默认的反序列化方法
//		JsonAnnotationDemoBean beanC = om.readValue(strC, JsonAnnotationDemoBean.class); // 遗留：定义了map的方法之后，就不会走重命名参数的方法，为什么？调用的原理是什么？顺序是什么？然后定义了静态方法之后，导致这里报错了
//		System.out.println(beanC);
//		
//		String strD = "{\"map_id\":4, \"name\":\"ddd\"}";
//		JsonAnnotationDemoBean beanD = om.readValue(strD, JsonAnnotationDemoBean.class); // 遗留：传入的是String，是怎么找到Map的构造方法的？
//		System.out.println(beanD);
//		
//		String strE = "{\"ins_id\":5, \"name\":\"eee\"}";
//		JsonAnnotationDemoBean beanE = om.readValue(strE, JsonAnnotationDemoBean.class);
//		System.out.println(beanE);
//		
		
		/*
		 * 八、@JsonTypeInfo、@JsonSubTypes、@JsonTypeInfo
		 * 	@JsonTypeInfo：注解在父类上，定义生成识别码
		 * 		use属性：定义使用什么作为识别码
		 * 			Id.CLASS：使用完整类名，反序列化时不需要@JsonSubTypes
		 * 			例：
		 * 				1、定义@JsonTypeInfo(use=Id.CLASS, include=As.PROPERTY, property="@class")，则
		 * 					父类对象序列化结果为：{"@class":"jsonannotationdemo.JsonAnnotationDemoBean","name":"aaa","stu_id":1}
		 * 					父类对象反序列化结果为：JsonAnnotationDemoBean [id=1, name=aaa, birthday=null]
		 * 					子类对象序列化结果为：{"@class":"jsonannotationdemo.JsonAnnotationDemoSubBeanA","id":6,"name":"subAfff"}
		 * 					子类对象反序列化结果为：JsonAnnotationDemoSubBeanA [id=6, name=subAfff]
		 * 			Id.MINIMAL：使用类名，生成的序列化结果中识别码为"@class":"JsonAnnotationDemoSubBeanA"，反序列化时也不需要@JsonSubTypes
		 * 			Id.NAME：使用子类的@JsonTypeName注解的value值，生成的序列化结果中识别码为"@class":"subA"，若子类未注解，则使用子类的类名。反序列化时需要@JsonSubTypes注解，该注解的子类的value值需要和生成的识别码的值一致。
		 * 			Id.CUSTOM：遗留：？
		 * 			Id.NONE：遗留：？
		 * 		include属性：定义生成识别码相对于对象属性的位置，反序列化时与此无关，都能识别
		 * 			As.PROPERTY：作为兄弟属性，例 {"@class":"jsonannotationdemo.JsonAnnotationDemoSubBeanA","id":6,"name":"subAfff"}
		 * 			As.EXTERNAL_PROPERTY：遗留：貌似和PROPERTY一样？，例 {"@class":"jsonannotationdemo.JsonAnnotationDemoSubBeanA","id":6,"name":"subAfff"}
		 * 			As.WRAPPER_OBJECT：作为一个包装的对象，例 {"jsonannotationdemo.JsonAnnotationDemoSubBeanA":{"id":6,"name":"subAfff"}}
		 * 			As.WRAPPER_ARRAY：作为一个包装的数组，例 ["jsonannotationdemo.JsonAnnotationDemoSubBeanA",{"id":6,"name":"subAfff"}]
		 * 		property属性：定义序列化和反序列时的识别码的key名称
		 * @JsonSubTypes：注解在父类上，用于定义子类和自定义的识别码对应关系
		 * @JsonTypeIngo：注解在子类上，用于自定义子类识别码
		 */
//		String strF = "{\"stu_id\":6, \"name\":\"fff\"}";
		JsonAnnotationDemoSubBeanA subAF = new JsonAnnotationDemoSubBeanA(6, "subAfff");
		String strF = om.writeValueAsString(subAF); // 遗留：怎么找到父类的识别码设置
//		om.writeValue(new File("JsonAnnotationDemo.json"), subAF);
		System.out.println(strF);
		JsonAnnotationDemoBean subAFDeserialize = om.readValue(strF, JsonAnnotationDemoBean.class); // 遗留：标准使用是这样吗？用父类对象来接收？
		System.out.println(subAFDeserialize);
		
		String strG = " {\"@class\":\"subB\",\"id\":7,\"name\":\"subBggg\"}";
		JsonAnnotationDemoBean subBG = om.readValue(strG, JsonAnnotationDemoBean.class);
		System.out.println(subBG);
		
	}
}
