package mongodbdemo;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="mongodbdemo")
public class MongodbDemoController {
	@Autowired
	private MongodbDemoService mongodbDemoService;
	
	// 遗留：URL访问访问时加上日期&birthday=2018-06-10会报错
	@ApiOperation(value="insert vo")
	@RequestMapping(value="/vo", method=RequestMethod.POST)
	public String insert(@RequestBody MongodbDemoVo vo) {
		System.out.println("---insert---");
		mongodbDemoService.insert(vo);
		return "insert success";
	}
	
	@RequestMapping(value="/vo/{name}/{page}/{size}", method=RequestMethod.GET)
	public String pageFindByName(@PathVariable String name, @PathVariable int page, @PathVariable int size) {
		System.out.println("---pageFindByName---");
		List<MongodbDemoVo> list = mongodbDemoService.pageFindByName(name, page, size);
		Iterator<MongodbDemoVo> iter = list.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		return "pageFindByName success";
	}
	
	// 遗留：value="/vo/{studentId}"和value="/vo/{name}"冲突了，怎么解决
//	@RequestMapping(value="/vo/{studentId}", method=RequestMethod.GET)
//	public String findByStudentId(@PathVariable int studentId) {
//		System.out.println("---findByStudentId---");
//		MongodbDemoVo vo = mongodbDemoService.findByStudentId(studentId);
//		System.out.println(vo);
//		return "findByStudentId success";
//	}
	
	@RequestMapping(value="/vo/{name}", method=RequestMethod.GET)
	public String findByNameAndSortByStudentIdDESC(@PathVariable String name) {
		System.out.println("---findByNameAndSortByStudentIdDESC---");
		List<MongodbDemoVo> list = mongodbDemoService.findByNameAndSortByStudentIdDESC(name);
		Iterator<MongodbDemoVo> iter = list.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		return "findByNameAndSortByStudentIdDESC success";
	}
}
