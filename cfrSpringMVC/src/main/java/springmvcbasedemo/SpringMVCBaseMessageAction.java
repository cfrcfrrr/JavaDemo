package springmvcbasedemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 控制器不需再去编写类属性来接收参数，所有接收的参数都直接放到了处理的业务方法上，且避免了对象实例化。
 * 最终访问路径为：http://localhost:8080/cfrWebJava/pages/message/hello_demo.action?mid=10&title=world&type.title=helloo，格式为ip:端口/项目名称/程序路径/方法路径?参数
 * @author Administrator
 *
 */
@SuppressWarnings("unused")
@Controller // 表示定义一个控制器
@RequestMapping("/pages/message/*") // 定义这个程序整体的访问路径
public class SpringMVCBaseMessageAction { // 类名不能随便取，比如对应的类名为“SpringMVCMessage”，则此action类名为“SpringMVCMessageAction”，而不能是“MessageAction”
	@RequestMapping(value="hello_demo",produces="text/html;charset=UTF-8") // 定义方法的访问子路径，可使用method参数可指定请求类型，不设置时表示支持GET和POST
//	@RequestMapping(value="hello_demo",method=RequestMethod.POST) // 表示只支持POST请求，用GET请求（http://localhost:8080/cfrWebJava/pages/message/hello_demo.action?mid=10&title=world&type.title=helloo）会报“警告: Request method 'GET' not supported”
//	@RequestMapping(value="hello_demo",method=RequestMethod.GET) // 表示只支持GET请求
	public ModelAndView demo(SpringMVCBaseMessage msg) {
//		System.out.println(msg);
		ModelAndView mav = new ModelAndView("/pages/forward.jsp");
		mav.addObject("msg","你好");
		mav.addObject("url", "/index.jsp");
		System.out.println(msg);
		return mav;
	}
}