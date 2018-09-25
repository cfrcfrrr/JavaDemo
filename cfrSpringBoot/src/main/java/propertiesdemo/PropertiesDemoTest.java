package propertiesdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@PropertySource(value="classpath:propertiesDemoConfig/propertiesDemo.properties") // 遗留：只要在config类中配置本行，读取了配置文件，在test类中就可以用@Value或Environment访问，但是为什么在Test类中配置本行不能访问
public class PropertiesDemoTest {
	
//	@Autowired
//	private PropertiesDemoConfig myconfig; // 读取自定义配置文件
	
	@Value("${author}") // 读取默认配置文件方式一
	private String author;
	
	@Value("${properties.demo.id}")
	private String id;
	
	@Autowired
    private Environment env; // 读取默认配置文件方式二
	
	@Test
	public void getPrpertiesConfigTest() {
//		System.out.println("id is " + myconfig.getId());
//		System.out.println("name is " + myconfig.getName());
//		System.out.println("age is " + myconfig.getAge());
		System.out.println("author is " + author);
		System.out.println("version is " + env.getProperty("version"));
		
		System.out.println("@Value method get id is " + id);
		System.out.println("Environment method get id is " + env.getProperty("properties.demo.id"));
	}
}
