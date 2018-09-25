package dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import dao.BaseDao;

@Repository("hisDao")
public class HisBaseDaoImpl extends HibernateDaoSupport implements BaseDao{
	
	protected HibernateTemplate hisHibernateTemplate;
	
	@Autowired
	public void setHisHibernateTemplate(HibernateTemplate hisHibernateTemplate) {
		this.hisHibernateTemplate = hisHibernateTemplate;
		super.setHibernateTemplate(hisHibernateTemplate);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List findAll(String hql) throws Exception {
		return hisHibernateTemplate.find(hql);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List query(String hql,Object value) throws Exception {
		return hisHibernateTemplate.find(hql, value);
	}
	
	@Override
	public <T> Serializable save(T entity) throws Exception {
		Serializable s = this.getHibernateTemplate().save(entity);
		return s;
	}

}
