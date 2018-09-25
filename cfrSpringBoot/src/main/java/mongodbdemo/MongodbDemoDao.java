package mongodbdemo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.QueryBuilder;

//遗留：看一下项目代码中，各个层的方法的返回值是什么
@Repository
public class MongodbDemoDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public boolean insert(MongodbDemoVo vo) {
		mongoTemplate.insert(vo);
		return true;
	}
	
	public List<MongodbDemoVo> pageFindByName(String name, Integer page, Integer size) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
//		query.with(new Sort(Sort.Direction.DESC, "studentId"));
		Sort sort = new Sort(Sort.Direction.DESC, "studentId");
		Pageable pageable = new PageRequest(page - 1, size, sort); // 遗留：用什么替代？
		query.with(pageable);
		List<MongodbDemoVo> list = mongoTemplate.find(query, MongodbDemoVo.class);
		return list;
	}
}