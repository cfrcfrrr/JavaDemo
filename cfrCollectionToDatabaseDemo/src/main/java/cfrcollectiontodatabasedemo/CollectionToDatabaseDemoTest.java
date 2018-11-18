package cfrcollectiontodatabasedemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CollectionToDatabaseDemoTest {
    @Autowired
    private CollectionToDatabaseDemoRepo repo;

    @Test
    public void insert() {
        CollectionToDatabaseDemoEntity entity = new CollectionToDatabaseDemoEntity();
        entity.setName("aaa");
        ArrayList list = new ArrayList();
        list.add("strA");
        list.add("strB");
        entity.setStrList(list);
        repo.save(entity);
    }
}
