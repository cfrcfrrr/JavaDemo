package objectassociatedemo;

import objectassociatedemo.entity.ObjectAssociateDemoEntity;
import objectassociatedemo.service.ObjectAssociateDemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ObjectAssociateDemoTest {
    @Autowired
    private ObjectAssociateDemoService objectAssociateDemoService;

    @Test
    public void test() throws Exception {
        List<ObjectAssociateDemoEntity> list = new ArrayList<>();
        ObjectAssociateDemoEntity obj1 = objectAssociateDemoService.findById(1);
        ObjectAssociateDemoEntity obj2 = objectAssociateDemoService.findById(2);
        ObjectAssociateDemoEntity obj3 = objectAssociateDemoService.findById(3);
        list.add(obj1);
        list.add(obj2);
        list.add(obj3);

        Iterator<ObjectAssociateDemoEntity> iterator1 = list.iterator();
        System.out.println("-----------before change--------------");
        while(iterator1.hasNext()) {
            System.out.println(iterator1.next().toString());
        }
        // 修改一：如果是暂停，然后手动去数据库修改，不会有问题，也就是不会导致List中读出来的数据变了
        // Thread.sleep(2000);
        // 修改二：如果是通过dao层去修改，也不会有问题
        // objectAssociateDemoService.updateNameById(2, "bb");
        // 修改三：如果是通过其他对象去设置值，也不会有问题
        // ObjectAssociateDemoEntity newObj2 = objectAssociateDemoService.findById(2);
        // newObj2.setName("bb");
        // objectAssociateDemoService.saveUser(newObj2);
        // 修改四：但是如果是通过List添加的对象去修改，就会有问题
        obj2.setName("bb");
        objectAssociateDemoService.saveUser(obj2); // 实际上也不是因为保存到数据库导致的问题，就是对象属性变了导致的
        System.out.println("------------after change-------------");
        Iterator<ObjectAssociateDemoEntity> iterator2 = list.iterator();
        while(iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }
}
