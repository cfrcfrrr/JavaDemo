package jsonobjectdemo;

public class JsonObjectDemoBean {
	private Integer id = null;
	private String name = null;
	public JsonObjectDemoBean(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public JsonObjectDemoBean() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "JsonObjectDemoBean [id=" + id + ", name=" + name + "]";
	}
}
