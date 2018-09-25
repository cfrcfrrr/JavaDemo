package propertiesdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="properties.demo") // 读取配置的统一前缀
@PropertySource(value="classpath:propertiesDemoConfig/propertiesDemo.properties") // 读取配置文件
public class PropertiesDemoConfig {
//	private String id;
//	private String name;
//	private String age;
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getAge() {
//		return age;
//	}
//	public void setAge(String age) {
//		this.age = age;
//	}
}
