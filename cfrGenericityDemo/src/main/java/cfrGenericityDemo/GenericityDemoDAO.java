package cfrGenericityDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class GenericityDemoDAO {
    public void save(Object entity) throws Exception {
        Configuration configuration = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            System.out.println(entity.getClass());
            session.save(entity); // 不知道为什么会被修改为自动提交
            // tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void saveList(List list) throws Exception {
        Configuration configuration = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for(int i = 0; i < list.size(); i ++) {
                System.out.println(list.get(i).getClass());
                session.save(list.get(i));
            }
            // tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public <E> void saveListByGenericity(List<E> list) throws Exception {
        Configuration configuration = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for(int i = 0; i < list.size(); i ++) {
                E entity = list.get(i);
                System.out.println(entity.getClass());
                session.save(entity);
            }
            // tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void saveListByWildCard(List<?> list) throws Exception {
        Configuration configuration = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        try {
            configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            for(int i = 0; i < list.size(); i ++) {
                System.out.println(list.get(i).getClass());
                session.save(list.get(i));
            }
            // tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
