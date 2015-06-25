<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<style>
  
  .ui-tooltip {
    padding: 4px;
    color: white;
    border-radius: 10px;
    font: 14px;
    text-transform: uppercase;
    box-shadow: 0 0 4px black;
    background: black;
  }
  </style>
<script>
	$(document).ready(function() {
		
		$(".left_menu").tooltip({
		      position: {
		          my: "right center",
		          at: "left-10 center",
		          using: function( position, feedback ) {
		            $( this ).css( position );
		            $( "<div>" )
		              .addClass( "arrow" )
		              .addClass( feedback.vertical )
		              .addClass( feedback.horizontal )
		              .appendTo( this );
		          }
		        }
		      });
		
		$(".left_menu li").mouseover(function(){
			if($(this).attr("class")!="selected" && $(this).parent().attr("id")!="project_logo" && $(this).attr("id").indexOf("menu_item")>=0){				
				 var imgPath=$(this).find("img").attr("src");
				 if(imgPath.indexOf("_")<0)
					 {
					    var splitedPath=imgPath.split(".");
					    imgPath=splitedPath[0]+"_selected.png";
						 $(this).find("img").attr("src",imgPath);
						 }
			}
		});
		
		$(".left_menu li").mouseleave(function(){
			if($(this).attr("class")!="selected" && $(this).parent().attr("id")!="project_logo" && $(this).attr("id").indexOf("menu_item")>=0){
				 var imgPath=$(this).find("img").attr("src");
				 var splitedPath=imgPath.split("_");
				 imgPath=splitedPath[0]+".png";
				 $(this).find("img").attr("src",imgPath);
			}
		});
		
	});
	
	
</script>

</head>
<html>
<ul  id="project_logo">
	<li><s:if test="%{project.logo==null}">
			<a href='<s:url value="/project/viewProject.action"><s:param name="projectId" value="%{projectId}"/></s:url>'>
			   <img src='${ pageContext.request.contextPath }/images/icon/project_default.png' title='<s:property  value="%{project.name}"/>'>
			</a>
		</s:if>
		<s:else>
			<a href='<s:url value="/project/viewProject.action"><s:param name="projectId" value="%{projectId}"/></s:url>'>
			   <img src='${ pageContext.request.contextPath }/<s:property  value="%{project.logo.path}"/>' style="width: 40px; margin: 5px 0px;" title='<s:property  value="%{project.name}"/>' />
			</a>
		</s:else>
	</li>
</ul>
<ul>
	<li id="menu_item_kanban">
	    <a href='<s:url value="/sprint/loadKanban.action"><s:param name="projectId" value="%{projectId}"/></s:url>'>
		   <img src="${ pageContext.request.contextPath }/images/icon/kanban.png" title="看板" /> 
		</a>
	</li>
	
	<li id="menu_item_story">
	    <a href='<s:url value="/story/viewStoriesOfProject.action"><s:param name="projectId" value="%{projectId}"/></s:url>'>
		   <img src="${ pageContext.request.contextPath }/images/icon/story.png" title="需求" />
		</a>
	</li>
	
	<li id="menu_item_plan">
	    <a href='<s:url value="/sprint/listSprints.action"><s:param name="projectId" value="%{projectId}"/></s:url>'>
		  <img src="${ pageContext.request.contextPath }/images/icon/plan.png" title="计划" />
		</a>
	</li>
	
	<li id="menu_item_impediment">
	    <a href='<s:url value="/impediment/showimpediments.action"><s:param name="projectId" value="%{projectId}"/></s:url>'>
	       <img src="${ pageContext.request.contextPath }/images/icon/impediment.png" title="障碍" />
	    </a>
	</li>
	
	<li id="separate" style="height:1px;width:30px;border-bottom:1px solid #B3B3B3;margin-left:5px"/>
	
	<li id="menu_item_projects">
	    <a href="${ pageContext.request.contextPath }/project/listProject.action">
	       <img src="${ pageContext.request.contextPath }/images/icon/project.png" title='项目库' />
	    </a>
	</li>
	
	<li id="menu_item_setting">
	    <a href='<s:url value="/user/setting.action"></s:url>'>
	       <img src="${ pageContext.request.contextPath }/images/icon/setting.png" title="个人设置" />
	    </a>
	</li>
	
	<li id="menu_item_help">
	    <a href="#">
	       <img src="${ pageContext.request.contextPath }/images/icon/help.png" title='帮助' />
	    </a>
	</li>
	
	<li id="menu_item_quit">
	    <a href="${ pageContext.request.contextPath }/user/logout.action">
	       <img src="${ pageContext.request.contextPath }/images/icon/quit.png" title="退出" />
	    </a>
	</li>
</ul>
</html>