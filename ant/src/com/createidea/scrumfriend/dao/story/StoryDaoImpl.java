package com.createidea.scrumfriend.dao.story;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.FloatType;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.createidea.scrumfriend.dao.BaseDaoImpl;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.StoryTo;

public class StoryDaoImpl extends BaseDaoImpl implements  StoryDao {

	@Override
	public List<StoryTo> storysByStatus(int status) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoryTo.class);
		detachedCriteria.add(Restrictions.eq("status", status));
		detachedCriteria.addOrder(Order.asc("priority"));
		return (List<StoryTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}
	
	@Override
	public void updateStory(StoryTo story) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(story);
	}

	@Override
	public StoryTo getStoryById(String card_id) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoryTo.class);
		detachedCriteria.add(Restrictions.eq("id", card_id));
		List storiesList=this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(storiesList!=null && storiesList.size()>0)
		   return (StoryTo)this.getHibernateTemplate().findByCriteria(detachedCriteria).get(0);
		else 
		   return null;
	}

	@Override
	public List<StoryTo> getActiveStoriesForProject(String projectId) {
		// TODO Auto-generated method stub
		ArrayList statusList=new ArrayList<Integer>();
		statusList.add(0);
		statusList.add(1);
		statusList.add(2);
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoryTo.class);
		detachedCriteria.add(Restrictions.eq("project.id", projectId));
		detachedCriteria.add(Restrictions.in("status", statusList));
		detachedCriteria.addOrder(Order.asc("priorityNum"));
		detachedCriteria.addOrder(Order.asc("priority"));
		detachedCriteria.addOrder(Order.desc("businessValue"));
		try {
			return (List<StoryTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String createStory(StoryTo story) {
		// TODO Auto-generated method stub
		return (String)this.getHibernateTemplate().save(story);
	}

	@Override
	public List<StoryTo> getStoriesForProjectByStatus(String projectId, int status) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoryTo.class);
		detachedCriteria.add(Restrictions.eq("project.id", projectId));
		detachedCriteria.add(Restrictions.eq("status", status));
		detachedCriteria.addOrder(Order.desc("priority"));
		return (List<StoryTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
		
	}

	@Override
	public float calculateTotalPointsForProject(ProjectTo project) {
		// TODO Auto-generated method stub
         final String sql="select sum(point) as point from story where project_id='"+project.getId()+"'"+"and status in (0,1)";
		 return calculateStoryPoint(sql);
	}

	@Override
	public float calculateCompletedPointForProject(ProjectTo project) {
		// TODO Auto-generated method stub
		final String sql="select sum(point) as point from story where status=1 and project_id='"+project.getId()+"'";
		return calculateStoryPoint(sql);
	}

	@Override
	public float calculateRemainingPointForProject(ProjectTo project) {
		// TODO Auto-generated method stub
		final String sql="select sum(point) as point from story where status=0 and project_id='"+project.getId()+"'";
		return calculateStoryPoint(sql);
	}
	

	@Override
	public float calculateCommittedStoryPoint(String sprintId) {
		// TODO Auto-generated method stub
		final String sql="select sum(point) as point from story where sprint_id='"+sprintId+"'" +"and status in (0,1)";
		return calculateStoryPoint(sql);
	}

	@Override
	public float calculateCompletedStoryPoint(String sprintId) {
		// TODO Auto-generated method stub
		final String sql="select sum(point) as point from story where status=1 and sprint_id='"+sprintId+"'";
		return calculateStoryPoint(sql);
	}
	
	public float calculateStoryPoint(final String sql) {
		return (Float) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Float doInHibernate(Session session) throws HibernateException {
						SQLQuery query = session.createSQLQuery(sql).addScalar("point", FloatType.INSTANCE);
						Object count = query.uniqueResult();
						if(count==null)
							return Float.valueOf(0);
						else
						    return (Float) count;
					}
				});
	}

	@Override
	public List<StoryTo> getStoriesForSprintByStatus(String sprintId, int status) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoryTo.class);
		detachedCriteria.add(Restrictions.eq("sprint.id", sprintId));
		detachedCriteria.add(Restrictions.eq("status", status));
		detachedCriteria.addOrder(Order.desc("priority"));
		return (List<StoryTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public List getStoriesForProjectBySearchWithName(String projectId,
			String query) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoryTo.class);
		detachedCriteria.add(Restrictions.eq("project.id", projectId));
		if(query!=null && !("".equals(query)))
		detachedCriteria.add(Restrictions.ilike("name", query, MatchMode.ANYWHERE));
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);

	}

	@Override
	public List<StoryTo> getStoriesForKanban(String sprintId) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(StoryTo.class);
		detachedCriteria.add(Restrictions.eq("sprint.id", sprintId));
		detachedCriteria.add(Restrictions.in("status", new Integer[]{0,1} ));
		detachedCriteria.addOrder(Order.desc("priority"));
		return (List<StoryTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public float calculateStoriesTotalPointByPriority(int priority,String projectId) {
		// TODO Auto-generated method st
		String sqlString="select sum(point) as point from story where status=1 and project_id='"+projectId+"'";
		return calculateStoryPoint(sqlString);
	}

	@Override
	public float calculateStoryPoints(String projectId, int status,int priority) {
		// TODO Auto-generated method stub
		String sqlString="select sum(point) as point from story where status='"+status+ "' and project_id='"+projectId+"' and priority='"+priority+"'" ;
		return calculateStoryPoint(sqlString);
	}

	
}
