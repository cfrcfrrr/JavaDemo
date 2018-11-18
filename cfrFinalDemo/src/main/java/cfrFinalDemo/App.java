package cfrFinalDemo;

/**
 * 一、
 * 对于基础类型、包装类、String，final关键字能保证初始化后不变，
 * 但是对于引用类型，只能保证被引用的对象不变，（也就是保存的引用对象内存地址不变），但是引用对象本身是可以改变的，容易出现问题。
 * 二、
 * 没有办法直接通过final等关键字直接设置变量首次赋值后不可改变，只能通过set方法，判断是否为空，变相实现（不过这样没办法实现如果第一次想赋值成null的情况）
 */
public class App {
    public static void main(String[] args) {
        // final修饰引用类型，指向的引用对象不会变，但引用对象本身可能变化
        MyClassA myClassA = new MyClassA();
        System.out.println(myClassA.getMyClassB().getName()); // null
        myClassA.getMyClassB().setName("aaa");
        System.out.println(myClassA.getMyClassB().getName()); // aaa

        // 没有办法直接通过final等关键字直接设置变量首次赋值后不可改变，只能通过set方法，判断是否为空，变相实现
        System.out.println(myClassA.getFinalAfterFirstAssignment());
        myClassA.setFinalAfterFirstAssignment("bbb");
        System.out.println(myClassA.getFinalAfterFirstAssignment()); // bbb
        myClassA.setFinalAfterFirstAssignment("ccc");
        System.out.println(myClassA.getFinalAfterFirstAssignment()); // bbb
    }
}
