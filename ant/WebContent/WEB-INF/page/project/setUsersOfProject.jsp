<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-dojo-tags"prefix="sx"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
	<head>
		<title>Project</title>
		<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.css" />
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.6.2.js"></script>
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.form.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
		   
		    $("#addNewMemberButon").click(function(){
		    	alert("${pageContext.request.contextPath }/user/getDefaultProject.action?projectId="+$("#projectId").val()+"&userEmail="+$("#newMemeberEmailTextField").val());
		    	 $.ajax({
					  "url":"${pageContext.request.contextPath }/project/addANewProjectMember.action?projectId="+$("#projectId").val()+"&userEmail="+$("#newMemeberEmailTextField").val(),
					  "type":"get",
					  "success":function(data,status){
						  if(data){	
							  $(".membersList").find("li").eq(0).before("<li>"+$("#newMemeberEmailTextField").val()+"</li>");									  								  
						  }
					  }
		    }); 
		   
		    });	
		    
		    $(".delete_icon").click(function(){
		    	var id=this.id;
		    	$.ajax({
					  "url":"${pageContext.request.contextPath }/project/removeAMemberFromProject.action?projectId="+$("#projectId").val()+"&userId="+this.id,
					  "type":"get",
					  "success":function(data,status){
						 
						  ($("#"+id).parent()).remove();
					  }
		    }); 
		    });
		});
		</script>
	</head>

	<body>
	    <div class="maintainProjectMembers">
	    <s:hidden name="projectId" value="%{projectId}"></s:hidden>
	    <s:textfield name="userEmail" placeholder="邮件" id="newMemeberEmailTextField" type="email"></s:textfield><button id="addNewMemberButon">新加项目成员</button>
	    <ul class="membersList">
	       <s:iterator value="usersOfProject">
		       <li>
		          <s:property value="%{name}"/><img class="delete_icon" width="16" height="16" src="${ pageContext.request.contextPath }/images/icon/close_delete.png" id="<s:property value='id'/>"/>
		       </li>
	       </s:iterator>
	    </ul>
	  </div>
	</body>
</html>
