<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>需求库-<s:property value="project.name" /></title>
<LINK rel="Shortcut Icon" href="${pageContext.request.contextPath}/images/icon/shortcut.png" />
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.css" />
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/multi-select/jquery.multiselect.css" />
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery.gridly.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.gridly.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/freewall.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/scrum-shrink.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.multiselect.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.multiselect.zh-cn.js"></script>

<script>
	function calculateStoryCardSize(point){
		if(!point||point==0)
			return 1;
		else if(point==1)
			return 1;
		else if(point>1 && point<=3)
			return 2;
		else if(point>3 && point <=6)
			return 3;
		else if(point>6 && point<=13)
			return 4;
		else 
			return 5;
		
	}
	
</script>

<style type="text/css">
  .storieslist {
    position: relative;
  }
  .brick.small {
    width: 140px;
    height: 140px;
    background:red;
  }
  .brick.large {
    width: 300px;
    height: 300px;
    background:green;
  }
</style>
</head>
<body>

<div class="content">

	<div class="project_stories" id='project_stories' style="position: relative;">
			<s:hidden name="projectId" value="%{projectId}" />
			<s:hidden name="draggingCardId" value="" />
			<div class="storieslist">
			  
			  <s:iterator value="%{stories}" var="story">
			   
			   <div class="story_card brick large" id='<s:property value="%{#story.id}"/>'>
				  <h2><s:property value="%{#story.name}"/></h2>
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
		
		   var reordering = function($elements) {
			  // Called before the drag and drop starts with the elements in their starting position.
			 var draggingCardId=$(".dragging").attr("id");
			 $("#draggingCardId").val(draggingCardId);
			};
			var reordered = function($elements) {
			  // Called after the drag and drop ends with the elements in their ending position.
			  var draggedCardId=$("#draggingCardId").val();
			  var draggedCardIndex;
			 for(var i=0;i<$elements.length; i++)
				 {
				 if($elements[i].id==draggedCardId)
					 draggedCardIndex=i;
				 }
			 alert($("#"+$elements[draggedCardIndex+1].id).children("h2").text());
			 alert($("#"+$elements[draggedCardIndex-1].id).children("h2").text());
			  
			};
		$(".storieslist").gridly({
		    base: 60, // px 
		    gutter: 20, // px
		    columns: 12,
		    callbacks: { reordering: reordering , reordered: reordered }
		  });
		$(".addStory").click(function(){
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
		
		
		$(".editStory").live('click',function(){
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
		
		$(".deleteStory").live('click',function(){
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
		
		
		var allDetachedStoryCards;
		$("select").multiselect({
			 selectedList: 5,
			 close: function(){
				 if ($('input[name=filter[]]:checked').length < 1) {  
					     
					   }  
				 else {  
					    allDetachedStoryCards=$('input[name=filter[]]:not(:checked)');  
					    $('input[name=filter[]]:not(:checked)').each(function() {
					    	$("."+$(this).val()).remove();
					    });
					    wall.refresh($(window).width() -100, $(window).height() - 30);
					   }   
			   },
			  beforeclose: function(){
				  $(".story_card").each(function(){$(this).remove()});
				  if(allStoryCards){
					  allStoryCards.each(function() {
					  $("#storieslist").append($(this));
					  wall.refresh($(window).width() -100, $(window).height() - 30);
					    });
				  }
			}
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
		$(".ui-dialog-titlebar img").live('click',function(){
			DIALOG.dialog('close');
		});
	}
</script>
</html>