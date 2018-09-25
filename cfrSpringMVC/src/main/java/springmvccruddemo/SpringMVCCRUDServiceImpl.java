package springmvccruddemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SpringMVCCRUDServiceImpl implements SpringMVCCRUDIService {

	public boolean insert(SpringMVCCRUDMessage vo) throws Exception {
		System.out.println("【插入数据】" + vo);
		return true;
	}

	public boolean delete(Set<Integer> mids) throws Exception {
		System.out.println("【删除数据】" + mids);
		return true;
	}

	public SpringMVCCRUDMessage get(int mid) throws Exception {
		System.out.println("【查询数据】"); // 遗留：打印这行语句是要干嘛？
		SpringMVCCRUDMessage vo = new SpringMVCCRUDMessage();
		vo.setMid(mid);
		vo.setPrice(11.1);
		vo.setPubdate(new Date());
		vo.setTitle("三毛流浪记");
		SpringMVCCRUDMessageType type = new SpringMVCCRUDMessageType();
		type.setTitle("小说");
		vo.setType(type);
		return vo;
	}

	public boolean update(SpringMVCCRUDMessage vo) throws Exception {
		System.out.println("【更新数据】" + vo);
		return true;
	}

	public Map<String, Object> list(String column, String keyword, int currentPage, int lineSize) throws Exception {
		System.out.println("【分页查询】");
		Map<String, Object> map = new HashMap<String, Object>(); // 错误：需要使用子类HashMap来实例化父类对象，不能直接使用Map
		List<SpringMVCCRUDMessage> li = new ArrayList<SpringMVCCRUDMessage>();
		for(int i = (currentPage - 1) * lineSize + 1; i < currentPage * lineSize + 1; i ++) {
			SpringMVCCRUDMessage vo = new SpringMVCCRUDMessage();
			vo.setMid(i);
			vo.setPrice(i * 11.1);
			vo.setPubdate(new Date());
			vo.setTitle("史记" + i);
			SpringMVCCRUDMessageType type = new SpringMVCCRUDMessageType();
			type.setTitle("历史书籍");
			vo.setType(type);
			li.add(vo);
		}
		map.put("allMessages", li);
		map.put("messageCount", 1000);
		return map;
	}

}
