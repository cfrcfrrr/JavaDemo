package cfrStaticSetMethodThreadSafetyDemo;

/**
 * 结论：在类初始化时反射set方法是线程安全的，只不过这样好像是会锁住方法的，同一时间只有一个线程能还占用这个方法。
 * 遗留：还是要再看看常用的做法。
 */
public class App 
{
    private static Long forSize = 100000000000L;
    public static void main( String[] args ) {
        Runnable runnableA = new Runnable() {
            @Override
            public void run() {
                try {
                    MyClass myClass = new MyClass();
                    System.out.println("start " + Thread.currentThread().getName());
                    for (Long i = 0l; i < forSize; i ++) {
                        MyClass.setMethod.get("name").invoke(myClass, "aaa");
                        String getName = myClass.getName();
                        if(!"aaa".equals(getName)) {
                            System.out.println(Thread.currentThread().getName() + getName);
                        }
                        if(i % 1000000000 == 0) {
                            System.out.println(Thread.currentThread().getName() + " " + i);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable runnableB = new Runnable() {
            @Override
            public void run() {
                try {
                    MyClass myClass = new MyClass();
                    System.out.println("start " + Thread.currentThread().getName());
                    for (Long i = 0l; i < forSize; i ++) {
                        MyClass.setMethod.get("name").invoke(myClass, "bbb");
                        String getName = myClass.getName();
                        if(!"bbb".equals(getName)) {
                            System.out.println(Thread.currentThread().getName() + getName);
                        }
                        if(i % 1000000000 == 0) {
                            System.out.println(Thread.currentThread().getName() + " " + i);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnableA).start();
        new Thread(runnableB).start();

        // 遗留问题：为什么这里TestThreadA、TestThreadB需要定义为static类，否则会报错？
        new Thread(new TestThreadA()).start();
        new Thread(new TestThreadB()).start();
    }
    private static class TestThreadA implements Runnable {
        public TestThreadA() {
        }

        @Override
        public void run() {
            try {
                MyClass myClass = new MyClass();
                while (true) {
                    MyClass.setMethod.get("name").invoke(myClass, "aaa");
                    String getName = myClass.getName();
                    if(!"aaa".equals(getName)) {
                        System.out.println(Thread.currentThread().getName() + getName);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private static class TestThreadB implements Runnable {
        public TestThreadB() {
        }

        @Override
        public void run() {
            try {
                MyClass myClass = new MyClass();
                while (true) {
                    MyClass.setMethod.get("name").invoke(myClass, "bbb");
                    String getName = myClass.getName();
                    if(!"bbb".equals(getName)) {
                        System.out.println(Thread.currentThread().getName() + getName);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
