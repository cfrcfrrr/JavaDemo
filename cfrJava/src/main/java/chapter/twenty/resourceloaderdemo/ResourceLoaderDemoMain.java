package chapter.twenty.resourceloaderdemo;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ResourceLoaderDemoMain {
	public static void main(String[] args) throws IOException {
		ResourceLoader loader = new DefaultResourceLoader();
		// 文件读取
		System.out.println("-------------file:--------------");
		Resource sourceA = loader.getResource("file:e:\\tmp.txt"); // 遗留：路径用/或者\\都可以，为什么？
		Scanner scanA = new Scanner(sourceA.getInputStream());
		scanA.useDelimiter("\n");
		while (scanA.hasNext()) {
			System.out.println(scanA.next());
		}
		// CLASSPATH读取
		System.out.println("-------------classpath:--------------");
		Resource sourceB = loader.getResource("classpath:chapter\\twenty\\ResourceContext.xml");
		Scanner scanB = new Scanner(sourceA.getInputStream());
		scanB.useDelimiter("\n");
		while (scanB.hasNext()) {
			System.out.println(scanB.next());
		}
		// 读取网络资源
		System.out.println("-------------http:--------------");
		Resource sourceC = loader.getResource("https://www.baidu.com/");
		Scanner scanC = new Scanner(sourceC.getInputStream());
		scanC.useDelimiter("\n");
		while (scanC.hasNext()) {
			System.out.println(scanC.next());
		}
	}
}
