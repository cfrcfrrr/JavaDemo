package dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import dao.BaseDao;

@Repository("tmsDao")
public class TmsBaseDaoImpl extends HibernateDaoSupport implements BaseDao{
	protected HibernateTemplate tmsHibernateTemplate;

	@Autowired
	public void setTmsHibernateTemplate(HibernateTemplate tmsHibernateTemplate) {
		this.tmsHibernateTemplate = tmsHibernateTemplate;
		super.setHibernateTemplate(tmsHibernateTemplate);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAll(String hql) throws Exception {
		return tmsHibernateTemplate.find(hql);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List query(String hql,Object value) throws Exception {
		return tmsHibernateTemplate.find(hql, value);
	}
	
	@Override
	public <T> Serializable save(T entity) throws Exception {
		Serializable s = this.getHibernateTemplate().save(entity);
		return s;
	}
	
}
