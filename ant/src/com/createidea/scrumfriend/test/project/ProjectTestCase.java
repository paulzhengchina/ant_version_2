package com.createidea.scrumfriend.test.project;

import com.createidea.scrumfriend.struts.action.ProjectAction;
import com.createidea.scrumfriend.test.BasicTestCase;

public class ProjectTestCase extends BasicTestCase {
        
	
	private ProjectAction projectAction;
	
	
	public void testListProjectsAction() throws Exception{
		actionMapping=getActionMapping("/project/listProject.action");
		this.assertNotNull(actionMapping);
		this.assertEquals("/project", actionMapping.getNamespace());
		this.assertEquals("listProject", actionMapping.getName());
		this.request.getSession().setAttribute(this.USER, currentUser.getId());
		actionProxy=getActionProxy("/project/listProject.action");  
		assertNotNull(actionProxy);  		  
		projectAction = (ProjectAction) actionProxy.getAction();  
	    assertNotNull(projectAction);  	  
		String result = actionProxy.execute(); 
        this.assertNotNull("projects belongs to the currentuser should not be null", projectAction.getProjects());
		assertEquals("success", result);  		
	}
	
	public void testCreateProjectAction() throws Exception{
		
	
	}
}
