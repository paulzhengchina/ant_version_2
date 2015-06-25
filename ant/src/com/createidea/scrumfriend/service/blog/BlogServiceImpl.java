package com.createidea.scrumfriend.service.blog;

import java.util.Date;
import java.util.List;

import com.createidea.scrumfriend.dao.blog.BlogDao;
import com.createidea.scrumfriend.to.BlogTo;

public class BlogServiceImpl implements BlogService {
    private BlogDao blogDao;

	public BlogDao getBlogDao() {
		return blogDao;
	}

	public void setBlogDao(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

	@Override
	public void saveBlog(BlogTo blog) {
		// TODO Auto-generated method stub
		blog.setAuthor(null);
		blog.setCreatedTime(new Date());
		blogDao.saveBlog(blog);
	}

	@Override
	public List<BlogTo> getBlogs() {
		// TODO Auto-generated method stub
		return blogDao.getBlogs();
	}

	@Override
	public BlogTo getBlog(String blogId) {
		// TODO Auto-generated method stub
		return blogDao.getBlog(blogId);
	}

	@Override
	public List<BlogTo> getRecommedBlogs() {
		// TODO Auto-generated method stub
		return blogDao.getRecommedBlogs();
	}
    
    
    
}
