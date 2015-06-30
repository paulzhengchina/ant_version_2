package com.createidea.scrumfriend.dao.blog;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.createidea.scrumfriend.dao.BaseDaoImpl;
import com.createidea.scrumfriend.to.BlogTo;
import com.createidea.scrumfriend.to.ProjectTo;

public class BlogDaoImpl extends BaseDaoImpl implements BlogDao {

	@Override
	public void saveBlog(BlogTo blog) {
		// TODO Auto-generated method stub
		this.getSessionFactory().getCurrentSession().saveOrUpdate(blog);
	}

	@Override
	public List<BlogTo> getBlogs() {
		// TODO Auto-generated method stub
		criteria=this.sessionFactory.getCurrentSession().createCriteria(BlogTo.class);
		criteria.addOrder(Order.desc("createdTime"));
		return (List<BlogTo>)criteria.list();
	}

	@Override
	public BlogTo getBlog(String blogId) {
		// TODO Auto-generated method stub
		criteria=this.sessionFactory.getCurrentSession().createCriteria(BlogTo.class);
		criteria.add(Restrictions.eq("id", blogId ));
		BlogTo blog=null;
		Object blogObj=criteria.uniqueResult();
		if(blogObj!=null)
			blog=(BlogTo)blogObj;
		return blog;
	}

	@Override
	public List<BlogTo> getRecommedBlogs() {
		// TODO Auto-generated method stub
		criteria=this.sessionFactory.getCurrentSession().createCriteria(BlogTo.class);
		criteria.add(Restrictions.eq("recommend", 1));
		criteria.addOrder(Order.desc("createdTime"));
		return (List<BlogTo>)criteria.list();
	}

	

}
