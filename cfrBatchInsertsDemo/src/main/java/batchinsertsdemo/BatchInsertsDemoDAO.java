package batchinsertsdemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.HibernateEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BatchInsertsDemoDAO {
    // @PersistenceContext
    // private EntityManager entityManager;(x)

    // @Autowired
    // private SessionFactory sessionFactory;(x)

    public void saveListByHibernate(List<BatchInsertsDemoEntity> list) throws Exception {
        if (list == null || list.size() == 0) {
            return;
        }

        Configuration configuration = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction tx = null;
        final int COMMIT_NUM = 10000;
        try {
            configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
            // session = sessionFactory.getCurrentSession();(x)
            session = sessionFactory.openSession();
            // session = ((HibernateEntityManager)entityManager).getSession();(x)
            // session = (Session) entityManager.getDelegate();(x)
            tx = session.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                session.save(list.get(i));
                if ((i + 1) % COMMIT_NUM == 0) {
                    session.flush();
                    session.clear();
                    // tx.commit();(x)
                }
            }
            tx.commit();
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

    public void saveListByJdbc(List<BatchInsertsDemoEntity> list) throws Exception {
        if(list == null || list.size() == 0) {
            return;
        }

        Connection connection = null;
        final int COMMIT_NUM = 10000;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&useServerPrepStmts=false&rewriteBatchedStatements=true","root","root");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","root");
            connection.setAutoCommit(false); //设置手动提交
            //预编译sql对象,只编译一回
            PreparedStatement ps = connection.prepareStatement(
                    "insert into batch_inserts_demo_entity (name) values(?)");
            for (int i = 0; i < list.size(); i++) {
                // ps.setString(1, String.valueOf(UUID.randomUUID()));
                ps.setString(1, list.get(i).getName());
                ps.addBatch();//添加到批次
                if((i + 1) % COMMIT_NUM == 0) {
                    ps.executeBatch();//提交批处理
                    // connection.commit();//执行
                }
            }
            ps.executeBatch();//提交批处理
            // connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
