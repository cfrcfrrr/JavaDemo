package chapter.twenty.jdbctemplatedemo;

public class JDBCTemplateHero {
    private Integer id;
    private String name;
    private String dynasty;
    private Integer master;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDynasty() {
        return dynasty;
    }

    public Integer getMaster() {
        return master;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    public void setMaster(Integer master) {
        this.master = master;
    }

    @Override
    public String toString() {
        return "JDBCTemplateHero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", master=" + master +
                '}';
    }
}
