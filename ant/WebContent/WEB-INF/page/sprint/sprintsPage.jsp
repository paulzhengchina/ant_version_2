<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>计划安排</title>
<LINK rel="Shortcut Icon" href="${pageContext.request.contextPath}/images/icon/shortcut.png" />
<link rel="stylesheet" type="text/css"href="${ pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/ztree/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/ztree/jquery.ztree.exedit-3.5.js"></script>

	<style type="text/css">
    

	</style>
</head>
<body>
	<s:hidden name="projectId" value="%{projectId}"></s:hidden>
	<div class="content">
		<div class="header">
			<p class="project_name">
				<s:property value="%{project.name}" />
			</p>
			<p class="page_info">计划</p>
			<p class="create_item">+创建阶段</p>
		</div>
		<div class="sprints_overview">
			<s:iterator value="sprints" var="sprint">
			    <div class="parent_sprint" id="<s:property value='%{#sprint.id}'/>">
			        <s:if test="%{#sprint.subSprints.size()>0}">
				        <div class="operations">
				           <a href="#" title="新建子阶段"><img src="${ pageContext.request.contextPath }/images/icon/add.png" id="<s:property value='%{#sprint.id}'/>" class="addChildSprint"/></a>
				           <a href="#" title="编辑"><img src="${ pageContext.request.contextPath }/images/icon/edit.png" id="<s:property value='%{#sprint.id}'/>" class="editSprint"/></a>
				        </div>
			        </s:if>
			        <s:else>
			            <div class="operations">
				           <a href="${ pageContext.request.contextPath }/sprint/loadKanban.action?sprintId=<s:property value='%{#sprint.id}'/>" title="查看"><img src="${ pageContext.request.contextPath }/images/icon/view.png" id="<s:property value='%{#sprint.id}'/>" class="viewSprint"/></a>
				           <a href="#" title="新建子阶段"><img src="${ pageContext.request.contextPath }/images/icon/add.png" id="<s:property value='%{#sprint.id}'/>" class="addChildSprint"/></a>
				           <a href="#" title="编辑"><img src="${ pageContext.request.contextPath }/images/icon/edit.png" id="<s:property value='%{#sprint.id}'/>" class="editSprint"/></a>
						   <a href="#" title="删除"><img src="${ pageContext.request.contextPath }/images/icon/delete.png" id="<s:property value='%{#sprint.id}'/>" class="deleteSprint"></img></a>
				        </div>
			        </s:else>
			        <div class="name">
			           <s:property value='%{#sprint.name}' />
			        </div>
			        
			        <div class="date">
			            <s:date name="%{#sprint.startTime}" format="yyyy/MM/dd" /> - <s:date name="%{#sprint.endTime}" format="yyyy/MM/dd" />
			        </div>
			        
			        <div class="child_sprints">
						<s:if test="%{#sprint.subSprints.size()>0}">
						
							<s:iterator value="%{#sprint.subSprints}">
							    <div class="child_sprint" id="<s:property value='id'/>">
							        <div class="info">
                                      <div class="child_name">
                                           <s:property value='name' />
                                      </div>
										
									  <div class="date">
										<s:date name="startTime" format="yyyy/MM/dd" />
										-
										<s:date name="endTime" format="yyyy/MM/dd" />
									  </div>
									</div>
									
									<div class="child_operations">
									  <div class="flag">
									    ...
									  </div>
									  
									  <ul class="operation_items">
									     <li class="viewSprint" id="<s:property value='id'/>"><a href="${ pageContext.request.contextPath }/sprint/loadKanban.action?sprintId=<s:property value='id'/>">查看</a></li>
									     <li class="editSprint" id="<s:property value='id'/>"><a href="#">编辑</a></li>
									     <li class="deleteSprint" id="<s:property value='id'/>"><a href="#">删除</a></li>
									  </ul>
									</div>
									<div class="clear"></div>
								</div>
							</s:iterator>
							
						</s:if>
						<div class="clear"></div>
					</div>
					
				</div>
			</s:iterator>
		</div>
	</div>
	<div class="left_menu">
		<jsp:include page="../menu.jsp" flush="true"></jsp:include>
	</div>
    <div class="sprint_dialog dialog"></div>
    <div class="delete_dialog dialog" style="display:none"><p class="title">确定删除该阶段吗？</p></div>
</body>
<SCRIPT type="text/javascript">
	$(document).ready(function() {
						
		initialCurrentMenuItem("menu_item_plan");
		showToolTipForContent();
		
		$("#menu_item_help").click(											
		function() {											
		DIALOG = $(".sprint_dialog");
		DIALOG.dialog({
						autoOpen : false,
						title : "设置项目成员",
						modal : true,
						width : 500,
						height : 640,
						close : function() {}
						});
		DIALOG.load("${pageContext.request.contextPath}/helppages/plan.html").dialog('open');
		customizeDialog();
		});
		
		
		$(".editSprint").click(function(){
			var sprintId=$(this).attr("id");
			DIALOG = $(".sprint_dialog");
			DIALOG.dialog({
				autoOpen : false,
				title : "修改阶段信息",
				modal : true,
				width : 600,
				height : 300,
				close : function() {
					
				}
			});
			DIALOG.dialog('open');
			DIALOG.html("");
			DIALOG.css('background','url("../images/loading.gif")  no-repeat  center rgba(0, 0, 0, 0)') ;
			customizeDialog();
			DIALOG.load("${pageContext.request.contextPath}/sprint/modifySprint.action?sprintId="+ sprintId,function(){DIALOG.css('background','none') ;});
		});
		
		$(".deleteSprint").live('click',function(){
			 var sprintId=$(this).attr("id");
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
									  "url":"${pageContext.request.contextPath}/sprint/deleteSprint.action",
									  "type":"post",
									  "data":{sprintId:sprintId},
									  "success":function(data,status){
										  $("#"+sprintId).remove();
									},
									"error":function(xhr,s1,s2){
										alert('删除失败,可能阶段中已经分配需求，请解绑定需求后再试!');
									}
								});
	                		$(this).dialog("close");
	                	}
	                }
	                
              });
			   DIALOG.dialog("open");
			   customizeDialog();
			   
		});

		$(".addChildSprint,.create_item").click(function loadAddSprint(parentNode){
			var sprintId=$(this).attr("id");
			var projectId=$("#projectId").val();
			DIALOG = $(".sprint_dialog");
			DIALOG.dialog({
				autoOpen : false,
				title : "创建子阶段",
				modal : true,
				width : 600,
				height : 300,
				close : function() {
					
				}
			});
			DIALOG.dialog('open');
			DIALOG.html("");
			DIALOG.css('background','url("../images/loading.gif")  no-repeat  center rgba(0, 0, 0, 0)') ;
			customizeDialog();
			DIALOG.load("${pageContext.request.contextPath}/sprint/createSprint.action?sprintId="+ sprintId+"&projectId="+projectId,function(){DIALOG.css('background','none') ;});
		});
   });
</SCRIPT>
</html>