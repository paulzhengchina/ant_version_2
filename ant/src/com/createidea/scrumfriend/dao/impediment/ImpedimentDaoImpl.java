package com.createidea.scrumfriend.dao.impediment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.createidea.scrumfriend.dao.BaseDaoImpl;
import com.createidea.scrumfriend.to.BlogTo;
import com.createidea.scrumfriend.to.ImpedimentTo;
import com.createidea.scrumfriend.to.StoryTo;


public class ImpedimentDaoImpl extends BaseDaoImpl implements ImpedimentDao {

	@Override
	public List<ImpedimentTo> getAllImpediments(String projectId) {
		// TODO Auto-generated method stub
		ArrayList<Integer> statusList=new ArrayList<Integer>();
		statusList.add(0);
		statusList.add(1);
		statusList.add(2);
		statusList.add(3);
		criteria=this.sessionFactory.getCurrentSession().createCriteria(ImpedimentTo.class);
		criteria.add(Restrictions.eq("project.id", projectId));
		criteria.add(Restrictions.in("status", statusList));
		criteria.addOrder(Order.asc("status"));
		criteria.addOrder(Order.asc("severity"));		
		return (List<ImpedimentTo>)criteria.list();
	}

	@Override
	public ImpedimentTo saveImpediment(ImpedimentTo impediment) {
		// TODO Auto-generated method stub
		if(impediment.getId()==null||impediment.getId()=="")
		{
			String impedimentId=(String)this.sessionFactory.getCurrentSession().save(impediment);
			return getImpediment(impedimentId);
		}
		else{
			this.sessionFactory.getCurrentSession().saveOrUpdate(impediment);
	        return impediment;
		}
	}

	@Override
	public ImpedimentTo getImpediment(String id) {
		// TODO Auto-generated method stubc
		criteria=this.sessionFactory.getCurrentSession().createCriteria(ImpedimentTo.class);
		criteria.add(Restrictions.eq("id", id));
		ImpedimentTo impediment=null;
		Object impedimentObj=criteria.uniqueResult();
		if(impedimentObj!=null)
			impediment=(ImpedimentTo)impedimentObj;
		return impediment;
			
	}

	@Override
	public List<ImpedimentTo> searchImpedimentsByConditions(Integer[] filteredSatuses, Integer[] filteredseverities, String projectId) {
		// TODO Auto-generated method stub
		criteria=this.sessionFactory.getCurrentSession().createCriteria(ImpedimentTo.class);
		criteria.add(Restrictions.eq("project.id", projectId));
		if(filteredSatuses.length>0)
			criteria.add(Restrictions.in("status", filteredSatuses));
		if(filteredseverities.length>0)
			criteria.add(Restrictions.in("severity", filteredseverities));
		criteria.addOrder(Order.asc("status"));
		criteria.addOrder(Order.asc("severity"));		
		return (List<ImpedimentTo>)criteria.list();
	}

	@Override
	public int getImpedimentsCountByStatusAndSevrity(String projectId,int status, int severity) {
		// TODO Auto-generated method stub
		criteria=this.sessionFactory.getCurrentSession().createCriteria(ImpedimentTo.class);
		criteria.add(Restrictions.eq("project.id", projectId));
		criteria.add(Restrictions.eq("status", status));
		criteria.add(Restrictions.eq("severity", severity));
		List impediments=criteria.list();
		int count=0;
		if(impediments!=null)
			count=impediments.size();
		return count;
	}
	
	

	
}
