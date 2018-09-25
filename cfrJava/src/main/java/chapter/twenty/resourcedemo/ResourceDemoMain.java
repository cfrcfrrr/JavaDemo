package chapter.twenty.resourcedemo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class ResourceDemoMain {
	public static void main(String[] args) throws IOException {
		// 1、读取内存资源
		System.out.println("----------------------ByteArrayResource---------------");
		// 此处的内存处理流与之前讲解的ByteArrayInputStream使用形式类似
		Resource sourceA = new ByteArrayResource("helloworld".getBytes());
		// 可以取得更多的资源信息，如获取数据长度，InputStream获取不到这些信息
		System.out.println("数据长度： " + sourceA.contentLength());
		Scanner scanA = new Scanner(sourceA.getInputStream());
		while (scanA.hasNext()) {
			System.out.println(scanA.next());
		}
		
		// 2、读取文件资源
		System.out.println("----------------------FileSystemResource---------------");
		Resource sourceB = new FileSystemResource(new File("E:" + File.separator + "tmp.txt"));
		Scanner scanB = new Scanner(sourceB.getInputStream());
		scanB.useDelimiter("\n"); // 遗留：为什么文件中看到的换行符是CR LF，这里设置为\n就可以，不用设置成\r\n？
		while (scanB.hasNext()) {
			System.out.println(scanB.next());
		}
		
		// 3、读取CLASSPATH资源
		System.out.println("----------------------ClassPathResource---------------");
		Resource sourceC = new ClassPathResource("chapter/twenty/resourceContext.xml");
		Scanner scanC = new Scanner(sourceC.getInputStream());
		scanC.useDelimiter("\n");
		while (scanC.hasNext()) {
			System.out.println(scanC.next());
		}
	}
}
