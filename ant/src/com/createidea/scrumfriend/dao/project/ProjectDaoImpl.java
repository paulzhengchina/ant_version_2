package com.createidea.scrumfriend.dao.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;




import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.createidea.scrumfriend.dao.BaseDaoImpl;
import com.createidea.scrumfriend.dao.user.UserDao;
import com.createidea.scrumfriend.to.ImpedimentTo;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.UserTo;

public class ProjectDaoImpl extends BaseDaoImpl implements  ProjectDao {
    
	@Override
	public void saveProject(ProjectTo project) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(project);
	}

	@Override
	public List<ProjectTo> getProjects(String userId){
		// TODO Auto-generated method stub
		criteria=this.sessionFactory.getCurrentSession().createCriteria(ProjectTo.class);
		criteria.createAlias("users", "users");
		criteria.add(Restrictions.eq("users.id", userId ));	
		criteria.add(Restrictions.eq("status", 1));
		return criteria.list();
	}

	@Override
	public void deleteProject(ProjectTo project) {
		project.setStatus(ProjectTo.DELETED_STATUS);
		updateProject(project);
	}

	@Override
	public ProjectTo getProjectById(String id) {
		ProjectTo projectTo=null;
		criteria=this.sessionFactory.getCurrentSession().createCriteria(ProjectTo.class);
		criteria.add(Restrictions.eq("id", id ));
		Object projectObj=criteria.uniqueResult();
		if(projectObj!=null)
			projectTo=(ProjectTo)projectObj;
		return projectTo;
	}
	
	public void updateProject(ProjectTo projectTo){		
		saveProject(projectTo);
	}
}
