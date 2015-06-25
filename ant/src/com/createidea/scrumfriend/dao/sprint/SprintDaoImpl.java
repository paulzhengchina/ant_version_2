package com.createidea.scrumfriend.dao.sprint;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.createidea.scrumfriend.dao.BaseDaoImpl;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.StoryTo;

public class SprintDaoImpl extends BaseDaoImpl implements  SprintDao {


	@Override
	public void updateSprint(SprintTo sprint) {		
		this.getHibernateTemplate().saveOrUpdate(sprint);
		
	}

	@Override
	public SprintTo getSprintById(String sprintId) {
		SprintTo sprintTo = (SprintTo) this.getHibernateTemplate().get(SprintTo.class, sprintId);
		return sprintTo;
	}

	@Override
	public List<SprintTo> getSprintForProject(String projectId) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SprintTo.class);
		detachedCriteria.add(Restrictions.eq("project.id", projectId));
		detachedCriteria.addOrder(Order.asc("startTime"));
		return (List<SprintTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public SprintTo createSprint(SprintTo sprint,String projectId) {
		sprint.setProject(new ProjectTo(projectId));
		String id=(String)this.getHibernateTemplate().save(sprint);
		return getSprintById(id);
	}

	@Override
	public List<SprintTo> getSprintsForProjectByStatus(String projectId,
			int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSprint(String sprintId) {
		SprintTo sprint = new SprintTo();
		sprint.setId(sprintId);
		this.getHibernateTemplate().delete(sprint);
		
	}

	public List<SprintTo> getCurrentSprints(String projectId) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SprintTo.class);
		detachedCriteria.add(Restrictions.eq("project.id", projectId));
		detachedCriteria.add(Restrictions.le("startTime", getPureDateOfToday()));
		detachedCriteria.add(Restrictions.ge("endTime", getLatestDateOfToday()));
		List<SprintTo> sprints=(List<SprintTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
		return sprints;
	}
	
	private Date getPureDateOfToday(){
		Calendar time=Calendar.getInstance();
		time.set(Calendar.HOUR_OF_DAY, 23);
		time.set(Calendar.MINUTE, 59);
		time.set(Calendar.SECOND, 59);
		return time.getTime();
	}
	
	private Date getLatestDateOfToday(){
		Calendar time=Calendar.getInstance();
		time.set(Calendar.DATE,time.get(Calendar.DATE)-1);
		time.set(Calendar.HOUR_OF_DAY, 23);
		time.set(Calendar.MINUTE, 59);
		time.set(Calendar.SECOND,59);		
		return time.getTime();
	}
	
	private Date getEarlistTimeOfToday(){
		Calendar time=Calendar.getInstance();
		time.set(Calendar.HOUR, 0);
		time.set(Calendar.MINUTE, 0);
		time.set(Calendar.SECOND, 1);
		return time.getTime();
	}
	
	@Override
	public List<SprintTo> getSprintsFinishedYesterday(Date today,Date twoDaysAgo) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SprintTo.class);
		detachedCriteria.add(Restrictions.gt("endTime", twoDaysAgo));
		detachedCriteria.add(Restrictions.lt("endTime", today));
		return (List<SprintTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public List<SprintTo> getParentSprints(String projectId) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SprintTo.class);
		detachedCriteria.add(Restrictions.eq("project.id", projectId));
		detachedCriteria.add(Restrictions.isNull("parentSprint"));
		detachedCriteria.addOrder(Order.asc("startTime"));
		return (List<SprintTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}
	
}
