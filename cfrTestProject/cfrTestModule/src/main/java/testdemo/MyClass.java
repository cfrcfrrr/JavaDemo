package testdemo;

public class MyClass implements Cloneable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getMax() {
        System.out.println("in getMax");
        return 3;
    }
}
