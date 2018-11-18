package cfrHibernateSessionSaveGetIdDemoProject;

import javax.persistence.*;

@Entity
@Table(name = "hibernate_session_save_get_id_demo_entity")
public class HibernateSeessionSaveGetIdDemoEntity {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    public HibernateSeessionSaveGetIdDemoEntity(String name) {
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
