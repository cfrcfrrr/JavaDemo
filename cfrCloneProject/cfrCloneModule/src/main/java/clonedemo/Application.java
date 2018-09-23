package clonedemo;

public class Application {
    public static void main(String[] args) throws Exception {
        MySubClass mySubClass1 = new MySubClass("subAa");
        // 直接等于，是引用复制，指向同一个对象、同一块地址，修改会相互影响
        MyDefaultShallowCloneClass myDefaultShallowCloneClass1 = new MyDefaultShallowCloneClass(1, "aaa", mySubClass1);
        MyDefaultShallowCloneClass myDefaultShallowCloneClass2 = myDefaultShallowCloneClass1;
        System.out.println(myDefaultShallowCloneClass1);
        System.out.println(myDefaultShallowCloneClass2);
        System.out.println("使用=时，oldObject == newObject: " + String.valueOf(myDefaultShallowCloneClass1 == myDefaultShallowCloneClass2)); // true

    //    使用clone，则是对象克隆，不指向同一个对象、同一块地址，不会相互影响
        MyDefaultShallowCloneClass myDefaultShallowCloneClass3 = (MyDefaultShallowCloneClass) myDefaultShallowCloneClass1.clone();
        System.out.println(myDefaultShallowCloneClass3);
        System.out.println("使用clone时，oldObject == newObject: " + String.valueOf(myDefaultShallowCloneClass1 == myDefaultShallowCloneClass3)); // false

    //    但是默认情况下，clone是浅拷贝，也就是对象内部的对象还是关联的，会相互影响，只有基础数据是不会相互影响的
        System.out.println("使用clone时，oldObject.getName() == newObject.getName(): " + String.valueOf(myDefaultShallowCloneClass1.getName() == myDefaultShallowCloneClass3.getName())); // true
        System.out.println("使用clone时，oldObject.getMySubClass() == newObject.getMySubClass(): " + String.valueOf(myDefaultShallowCloneClass1.getMySubClass() == myDefaultShallowCloneClass3.getMySubClass())); // true
        // 不过String比较特殊，没办法取出对象重新设置，所有不会导致问题，但其他类就会有问题
        myDefaultShallowCloneClass3.getMySubClass().setName("subB");
        System.out.println(myDefaultShallowCloneClass1.getMySubClass().getName()); // subB

    //    设置深拷贝，则不会相互影响，但是要注意的是，如果引用类型子对象内部又有引用类型的子孙对象，则每层都需要重写clone方法
        MySubClass mySubClass2 = new MySubClass("subAA");
        MyDeepCloneClass myDeepCloneClass1 = new MyDeepCloneClass(1, "aa", mySubClass2);
        MyDeepCloneClass myDeepCloneClass2 = (MyDeepCloneClass) myDeepCloneClass1.clone();
        System.out.println("深度clone时，oldObject.getMySubClass() == newObject.getMySubClass(): " + String.valueOf(myDeepCloneClass1.getMySubClass() == myDeepCloneClass2.getMySubClass())); // false
        myDeepCloneClass2.getMySubClass().setName("subBB");
        System.out.println(myDeepCloneClass1.getMySubClass().getName()); // subAA

    }
}
