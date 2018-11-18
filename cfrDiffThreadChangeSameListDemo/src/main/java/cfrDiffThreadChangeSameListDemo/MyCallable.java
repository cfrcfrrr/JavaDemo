package cfrDiffThreadChangeSameListDemo;

import java.util.List;
import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    private List<MyEntity> myEntityList;

    public MyCallable(List<MyEntity> myEntityList) {
        this.myEntityList = myEntityList;
    }

    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(50);
            if(Math.random() > 0.5) {
                int removeIndex = (int) Math.floor(Math.random() * this.myEntityList.size());
                if(this.myEntityList.size() > 0) {
                    this.myEntityList.remove(removeIndex); // 不会报错
                }
                System.out.println(Thread.currentThread().getName() + " remove " + removeIndex);
            }
            if(Math.random() > 0.5) {
                int addIndex = (int) Math.floor(Math.random() * this.myEntityList.size());
                this.myEntityList.add(addIndex, new MyEntity(addIndex, String.valueOf(addIndex) + addIndex));
                System.out.println(Thread.currentThread().getName() + " add " + addIndex); // 不会报错
            }
            if(Math.random() > 0.5) {
                int editIndex = (int) Math.floor(Math.random() * this.myEntityList.size());
                this.myEntityList.get(editIndex).setName(Thread.currentThread().getName()); // 不会报错
                System.out.println(Thread.currentThread().getName() + " edit " + editIndex);
            }

            // for (MyEntity myEntity : this.myEntityList) {
            //     if (Math.random() > 0.8) {
            //         System.out.println(Thread.currentThread().getName() + " change" + myEntity); // 在遍历过程中被其他线程修改，没有问题
            //         myEntity.setName(Thread.currentThread().getName());
            //     }
            //     if (Math.random() > 0.9) {
            //         System.out.println(Thread.currentThread().getName() + " remove" + myEntity); // 在遍历过程中被其他线程删除，报异常
            //         this.myEntityList.remove(myEntity);
            //     }
            // }
            System.out.println(Thread.currentThread().getName() + myEntityList);
        }
        return null;
    }
}
