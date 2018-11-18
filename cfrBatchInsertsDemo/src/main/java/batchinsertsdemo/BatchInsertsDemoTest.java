package batchinsertsdemo;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 插入1万数据：
 *      JPA 20s
 *      Hibernate 10s
 *      JDBC 18s
 * 插入10万数据：（hibernate 和 jdbc 每1万条保存一次）
 *      JPA 53s、137s
 *      Hibernate 118s、73s
 *      JDBC 136s、67s
 * 插入50万数据：（hibernate 和 jdbc 每1万条保存一次）
 *      JPA 685s
 *      Hibernate 623s
 *      JDBC 873s
 *
 * 结论：
 *  感觉整体来看，
 *      数据量较小(<10w)时，耗时：JPA >> JDBC > Hibernate
 *      数据量较大(>50w)时，耗时：JDBC > JPA ≈ Hibernate
 *  推荐使用Hibernate > JDBC > JPA
 */
@SpringBootTest
@RunWith(SpringRunner.class)
// @FixMethodOrder(value = MethodSorters.DEFAULT) // 执行顺序不定，通过这个注解也不能设置成按方法定义的顺序去执行
public class BatchInsertsDemoTest {
    @Autowired
    private BatchInsertsDemoRepo repo;

    private int saveSize = 500000;

    @Test
    public void jpaSave() throws Exception {
        Long startTime = System.currentTimeMillis();
        List<BatchInsertsDemoEntity> list = new ArrayList<>();
        for (int i = 0; i < saveSize; i++) {
            list.add(new BatchInsertsDemoEntity("jpa " + String.valueOf(i)));
        }
        System.out.println(repo.saveAll(list).size());
        System.out.println("------jpa save " + saveSize + " cost " + String.valueOf(System.currentTimeMillis() - startTime));
    }

    @Test
    public void hibernateSave() throws Exception {
        Long startTime = System.currentTimeMillis();
        List<BatchInsertsDemoEntity> list = new ArrayList<>();
        for (int i = 0; i < saveSize; i++) {
            list.add(new BatchInsertsDemoEntity("hibernate " + String.valueOf(i)));
        }
        new BatchInsertsDemoDAO().saveListByHibernate(list);
        System.out.println("------hibernate save " + saveSize + " cost " + String.valueOf(System.currentTimeMillis() - startTime)); // 10000数据：10s
    }

    @Test
    public void jdbcSave() throws Exception {
        Long startTime = System.currentTimeMillis();
        List<BatchInsertsDemoEntity> list = new ArrayList<>();
        for (int i = 0; i < saveSize; i++) {
            list.add(new BatchInsertsDemoEntity("jdbc " + String.valueOf(i)));
        }
        new BatchInsertsDemoDAO().saveListByJdbc(list);
        System.out.println("------jdbc save " + saveSize + " cost " + String.valueOf(System.currentTimeMillis() - startTime)); // 10000数据：18s
    }
}
