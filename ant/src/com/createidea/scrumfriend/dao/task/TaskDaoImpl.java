package com.createidea.scrumfriend.dao.task;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.createidea.scrumfriend.dao.BaseDaoImpl;
import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.TaskTo;

public class TaskDaoImpl extends BaseDaoImpl implements  TaskDao {

	@Override
	public String saveTask(TaskTo task) {
		// TODO Auto-generated method stub
		return (String)this.sessionFactory.getCurrentSession().save(task);
	}

	@Override
	public float calculateRemainingEffortForSprint(SprintTo sprint) {
		// TODO Auto-generated method stub
		final String sql="select sum(left_effort) as left_effort from task where sprint_id='"+sprint.getId()+"'";
		return calculateRemainingEffort(sql);
	}
	
	
	public float calculateRemainingEffort(final String sql) {
		float count=0;
		Object countObj=this.sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult();
		if(countObj!=null)
			count=((BigDecimal)countObj).floatValue();
		return count;
	}
	
	public List<TaskTo> getTasksOfStory(String storyId) {
		criteria=this.sessionFactory.getCurrentSession().createCriteria(TaskTo.class);
		criteria.add(Restrictions.eq("story.id", storyId ));
		criteria.addOrder(Order.asc("id"));
	    return (List<TaskTo>)criteria.list();
	}

	@Override
	public TaskTo getTaskById(String taskId) {
		// TODO Auto-generated method stub
		criteria=this.sessionFactory.getCurrentSession().createCriteria(TaskTo.class);
		criteria.add(Restrictions.eq("id", taskId ));		
	    TaskTo task=null;
	    Object taskObj=criteria.uniqueResult();
	    if(taskObj!=null)
	    	task=(TaskTo)taskObj;
	    return task;
	}

	@Override
	public void updateTask(TaskTo task) {
		// TODO Auto-generated method stub
		this.saveTask(task);
	}
	
}
