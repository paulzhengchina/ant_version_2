package com.createidea.scrumfriend.struts.action;

import java.io.File;
import java.util.List;

import com.createidea.scrumfriend.service.attachment.AttachmentService;
import com.createidea.scrumfriend.service.blog.BlogService;
import com.createidea.scrumfriend.to.AttachmentTo;
import com.createidea.scrumfriend.to.BlogTo;

public class BlogAction extends BaseAction {
	private BlogService blogService;
	private BlogTo blog;
	private List<BlogTo> blogList;
	private List<BlogTo> recommendBlogList;
	private String blogid;
	private File image;
	private String imageContentType;
	private String imageFileName;
	private AttachmentService attachmentService;
	private String updatedLogoPath;
	private String attachmentId;

	
	public String displayAllBlogs(){
		blogList=blogService.getBlogs();
		recommendBlogList=blogService.getRecommedBlogs();
		return SUCCESS;
	}
	
	public String createBlog(){
		return SUCCESS;
	}
    
	public String saveBlog(){
		blogService.saveBlog(blog);
		return SUCCESS;
	}
	
	public String editBlog(){
		blog=blogService.getBlog(blogid);
		return SUCCESS;
	}
	
	public String readBlog(){
		blog=blogService.getBlog(blogid);
		recommendBlogList=blogService.getRecommedBlogs();
		return SUCCESS;
	}
	
	public String uploadLogo()
	{
		AttachmentTo logo=attachmentService.saveAttachment(image, imageContentType, imageFileName, getApplicationRootPath());
		updatedLogoPath=logo.getPath();  
    	attachmentId=logo.getId();
    	return SUCCESS;
	}
	
	/*
	 * get and set methods start here
	 */
	public BlogTo getBlog() {
		return blog;
	}

	public void setBlog(BlogTo blog) {
		this.blog = blog;
	}

	public List<BlogTo> getBlogList() {
		return blogList;
	}

	public void setBlogList(List<BlogTo> blogList) {
		this.blogList = blogList;
	}
	
	public BlogService getBlogService() {
		return blogService;
	}

	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	public String getBlogid() {
		return blogid;
	}

	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}

	public List<BlogTo> getRecommendBlogList() {
		return recommendBlogList;
	}

	public void setRecommendBlogList(List<BlogTo> recommendBlogList) {
		this.recommendBlogList = recommendBlogList;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public AttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public String getUpdatedLogoPath() {
		return updatedLogoPath;
	}

	public void setUpdatedLogoPath(String updatedLogoPath) {
		this.updatedLogoPath = updatedLogoPath;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}
	
	
	
}
