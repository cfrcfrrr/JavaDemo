package comparetraversalwithdatabasedemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 结论：
 * 如果有break：
 *  消耗时间：遍历+反射获取属性（1s） < 遍历+调用方法使用get()获取属性 （2s） ≈ 遍历+此方法中使用get()获得属性（2s） << 从数据库获取数据 （20s）
 * 如果没有break：
 *  消耗时间（50000条数据）：遍历+get() 24小时没跑完 >> 从数据库获取(10h)
 * 分析：
 *  使用遍历（两层for去对比），
 *      如果数据是有序的，且能break，相当于一层循环，时间复杂度：O(n)，耗时很短，秒级；
 *      而如果是不能break的，遍历的耗时就是指数型增长，时间复杂度：O(n*n)；
 *  使用数据库（for+数据库获取）
 *      可能因为数据库获取的很快，所以时间复杂度O(n)，是线性增长，不过因为连接有消耗，在小数据量时比较耗时；
 *  结论：
 *      数据有序，能break的情况，使用遍历；
 *      不能break的情况，小数据量（3000条以下）时，使用遍历比较省时，也比较简单，而对于大数据量，不能使用循环，必须使用数据库获取
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MyTest {
    @Autowired
    private MyRepo repo;

    /**
     * 数据库总量增加，插入时间不变，查询时间增加
     * ---insert 20000(versionId = 0) use 97.799 s when before insert all size = 0
     * ---find versionId = 0 use 1.513s when all size = 20000
     * ---insert 20000(versionId = 1) use 97.614 s when before insert all size = 20000
     * ---find versionId = 0 use 0.655s when all size = 40000
     * ---insert 20000(versionId = 2) use 99.672 s when before insert all size = 40000
     * ---find versionId = 0 use 0.718s when all size = 60000
     * ---insert 20000(versionId = 3) use 97.052 s when before insert all size = 60000
     * ---find versionId = 0 use 0.827s when all size = 80000
     * ---insert 20000(versionId = 4) use 92.215 s when before insert all size = 80000
     * ---find versionId = 0 use 1.029s when all size = 100000
     * ---insert 20000(versionId = 5) use 98.31 s when before insert all size = 100000
     * ---find versionId = 0 use 1.41s when all size = 120000
     * ---insert 20000(versionId = 6) use 94.215 s when before insert all size = 120000
     * ---find versionId = 0 use 1.264s when all size = 140000
     * ---insert 20000(versionId = 7) use 97.533 s when before insert all size = 140000
     * ---find versionId = 0 use 1.404s when all size = 160000
     * ---insert 20000(versionId = 8) use 99.364 s when before insert all size = 160000
     */
    // @Test
    public void insert() {
        int size = 50000;
        for(long versionId = 0; versionId < 2; versionId ++) {
            List<MyEntity> list = new ArrayList<>();
            Date now = new Date();
            for (int i = 0; i < size; i++) {
                String strI = String.valueOf(i);
                list.add(new MyEntity(now, strI, versionId, strI, strI, strI));
            }
            Long startTime = System.currentTimeMillis();
            repo.saveAll(list);
            System.out.println("---insert " + size + "(versionId = " + versionId + ") use " + (float)(System.currentTimeMillis() - startTime) / 1000 + " s when before insert all size = " + versionId * size);

            startTime = System.currentTimeMillis();
            List<MyEntity> listVersionId = repo.findByVersionId(0l);
            System.out.println("---find versionId = " + 0 + " use " + (float)(System.currentTimeMillis() - startTime) / 1000 + "s when all size = " + (versionId + 1) * size);
        }
    }

    /**
     *                   1000   5000    10000   50000
     * break             0.5s
     * 无break+get       3s     238s    2799s    跑了24小时没跑出来
     * 无break+reflect   2s     185s    3667s
     * database          20s            1990s   38932s
     */
    // @Test
    public void compareUseTraversalAndMethodInvoke() {
        System.out.println("------compare use traversal and method invode------");
        System.out.println(new Date());
        Long startTime = System.currentTimeMillis();

        Long versionI = 0l;
        Long versionJ = 1l;

        List<MyEntity> myEntityListI = repo.findByVersionId(versionI);
        List<MyEntity> myEntityListJ = repo.findByVersionId(versionJ);
        System.out.println("versionI " + versionI + " size: " + myEntityListI.size());
        System.out.println("versionJ " + versionJ + " size: " + myEntityListJ.size());

        List<MyEntity> editMyEntityI = new ArrayList<>();
        List<MyEntity> editMyEntityJ = new ArrayList<>();
        List<MyEntity> tmpDeleteI = new ArrayList<>();
        List<MyEntity> tmpDeleteJ = new ArrayList<>();

        for(MyEntity myEntityI: myEntityListI) {
            for(MyEntity myEntityJ: myEntityListJ) {
                if(myEntityI.isSameKey(myEntityJ)) {
                    if(myEntityI.diffValidField(myEntityJ)) {
                        editMyEntityI.add(myEntityI);
                        editMyEntityJ.add(myEntityJ);
                    }
                    tmpDeleteI.add(myEntityI);
                    // myEntityListJ.remove(myEntityJ);
                    // break;
                    tmpDeleteJ.add(myEntityJ);
                }
            }
            myEntityListJ.removeAll(tmpDeleteJ);
        }
        myEntityListI.removeAll(tmpDeleteI);

        if(myEntityListI.size() > 0) {
            System.out.println("I 删除");
            for (MyEntity remainMyEntityI : myEntityListI) {
                System.out.println(remainMyEntityI);
            }
        }
        int editSize = editMyEntityI.size();
        if(editSize > 0) {
            for(int index = 0; index < editSize; index ++) {
                System.out.println("I修改");
                System.out.println(editMyEntityI.get(index));
                System.out.println("J修改");
                System.out.println(editMyEntityJ.get(index));
            }
        }
        if(myEntityListJ.size() > 0) {
            System.out.println("J新增");
            for (MyEntity addMyEntityJ : myEntityListJ) {
                System.out.println(addMyEntityJ);
            }
        }

        System.out.println("use " + (float)(System.currentTimeMillis() - startTime) / 1000 + "s");
    }

    /**
     * 30000条数据，使用get的26s，使用反射的14s
     * @throws Exception
     */
    // @Test
    public void compareUseTraversalAndReflect() throws Exception {
        System.out.println("------compare use traversal and reflect------");
        System.out.println(new Date());
        Long startTime = System.currentTimeMillis();

        Long versionI = 0l;
        Long versionJ = 1l;

        List<MyEntity> myEntityListI = repo.findByVersionId(versionI);
        List<MyEntity> myEntityListJ = repo.findByVersionId(versionJ);
        System.out.println("versionI " + versionI + " size: " + myEntityListI.size());
        System.out.println("versionJ " + versionJ + " size: " + myEntityListJ.size());

        List<MyEntity> editMyEntityI = new ArrayList<>();
        List<MyEntity> editMyEntityJ = new ArrayList<>();
        List<MyEntity> tmpDeleteI = new ArrayList<>();
        List<MyEntity> tmpDeleteJ = new ArrayList<>();

        for(MyEntity myEntityI: myEntityListI) {
            for(MyEntity myEntityJ: myEntityListJ) {
                if(myEntityI.isSameKeyUseReflect(myEntityJ)) {
                    if(!myEntityI.diffValidFieldUseReflect(myEntityJ)) {
                        editMyEntityI.add(myEntityI);
                        editMyEntityJ.add(myEntityJ);
                    }
                    tmpDeleteI.add(myEntityI);
                    // myEntityListJ.remove(myEntityJ);
                    // break;
                    tmpDeleteJ.add(myEntityJ);
                }
            }
            myEntityListJ.removeAll(tmpDeleteJ);
        }
        myEntityListI.removeAll(tmpDeleteI);

        if(myEntityListI.size() > 0) {
            System.out.println("I 删除");
            for (MyEntity remainMyEntityI : myEntityListI) {
                System.out.println(remainMyEntityI);
            }
        }
        int editSize = editMyEntityI.size();
        if(editSize > 0) {
            for(int index = 0; index < editSize; index ++) {
                System.out.println("I修改");
                System.out.println(editMyEntityI.get(index));
                System.out.println("J修改");
                System.out.println(editMyEntityJ.get(index));
            }
        }
        if(myEntityListJ.size() > 0) {
            System.out.println("J新增");
            for (MyEntity addMyEntityJ : myEntityListJ) {
                System.out.println(addMyEntityJ);
            }
        }

        System.out.println("use " + (float)(System.currentTimeMillis() - startTime) / 1000 + "s");
    }

    /**
     * 减少方法调用，速度几乎没变，10000的数据，如果有两个方法调用的，需要2.3s，如果没有两个方法调用，只需要1.3s，就是不管数据量多少，差不多减少1s
     */
    // @Test
    public void compareUseTraversalNoMethodInvoke() {
        System.out.println("------compare use traversal no method invode------");
        System.out.println(new Date());
        Long startTime = System.currentTimeMillis();

        Long versionI = 0l;
        Long versionJ = 1l;

        List<MyEntity> myEntityListI = repo.findByVersionId(versionI);
        List<MyEntity> myEntityListJ = repo.findByVersionId(versionJ);
        System.out.println("versionI " + versionI + " size: " + myEntityListI.size());
        System.out.println("versionJ " + versionJ + " size: " + myEntityListJ.size());

        List<MyEntity> editMyEntityI = new ArrayList<>();
        List<MyEntity> editMyEntityJ = new ArrayList<>();
        List<MyEntity> tmpDeleteI = new ArrayList<>();
        List<MyEntity> tmpDeleteJ = new ArrayList<>();
        for(MyEntity myEntityI: myEntityListI) {
            for(MyEntity myEntityJ: myEntityListJ) {
                if(Objects.equals(myEntityI.getName(), myEntityJ.getName()) && Objects.equals(myEntityI.getParameterOne(), myEntityJ.getParameterOne()) && Objects.equals(myEntityI.getParameterTwo(), myEntityJ.getParameterTwo())) { // 减少方法调用1
                    if(!Objects.equals(myEntityI.getParameterThree(), myEntityJ.getParameterThree())) { // 减少方法调用2
                        editMyEntityI.add(myEntityI);
                        editMyEntityJ.add(myEntityJ);
                    }
                    tmpDeleteI.add(myEntityI);
                    // myEntityListJ.remove(myEntityJ);
                    // break;
                    tmpDeleteJ.add(myEntityJ);
                }
            }
            myEntityListJ.removeAll(tmpDeleteJ);
        }
        myEntityListI.removeAll(tmpDeleteI);

        if(myEntityListI.size() > 0) {
            System.out.println("I 删除");
            for (MyEntity remainMyEntityI : myEntityListI) {
                System.out.println(remainMyEntityI);
            }
        }
        int editSize = editMyEntityI.size();
        if(editSize > 0) {
            for(int index = 0; index < editSize; index ++) {
                System.out.println("I修改");
                System.out.println(editMyEntityI.get(index));
                System.out.println("J修改");
                System.out.println(editMyEntityJ.get(index));
            }
        }
        if(myEntityListJ.size() > 0) {
            System.out.println("J新增");
            for (MyEntity addMyEntityJ : myEntityListJ) {
                System.out.println(addMyEntityJ);
            }
        }

        System.out.println("use " + (float)(System.currentTimeMillis() - startTime) / 1000 + "s");
    }

    /**
     * 直接去数据库查询，没有配置连接池的话，1000的数据（1新增、2修改、1删除），就要20s，而用遍历的方法只要0.5s
     * 但就算配置了连接池，时间也没有区别，遗留问题：怎么看是否使用了连接池里面的连接，使用了几个
     */
    @Test
    public void compareUseDatabase() {
        System.out.println("------compare use database------");
        System.out.println(new Date());
        Long startTime = System.currentTimeMillis();

        Long versionI = 0l;
        Long versionJ = 1l;

        List<MyEntity> myEntityListI = repo.findByVersionId(versionI);
        List<MyEntity> myEntityListJ = repo.findByVersionId(versionJ);
        System.out.println("versionI " + versionI + " size: " + myEntityListI.size());
        System.out.println("versionJ " + versionJ + " size: " + myEntityListJ.size());

        List<MyEntity> editMyEntityI = new ArrayList<>();
        List<MyEntity> editMyEntityJ = new ArrayList<>();
        List<MyEntity> tmpDeleteI = new ArrayList<>();

        for(MyEntity myEntityI: myEntityListI) {
            List<MyEntity> sameKeyMyEntityJList = repo.findByNameAndParameterOneAndParameterTwoAndVersionId(myEntityI.getName(), myEntityI.getParameterOne(), myEntityI.getParameterTwo(), versionJ);
            if(sameKeyMyEntityJList.size() > 0) {
                MyEntity myEntityJ = sameKeyMyEntityJList.get(0);
                myEntityListJ.remove(myEntityJ);
                tmpDeleteI.add(myEntityI);
                if(myEntityI.diffValidField(myEntityJ)) {
                    editMyEntityI.add(myEntityI);
                    editMyEntityJ.add(myEntityJ);
                }
            }

        }
        myEntityListI.removeAll(tmpDeleteI);

        if(myEntityListI.size() > 0) {
            System.out.println("I 删除");
            for (MyEntity remainMyEntityI : myEntityListI) {
                System.out.println(remainMyEntityI);
            }
        }
        int editSize = editMyEntityI.size();
        if(editSize > 0) {
            for(int index = 0; index < editSize; index ++) {
                System.out.println("I修改");
                System.out.println(editMyEntityI.get(index));
                System.out.println("J修改");
                System.out.println(editMyEntityJ.get(index));
            }
        }
        if(myEntityListJ.size() > 0) {
            System.out.println("J新增");
            for (MyEntity addMyEntityJ : myEntityListJ) {
                System.out.println(addMyEntityJ);
            }
        }

        System.out.println("use " + (float)(System.currentTimeMillis() - startTime) / 1000 + "s");
    }
}
