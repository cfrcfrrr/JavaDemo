package cfrStaticSetMethodThreadSafetyDemo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MyClass {
    private String name;

    public static Map<String, Method> setMethod;

    static {
        try {
            setMethod = new HashMap<String, Method>() {{put("name", MyClass.class.getMethod("setName", String.class));}};
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
