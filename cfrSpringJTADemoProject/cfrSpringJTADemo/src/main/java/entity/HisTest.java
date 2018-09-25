package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "histest")
public class HisTest implements Serializable{

	private static final long serialVersionUID = 4054550989690940818L;
	
	private Integer id;
	private String name;
	
	@Id
	@GeneratedValue
	@Column(name = "HIS_ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "HIS_NAME", nullable = false, length = 5)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
