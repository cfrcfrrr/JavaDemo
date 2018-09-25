package mytomcatdemo;

import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyTomcatDemoConfig {
	@Bean
	public TomcatEmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() {
		TomcatEmbeddedServletContainerFactory myTomcat = new TomcatEmbeddedServletContainerFactory();
		myTomcat.setPort(9000);
		return myTomcat;
	}
}
