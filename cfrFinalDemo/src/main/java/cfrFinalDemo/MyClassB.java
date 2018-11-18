package cfrFinalDemo;

/**
 * 遗留问题：几种单例设计模式的实现方式（https://www.cnblogs.com/zhaoyan001/p/6365064.html）
 */
public class MyClassB {
    private String name;

    private static MyClassB myClassB;

    public static MyClassB getInstance() {
        if(myClassB == null) {
            synchronized (MyClassB.class) {
                if(myClassB == null) {
                    myClassB = new MyClassB();
                }
            }
        }
        return myClassB;
    }

    private MyClassB() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyClassB{" +
                "name='" + name + '\'' +
                '}';
    }
}
