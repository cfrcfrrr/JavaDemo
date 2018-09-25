package cfrObjectToCollectionProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static Map<String, Object> test() {
        List<String> list = new ArrayList<String>();
        list.add("listValueA");
        list.add("listValueB");
        Map<String, String> map = new HashMap<String, String>();
        map.put("mapKeyA", "mapValueA");
        map.put("mapKeyB", "mapValueB");

        List<MySubClass> mySubClassList = new ArrayList<MySubClass>();
        mySubClassList.add(new MySubClass("subA", "schoolA"));
        mySubClassList.add(new MySubClass("subB", "schoolB"));

        List<MyClass> myClassList = new ArrayList<MyClass>();
        myClassList.add(new MyClass("classA", "cityA"));
        myClassList.add(new MyClass("classB", "cityB"));

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("list", list);
        result.put("map", map);
        result.put("subClass", mySubClassList);
        result.put("class", myClassList);

        return result;
    }
}
