package comparetraversalwithdatabasedemo;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "compare_traversal_with_database_demo_entity")
public class MyEntity {
    private static final String [] KEY_FIELDS = new String[] {
            "name",
            "parameterOne",
            "parameterTwo",
    };
    private static final String [] VALID_FIELDS = new String[] {
            "parameterThree",
    };
    private Integer id;
    private Date addTime;
    private String name;
    private Long versionId;
    private String parameterOne;
    private String parameterTwo;
    private String parameterThree;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "add_time")
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "version_id")
    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    @Column(name = "parameter_one")
    public String getParameterOne() {
        return parameterOne;
    }

    public void setParameterOne(String parameterOne) {
        this.parameterOne = parameterOne;
    }

    @Column(name = "parameter_two")
    public String getParameterTwo() {
        return parameterTwo;
    }

    public void setParameterTwo(String parameterTwo) {
        this.parameterTwo = parameterTwo;
    }

    @Column(name = "parameter_three")
    public String getParameterThree() {
        return parameterThree;
    }

    public void setParameterThree(String parameterThree) {
        this.parameterThree = parameterThree;
    }

    public MyEntity() {
    }

    public MyEntity(Date addTime, String name, Long versionId, String parameterOne, String parameterTwo, String parameterThree) {
        this.addTime = addTime;
        this.name = name;
        this.versionId = versionId;
        this.parameterOne = parameterOne;
        this.parameterTwo = parameterTwo;
        this.parameterThree = parameterThree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyEntity)) return false;
        MyEntity myEntity = (MyEntity) o;
        return Objects.equals(getId(), myEntity.getId()) &&
                Objects.equals(getAddTime(), myEntity.getAddTime()) &&
                Objects.equals(getName(), myEntity.getName()) &&
                Objects.equals(getVersionId(), myEntity.getVersionId()) &&
                Objects.equals(getParameterOne(), myEntity.getParameterOne()) &&
                Objects.equals(getParameterTwo(), myEntity.getParameterTwo()) &&
                Objects.equals(getParameterThree(), myEntity.getParameterThree());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddTime(), getName(), getVersionId(), getParameterOne(), getParameterTwo(), getParameterThree());
    }

    @Override
    public String toString() {
        return "MyEntity{" +
                "id=" + id +
                ", addTime=" + addTime +
                ", name='" + name + '\'' +
                ", versionId=" + versionId +
                ", parameterOne='" + parameterOne + '\'' +
                ", parameterTwo='" + parameterTwo + '\'' +
                ", parameterThree='" + parameterThree + '\'' +
                '}';
    }

    public Boolean isSameKey(MyEntity that) {
        return  Objects.equals(getName(), that.getName()) &&
                Objects.equals(getParameterOne(), that.getParameterOne()) &&
                Objects.equals(getParameterTwo(), that.getParameterTwo());
    }

    public Boolean diffValidField(MyEntity that) {
        return !Objects.equals(getParameterThree(), that.getParameterThree());
    }

    public Boolean isSameKeyUseReflect(MyEntity that) throws Exception {
        Class myEntityClazz = MyEntity.class;
        for(String keyField : MyEntity.KEY_FIELDS) {
            Field field = myEntityClazz.getDeclaredField(keyField);
            field.setAccessible(true);
            if(!Objects.equals(field.get(this), field.get(that))) return false;
        }
        return true;
    }

    public Boolean diffValidFieldUseReflect(MyEntity that) throws Exception {
        Class myEntityClazz = MyEntity.class;
        for(String validField : MyEntity.VALID_FIELDS) {
            Field field = myEntityClazz.getDeclaredField(validField);
            field.setAccessible(true);
            if(!Objects.equals(field.get(this), field.get(that))) return false;
        }
        return true;
    }
}
