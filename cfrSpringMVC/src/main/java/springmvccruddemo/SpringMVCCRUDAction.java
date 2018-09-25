package springmvccruddemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 控制器，调用服务层接口
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/SpringMVCCRUD/*") // 路径需要以/开头，以/*结尾
public class SpringMVCCRUDAction {
	private SpringMVCCRUDIService service = new SpringMVCCRUDServiceImpl();

	/* 可通过insert.jsp访问到此方法
	 结果：
		服务端输出
			【插入数据】SpringMVCCRUDMessage [mid=1, title=三毛流浪记, price=11.1, pubdate=Tue Jan 16 00:05:00 CST 2018, type=SpringMVCCRUDType [title=小说]]
			true
	 	浏览器弹窗显示“插入数据成功”后切换到index页面
	 */
	@RequestMapping("insert") // 这里开头不需要设置/，结尾也不需要设置*.action
	public ModelAndView insertAction(SpringMVCCRUDMessage vo) throws Exception {
		System.out.println(this.service.insert(vo)); // 错误：增加sysout // 优化：使用this.service，而不是service，代码更直观
		ModelAndView mav = new ModelAndView("/SpringMVCCRUD/forward.jsp");
		mav.addObject("msg", "插入数据成功");
		mav.addObject("url", "/index.jsp");
		return mav;
	}

	/* 可通过“http://localhost:8080/cfrWebJava/SpringMVCCRUD/delete.action?mids=1%7C2%7C3”访问，其中%7C表示|
	 结果：
		服务端输出
			【删除数据】[1, 2, 3]
			true
	 	浏览器弹窗显示“删除数据成功”后切换到index页面
	 */
	@RequestMapping("delete")
	public ModelAndView deleteAciton(String mids) throws Exception { // 遗留：这里类型不能设置为Set<Integer>，需要修改为String，这个类型包括哪些，代表什么意思，比如设置成String就是用String去接手访问路径？后面的内容吗？
		Set<Integer> si = new HashSet<Integer>();
		String [] midsSpl = mids.split("\\|");
		for(int i = 0; i < midsSpl.length; i ++) {
			si.add(Integer.parseInt(midsSpl[i]));
		}
		System.out.println(this.service.delete(si));
		ModelAndView mav = new ModelAndView("/SpringMVCCRUD/forward.jsp");
		mav.addObject("msg", "删除数据成功");
		mav.addObject("url", "/index.jsp");
		return mav;
	}

	/* 使用@RequestParam可以设置URL访问的别名（但通常不会这么设置，最好保持一致），也就是可以不和方法定义的参数名一致，并且可设置默认值，因此以下URL都可访问到此方法
	 http://localhost:8080/cfrWebJava/SpringMVCCRUD/get.action?parammid=1 // 访问到1
	 http://localhost:8080/cfrWebJava/SpringMVCCRUD/get.action? // 访问到0
	 http://localhost:8080/cfrWebJava/SpringMVCCRUD/get.action // 访问到0
	 http://localhost:8080/cfrWebJava/SpringMVCCRUD/get.action?mid=1 // 访问到0
	 http://localhost:8080/cfrWebJava/SpringMVCCRUD/get.action?xxxxx=1 // 访问到0
	 相当于Spring帮助用户解决了参数是否重复的判断，和参数类型的自动转换操作（遗留：怎么理解）
	 结果（访问到1）:
	 	服务端显示：
	 		【查询数据】
			SpringMVCCRUDMessage [mid=1, title=三毛流浪记, price=11.1, pubdate=Thu May 17 00:27:38 CST 2018, type=SpringMVCCRUDType [title=小说]]
		浏览器弹窗显示“查询数据成功”后切换到index页面
	*/
	@RequestMapping("get")
	public ModelAndView getAciton(@RequestParam(value="parammid",defaultValue="0") int mid) throws Exception {
		System.out.println(this.service.get(mid));
		ModelAndView mav = new ModelAndView("/SpringMVCCRUD/forward.jsp");
		mav.addObject("msg", "查询数据成功");
		mav.addObject("url", "/index.jsp");
		return mav;
	}

	/* 可通过update.jsp访问到此方法
	 结果：
		服务端输出
			【更新数据】SpringMVCCRUDMessage [mid=2, title=西游记, price=22.2, pubdate=Mon Jan 15 00:05:00 CST 2018, type=SpringMVCCRUDType [title=西游记]]
			true
	 	浏览器弹窗显示“更新数据成功”后切换到index页面
	 */
	@RequestMapping("update")
	public ModelAndView updateAciton(SpringMVCCRUDMessage vo, SpringMVCCRUDMessageType type) throws Exception {
		vo.setType(type);
		System.out.println(this.service.update(vo));
		ModelAndView mav = new ModelAndView("/SpringMVCCRUD/forward.jsp");
		mav.addObject("msg", "更新数据成功");
		mav.addObject("url", "/index.jsp");
		return mav;
	}

	/* 可通过“http://localhost:8080/cfrWebJava/SpringMVCCRUD/list.action?column="anyColumn"&keyword="anyKeyword"&currentPage=2&lineSize=3”访问
	结果：
		服务端输出
			【分页查询】
			{messageCount=1000, allMessages=[SpringMVCCRUDMessage [mid=4, title=史记4, price=44.4, pubdate=Thu May 17 00:32:00 CST 2018, type=SpringMVCCRUDType [title=历史书籍]], SpringMVCCRUDMessage [mid=5, title=史记5, price=55.5, pubdate=Thu May 17 00:32:00 CST 2018, type=SpringMVCCRUDType [title=历史书籍]], SpringMVCCRUDMessage [mid=6, title=史记6, price=66.6, pubdate=Thu May 17 00:32:00 CST 2018, type=SpringMVCCRUDType [title=历史书籍]]]}
		浏览器弹窗显示“分页查询数据成功”后切换到index页面
	*/
	@RequestMapping("list")
	public ModelAndView listAciton(String column, String keyword, int currentPage, int lineSize) throws Exception {
		System.out.println(this.service.list(column, keyword, currentPage, lineSize));
		ModelAndView mav = new ModelAndView("/SpringMVCCRUD/forward.jsp");
		mav.addObject("msg", "分页查询数据成功");
		mav.addObject("url", "/index.jsp");
		return mav;
	}
	
	// 遗留：这几个类再了解下，为什么直接传日期会报错
	/**
	 * Web数据的转换绑定
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		// 注册一个专门的日期转换器的操作类，并且允许输入的数据为空
		binder.registerCustomEditor(Date.class,	new CustomDateEditor(sdf, true));
	}
}
