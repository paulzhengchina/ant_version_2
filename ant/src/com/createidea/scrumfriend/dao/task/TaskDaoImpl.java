package com.createidea.scrumfriend.dao.task;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.FloatType;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.createidea.scrumfriend.dao.BaseDaoImpl;
import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.StatisticsSprintTo;
import com.createidea.scrumfriend.to.StoryTo;
import com.createidea.scrumfriend.to.TaskTo;
import com.createidea.scrumfriend.to.UserTo;

public class TaskDaoImpl extends BaseDaoImpl implements  TaskDao {

	@Override
	public String saveTask(TaskTo task) {
		// TODO Auto-generated method stub
		return (String)this.getHibernateTemplate().save(task);
	}

	@Override
	public float calculateRemainingEffortForSprint(SprintTo sprint) {
		// TODO Auto-generated method stub
		final String sql="select sum(left_effort) as left_effort from task where sprint_id='"+sprint.getId()+"'";
		return calculateRemainingEffort(sql);
	}
	
	
	public float calculateRemainingEffort(final String sql) {
		return (Float) this.getHibernateTemplate().execute(
			new HibernateCallback() {
				public Float doInHibernate(Session session) throws HibernateException {
					SQLQuery query = session.createSQLQuery(sql).addScalar("left_effort", FloatType.INSTANCE);
					Object count = query.uniqueResult();
					if(count==null)
						return Float.valueOf(0);
					else
					    return (Float) count;
				}
			});
	}
	
	public List<TaskTo> getTasksOfStory(String storyId) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TaskTo.class);
		detachedCriteria.add(Restrictions.eq("story.id", storyId ));
		detachedCriteria.addOrder(Order.asc("id"));
	    return (List<TaskTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public TaskTo getTaskById(String taskId) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TaskTo.class);
		detachedCriteria.add(Restrictions.eq("id", taskId ));		
	    List<TaskTo> tasks=(List<TaskTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	    if(tasks!=null&&tasks.size()>0)
	    	return tasks.get(0);
	    else {
			return null;
		}
	}

	@Override
	public void updateTask(TaskTo task) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(task);
	}
	
}
