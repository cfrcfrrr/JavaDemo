package instanceofdemo;

import java.util.List;

// 遗留问题：但是为什么(String) obj 或 (List<MyClass>) obj不能通过编译，而（List）obj可以？
public class App {
    public static void main(String[] args) {
        // obj instanceof Class
        int i = 0;
        // obj 不能为基础类型
        // System.out.println(i instanceof int); // 编译报错，cannot cast int to int
        // System.out.println(i instanceof Integer); // 编译报错，cannot cast int to Integer
        // Class 也不能为基础类型
        // System.out.println(null instanceof int); // 编译报错，cannot cast null to int
        // 如果obj是null，且Class是合法的，则返回false
        System.out.println(null instanceof Integer); // false

        System.out.println(new MyClass() instanceof MyClass); // true
        System.out.println(new MyClass() instanceof MyFatherClass); // true

        // obj能转换到Class类，就是true
        MyFatherClass myFatherClass = new MyClass();
        System.out.println(myFatherClass instanceof MyClass); // true
        System.out.println(myFatherClass instanceof MyInterface); // true
        // 如果不能转换过去，就返回false
        System.out.println(new MyFatherClass() instanceof MyClass); // false


        MyClass myClass = new MyClass();
        // System.out.println(myClass instanceof String); // 编译报错， cannot cast MyClass to String
        System.out.println(myClass instanceof List); // false
        System.out.println(myClass instanceof List<?>); // false
        // System.out.println(myClass instanceof List<MyClass>); // 编译报错， illegal generic type of instanceof

        // 实际上instanceof的逻辑如下，其实就是进行转换看是否能转换成功
        // boolean result;
        // if (obj == null) {
        //     result = false;
        // } else {
        //     try {
        //         T temp = (T) obj; // checkcast
        //         result = true;
        //     } catch (ClassCastException e) {
        //         result = false;
        //     }
        // }
    }
}
