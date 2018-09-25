package objectassociatedemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import objectassociatedemo.entity.ObjectAssociateDemoEntity;
import objectassociatedemo.service.ObjectAssociateDemoService;

import java.util.List;

@RestController
public class ObjectAssociateDemoController {
	@Autowired
	private ObjectAssociateDemoService objectAssociateDemoService;

	@RequestMapping(value = "/entity", method=RequestMethod.POST)
	public String addEntity(@RequestBody ObjectAssociateDemoEntity entity) {
		ObjectAssociateDemoEntity ret = objectAssociateDemoService.saveUser(entity);
		return ret.toString();
	}

	@RequestMapping(value = "/entity", method=RequestMethod.DELETE)
	public String deleteEntityById(@RequestBody ObjectAssociateDemoEntity entity) {
		objectAssociateDemoService.delete(entity);
		return "success";
	}

	@RequestMapping(value = "/entity/all", method = RequestMethod.GET)
	public String findAll() {
		List<ObjectAssociateDemoEntity> result = objectAssociateDemoService.findAll();
		return result.toString();
	}

	@RequestMapping(value = "/entity", method = RequestMethod.GET)
	public String findById(@RequestParam int id) {
		return objectAssociateDemoService.findById(id).toString();
	}

	@RequestMapping(value = "/entity", method=RequestMethod.PUT)
	public String updateNameById(@RequestParam Integer id, @RequestParam String name) {
		objectAssociateDemoService.updateNameById(id, name);
		return "success";
	}
}