package dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao{
	@SuppressWarnings("rawtypes")
	public List findAll(String hql) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List query(String hql, Object value) throws Exception;
	
	public <T> Serializable save(T entity) throws Exception;
}
