package objectassociatedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 所以结论是，数据库取出数据保存为对象，再添加到List中，只要不是添加到List中的对象本身属性变了，后面数据库如何更改，都不会影响到List
 */
@SpringBootApplication
public class ObjectAssociateDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(ObjectAssociateDemoApplication.class);
	}
}
