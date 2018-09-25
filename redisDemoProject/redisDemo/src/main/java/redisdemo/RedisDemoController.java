package redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisDemoController {
	@Autowired
	private RedisDemoService service;
	
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public String addUser(@RequestParam String name, @RequestParam String age) {
		int result = service.addUser(name, age);
		return String.valueOf(result);
	}

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public String findById(@RequestParam String id) {
		RedisDemoUser result = service.findById(id);
		return result.toString();
	}

	@RequestMapping(value = "/user/", method = RequestMethod.PUT)
	public String updataById(@RequestParam String id, @RequestParam String name){
		service.updataById(id, name);
		return "success";
	}

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public String deleteById(@RequestParam String id){
		service.deleteById(id);
		return "success";
	}
}
