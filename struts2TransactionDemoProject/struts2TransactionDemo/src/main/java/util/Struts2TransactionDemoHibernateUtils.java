package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//import org.hibernate.service.ServiceRegistryBuilder;

public class Struts2TransactionDemoHibernateUtils {
    private static SessionFactory sessionFactory;

    //保证单例模式
    private Struts2TransactionDemoHibernateUtils(){

    }

    //公有的静态方法
    public static SessionFactory getSessionFactory(){

        if(sessionFactory ==null){
//            创建配置对象
            Configuration configuration =new Configuration().configure();
//            创建服务注册对象
//            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
//            创建会话工厂对象
            sessionFactory = configuration.buildSessionFactory();
//            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        }else {
            return sessionFactory;
        }
    }
}
