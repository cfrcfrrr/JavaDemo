package addcheckwhenrunbytomcatdemo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 在web.xml中添加listener指定到这个类，再实现ServletContextListener接口，就可以在tomcat启动和关闭时做一些处理
 */
public class RunCheck implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("------tomcat启动时会跑进来");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("------tomcat关闭时会跑进来");
    }
}
