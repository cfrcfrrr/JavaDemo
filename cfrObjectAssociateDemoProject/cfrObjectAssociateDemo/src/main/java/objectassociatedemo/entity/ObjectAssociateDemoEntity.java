package objectassociatedemo.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "object_associate_demo_entity")
public class ObjectAssociateDemoEntity implements Serializable {
	private static final long serialVersionUID = 3673107019345952138L;

	// 遗留问题：把类型改成String会自动建表失败，为什么？
	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

	public ObjectAssociateDemoEntity() {
		super();
	}

	public ObjectAssociateDemoEntity(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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
		return "ObjectAssociateDemoEntity [id=" + id + ", name=" + name + "]";
	}
}