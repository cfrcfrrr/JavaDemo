package service;

import dao.Struts2TransactionDemoDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.Struts2TransactionDemoHibernateUtils;

import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

public class Struts2TransactionDemoService {
    public void test() throws Exception {
        System.out.println("service...");
//        UserTransaction tx = (UserTransaction) new InitialContext().lookup("javax.transaction.UserTransaction");
        Session session = Struts2TransactionDemoHibernateUtils.getSessionFactory().getCurrentSession();
//        new Struts2TransactionDemoDao().testA();
//        new Struts2TransactionDemoDao().testB();
        Transaction tx = session.beginTransaction();
        tx.commit();
    }
}
