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
				   
					<label class="checkbox-inline"> <input type="checkbox" checked class="filter_item"
						id="priority0" value="priority0"> <span class="priority0 filter_option">必须有</span>
					</label> 
					<label class="checkbox-inline"> <input type="checkbox" checked class="filter_item"
						id="priority1" value="priority1"> <span class="priority1 filter_option">应该有</span>
					</label> 
					<label class="checkbox-inline"> <input type="checkbox" checked class="filter_item"
						id="priority2" value="priority2"> <span class="priority2 filter_option">可以有</span>
					</label>
					<label class="checkbox-inline"> <input type="checkbox" checked class="filter_item"
						id="priority3" value="priority3"> <span class="priority3 filter_option">可以没有</span>
					</label>
				    
					<label class="checkbox-inline"> <input type="checkbox" checked class="filter_item"
						id="status0" value="status0"> <span class="filter_option">等待</span>
					</label> 
					<label class="checkbox-inline"> <input type="checkbox" class="filter_item"
						id="status1" value="status1"> <span class="filter_option">完成</span>
					</label> 
					<label class="checkbox-inline"> <input type="checkbox" class="filter_item"
						id="status2" value="status2"> <span class="filter_option">删除</span>
					</label>
					&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-success btn-xs" id="filter_submit">筛选</button>
				</div>
			</div>
			
			<div class="storieslist">			  
			   
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
		 
		
          	
		  $(".storieslist").load("${pageContext.request.contextPath }/story/loadStoriesOfProject.action",
				                  {projectId:$("#projectId").val()},
				                  function(){				                	  
				                	  calculateColumns();
				                	  $(".storieslist").gridly({				              			
				              		    base: 60, // px 
				              		    gutter: 15, // px
				              		    columns: columns,
				              		    callbacks: { reordering: reordering , reordered: reordered }
				              		  });		
				                  }
		                        );
		  
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
				resizable:false,
				position : ["center",100],
				close : function() {
					
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
				resizable:false,
				position : ["center",100],
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
		
		$("#filter_submit").click(function(){
			 var filterItems='';
			 $(".filter_item:checked").each(function(){
				 filterItems=filterItems+this.value+",";
				 });
		
			 $(".storieslist").load("${pageContext.request.contextPath }/story/filterStories.action",
	                  {projectId:$("#projectId").val(),filterItems:filterItems},
	                  function(){
	                	  $(".storieslist").gridly({				              			
	              		    base: 60, // px 
	              		    gutter: 15, // px
	              		    columns: columns,
	              		    callbacks: { reordering: reordering , reordered: reordered }
	              		  });		
	                  }
                   );
		});
		
	});
	
	function calculateColumns(){
		    var windowWidth=$(window).width();
			columns=12;
			if(windowWidth<=960)
				columns=12;
			if(windowWidth>960 && windowWidth<=1366)
				columns=12;
			if(windowWidth>1366 && windowWidth<=1700)
				columns=20;
			if(windowWidth>1700)
				columns=24;
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
			DIALOG.html("");
			DIALOG.dialog('close');
		});
	}
</script>
</html>