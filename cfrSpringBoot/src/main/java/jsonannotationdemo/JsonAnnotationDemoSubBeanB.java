package jsonannotationdemo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonTypeName(value="subB")
public class JsonAnnotationDemoSubBeanB extends JsonAnnotationDemoBean {
	private Integer id;
	private String name;
	public JsonAnnotationDemoSubBeanB() {}
	public JsonAnnotationDemoSubBeanB(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "JsonAnnotationDemoSubBeanB [id=" + id + ", name=" + name + "]";
	}
}
