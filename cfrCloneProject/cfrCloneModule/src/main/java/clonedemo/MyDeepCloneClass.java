package clonedemo;

public class MyDeepCloneClass implements Cloneable {
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

    public MyDeepCloneClass(int age, String name, MySubClass mySubClass) {
        this.age = age;
        this.name = name;
        this.mySubClass = mySubClass;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        MyDeepCloneClass newMyDeepCloneClass = (MyDeepCloneClass) super.clone();
        newMyDeepCloneClass.setMySubClass((MySubClass) this.mySubClass.clone());
        return newMyDeepCloneClass;
    }
}
