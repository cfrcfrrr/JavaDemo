package objectmapperdemo;

import java.util.Date;
import java.util.List;

public class ObjectMapperDemoBean {
	private Integer id;
	private String name;
	private Boolean isStudent;
	private Date birthday;
	private List<String> teachers;
	private Long bookSize;

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

	public Boolean getIsStudent() {
		return isStudent;
	}

	public void setIsStudent(Boolean isStudent) {
		this.isStudent = isStudent;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public List getTeachers() {
		return teachers;
	}

	public void setTeachers(List teachers) {
		this.teachers = teachers;
	}

	public Long getBookSize() {
		return bookSize;
	}

	public void setBookSize(Long bookSize) {
		this.bookSize = bookSize;
	}

	@Override
	public String toString() {
		return "ObjectMapperDemoBean [id=" + id + ", name=" + name + ", isStudent=" + isStudent + ", birthday="
				+ birthday + ", teachers=" + teachers + ", bookSize=" + bookSize + "]";
	}
}
