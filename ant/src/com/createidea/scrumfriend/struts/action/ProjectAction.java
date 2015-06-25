package com.createidea.scrumfriend.struts.action;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.createidea.scrumfriend.service.attachment.AttachmentService;
import com.createidea.scrumfriend.service.impediment.ImpedimentService;
import com.createidea.scrumfriend.service.project.ProjectService;
import com.createidea.scrumfriend.service.sprint.SprintService;
import com.createidea.scrumfriend.service.story.StoryService;
import com.createidea.scrumfriend.service.user.UserService;
import com.createidea.scrumfriend.to.AttachmentTo;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.SprintTo;
import com.createidea.scrumfriend.to.StoryTo;
import com.createidea.scrumfriend.to.UserTo;

public class ProjectAction extends BaseAction {

	
	private ProjectService projectService;
	private ProjectTo project;
	private List<ProjectTo> projects;
	private UserService userService;
	private String projectId;
	private String userId;
    private String attachmentId;
	private File image;
	private String imageContentType;
	private String imageFileName;
	private AttachmentService attachmentService;
	private String updatedLogoPath;
	private List<StoryTo> storiesBelongToProject;
	private List<UserTo> usersOfProject;
	private String userEmail;
	private List<SprintTo> sprintsOfProject;
	private SprintService sprintService;
	private Map<String,Float> storySummray =new HashMap<String,Float>();
	private StoryService storyService;
	
	

	public List<ProjectTo> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectTo> projects) {
		this.projects = projects;
	}

	public String loadCreatePage(){
    	return SUCCESS;
    }
    
    public String createProject(){
    	try {
    	    if("".equals(project.getLogo().getId()))
    	    	project.setLogo(null);
			project.setUser(new UserTo((String)this.getSession().getAttribute(this.USER)));
            project.setStatus(ProjectTo.NORMAL_STATUS);
	    	projectService.createProject(project);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
    }
    public String listProjects(){
    	projects = projectService.getActiveProjectsForUser((String)this.getSession().getAttribute(this.USER));
    	return SUCCESS;
    }
    
    public String setUsersOfProject(){	
    	usersOfProject=new ArrayList<UserTo>(projectService.getProjectById(projectId).getUsers());
    	return SUCCESS;
    }
    
    public String deleteProject(){
    	projectService.deleteProject((String)this.getSession().getAttribute(this.USER),projectId);
    	return SUCCESS;
    }
    
    public String updateProject(){
    	projectService.updateProject(project);
    	return SUCCESS;
    }
    
    public String modifyProject() throws ParseException{
    	project = projectService.getProjectById(projectId);
    	return SUCCESS;
    }
    
    public String viewProject(){
    	project = projectService.getProjectById(projectId);
    	sprintsOfProject=sprintService.getSprintsForProject(projectId);
    	setupSummaryOfStory();
    	return SUCCESS;
    }
    
    private void setupSummaryOfStory(){
    	storySummray.put(STORY_EFFORT_MUST_AND_COMPLETED,storyService.calculateStoryPoints(project,STORY_STATUS_COMPLETED, STORY_PRIORITY_MUST));
    	
    	storySummray.put(STORY_EFFORT_MUST_AND_NOTCOMPLETED,storyService.calculateStoryPoints(project,STORY_STATUS_NEW, STORY_PRIORITY_MUST));
    	
    	storySummray.put(STORY_EFFORT_SHOULD_AND_COMPLETED,storyService.calculateStoryPoints(project,STORY_STATUS_COMPLETED, STORY_PRIORITY_SHOULD));
    	
    	storySummray.put(STORY_EFFORT_SHOULD_AND_NOTCOMPLETED,storyService.calculateStoryPoints(project,STORY_STATUS_NEW, STORY_PRIORITY_SHOULD));
    	
    	storySummray.put(STORY_EFFORT_CAN_AND_COMPLETED,storyService.calculateStoryPoints(project,STORY_STATUS_COMPLETED, STORY_PRIORITY_CAN));
    	
    	storySummray.put(STORY_EFFORT_CAN_AND_NOTCOMPLETED,storyService.calculateStoryPoints(project,STORY_STATUS_NEW, STORY_PRIORITY_CAN));
    	
    	storySummray.put(STORY_EFFORT_CANNOT_AND_COMPLETED,storyService.calculateStoryPoints(project,STORY_STATUS_COMPLETED, STORY_PRIORITY_CAN));
    	
    	storySummray.put(STORY_EFFORT_CANNOT_AND_NOTCOMPLETED,storyService.calculateStoryPoints(project,STORY_STATUS_NEW, STORY_PRIORITY_CANNOT));
    }
    
    public String changeProjectLogo(){
    	@SuppressWarnings("deprecation")
		AttachmentTo logo=attachmentService.saveAttachment(image, imageContentType, imageFileName, getApplicationRootPath());
    	updatedLogoPath=logo.getPath();  
    	attachmentId=logo.getId();
    	return SUCCESS;
    }
      
    public String addANewProjectMember(){
    	projectService.createANewMemeberForProject(userEmail,projectId);
    	return SUCCESS;
    }
    
    public String removeAMemberFromProject(){
    	if(userId==null||"".equals(userId))
    		userId=(String)this.getSession().getAttribute(this.USER);
    	projectService.removeAMemberFromProject(userId,projectId);
    	return SUCCESS;
    }
    
    public String loadAddMemeberForProject(){
    	return SUCCESS;
    }
    
    public String getUserListOfProject(){
    	usersOfProject=new ArrayList<>();
    	usersOfProject.addAll(projectService.getProjectById(projectId).getUsers());
    	return SUCCESS;
    }
	/*
    * set , get methods
    */
	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public ProjectTo getProject() {
		return project;
	}

	public void setProject(ProjectTo project) {
		this.project = project;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getUpdatedLogoPath() {
		return updatedLogoPath;
	}

	public void setUpdatedLogoPath(String updatedLogoPath) {
		this.updatedLogoPath = updatedLogoPath;
	}

	public List<StoryTo> getStoriesBelongToProject() {
		return storiesBelongToProject;
	}

	public void setStoriesBelongToProject(List<StoryTo> storiesBelongToProject) {
		this.storiesBelongToProject = storiesBelongToProject;
	}

	public void setUsersOfProject(List<UserTo> usersOfProject) {
		this.usersOfProject = usersOfProject;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<UserTo> getUsersOfProject() {
		return usersOfProject;
	}

	public List<SprintTo> getSprintsOfProject() {
		return sprintsOfProject;
	}

	public void setSprintsOfProject(List<SprintTo> sprintsOfProject) {
		this.sprintsOfProject = sprintsOfProject;
	}

	public SprintService getSprintService() {
		return sprintService;
	}

	public void setSprintService(SprintService sprintService) {
		this.sprintService = sprintService;
	}

	
	public Map<String, Float> getStorySummray() {
		return storySummray;
	}

	public void setStorySummray(Map<String, Float> storySummray) {
		this.storySummray = storySummray;
	}

	public StoryService getStoryService() {
		return storyService;
	}

	public void setStoryService(StoryService storyService) {
		this.storyService = storyService;
	}

	
	
}
