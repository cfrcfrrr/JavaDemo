package mongodbdemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongodbDemoService {
	@Autowired
	private MongodbDemoRepo mongodbDemoRepo;
	@Autowired
	private MongodbDemoDao mongodbDemoDao;
	
	public void insert(MongodbDemoVo vo) {
		mongodbDemoDao.insert(vo);
	}
	
	public List<MongodbDemoVo> pageFindByName(String name, int page, int size) {
		List<MongodbDemoVo> list = mongodbDemoDao.pageFindByName(name, page, size);
		return list;
	}
	
	public MongodbDemoVo findByStudentId(int studentId) {
		MongodbDemoVo vo = mongodbDemoRepo.findByStudentId(studentId);
		return vo;
	}
	
	public List<MongodbDemoVo> findByNameAndSortByStudentIdDESC(String name){
		List<MongodbDemoVo> list = mongodbDemoRepo.findByNameAndSortByStudentIdDESC(name);
		return list;
	}
}
