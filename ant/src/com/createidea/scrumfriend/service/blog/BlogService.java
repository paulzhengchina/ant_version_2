package com.createidea.scrumfriend.service.blog;

import java.util.List;

import com.createidea.scrumfriend.to.BlogTo;

public interface BlogService {

	public void saveBlog(BlogTo blog);

	public List<BlogTo> getBlogs();

	public BlogTo getBlog(String blogId);

	public List<BlogTo> getRecommedBlogs();

}
