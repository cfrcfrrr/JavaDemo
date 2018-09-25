package service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.BaseDao;
import entity.HisTest;
import entity.TmsTest;
import service.BaseService;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class BaseServiceImpl implements BaseService{
	
	@Resource
	private BaseDao tmsDao;
	@Resource
	private BaseDao hisDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findTms() throws Exception {
		/*DataSourceContextHolder.setDataSourceType(DataSourceConst.TMS_STRING);
		String hql = "from MedicalCard";
		return tmsDao.findAll(hql);*/
		String hql = "FROM TmsTest";
		return tmsDao.findAll(hql);
	}
	
	@SuppressWarnings("rawtypes")
	public List findHis() throws Exception{
		/*DataSourceContextHolder.setDataSourceType(DataSourceConst.HIS_STRING);
		String hql = "from Patient where idNo = ?";
		return hisDao.query(hql, idNo);*/
		String hql = "FROM HisTest";
		return hisDao.findAll(hql);
	}

	@Override
	public void save(TmsTest tms, HisTest his) throws Exception {
		tmsDao.save(tms);
		hisDao.save(his);
	}

}
