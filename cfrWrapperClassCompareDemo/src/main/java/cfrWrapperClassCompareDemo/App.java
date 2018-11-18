package cfrWrapperClassCompareDemo;

/**
 * 原理：== 比较引用地址，equals()比较类型，后比较值
 * 分析：==比较，在至少有一边的包装类是new创建的，或者两边都是基本类型创建的，但是超过了重复引用对象的返回时，都会不等，逻辑复杂，不可控；
 *       而equals比较是先比较类型，再比较值，逻辑清晰，可控；
 * 结论：不要使用==比较，应该使用equals()比较（并且要注意保证两个比较的对象的类型相同，如果是equals内是基本类型，则自动装箱后的类型要相同）
 */
public class App 
{
    public static void main( String[] args )
    {
        // 一、两个同类型同值包装类用==比较，因为引用地址不同，不相等
        Integer integer1 = new Integer(1);
        Integer integer2 = new Integer(1);
        System.out.println(System.identityHashCode(integer1)); // 1163157884
        System.out.println(System.identityHashCode(integer2)); // 1956725890
        System.out.println(integer1 == integer2); // false
        System.out.println(integer1.equals(integer2)); // true
        System.out.println(integer1 == 1); // true，基本类型和包装类进行==比较时，包装类会自动拆箱后与基本类型比较，因此相等
        System.out.println(1 == integer1); // true

        System.out.println("---------");
        Integer integer3 = 1;
        Integer integer4 = 1;
        System.out.println(System.identityHashCode(integer3)); // 356573597
        System.out.println(System.identityHashCode(integer4)); // 356573597
        System.out.println(integer3 == integer4); // true
        System.out.println(integer3.equals(integer4)); // true
        System.out.println(integer3 == 1); // true
        System.out.println("---------");

        System.out.println(integer1 == integer3); // false，两个包装类进行==比较时，只要有一方是new获得的，因为比较的是对象地址，因此不等
        System.out.println(integer1.equals(integer3)); // true

        System.out.println("---------");
        Integer integer5 = 128;
        Integer integer6 = 128;
        System.out.println(System.identityHashCode(integer5)); // 1735600054
        System.out.println(System.identityHashCode(integer6)); // 21685669
        System.out.println(integer5 == integer6); // false，超过[-128,127]的部分，会重新建对象，因此不等
        System.out.println(integer5.equals(integer6)); // true

        System.out.println("---------");
        Integer integer7 = 128;
        System.out.println(integer7.equals(128)); // true，包装类与基本类型进行equals比较时，会对基本类型自动装箱，如果自动装箱后类型不同，则不等，如果类型相同，再比较值
        System.out.println(integer7.equals(127l)); // false

        System.out.println("---------");
        Integer integer8 = 1;
        Short short8 = 1;
        // System.out.println(integer8 == short8); // 类型不同，不能比较，编译报错
        System.out.println(integer7.equals(short8)); // false，两个包装类用equals进行比较时，会先类型是否相同，如果不等，直接不等


    }
}
