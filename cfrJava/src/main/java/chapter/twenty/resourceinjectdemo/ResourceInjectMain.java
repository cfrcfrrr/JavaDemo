package chapter.twenty.resourceinjectdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.annotation.Resources;

/**
 * ResourceLoader被Spring封装，用户只需要关注Resource
 * @author Administrator
 *
 */
public class ResourceInjectMain {
    @SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter/twenty/resourceInjectContext.xml");
        ResourceInjectBean rib = ctx.getBean("rib", ResourceInjectBean.class);
        // 读取资源
        System.out.println("---resource inject---");
        Scanner s = new Scanner(rib.getResource().getInputStream());
        s.useDelimiter("\n");
        while(s.hasNext()) {
            System.out.println(s.next());
        }
        // 读取资源列表
        System.out.println("---resource list inject---");
        Iterator<Resource> iter = rib.getResourceList().iterator(); // 要遍历List等就用Iterator
        while(iter.hasNext()) {
        	Scanner scan = new Scanner(iter.next().getInputStream());
        	scan.useDelimiter("\n");
        	while(scan.hasNext()) {
        		System.out.println(scan.next());
        	}
        	System.out.println("---");
        }
        // 路径通配符
        // 使用直接注入并不行
//        System.out.println("---resource path wild card---");
//        Iterator<Resource> iter2 = rib.getResourcePathWildCard().iterator();
//        while(iter2.hasNext()) {
//        	Scanner scan2 = new Scanner(iter2.next().getInputStream());
//        	scan2.useDelimiter("\n");
//        	while(scan2.hasNext()) {
//        		System.out.println(scan2.next());
//        	}
//        	System.out.println("---");
//        }
        
//        ？：匹配任意一位字符；
//        *：匹配零个、一个或多个任意字符；
//        **：匹配零个、一个或多个目录；
        ResourcePatternResolver loader = new PathMatchingResourcePatternResolver();
        Resource[] resouces = loader.getResources("classpath:chapter/twenty/resourceInject-?.txt");
        for (int i = 0; i < resouces.length; i ++) {
        	System.out.println("文件名称：" + resouces[i].getFilename() + "，文件大小：" + resouces[i].contentLength());
        }
    }
}
