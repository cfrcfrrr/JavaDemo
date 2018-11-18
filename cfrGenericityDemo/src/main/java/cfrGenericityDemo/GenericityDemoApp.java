package cfrGenericityDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * 结论：就单单hibernate保存这个来讲，不用做任何特殊处理，用基础类Object和List来设置参数就可以，session会自动识别类型，不需要在传参时设置
 * 遗留问题：使用泛型和通配符到底有什么用？为什么感觉和没使用一个效果？
 */
public class GenericityDemoApp
{
    public static void main( String[] args ) throws Exception {
        GenericityDemoDAO genericityDemoDAO = new GenericityDemoDAO();
        System.out.println("---save---");
        // genericityDemoDAO.save(new GenericityDemoEntityA("aaa"));
        // genericityDemoDAO.save(new GenericityDemoEntityB("bbb"));

        List<GenericityDemoEntityA> listA = new ArrayList<>();
        listA.add(new GenericityDemoEntityA("a1"));
        listA.add(new GenericityDemoEntityA("a2"));
        listA.add(new GenericityDemoEntityA("a3"));

        List<GenericityDemoEntityB> listB = new ArrayList<>();
        listB.add(new GenericityDemoEntityB("b1"));
        listB.add(new GenericityDemoEntityB("b2"));
        listB.add(new GenericityDemoEntityB("b3"));

        System.out.println("---saveList---");
        // genericityDemoDAO.saveList(listA);
        // genericityDemoDAO.saveList(listB);

        System.out.println("---saveList genericity（泛型T）---");
        // genericityDemoDAO.saveListByGenericity(listA);
        // genericityDemoDAO.saveListByGenericity(listB);

        System.out.println("---saveList genericity（通配符？）---");
        genericityDemoDAO.saveListByWildCard(listA);
        genericityDemoDAO.saveListByWildCard(listB);

        System.out.println("over.");
    }
}
