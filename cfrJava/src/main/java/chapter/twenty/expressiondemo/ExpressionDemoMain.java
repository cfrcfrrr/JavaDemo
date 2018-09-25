package chapter.twenty.expressiondemo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@SuppressWarnings("unused")
public class ExpressionDemoMain {
    public static void main(String[] args) throws NoSuchMethodException {
        // 表达式示例
        String str1 = "(\"hello\" + \"world\").substring(2,5)"; // 定义字符串
        ExpressionParser ep1 = new SpelExpressionParser(); // 定义解析器
        org.springframework.expression.Expression exp1 = ep1.parseExpression(str1); // 解析字符串
        System.out.println(exp1.getValue());
        // 设置占位符
        String str2 = "(\"hello\" + \"world\").substring(#start,#end)";
        ExpressionParser ep2 = new SpelExpressionParser();
        org.springframework.expression.Expression exp2 = ep2.parseExpression(str2);
        EvaluationContext ec2 = new StandardEvaluationContext(); // 用于设置表达式的一些属性信息，如占位符
        ec2.setVariable("start",3); // 设置占位符
        ec2.setVariable("end", 6);
        System.out.println(exp2.getValue(ec2));
        // 根变量可以在context实例化时直接赋值
        String str4 = "#root";
        ExpressionParser ep4 = new SpelExpressionParser();
        org.springframework.expression.Expression exp4 = ep4.parseExpression(str4);
        EvaluationContext ec4 = new StandardEvaluationContext("aaa");
        System.out.println(exp4.getValue(ec4));
        // 方法引用
        ExpressionParser ep5 = new SpelExpressionParser();
        Method met5 = Integer.class.getMethod("parseInt",String.class);
        org.springframework.expression.Expression exp5 = ep5.parseExpression("#method('12345')");
        EvaluationContext ec5 = new StandardEvaluationContext();
        ec5.setVariable("method",met5);
        System.out.println(exp5.getValue(ec5));
        // 属性引用
        ExpressionParser ep6 = new SpelExpressionParser();
        org.springframework.expression.Expression exp6 = ep6.parseExpression("time");
        EvaluationContext ec6 = new StandardEvaluationContext(new Date());
        System.out.println(exp6.getValue(ec6));

        // 设置分隔符
        String str3 = "#[1+2]";
        ExpressionParser ep3 = new SpelExpressionParser();
        org.springframework.expression.Expression exp3 = ep3.parseExpression(str3, new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }

            @Override
            public String getExpressionPrefix() {
                return "#["; // 不需要返回引号
            }

            @Override
            public String getExpressionSuffix() {
                return "]";
            }
        });
        System.out.println(exp3.getValue());
        // 字面表达式
        // 字符串
        System.out.println("'hello' + 'world' -> " + new SpelExpressionParser().parseExpression("'hello' + 'world'").getValue());
        // 数值
        System.out.println("1 -> " + new SpelExpressionParser().parseExpression("1").getValue());
        // 布尔型
        System.out.println("true -> " + new SpelExpressionParser().parseExpression("true").getValue());
        // null
        System.out.println("null -> " + new SpelExpressionParser().parseExpression("null").getValue());
        // 数学表达式
        // 四则运算
        System.out.println("1 + 2 -> " + new SpelExpressionParser().parseExpression("1 + 2").getValue());
        // 求模
        System.out.println("10 % 3 -> " + new SpelExpressionParser().parseExpression("10 % 3").getValue());
        System.out.println("10 MOD 3 -> " + new SpelExpressionParser().parseExpression("10 MOD 3").getValue());
        // 幂运算
        System.out.println("2 ^ 3 -> " + new SpelExpressionParser().parseExpression("2 ^ 3").getValue());
        // 除法
        System.out.println("10 DIV 3 -> " + new SpelExpressionParser().parseExpression("10 DIV 3").getValue());
        System.out.println("10 / 3 -> " + new SpelExpressionParser().parseExpression("10 / 3").getValue());
        // 关系表达式
        // 等于
        System.out.println("1 == 2 -> " + new SpelExpressionParser().parseExpression("1 == 2").getValue());
        System.out.println("1 EQ 2 -> " + new SpelExpressionParser().parseExpression("1 EQ 2").getValue());
        // 不等于
        System.out.println("1 != 2 -> " + new SpelExpressionParser().parseExpression("1 != 2").getValue());
        System.out.println("1 NE 2 -> " + new SpelExpressionParser().parseExpression("1 NE 2").getValue());
        // 大于
        System.out.println("10 > 2 -> " + new SpelExpressionParser().parseExpression("10 > 2").getValue());
        System.out.println("10 GT 2 -> " + new SpelExpressionParser().parseExpression("10 GT 2").getValue());
        // 大于等于
        System.out.println("10 >= 10 -> " + new SpelExpressionParser().parseExpression("10 >= 10").getValue());
        System.out.println("10 GE 10 -> " + new SpelExpressionParser().parseExpression("10 GE 10").getValue());
        // 小于
        System.out.println("2 < 3 -> " + new SpelExpressionParser().parseExpression("2 < 3").getValue());
        System.out.println("2 LT 3 -> " + new SpelExpressionParser().parseExpression("2 LT 3").getValue());
        // 小于等于
        System.out.println("2 <= 2 -> " + new SpelExpressionParser().parseExpression("2 <= 2").getValue());
        System.out.println("2 LE 2 -> " + new SpelExpressionParser().parseExpression("2 LE 2").getValue());
        // 区间
        System.out.println("10 BETWEEN {5,15} -> " + new SpelExpressionParser().parseExpression("10 BETWEEN {5,15}").getValue());
        // 逻辑表达式
        // 与
        System.out.println("1 == 1 && 3 > 2 -> " + new SpelExpressionParser().parseExpression("1 == 1 && 3 > 2").getValue());
        System.out.println("1 == 1 AND 3 > 2 -> " + new SpelExpressionParser().parseExpression("1 == 1 AND 3 > 2").getValue());
        // 或
        System.out.println("1 == 1 || 3 > 2 -> " + new SpelExpressionParser().parseExpression("1 == 1 || 3 > 2").getValue());
        System.out.println("1 == 1 OR 3 > 2 -> " + new SpelExpressionParser().parseExpression("1 == 1 OR 3 > 2").getValue());
        // 非
        System.out.println("!(3 > 2) -> " + new SpelExpressionParser().parseExpression("!(3 > 2)").getValue());
        System.out.println("NOT(3 > 2) -> " + new SpelExpressionParser().parseExpression("NOT(3 > 2)").getValue());
        // 字符串表达式，大部分都支持，下面列出几个示例
        // 连接
        System.out.println("'hello'.concat('world') -> " + new SpelExpressionParser().parseExpression("'hello'.concat('world')").getValue());
        System.out.println("'hello' + 'world' -> " + new SpelExpressionParser().parseExpression("'hello' + 'world'").getValue());
        // 取内容
        System.out.println("'hello'[1] -> " + new SpelExpressionParser().parseExpression("'hello'[1]").getValue());
        // 替换
        System.out.println("'hello'.replace('l','o') -> " + new SpelExpressionParser().parseExpression("'hello'.replace('l','o')").getValue());
        // 三目运算符
        System.out.println("1 > 2 ? 'hello':'world' -> " + new SpelExpressionParser().parseExpression("1 > 2 ? 'hello':'world'").getValue());
        System.out.println("null == null ? 'hello':'world' -> " + new SpelExpressionParser().parseExpression("null == null ? 'hello':'world'").getValue());
        System.out.println("null?:'world' -> " + new SpelExpressionParser().parseExpression("null?:'world'").getValue());
        System.out.println("true?'hello':'world' -> " + new SpelExpressionParser().parseExpression("true?'hello':'world'").getValue());
        System.out.println("true?:'world' -> " + new SpelExpressionParser().parseExpression("true?:'world'").getValue());
        // 正则运算
        System.out.println("'100' matches '\\d{3}' -> " + new SpelExpressionParser().parseExpression("'100' matches '\\d{3}'").getValue());
        System.out.println("'100'.matches('\\d{3}') -> " + new SpelExpressionParser().parseExpression("'100'.matches('\\d{3}')").getValue());
        // Class表达式
        System.out.println("T(String) -> " + new SpelExpressionParser().parseExpression("T(String)").getValue());
        System.out.println("T(java.util.Date) -> " + new SpelExpressionParser().parseExpression("T(java.util.Date)").getValue());
        System.out.println("T(Integer).MAX_VALUE -> " + new SpelExpressionParser().parseExpression("T(Integer).MAX_VALUE").getValue());
        System.out.println("T(Integer).parseInt('123') -> " + new SpelExpressionParser().parseExpression("T(Integer).parseInt('123')").getValue());
//        System.out.println(new SpelExpressionParser().parseExpression("Integer.parseInt('123')").getValue()); // 错误，不能直接使用类名，必须用T(Integer)
        // 但是不用表达式时可以用类名转换
        int tmp = Integer.parseInt("123");
        System.out.println(tmp);
        System.out.println("new java.util.Date() -> " + new SpelExpressionParser().parseExpression("new java.util.Date()").getValue());
        System.out.println("new String('hello') -> " + new SpelExpressionParser().parseExpression("new String('hello')").getValue());
        System.out.println("'hello' instanceof T(String) -> " + new SpelExpressionParser().parseExpression("'hello' instanceof T(String)").getValue());
        // 变量操作
        // 变量引用，就是上面的设置占位符
        // 方法引用
        ExpressionParser ep7 = new SpelExpressionParser();
        Method method7 = Integer.class.getMethod("parseInt", String.class); // 通过反射，获取名为“parseInt”，参数为String.class的方法
        Expression exp7 = ep7.parseExpression("#myMethod('123')");
        StandardEvaluationContext context7 = new StandardEvaluationContext();
        context7.registerFunction("myMethod", method7);
        System.out.println(exp7.getValue(context7));
        // 调用属性
        ExpressionParser ep8 = new SpelExpressionParser();
        Expression exp8 = ep8.parseExpression("time"); // 遗留："time"表示要调用getTime()方法，为什么？
        EvaluationContext context8 = new StandardEvaluationContext(new Date());
        System.out.println(exp8.getValue(context8)); // 1529508463249
        // 调用属性-根变量为空
        ExpressionParser ep9 = new SpelExpressionParser();
//        Expression exp9 = ep9.parseExpression("time"); // 如果没有设置根变量则会报异常，可以使用grovy安全运算符避免空异常
        Expression exp9 = ep9.parseExpression("#root?.time");
        EvaluationContext context9 = new StandardEvaluationContext();
        System.out.println(exp9.getValue(context9)); // null
        
        // 以上都是在程序中直接定义表达式，也可利用资源文件 // 遗留：理解下
        ApplicationContext ctx10 = new ClassPathXmlApplicationContext("chapter/twenty/expressionDemoContext.xml");
        ExpressionParser ep10 = new SpelExpressionParser();
        Expression exp10 = ep10.parseExpression("@bean.getInfo()"); // @名称.方法
        StandardEvaluationContext context10 = new StandardEvaluationContext();
        context10.setBeanResolver(new BeanFactoryResolver(ctx10)); // 将整个配置文件的内容读取交给上下文
        System.out.println(exp10.getValue(context10));
        
        // 集合表达式
        // List
        // Spring中List集合与数组等价，可利用{value1, value2, ...}形式操作
        ExpressionParser ep11 = new SpelExpressionParser();
        Expression exp11 = ep11.parseExpression("{10, 20, 30}");
        EvaluationContext context11 = new StandardEvaluationContext();
        System.out.println("{10, 20, 30} -> " + exp11.getValue(context11)); // [10, 20, 30]
        
        ExpressionParser ep12 = new SpelExpressionParser();
        Expression exp12 = ep12.parseExpression("{10, 20, 30}[1]");
        EvaluationContext context12 = new StandardEvaluationContext();
        System.out.println("{10, 20, 30}[1] -> " + exp12.getValue(context12)); // 20
        
        List<String> list13 = new ArrayList<String>();
        list13.add("ListAaa");
        list13.add("ListBbb");
        ExpressionParser ep13 = new SpelExpressionParser();
        Expression exp13 = ep13.parseExpression("#myList"); // 遗留：那如果表达式里面本来就像表达#怎么办？
        EvaluationContext context13 = new StandardEvaluationContext();
        context13.setVariable("myList", list13);
        System.out.println("List -> " + exp13.getValue(context13));
        
        // Set
        Set<String> set14 = new HashSet<String>();
        set14.add("SetAaa");
        set14.add("SetBbb");
        ExpressionParser ep14 = new SpelExpressionParser();
        Expression exp14 = ep14.parseExpression("#mySet");
        EvaluationContext context14 = new StandardEvaluationContext();
        context14.setVariable("mySet", set14);
        System.out.println("Set -> " + exp14.getValue(context14));
        
        // Map
        Map<Integer, String> map15 = new HashMap<Integer, String>();
        map15.put(1, "MapAaa");
        map15.put(2, "MapBbb");
        ExpressionParser ep15 = new SpelExpressionParser();
        Expression exp15 = ep15.parseExpression("#myMap");
        EvaluationContext context15 = new StandardEvaluationContext();
        context15.setVariable("myMap", map15);
        System.out.println("Map -> " + exp15.getValue(context15));
        
        // 数据修改
        List<String> list16 = new ArrayList<String>();
        list16.add("ListAaa");
        list16.add("ListBbb");
        ExpressionParser ep16 = new SpelExpressionParser();
        Expression exp16 = ep16.parseExpression("#myList[1] = 'ListCcc'"); // 遗留：那如果表达式里面本来就像表达#怎么办？
        EvaluationContext context16 = new StandardEvaluationContext();
        context16.setVariable("myList", list16);
        System.out.println("newList[1] -> " + exp16.getValue(context16)); // 这一步会修改
        System.out.println("newList -> " + list16);
        
        // 批量处理List、Set
        List<String> list17 = new ArrayList<String>();
        list17.add("ListAaa");
        list17.add("ListBbb");
        ExpressionParser ep17 = new SpelExpressionParser();
        Expression exp17 = ep17.parseExpression("#myList.!['统一加前缀'+#this]"); // 遗留：怎么实现的
        EvaluationContext context17 = new StandardEvaluationContext();
        context17.setVariable("myList", list17);
        System.out.println("newList -> " + exp17.getValue(context17));
        System.out.println("oldList -> " + list17); // oldList -> [ListAaa, ListBbb]，但原数据不会修改\
        
        // 批量处理Map转List
        Map<Integer, String> map18 = new HashMap<Integer, String>();
        map18.put(1, "MapAaa");
        map18.put(2, "MapBbb");
        ExpressionParser ep18 = new SpelExpressionParser();
        Expression exp18 = ep18.parseExpression("#mapToList.![#this.key + '-' + #this.value]");
        EvaluationContext context18 = new StandardEvaluationContext();
        context18.setVariable("mapToList", map18);
        System.out.println("mapToList -> " + exp18.getValue(context18));
        
        // 数据筛选
        Map<Integer, String> map19 = new HashMap<Integer, String>();
        map19.put(1, "MapAaa");
        map19.put(2, "MapBbb");
        ExpressionParser ep19 = new SpelExpressionParser();
        Expression exp19 = ep19.parseExpression("#myMap.?[#this.key == 2]"); // 这里必须用2，不能用'2'，否则会筛选不到。另外如果写成"#myMap.![#this.key == 2]"，则输出结果为[false, true]，变成判断
        EvaluationContext context19 = new StandardEvaluationContext();
        context19.setVariable("myMap", map19);
        System.out.println("map.key = 2 -> " + exp19.getValue(context19)); // map.key = 2 -> {2=MapBbb}
    }
}
