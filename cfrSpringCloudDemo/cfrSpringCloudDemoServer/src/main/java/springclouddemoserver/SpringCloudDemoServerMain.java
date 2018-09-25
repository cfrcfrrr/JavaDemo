package springclouddemoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudDemoServerMain {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoServerMain.class, args);
		System.out.println("server start.");
	}
}
