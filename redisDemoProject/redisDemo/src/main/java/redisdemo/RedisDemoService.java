package redisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisDemoService {
	@Autowired
	private RedisDemoDao dao;

	public int addUser(String name, String age) {
		return dao.addUser(name, age);
	}

	public RedisDemoUser findById(String id) {
		return dao.findById(id);
	}

	public void updataById(String id, String name) {
		dao.updataById(id, name);
	}

	public void deleteById(String id) {
		dao.deleteById(id);
	}
}
