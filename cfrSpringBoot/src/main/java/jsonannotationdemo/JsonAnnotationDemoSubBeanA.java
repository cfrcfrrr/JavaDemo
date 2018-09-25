package jsonannotationdemo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonTypeName(value="subA") // 子类自定义识别码值
public class JsonAnnotationDemoSubBeanA extends JsonAnnotationDemoBean {
	private String name;
	private Integer id;
	public JsonAnnotationDemoSubBeanA() {}
	public JsonAnnotationDemoSubBeanA(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "JsonAnnotationDemoSubBeanA [id=" + id + ", name=" + name + "]";
	}
}
