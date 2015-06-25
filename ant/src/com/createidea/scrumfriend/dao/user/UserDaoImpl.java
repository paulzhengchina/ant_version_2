package com.createidea.scrumfriend.dao.user;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.createidea.scrumfriend.dao.BaseDaoImpl;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.TaskTo;
import com.createidea.scrumfriend.to.UserTo;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public UserTo getUserByName(String name) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserTo.class);
		detachedCriteria.add(Restrictions.eq("name", name ));		
	    List<UserTo> userList=(List<UserTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	     if(userList!=null && userList.size()>0)
	    	 return userList.get(0);
	     else
	    	 return null;
	}

	@Override
	public UserTo createUser(String username, String password, String email) {
		// TODO Auto-generated method stub
		UserTo user=new UserTo();
		user.setEmail(email);
		user.setName(username);
		user.setPassword(password);
		String id=(String)this.getHibernateTemplate().save(user);
		return new UserTo(id);
	
	}

	@Override
	public void removeUser(UserTo userTo) {
		// TODO Auto-generated method stub
		UserTo user=getUserByName(userTo.getName());
		if(user!=null)
		  this.getHibernateTemplate().delete(user);
	}

	@Override
	public UserTo getRandomUser() {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserTo.class);
	    List<UserTo> userList=(List<UserTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	    return userList.get(0);
	}

	@Override
	public void saveOrUpdateUser(UserTo user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(user);
	}

	@Override
	public UserTo getUserById(String userId) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserTo.class);
		detachedCriteria.add(Restrictions.eq("id", userId ));	
		List<UserTo> userList=(List<UserTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(userList!=null&&userList.size()>0)
		    return userList.get(0);
		else
			return null;
	}

	@Override
	public void setDefaultProject(String userId, String projectId) {
		UserTo userTo = (UserTo) this.getHibernateTemplate().get(UserTo.class, userId);
		ProjectTo project = new ProjectTo();
		project.setId(projectId);
		userTo.setDefaultProject(project);
		saveOrUpdateUser(userTo);
	}

	@Override
	public UserTo getUserByEmail(String userEmail) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserTo.class);
		detachedCriteria.add(Restrictions.eq("email", userEmail ));	
		List<UserTo> userList=(List<UserTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if(userList!=null&&userList.size()>0)
		    return userList.get(0);
		else
			return null;
	}

	@Override
	public List<UserTo> getAllUsers() {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserTo.class);
		return (List<UserTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

}
