package clonedemo;

public class MyDefaultShallowCloneClass implements Cloneable {
    private int age;
    private String name;
    private MySubClass mySubClass;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MySubClass getMySubClass() {
        return mySubClass;
    }

    public void setMySubClass(MySubClass mySubClass) {
        this.mySubClass = mySubClass;
    }

    public MyDefaultShallowCloneClass(int age, String name, MySubClass mySubClass) {
        this.age = age;
        this.name = name;
        this.mySubClass = mySubClass;
    }
    public MyDefaultShallowCloneClass() {}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
