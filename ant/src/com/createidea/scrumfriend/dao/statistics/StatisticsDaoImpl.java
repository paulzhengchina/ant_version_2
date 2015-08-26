package com.createidea.scrumfriend.dao.statistics;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.createidea.scrumfriend.dao.BaseDaoImpl;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.StatisticsDateTo;
import com.createidea.scrumfriend.to.StatisticsProjectTo;
import com.createidea.scrumfriend.to.StatisticsSprintTo;

public class StatisticsDaoImpl extends BaseDaoImpl implements StatisticsDao {

	@Override
	public void saveStatistics(StatisticsProjectTo statistics) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(statistics);
	}

	@Override
	public List<StatisticsProjectTo> getProjectStatistics(String projectId) {
		// TODO Auto-generated method stub
		criteria=this.sessionFactory.getCurrentSession().createCriteria(StatisticsProjectTo.class);
		criteria.add(Restrictions.eq("project.id", projectId));
		criteria.addOrder(Order.asc("date"));
		return (List<StatisticsProjectTo>)criteria.list();
	}

	@Override
	public void updateSprintStatistics(StatisticsSprintTo statistics) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(statistics);
	}

	@Override
	public List<StatisticsSprintTo> getSprintsStatisticsForProject(String[] sprintIds) {
		// TODO Auto-generated method stub
		criteria=this.sessionFactory.getCurrentSession().createCriteria(StatisticsSprintTo.class);
		criteria.add(Restrictions.in("sprint.id", sprintIds));
		return criteria.list();
	}

	@Override
	public StatisticsDateTo getStatisticsDateByDate(Date date) {
		// TODO Auto-generated method stub
		criteria=this.sessionFactory.getCurrentSession().createCriteria(StatisticsDateTo.class);
		criteria.add(Restrictions.eq("date", date));
		StatisticsDateTo statisticsDateTo=null;
		Object statisticsObj=criteria.uniqueResult();
		if(statisticsObj!=null)
			statisticsDateTo=(StatisticsDateTo)statisticsObj;
		return statisticsDateTo;
	}

	@Override
	public void saveOrUpdateStatisticsDate(StatisticsDateTo statisticsDate) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().saveOrUpdate(statisticsDate);
	}

	@Override
	public List<StatisticsDateTo> getStatisticsDateForSprint(String sprintId) {
		criteria=this.sessionFactory.getCurrentSession().createCriteria(StatisticsDateTo.class);
		criteria.add(Restrictions.eq("sprint.id", sprintId));
		return (List<StatisticsDateTo>)criteria.list();
	}


}
