package cfrGenericityDemo;

import javax.persistence.*;

@Entity
@Table(name = "genericity_demo_entity_b")
public class GenericityDemoEntityB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    public GenericityDemoEntityB(String name) {
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
}
