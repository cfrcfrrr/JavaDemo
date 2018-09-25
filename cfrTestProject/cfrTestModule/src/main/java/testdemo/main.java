package testdemo;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws Exception {
        // for循环的方法，每次循环都会执行一次，所以最好在开始前保存数值
        // MyClass myClass = new MyClass();
        // int max = myClass.getMax();
        // // for(int i = 0; i < myClass.getMax(); i ++) {
        // for(int i = 0; i < max; i ++) {
        //     System.out.println(i);
        // }

    //
        MyClass myClass1 = new MyClass();
        myClass1.setName("aaa");
        List<MyClass> myClasses1 = new ArrayList<>();
        myClasses1.add(myClass1);
        myClasses1.get(0).setName("bbb");
        System.out.println(myClass1.getName());

        MyClass myClass2 = new MyClass();
        myClass2.setName("aaa");
        List<MyClass> myClasses2 = new ArrayList<>();
        myClasses2.add((MyClass)myClass2.clone());
        myClasses2.get(0).setName("bbb");
        System.out.println(myClass2.getName());
    }
}
