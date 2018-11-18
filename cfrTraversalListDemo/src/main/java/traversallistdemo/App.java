package traversallistdemo;

import java.util.*;

/**
 * 结论：
 * 对于ArrayList
 *      耗时：get + size() ≈ get + no size() < iterator < foreach，但是差别很小，ms级别
 *      因此使用哪个都可以
 * 对于LinedList
 *      耗时：iterator < foreach << get + size() ≈ get + no size()，
 *      应该使用iterator或foreach，不应该使用get
 * 因此总结来说，都使用iterator或foreach即可
 *
 * 数据：
 * ------traveralArrayList 10000000------
 * create data 9.34s
 * foreach 0.097s
 * get no size() 0.021s
 * get and size()0.03s
 * iterator 0.022s
 * ------traveralArrayList 500000------
 * create data 0.037s
 * foreach 0.024s
 * get no size() 786.271s
 * get and size() 686.747s
 * iterator 0.012s
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        traversalArrayList((long)10000 * 1000);
        traversalLinkedList((long)10000 * 50);
    }

    public static void traversalArrayList(Long size) {
        System.out.println("------traveralArrayList " + size + "------");
        Long tmpTime = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < size; i ++) {
            list.add(i);
        }
        System.out.println("create data " + (double)(System.currentTimeMillis() - tmpTime) / 1000 + "s");

        // forEach
        tmpTime = System.currentTimeMillis();
        for(Integer integer: list) {
        }
        System.out.println("foreach " + (double)(System.currentTimeMillis() - tmpTime) / 1000 + "s");

        // get
        tmpTime = System.currentTimeMillis();
        for(int i = 0; i < size; i ++) {
            list.get(i);
        }
        System.out.println("get no size() " + (double)(System.currentTimeMillis() - tmpTime) / 1000 + "s");

        // get
        tmpTime = System.currentTimeMillis();
        for(int i = 0; i < list.size(); i ++) {
            list.get(i);
        }
        System.out.println("get and size()" + (double)(System.currentTimeMillis() - tmpTime) / 1000 + "s");

        // iterator
        tmpTime = System.currentTimeMillis();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            iterator.next();
        }
        System.out.println("iterator " + (double)(System.currentTimeMillis() - tmpTime) / 1000 + "s");
    }

    public static void traversalLinkedList(Long size) {
        System.out.println("------traveralArrayList " + size + "------");
        Long tmpTime = System.currentTimeMillis();
        List<Integer> list = new LinkedList<>();
        for(int i = 0; i < size; i ++) {
            list.add(i);
        }
        System.out.println("create data " + (double)(System.currentTimeMillis() - tmpTime) / 1000 + "s");

        // forEach
        tmpTime = System.currentTimeMillis();
        for(Integer integer: list) {
        }
        System.out.println("foreach " + (double)(System.currentTimeMillis() - tmpTime) / 1000 + "s");

        // get
        tmpTime = System.currentTimeMillis();
        for(int i = 0; i < size; i ++) {
            list.get(i);
        }
        System.out.println("get no size() " + (double)(System.currentTimeMillis() - tmpTime) / 1000 + "s");

        // get
        tmpTime = System.currentTimeMillis();
        for(int i = 0; i < list.size(); i ++) {
            list.get(i);
        }
        System.out.println("get and size() " + (double)(System.currentTimeMillis() - tmpTime) / 1000 + "s");

        // iterator
        tmpTime = System.currentTimeMillis();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            iterator.next();
        }
        System.out.println("iterator " + (double)(System.currentTimeMillis() - tmpTime) / 1000 + "s");
    }
}
