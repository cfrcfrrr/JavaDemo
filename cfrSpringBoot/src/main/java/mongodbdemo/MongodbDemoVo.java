package mongodbdemo;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//遗留：MongoDB其他注解，例遗留：@CompoundIndex(复合索引)、@GeaSpatialIndexed(地理信息索引)、@Transient(该字段不会保存到mongodb)、@DBRef(类似关系型数据库的关联关系)、@PersistenceConstructoe(声明构造函数，将mongodb取出的值实例化为对象)
@Document(collection = "mongodb_demo_bean") // 将一个类声明为MongoDB集合 // 遗留：这个声明是不是主要是为了给集合重命名？如果不加这行应该也是可以生成数据库的吧
public class MongodbDemoVo {
	@Id // 文档的唯一标识
	private ObjectId id;
	@Indexed(unique = true) // 声明该字段需要唯一索引
	private Integer studentId;
	private String name;
	private Date birthday;
	private List<String> teachers;
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
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
	public List<String> getTeachers() {
		return teachers;
	}
	public void setTeachers(List<String> teachers) {
		this.teachers = teachers;
	}
	@Override
	public String toString() {
		return "MongodbDemoVo [id=" + id + ", studentId=" + studentId + ", name=" + name + ", birthday=" + birthday
				+ ", teachers=" + teachers + "]";
	}
}