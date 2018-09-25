package jpademo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jpa_demo_entity")
// @NamedQuery(name = "User.findByName", query = "select name,address from User
// u where u.name=?1")
public class JpaDemoEntity implements Serializable {
	private static final long serialVersionUID = 3673107019345952138L;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	String id;
	// @Column(name = "name")
	String name;

	public JpaDemoEntity() {
		super();
	}

	public JpaDemoEntity(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
		return "JpaDemoEntity [id=" + id + ", name=" + name + "]";
	}
}