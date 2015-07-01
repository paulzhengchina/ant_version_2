package com.createidea.scrumfriend.dao.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.createidea.scrumfriend.dao.BaseDaoImpl;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.UserTo;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private SessionFactory sessionFactory;
	@Override
	public UserTo getUserByName(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserTo.class);
		criteria.add(Restrictions.eq("name", name ));
		UserTo user=null;
	    Object userObj= criteria.uniqueResult();
	    if(userObj !=null)
	    	user=(UserTo)userObj;
	    return user;
	}

	@Override
	public UserTo createUser(String username, String password, String email) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		UserTo user=new UserTo();
		user.setEmail(email);
		user.setName(username);
		user.setPassword(password);
		String id=(String)session.save(user);
		return new UserTo(id);
	
	}

	@Override
	public void removeUser(UserTo userTo) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		UserTo user=getUserByName(userTo.getName());
		if(user!=null)
			session.delete(user);
	}

	@Override
	public UserTo getRandomUser() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserTo.class);		
	    List<UserTo> userList=(List<UserTo>)criteria.list();
	    return userList.get(0);
	}

	@Override
	public void saveOrUpdateUser(UserTo user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}

	@Override
	public UserTo getUserById(String userId) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserTo.class);
		criteria.add(Restrictions.eq("id", userId ));		
		UserTo user=null;
	    Object userObj= criteria.uniqueResult();
	    if(userObj !=null)
	    	user=(UserTo)userObj;
	    return user;
	}

	@Override
	public void setDefaultProject(String userId, String projectId) {
		UserTo userTo = getUserById(userId);
		ProjectTo project = new ProjectTo();
		project.setId(projectId);
		userTo.setDefaultProject(project);
		saveOrUpdateUser(userTo);
	}

	@Override
	public UserTo getUserByEmail(String userEmail) {
		// TODO Auto-generated method stub	
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserTo.class);
		Object userObjs= criteria.list();		
		criteria.add(Restrictions.eq("email", userEmail ));		
		UserTo user=null;
	    Object userObj= criteria.uniqueResult();
	    if(userObj !=null)
	    	user=(UserTo)userObj;
	    return user;
	     
	}

	@Override
	public List<UserTo> getAllUsers() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserTo.class);
		return criteria.list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	

}
