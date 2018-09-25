package cfrRedisDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisDemoController {
	@Autowired
	private RedisDemoService service;
	
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public String addUser(@RequestParam String name, @RequestParam String age) {
		int result = service.addUser(name, age);
		return String.valueOf(result);
	}
}
