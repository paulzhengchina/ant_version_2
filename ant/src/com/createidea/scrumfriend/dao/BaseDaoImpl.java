package com.createidea.scrumfriend.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.createidea.scrumfriend.utils.Pager;


public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao{
	
    
	public List findResultForPager(String hql,int start,int num){
		
   Configuration config = new Configuration().configure();
   ServiceRegistry resgistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
   SessionFactory  factory = config.buildSessionFactory(resgistry);
   Session session = factory.openSession();
   Query query=session.createQuery(hql);
    query.setFirstResult(start);
    query.setMaxResults(num);
	return query.list();
	}
	
	public int findAllResNum(String hql){
		List results=this.getHibernateTemplate().find(hql);
		if(results!=null&&results.size()>0)
			return results.size();
		else
			return 0;
	}
	
	public int findAllResNum(DetachedCriteria detachedCriteria ){
		List results=this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(results!=null&&results.size()>0)
			return results.size();
		else
			return 0;
	}
	
	public int findAllResNum(Object obj ){
		List results=this.getHibernateTemplate().findByExample(obj);
		if(results!=null&&results.size()>0)
			return results.size();
		else
			return 0;
	}
	
	public List findResultForPager(String hql,Pager pager){
		
		Configuration config = new Configuration().configure();
		   ServiceRegistry resgistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		   SessionFactory  factory = config.buildSessionFactory(resgistry);
		   Session session = factory.openSession();
		   Query query=session.createQuery(hql);
	    query.setFirstResult(pager.getCurrentPage()*pager.getNumForEachPage());
	    query.setMaxResults(pager.getNumForEachPage());
		return query.list();
		}
	
public List findResultForPager(DetachedCriteria detachedCriteria,Pager pager){
		
		return this.getHibernateTemplate().findByCriteria(detachedCriteria, pager.getCurrentPage()*pager.getNumForEachPage(), pager.getNumForEachPage());
	   
		}

public List findResultForPager(Object obj,Pager pager){
	
	return this.getHibernateTemplate().findByExample(obj, pager.getCurrentPage()*pager.getNumForEachPage(), pager.getNumForEachPage());
   
	}
}
