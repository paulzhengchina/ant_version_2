package com.createidea.scrumfriend.dao.blog;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.createidea.scrumfriend.dao.BaseDaoImpl;
import com.createidea.scrumfriend.to.BlogTo;

public class BlogDaoImpl extends BaseDaoImpl implements BlogDao {

	@Override
	public void saveBlog(BlogTo blog) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(blog);
	}

	@Override
	public List<BlogTo> getBlogs() {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BlogTo.class);
		detachedCriteria.addOrder(Order.desc("createdTime"));
		return (List<BlogTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public BlogTo getBlog(String blogId) {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BlogTo.class);
		detachedCriteria.add(Restrictions.eq("id", blogId));
        List<BlogTo> blogs=(List<BlogTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if(blogs!=null&&blogs.size()>0)
        	return blogs.get(0);
        else {
			return null;
		}
	}

	@Override
	public List<BlogTo> getRecommedBlogs() {
		// TODO Auto-generated method stub
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BlogTo.class);
		detachedCriteria.add(Restrictions.eq("recommend", 1));
		detachedCriteria.addOrder(Order.desc("createdTime"));
		return (List<BlogTo>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	

}
