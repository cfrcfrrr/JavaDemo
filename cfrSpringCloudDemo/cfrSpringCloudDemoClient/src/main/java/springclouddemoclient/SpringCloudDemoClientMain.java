package springclouddemoclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SpringCloudDemoClientMain {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudDemoClientMain.class, args);
		System.out.println("client start.");
	}

	@RequestMapping(value="/test")
	public String service() {
		return "test";
	}
}
