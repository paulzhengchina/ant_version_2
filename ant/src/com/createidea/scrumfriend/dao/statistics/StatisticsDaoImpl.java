package com.createidea.scrumfriend.dao.statistics;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.createidea.scrumfriend.dao.BaseDaoImpl;
import com.createidea.scrumfriend.to.StatisticsDateTo;
import com.createidea.scrumfriend.to.StatisticsProjectTo;
import com.createidea.scrumfriend.to.StatisticsSprintTo;

public class StatisticsDaoImpl extends BaseDaoImpl implements StatisticsDao {

	@Override
	public void saveStatistics(StatisticsProjectTo statistics) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(statistics);
	}

	@Override
	public List<StatisticsProjectTo> getProjectStatistics(String projectId) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StatisticsProjectTo.class);
		detachedCriteria.add(Restrictions.eq("project.id", projectId));
		detachedCriteria.addOrder(Order.asc("date"));
		return (List<StatisticsProjectTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public void updateSprintStatistics(StatisticsSprintTo statistics) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(statistics);
	}

	@Override
	public List<StatisticsSprintTo> getSprintsStatisticsForProject(String projectId) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StatisticsSprintTo.class);
		detachedCriteria.createCriteria("sprint").add(Restrictions.eq("project.id", projectId));
		return (List<StatisticsSprintTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public StatisticsDateTo getStatisticsDateByDate(Date date) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StatisticsDateTo.class);
		detachedCriteria.add(Restrictions.eq("date", date));
		List<StatisticsDateTo> statistics=(List<StatisticsDateTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(statistics!=null&&statistics.size()>0)
			return statistics.get(0);
		else
			return null;
	}

	@Override
	public void saveOrUpdateStatisticsDate(StatisticsDateTo statisticsDate) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(statisticsDate);
	}

	@Override
	public List<StatisticsDateTo> getStatisticsDateForSprint(String sprintId) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StatisticsDateTo.class);
		detachedCriteria.add(Restrictions.eq("sprint.id", sprintId));
		return (List<StatisticsDateTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}


}
