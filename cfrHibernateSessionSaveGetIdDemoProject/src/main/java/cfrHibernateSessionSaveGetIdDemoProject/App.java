package cfrHibernateSessionSaveGetIdDemoProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App 
{
    public static void main( String[] args ) {
        Configuration configuration = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            configuration = new Configuration();
            configuration.configure();
            // System.out.println(configuration.getProperty("hibernate.connection.autocommit")); // false
            sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            HibernateSeessionSaveGetIdDemoEntity hibernateSeessionSaveGetIdDemoEntity = new HibernateSeessionSaveGetIdDemoEntity("bbb");
            session.save(hibernateSeessionSaveGetIdDemoEntity); // 不知道为什么会被修改为自动提交
            // tx.commit();
            // tx.rollback(); 因此还原也没有用
            System.out.println(hibernateSeessionSaveGetIdDemoEntity.getId());
        } catch (Exception e) {
            // if (tx != null) {
            //     tx.rollback();
            // }
            e.printStackTrace();
        } finally {
            // if (session != null) {
            //     session.close();
            // }
        }
    }
}
