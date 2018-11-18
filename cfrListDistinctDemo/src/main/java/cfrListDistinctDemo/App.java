package cfrListDistinctDemo;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据：去重十万大小的List（一半重复）
 *  HashSet constructor 62ms
 *  Set add 47ms
 *  Stream 150ms
 *  traversal equals 32928ms
 *  traversal contains 42126ms
 *  结论：
 *  推荐
 *  HashSet（直接构造（更简练），或者通过add的返回值判断） ≈ steam（和使用HsshSet都是ms级别，语法更简练） >> list遍历再使用equals或contains去判断
 */
public class App {
    public static void main(String[] args) throws Exception {
        Method streamMet = App.class.getMethod("useStream", List.class);
        distinct(streamMet);

        distinct(App.class.getMethod("useHashSetConstructor", List.class));

        distinct(App.class.getMethod("useSetAdd", List.class));

        distinct(App.class.getMethod("useTraversalEquals", List.class));

        distinct(App.class.getMethod("useTraversalContains", List.class));
    }

    public static void distinct(Method method) throws Exception {
        System.out.println("---");
        Long startTime = System.currentTimeMillis();
        // List<String> list = new ArrayList(Arrays.asList("aaa", "bbb", "ccc", "aaa"));
        List<String> list = new ArrayList<>();
        final int LIST_SIZE = 100000;
        for(int i = 0; i < LIST_SIZE / 2; i ++) {
            list.add(String.valueOf(i));
            list.add(String.valueOf(i));
        }
        method.invoke(null, list); // 反射调用静态方法，将第一个参数设置为null
        System.out.println(method.getName() + "distinct " + LIST_SIZE + " cost " + String.valueOf(System.currentTimeMillis() - startTime));
    }

    public static void useHashSetConstructor(List<String> list) throws Exception {
        HashSet<String> hashSet = new HashSet<>(list);
        list.clear();
        list.addAll(hashSet);
        System.out.println(list.size());
    }

    public static void useSetAdd(List<String> list) throws Exception {
        Set set = new HashSet();
        List tmpList = new ArrayList<>();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            Object element = iterator.next();
            if(set.add(element)) {
                tmpList.add(element);
            }
        }
        list.clear();
        list.addAll(tmpList);
        System.out.println(list.size());
    }

    public static void useTraversalEquals(List<String> list) throws Exception {
        for(int i = 0; i < list.size() - 1; i ++) {
            for(int j = list.size() - 1; j > i; j --) {
                if(list.get(j).equals(list.get(i))) {
                    list.remove(j); // 遗留问题：为什么这样删除不会报错？
                }
            }
        }
        System.out.println(list.size());
    }
    
    public static void useTraversalContains(List<String> list) throws Exception {
        List tmpList = new ArrayList();
        for(int i = 0; i < list.size(); i ++) {
            if(!tmpList.contains(list.get(i))) {
                tmpList.add(list.get(i));
            }
        }
        list.clear();
        list.addAll(tmpList);
        System.out.println(list.size());
    }

    public static void useStream(List<String> list) throws Exception {
        list = list.stream().distinct().collect(Collectors.toList());
        System.out.println(list.size());
    }
}
