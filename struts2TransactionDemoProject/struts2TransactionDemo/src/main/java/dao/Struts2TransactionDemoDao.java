package dao;

import entity.Struts2TransactionDemoEntityA;
import entity.Struts2TransactionDemoEntityB;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.Struts2TransactionDemoHibernateUtils;


public class Struts2TransactionDemoDao {
    public void testA() throws Exception {
        //初始化事务
        Transaction transaction =null;
        try {
            //用之前创建的utils工具来获取session
//            session不是线程安全的，应该避免多个线程共享同一个Session实例，每个sessio对象内部有一个缓存，称为Hibernate第一缓存，存放元数据和预定义SQL
            // 位于缓存中的对象称为持久化对象
//            当commot()方法调用时、查询时、显示调用flush()方法时会清理缓存
            Session session = Struts2TransactionDemoHibernateUtils.getSessionFactory().getCurrentSession();
            //创建一个新的transaction对象，并开启
//            一个session同时只能有一个transaction是active（开启状态）
            transaction = session.beginTransaction();
            //编写hql语句
            Struts2TransactionDemoEntityA entityA = new Struts2TransactionDemoEntityA();
            entityA.setName("aaa");
            session.save(entityA);
//            if(true) {
//                throw new Exception("myException");
//            }
            //执行之前要提交事务
//            transaction.commit();
//            如果获取session的方法是openSession()，则需要使用seesion.close()手动关闭，而如果是getCurrentSession()，则提交时自动关闭，再手动调一次会报错
//            session.close();
        }catch (Exception e){
//            transaction.rollback();
            throw e;
        }finally {
            //将事务置为空
            if(transaction!=null){
                transaction=null;
            }
        }
    }

    public void testB() throws Exception {
        //初始化事务
        Transaction transaction =null;
        try {
            //用之前创建的utils工具来获取session
            Session session = Struts2TransactionDemoHibernateUtils.getSessionFactory().getCurrentSession();
            //创建事务并开启事务
            transaction =session.beginTransaction();
            //编写hql语句
            Struts2TransactionDemoEntityB entityB = new Struts2TransactionDemoEntityB();
            entityB.setName("bbb");
            session.save(entityB);
//            if(true) {
//                throw new Exception("myException");
//            }
            //执行之前要提交事务
//            transaction.commit();
//            如果获取session的方法是openSession()，则需要使用seesion.close()手动关闭，而如果是getCurrentSession()，则提交时自动关闭，再手动调一次会报错
//            session.close();
        }catch (Exception e){
            throw e;
        }finally {
            //将事务置为空
            if(transaction!=null){
                transaction=null;
            }
        }
    }
}
