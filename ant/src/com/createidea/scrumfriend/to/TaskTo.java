package com.createidea.scrumfriend.to;

public class TaskTo {
	private String id;
	private String title;
	private String description;
	private UserTo performer;
	private float leftEffort;
	private float totalEffort;
	private int status;
	private StoryTo story;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserTo getPerformer() {
		return performer;
	}

	public void setPerformer(UserTo performer) {
		this.performer = performer;
	}

	public float getLeftEffort() {
		return leftEffort;
	}

	public void setLeftEffort(float leftEffort) {
		this.leftEffort = leftEffort;
	}

	public float getTotalEffort() {
		return totalEffort;
	}

	public void setTotalEffort(float totalEffort) {
		this.totalEffort = totalEffort;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public StoryTo getStory() {
		return story;
	}

	public void setStory(StoryTo story) {
		this.story = story;
	}

	

}
