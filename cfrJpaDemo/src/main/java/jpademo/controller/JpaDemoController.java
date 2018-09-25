package jpademo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jpademo.entity.JpaDemoEntity;
import jpademo.service.JpaDemoService;

@RestController
public class JpaDemoController {
	@Autowired
	private JpaDemoService jpaDemoService;

	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public JpaDemoEntity addUser(@RequestBody JpaDemoEntity entity) {
		JpaDemoEntity ret = jpaDemoService.saveUser(entity);
		return ret;
	}
	
	// @RequestMapping(value = "/delete/{id}")
	// public void deleteBook(@PathVariable int id)
	// {
	// jpaDemoService.delete(id);
	// }
	//
	// @RequestMapping(value = "/")
	// public List<User> getBooks()
	// {
	// return jpaDemoService.findAll();
	// }
	//
	// @RequestMapping(value = "/{id}")
	// public User getUser(@PathVariable int id)
	// {
	// User user = jpaDemoService.findOne(id);
	// return user;
	// }
	//
	// @RequestMapping(value = "/search/name/{name}")
	// public List<User> getBookByName(@PathVariable String name)
	// {
	// List<User> users = jpaDemoService.findByName(name);
	// return users;
	// }
}