<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>需求库-<s:property value="project.name" /></title>
<LINK rel="Shortcut Icon" href="${pageContext.request.contextPath}/images/icon/shortcut.png" />
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/bootstrap-3.3.5-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/icheck_flat/grey.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.css" />
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery.gridly.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.gridly.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/freewall.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scrum-shrink.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/icheck.min.js"></script>


<style type="text/css">
  .storieslist {
    position: relative;
    margin-left:20px;
  }
  .brick {
    
  }
  
</style>
</head>
<body>

<div class="content">
    <div class="header">
            <img class="logo" src="${pageContext.request.contextPath}/images/head_logo.png"/>
			<p class="project_name">
				<s:property value="%{project.name}" />
			</p>
			<p class="page_info">需求库</p>
			<p class="create_item">+创建需求</p>
	</div>
	<div class="project_stories" id='project_stories' style="position: relative;">
	       
			<s:hidden name="projectId" value="%{projectId}" />
			<s:hidden name="draggingCardId" value="" />
			
			<div class="filter">
				<div class="priority text-left">
				   
					<label class="checkbox-inline"> <input type="checkbox" checked
						id="priority0" value="0"> <span class="priority0 filter_option">必须有</span>
					</label> 
					<label class="checkbox-inline"> <input type="checkbox" checked
						id="priority1" value="1"> <span class="priority1 filter_option">应该有</span>
					</label> 
					<label class="checkbox-inline"> <input type="checkbox" checked
						id="priority2" value="2"> <span class="priority2 filter_option">可以有</span>
					</label>
					<label class="checkbox-inline"> <input type="checkbox" 
						id="priority3" value="3"> <span class="priority3 filter_option">可以没有</span>
					</label>
				    
					<label class="checkbox-inline"> <input type="checkbox"
						id="status0" value="0"> <span class="filter_option">等待</span>
					</label> 
					<label class="checkbox-inline"> <input type="checkbox"
						id="status1" value="1"> <span class="filter_option">完成</span>
					</label> 
					<label class="checkbox-inline"> <input type="checkbox"
						id="status2" value="2"> <span class="filter_option">删除</span>
					</label>
					&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-success btn-xs">筛选</button>
				</div>
			</div>
			
			<div class="storieslist">
			  
			  <s:iterator value="%{stories}" var="story">
			   
			   <div class="story_card brick priority<s:property value='%{#story.priority}'/>" id='<s:property value="%{#story.id}"/>'>
				  <p class="title"><s:property value="%{#story.name}"/></p>
				  <p class="value" title="商业价值"><s:property value="%{#story.businessValue}"/></p>
				  <p class="accepetance_criteria"><s:property value="%{#story.dod}" escape="false"/></p>
				  <p class="point" title="工作量"><s:property value="%{#story.point}"/></p>
				  <p class="priorityNum" title="优先级"><s:property value="%{#story.priorityNum}"/></p>
				  <div class="operations">
				     <a href="#" title="编辑"><img width="20" height="20"src="${ pageContext.request.contextPath }/images/icon/project_edit.png" id="<s:property value='%{#story.id}'/>" class="editStory"/></a>
					 <a href="#" title="删除"><img width="20" height="20" src="${ pageContext.request.contextPath }/images/icon/project_delete.png" id="<s:property value='%{#story.id}'/>" class="deleteStory"></img></a>
				  </div>
				</div>
				  
			 </s:iterator>		
	</div>

    <div class="story_dialog dialog"></div>
    <div class="delete_dialog dialog" style="display:none"><p class="title">确定删除该需求吗？</p></div>	
</div>
<div class="left_menu">
		<jsp:include page="../menu.jsp" flush="true"></jsp:include>
</div>
</body>
<script>
	$(document).ready(function() {
		   
        		
		   var allStoryCards=$(".story_card");
		  
		   //draging start		   
		   var reordering = function($elements) {	
			 var draggingCardId=$(".dragging").attr("id");
			 $("#draggingCardId").val(draggingCardId);
			};
			
			var reordered = function($elements) {
			  var draggedCardId=$("#draggingCardId").val();
			  var draggedCardIndex;
			  var beforeGraggingStoryId;
			  var afterGraggingStoryId;
			 for(var i=0;i<$elements.length; i++)
				 {
				 if($elements[i].id==draggedCardId)
					 draggedCardIndex=i;
				 }
			 if(draggedCardIndex!=0)
				 beforeGraggingStoryId=$elements[draggedCardIndex-1].id;
			 
			 if(draggedCardIndex != $elements.length-1)
				 afterGraggingStoryId=$elements[draggedCardIndex+1].id;
			 
				 
			 $.ajax({
				  "url":"${pageContext.request.contextPath }/story/changePriority.action",
				  "type":"post",
				  "data":{projectId:$("#projectId").val(),
					      draggingStoryId: $("#draggingCardId").val(), 
					      beforeGraggingStoryId:beforeGraggingStoryId,
					      afterGraggingStoryId:afterGraggingStoryId},
				  "success":function(data,status){
					  if(data){
						  
					  }
				},
				"error":function(xhr,s1,s2){
					alert('系统出错');
				}
			});
						  
			};
		
			var windowWidth=$(window).width();
			var columns=12;
			if(windowWidth<=960)
				columns=12;
			if(windowWidth>960 && windowWidth<=1366)
				columns=16;
			if(windowWidth>1366 && windowWidth<=1666)
				columns=20;
			if(windowWidth>1666)
				columns=24;
			
		$(".storieslist").gridly({
			
		    base: 60, // px 
		    gutter: 15, // px
		    columns: columns,
		    callbacks: { reordering: reordering , reordered: reordered }
		  });		
		//dragging-stop
		
		$('input').iCheck({
			checkboxClass: 'icheckbox_flat-grey'
		  });
		
		$(".create_item").click(function(){
			DIALOG = $(".story_dialog");
			DIALOG.dialog({
				autoOpen : false,
				title : "添加需求",
				modal : true,
				width : 600,
				height : 600,
				close : function() {
					wall.fitWidth();
				}
			});
			DIALOG.dialog('open');
			DIALOG.html("");
			DIALOG.css('background','url("../images/loading.gif")  no-repeat  center rgba(0, 0, 0, 0)') ;
			customizeDialog();
			var projectId = $("#projectId").val();
			DIALOG.load("${pageContext.request.contextPath}/story/loadCreateStory.action?projectId="+ projectId,function(){DIALOG.css('background','none') ;});
		});
		
		
		$(".editStory").on('click',function(){
			DIALOG = $(".story_dialog");
			DIALOG.html('<img class="loading" src="../images/loading.gif"/>');
			DIALOG.dialog({
				autoOpen : false,
				title : "编辑需求",
				modal : true,
				width : 600,
				height : 670,
				close : function() {
					
				}
			});
			var storyId=$(this).attr('id');
			DIALOG.dialog('open');
			DIALOG.html("");
			DIALOG.css('background','url("../images/loading.gif")  no-repeat  center rgba(0, 0, 0, 0)') ;
			customizeDialog();
			DIALOG.load("${pageContext.request.contextPath}/story/loadEditStory.action?storyId="+ storyId,function(){DIALOG.css('background','none') ;});
		});
		
		$(".deleteStory").on('click',function(){
			 var storyId=$(this).attr("id");
			 DIALOG = $(".delete_dialog");
			 DIALOG.dialog({autoOpen: false, 
             title: "小蚂蚁看板提醒您：您确定删除该项目吗？",
	         modal: true,
	         resizable:false,
	         buttons:{
	               "否" :function(){
	                		$(this).dialog("close"); 
	                		return false;
	                	},
	                "是" :function(){  										   
							   $.ajax({
									  "url":"${pageContext.request.contextPath }/story/deleteStory.action",
									  "type":"post",
									  "data":{storyId:storyId},
									  "success":function(data,status){
										  location.reload();
									},
									"error":function(xhr,s1,s2){
										alert('删除失败,请稍后再试!');
									}
								});
	                		$(this).dialog("close");
	                	}
	                }
	                
               });
			   DIALOG.dialog("open");
			   customizeDialog();
			   
		   });		
		});
	
	function removeDeletedStoryCards(){
		$(".status2").each(function(){$(this).remove()});
		$(".status1").each(function(){$(this).remove()});
	}
	
	function customizeDialog(){
		$(".ui-dialog-titlebar button").remove();
		$(".ui-dialog-titlebar").html("<img src='${pageContext.request.contextPath}/images/icon/dialog_close.png'/>");
		$(".ui-dialog-titlebar img").css("position","absolute");
		$(".ui-dialog-titlebar img").css("right","2px");
		$(".ui-dialog-titlebar img").css("height","17px");
		$(".ui-dialog-titlebar img").css("width","17px");
		$(".ui-dialog-titlebar img").css("cursor","pointer");
		$(".ui-dialog-titlebar img").on('click',function(){
			DIALOG.dialog('close');
		});
	}
</script>
</html>