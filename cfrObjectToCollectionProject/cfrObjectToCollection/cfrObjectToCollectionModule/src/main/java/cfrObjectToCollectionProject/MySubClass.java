package cfrObjectToCollectionProject;

public class MySubClass extends MyClass {
    private String name;
    private String school;

    // 这里会调用父类的无参构造，所以父类要定义一个无参构造，否则会报错
    public MySubClass(String name, String school) {
        this.name = name;
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "MySubClass{" +
                "name='" + name + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
