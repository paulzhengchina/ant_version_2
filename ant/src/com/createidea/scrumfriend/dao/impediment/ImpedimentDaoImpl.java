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
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ImpedimentTo.class);
		detachedCriteria.add(Restrictions.eq("project.id", projectId));
		detachedCriteria.add(Restrictions.in("status", statusList));
		detachedCriteria.addOrder(Order.asc("status"));
		detachedCriteria.addOrder(Order.asc("severity"));		
		return (List<ImpedimentTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public ImpedimentTo saveImpediment(ImpedimentTo impediment) {
		// TODO Auto-generated method stub
		if(impediment.getId()==null||impediment.getId()=="")
		{
			String impedimentId=(String)this.getHibernateTemplate().save(impediment);
			return getImpediment(impedimentId);
		}
		else{
			this.getHibernateTemplate().saveOrUpdate(impediment);
	        return impediment;
		}
	}

	@Override
	public ImpedimentTo getImpediment(String id) {
		// TODO Auto-generated method stubc
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ImpedimentTo.class);
		detachedCriteria.add(Restrictions.eq("id", id));
		List<ImpedimentTo> impediments=(List<ImpedimentTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(impediments!=null&&impediments.size()>0)
			return impediments.get(0);
		else
			return null;
			
	}

	@Override
	public List<ImpedimentTo> searchImpedimentsByConditions(Integer[] filteredSatuses, Integer[] filteredseverities, String projectId) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ImpedimentTo.class);
		detachedCriteria.add(Restrictions.eq("project.id", projectId));
		if(filteredSatuses.length>0)
		detachedCriteria.add(Restrictions.in("status", filteredSatuses));
		if(filteredseverities.length>0)
		detachedCriteria.add(Restrictions.in("severity", filteredseverities));
		detachedCriteria.addOrder(Order.asc("status"));
		detachedCriteria.addOrder(Order.asc("severity"));		
		return (List<ImpedimentTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public int getImpedimentsCountByStatusAndSevrity(String projectId,int status, int severity) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ImpedimentTo.class);
		detachedCriteria.add(Restrictions.eq("project.id", projectId));
		detachedCriteria.add(Restrictions.eq("status", status));
		detachedCriteria.add(Restrictions.eq("severity", severity));
		return this.getHibernateTemplate().findByCriteria(detachedCriteria).size();
	}
	
	

	
}
