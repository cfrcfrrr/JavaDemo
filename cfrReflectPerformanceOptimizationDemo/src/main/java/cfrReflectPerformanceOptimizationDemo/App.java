package cfrReflectPerformanceOptimizationDemo;

import com.sun.xml.internal.ws.util.StringUtils;

import javax.swing.text.html.parser.Entity;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结论：反射的速度其实很快的，之前公司慢的原因不在于反射，而在于每次遍历过程中去数据库取了一次数据，每次耗了7ms左右，数量一多，就慢了
 */
public class App {
    public static void main(String[] args) throws Exception {
        List<MyEntity> list = new ArrayList<>();
        for(int i = 0; i < 10000; i ++) {
            MyEntity myEntity = new MyEntity();
            for(Field field : MyEntity.class.getDeclaredFields()) {
                    MyEntity.class.getMethod("set" + StringUtils.capitalize(field.getName()), String.class).invoke(myEntity,field.getName() + i);
            }
            list.add(myEntity);
        }

        // noReflect(list); // 46ms

        reflectNoStatic(list);

    }

    public static void noReflect(List<MyEntity> list) throws Exception {
        Long startTime = System.currentTimeMillis();
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for(int i = 0; i < list.size(); i ++) {
            MyEntity myEntity = list.get(i);
            map.put("msg1", myEntity.getMsg1());
            map.put("msg2", myEntity.getMsg2());
            map.put("msg3", myEntity.getMsg3());
            map.put("msg4", myEntity.getMsg4());
            map.put("msg5", myEntity.getMsg5());
            map.put("msg6", myEntity.getMsg6());
            map.put("msg7", myEntity.getMsg7());
            map.put("msg8", myEntity.getMsg8());
            map.put("msg9", myEntity.getMsg9());
            map.put("msg10", myEntity.getMsg10());
            map.put("msg11", myEntity.getMsg11());
            map.put("msg12", myEntity.getMsg12());
            map.put("msg13", myEntity.getMsg13());
            map.put("msg14", myEntity.getMsg14());
            map.put("msg15", myEntity.getMsg15());
            map.put("msg16", myEntity.getMsg16());
            map.put("msg17", myEntity.getMsg17());
            map.put("msg18", myEntity.getMsg18());
            map.put("msg19", myEntity.getMsg19());
            map.put("msg20", myEntity.getMsg20());
            map.put("msg21", myEntity.getMsg21());
            map.put("msg22", myEntity.getMsg22());
            map.put("msg23", myEntity.getMsg23());
            map.put("msg24", myEntity.getMsg24());
            map.put("msg25", myEntity.getMsg25());
            map.put("msg26", myEntity.getMsg26());
            map.put("msg27", myEntity.getMsg27());
            map.put("msg28", myEntity.getMsg28());
            map.put("msg29", myEntity.getMsg29());
            map.put("msg30", myEntity.getMsg30());
            mapList.add(map);
        }
        System.out.println(mapList.size());
        System.out.println(mapList.get(6666).get("msg8"));
        System.out.println("noReflect cost " + String.valueOf(System.currentTimeMillis() - startTime));
    }

    public static void reflectNoStatic(List<MyEntity> list) throws Exception {
        Long startTime = System.currentTimeMillis();
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for(int i = 0; i < list.size(); i ++) {
            MyEntity myEntity = list.get(i);
            for(Field field : MyEntity.class.getDeclaredFields()) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(myEntity));
            }
            mapList.add(map);
        }
        System.out.println(mapList.size());
        System.out.println(mapList.get(6666).get("msg8"));
        System.out.println("reflect no static cost " + String.valueOf(System.currentTimeMillis() - startTime));
    }
}
