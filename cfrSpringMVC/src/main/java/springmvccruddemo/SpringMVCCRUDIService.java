package springmvccruddemo;

import java.util.Map;
import java.util.Set;

public interface SpringMVCCRUDIService {
	public boolean insert(SpringMVCCRUDMessage vo) throws Exception;
	public boolean delete(Set<Integer> mids) throws Exception;
	public SpringMVCCRUDMessage get(int mid) throws Exception;
	public boolean update(SpringMVCCRUDMessage vo) throws Exception;
	public Map<String,Object> list(String column, String keyword, int currentPage, int lineSize) throws Exception;
}
