package cfrObjectToCollectionProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // 遗留问题：居然可以直接强转，但是为什么？
        Map<String, Object> result = Test.test();

        List<String> list = (List<String>) result.get("list");
        System.out.println(list.get(0)); // listValueA
        System.out.println(list.get(1)); // listValueB

        Map<String, String> map = (Map<String, String>) result.get("map");
        System.out.println(map.get("mapKeyA")); // mapValueA
        System.out.println(map.get("mapKeyB")); // mapValueB

        List<MyClass> myClassList = (List<MyClass>) result.get("subClass");
        System.out.println(myClassList.get(0)); // MySubClass{name='subA', school='schoolA'}
        System.out.println(myClassList.get(1)); // MySubClass{name='subB', school='schoolB'}

        // 遗留问题：特别是这个，明明存进去的是List<MyClass>，应该无论如何也转不了List<MySubClass>啊？
        List<MySubClass> mySubClassList = (List<MySubClass>) result.get("class");
        System.out.println(mySubClassList instanceof List);
        // System.out.println(mySubClassList instanceof List<MySubClass>); // 编译错误
        System.out.println(mySubClassList.get(0) instanceof MyClass); // true
        System.out.println(mySubClassList.get(0) instanceof MySubClass); // false
        System.out.println(mySubClassList.get(0)); // MyClass{name='classA', city='cityA'}
        System.out.println(mySubClassList.get(1)); // MyClass{name='classB', city='cityB'}

        // 遗留问题：为什么List<String>转List<Object>又不行？
        List<String> listString = new ArrayList<String>();
        listString.add("aaa");
        // List<Object> listObject = listString; // 编译异常
        // List<Object> listObject = (List<Obejct>) listString; // 编译异常
    }
}
