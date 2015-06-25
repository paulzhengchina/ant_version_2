package com.createidea.scrumfriend.to;

import java.util.Date;

public class ImpedimentTo {

	private String id;
	private String name;
	private String description;
	private UserTo createdBy;
	private UserTo fixedBy;
	private String solution;
	private String reason;
	private String result;
	private Date createdTime;
	private Date fixedTime;
	private Integer severity;//0:low;1:normal;2:high;3:emergent
	private Integer status; //0:waiting;1:on process;2:fixed;3:failed;4:deleted
	private ProjectTo project;
	
	public static final int SEVERITY_EMERGENT=3;
	public static final int SEVERITY_HIGH=2;
	public static final int SEVERITY_NORMAL=1;
	public static final int SEVERITY_LOW=0;
	
	public static final int STATUS_WAITING=0;
	public static final int STATUS_ONGOING=1;
	public static final int STATUS_COMPLETED=2;
	public static final int STATUS_FAILED=3;
	public static final int STATUS_DELETED=4;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UserTo getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserTo createdBy) {
		this.createdBy = createdBy;
	}
	public UserTo getFixedBy() {
		return fixedBy;
	}
	public void setFixedBy(UserTo fixedBy) {
		this.fixedBy = fixedBy;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getFixedTime() {
		return fixedTime;
	}
	public void setFixedTime(Date fixedTime) {
		this.fixedTime = fixedTime;
	}
	public Integer getSeverity() {
		return severity;
	}
	public void setSeverity(Integer severity) {
		this.severity = severity;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public ProjectTo getProject() {
		return project;
	}
	public void setProject(ProjectTo project) {
		this.project = project;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	

}
