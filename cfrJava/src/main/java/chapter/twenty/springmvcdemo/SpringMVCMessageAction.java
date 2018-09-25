package chapter.twenty.springmvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pages/message/*")
public class SpringMVCMessageAction {
	public class MessageAction {
		@RequestMapping("hello_demo")
		public void demo(SpringMVCMessage msg) {
			System.out.println(msg);
		}
	}
	
}
