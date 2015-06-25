package com.createidea.scrumfriend.struts.action;

import java.util.List;

import com.createidea.scrumfriend.service.impediment.ImpedimentService;
import com.createidea.scrumfriend.service.project.ProjectService;
import com.createidea.scrumfriend.to.ImpedimentTo;
import com.createidea.scrumfriend.to.ProjectTo;
import com.createidea.scrumfriend.to.UserTo;
import com.opensymphony.xwork2.Action;

public class ImpedimentAction extends BaseAction {
    private ImpedimentService impedimentService;
    private List<ImpedimentTo> impediments;
    private String projectId;
    private ImpedimentTo impediment;
    private String impedimentId;
	private ProjectService projectService;
	private ProjectTo project;
	private String fileterConditions;
	private int[][] impedimentsSummary= new int[4][4];
    
    public String showImpediments(){
    	impediments=impedimentService.getAllImpediments(projectId);
    	project=projectService.getProjectById(projectId);
    	return SUCCESS;
    }
    
    public String filterImpediments(){
    	impediments=impedimentService.filterImpediments(fileterConditions,projectId);
    	project=projectService.getProjectById(projectId);
    	return SUCCESS;
    }
    
    public String createImpediment(){
    	return SUCCESS;
    }
    
    public String saveImpediment(){
        String userId=(String)this.getSession().getAttribute(USER);
        UserTo creater=new UserTo(userId);
        impediment.setCreatedBy(creater);
        impedimentService.saveOrUpdateImpediment(impediment);
        projectId=impediment.getProject().getId();
    	return SUCCESS;
    }
    
    public String analyseImpediment(){
    	impediment=impedimentService.getImpediment(impedimentId);
    	return SUCCESS;
    }
    
    public String saveAnalyseImpediment(){
    	impediment=impedimentService.saveAnalyseImpediment(impediment);
    	projectId=impediment.getProject().getId();
    	return SUCCESS;
    }
    
    public String completeImpediment(){
    	impediment=impedimentService.getImpediment(impedimentId);
    	return SUCCESS;
    }
    
    public String saveResultImpediment(){
    	impediment=impedimentService.saveResultImpediment(impediment);
    	projectId=impediment.getProject().getId();
    	return SUCCESS;
    }
    
    public String viewImpediment(){
    	impediment=impedimentService.getImpediment(impedimentId);
    	return SUCCESS;
    }
    
    public String editImpediment(){
    	impediment=impedimentService.getImpediment(impedimentId);
    	switch (impediment.getStatus()) {
			case 0:
				return "edit_initial";
			case 1:
				return "edit_analyse";
			case 2:
				return "edit_result";
			case 3:
				return "edit_result";        
			default:
				return "edit_initial";
		}
    }
    
    public String updateImpediment(){
        impedimentService.saveOrUpdateImpediment(impediment);
        projectId=impediment.getProject().getId();
    	return SUCCESS;
    }
    
    public String deleteImpediment(){
    	impediment=impedimentService.deleteImpediment(impedimentId);
    	projectId=impediment.getProject().getId();
    	return SUCCESS;
    }
    
    public String getSummaryData(){
    	impedimentsSummary=impedimentService.setupSummaryOfImpediment(projectId);
    	return SUCCESS;
    }
   /*
    * get and set methods area
    */
	public ImpedimentService getImpedimentService() {
		return impedimentService;
	}

	public void setImpedimentService(ImpedimentService impedimentService) {
		this.impedimentService = impedimentService;
	}

	public List<ImpedimentTo> getImpediments() {
		return impediments;
	}

	public void setImpediments(List<ImpedimentTo> impediments) {
		this.impediments = impediments;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public ImpedimentTo getImpediment() {
		return impediment;
	}

	public void setImpediment(ImpedimentTo impediment) {
		this.impediment = impediment;
	}

	public String getImpedimentId() {
		return impedimentId;
	}

	public void setImpedimentId(String impedimentId) {
		this.impedimentId = impedimentId;
	}

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

	public String getFileterConditions() {
		return fileterConditions;
	}

	public void setFileterConditions(String fileterConditions) {
		this.fileterConditions = fileterConditions;
	}

	public int[][] getImpedimentsSummary() {
		return impedimentsSummary;
	}

	public void setImpedimentsSummary(int[][] impedimentsSummary) {
		this.impedimentsSummary = impedimentsSummary;
	}
    
    
}
