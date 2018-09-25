package mongodbdemo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongodbDemoRepo extends MongoRepository<MongodbDemoVo, ObjectId>{
	public MongodbDemoVo findByStudentId(Integer studentId);
	
	// 遗留：没有用啊
	@Query("{'name':?0}.sort({'studentId':-1})")
	public List<MongodbDemoVo> findByNameAndSortByStudentIdDESC(String name);
}
