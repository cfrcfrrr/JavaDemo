package httpservletdemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpServletDemoController {
	@RequestMapping("/")
	public void test(HttpServletRequest request, HttpServletResponse response) { // 遗留：像这种参数，是怎么赋值的？
		System.out.println("request: " + request);
		System.out.println("\tHost: " + request.getHeader("Host"));
		System.out.println("response: " + response);
		System.out.println("\tContent-Length: " + response.getHeader("Content-Length"));
		PrintWriter writer = null;
		try {
			writer = response.getWriter(); // writer可以将数据写回到response中
			writer.write("write response.");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
}
