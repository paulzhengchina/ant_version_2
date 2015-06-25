package com.createidea.scrumfriend.to;

import java.util.Date;

public class BlogTo {

	private String id;
	private String title;
	private String content;
	private Date createdTime;
	private UserTo author;
	private String summary;
	private Integer recommend; // 1:yes 2:No
	private AttachmentTo logo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public UserTo getAuthor() {
		return author;
	}

	public void setAuthor(UserTo author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public AttachmentTo getLogo() {
		return logo;
	}

	public void setLogo(AttachmentTo logo) {
		this.logo = logo;
	}
	
	
		
}
