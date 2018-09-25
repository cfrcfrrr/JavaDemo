package service;

import java.util.List;

import entity.HisTest;
import entity.TmsTest;

public interface BaseService {
	@SuppressWarnings("rawtypes")
	public List findTms() throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List findHis() throws Exception;
	
	public void save(TmsTest tms, HisTest his) throws Exception;
	
}
