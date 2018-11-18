package cfrChangeListWhenTraversalDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 结论：不管是用foreach、for index、iterator，在list遍历过程中，删除或增加元素都会报错或遍历数据错误，（虽然用for+index的情况在当前遍历后增加元素，且每次遍历重新计算了size没有问题，但是出问题的风险太高），因此不建议在遍历过程中修改列表大小；而修改元素是可以的。
 */
public class App {
    public static void main(String[] args) {
        List<MyClass> list = new ArrayList<>();
        list.add(new MyClass("aaa"));
        list.add(new MyClass("bbb"));
        list.add(new MyClass("ccc"));
        list.add(new MyClass("ddd"));

        // 在用for each遍历list过程中，删除和增加元素都会导致报错（某些特殊情况下不报错，但遍历的元素已经不对），但修改元素是可以的
        for (MyClass myClass : list) {
            if ("bbb".equals(myClass.getName())) {
                // list.remove(myClass); // 删除之后，在获取下一个元素时报错（如果删的刚好是倒数第二个元素，没有报错，看调试是没有再进ArrayList里面去获取元素，直接结束了）
                // list.add(new MyClass("ddd")); // 加了之后在获取下一个元素时报错java.util.ConcurrentModificationException
                list.get(0).setName("a"); // 修改可以
                // myClass.setName("b");
                list.get(1).setName("b");
                list.get(2).setName("c");
            }
            System.out.println(myClass);
        }
        System.out.println(list);

        System.out.println("---");
        // 使用for index，虽然删除元素不会导致报错，但会导致漏掉下一个元素，并且如果for的第二段判断不是调的list.size()重新计算大小，最后是会报错的；
        // 如果是增加元素在当前遍历之后，并且每次重新计算了size，则没有问题；修改是可以的
        for (int i = 0; i < list.size(); i++) {
            MyClass myClass = list.get(i);
            if ("b".equals(myClass.getName())) {
                // list.remove(myClass);
                // list.add(new MyClass("ddd"));
                list.get(0).setName("aa"); // 修改可以
                list.get(1).setName("bb");
                list.get(2).setName("cc");
            }
            System.out.println(myClass);
        }
        System.out.println(list);


        System.out.println("---");
        // 和for each相同
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            MyClass myClass = (MyClass) iterator.next();
            if ("bb".equals(myClass.getName())) {
                // list.remove(myClass);
                // list.add(new MyClass("ddd"));
                list.get(0).setName("aaaaaa"); // 修改可以
                list.get(1).setName("bbbbbb");
                list.get(2).setName("cccccc");
            }
            System.out.println(myClass);
        }
        System.out.println(list);
    }
}
