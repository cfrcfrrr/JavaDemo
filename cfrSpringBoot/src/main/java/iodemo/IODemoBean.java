package iodemo;

import java.io.Serializable;
import java.util.Date;

public class IODemoBean implements Serializable {
	private Integer id;
	private String name;
	private transient Date birthday; // 不想被序列化则声明为transient
	public IODemoBean() {
		
	}
	public IODemoBean(Integer id, String name, Date birthday) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
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
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "IODemoBean [id=" + id + ", name=" + name + ", birthday=" + birthday + "]";
	}
}
