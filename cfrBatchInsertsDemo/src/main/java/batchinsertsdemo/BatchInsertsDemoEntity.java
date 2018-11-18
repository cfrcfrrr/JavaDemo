package batchinsertsdemo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "batch_inserts_demo_entity")
public class BatchInsertsDemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @Id
    // @Column(length = 32, nullable = false, unique = true)
    // @GeneratedValue(generator = "idGenerator")
    // @GenericGenerator(name = "idGenerator", strategy = "uuid")
    // private String id;

    @Column(name = "name")
    private String name;

    public BatchInsertsDemoEntity() {
    }

    public BatchInsertsDemoEntity(String name) {
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
        return "BatchInsertsDemoEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
