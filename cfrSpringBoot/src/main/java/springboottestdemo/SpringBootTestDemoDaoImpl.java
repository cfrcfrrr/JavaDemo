package springboottestdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpringBootTestDemoDaoImpl {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void test() {
		System.out.println("------------------------dao start success.---------------------");
		SpringBootTestDemoBean vo = new SpringBootTestDemoBean();
		vo.setName("aaa");
		mongoTemplate.save(vo);
	}
}
