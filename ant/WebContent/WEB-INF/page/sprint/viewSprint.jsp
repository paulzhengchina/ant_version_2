<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><s:property value="project.name" /></title>
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/css/tree.css">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/css/easy-ui/demo.css">
<link rel="stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/css/easy-ui/easyui.css">
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery-1.6.2.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.masonry.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/scrum-shrink.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	

<script type="text/javascript">
		function append(){
			var t = $('#tt');
			var node = t.tree('getSelected');
			loadCreateSprint(node.id);
			/* t.tree('append', {
				parent: (node?node.target:null),
				data: [{
					text: 'new item1'
				},{
					text: 'new item2'
				}]
			}); */
		}
		function edit(){
			var t = $('#tt');
			var node = t.tree('getSelected');
			loadEditSprint(node.id);
		}
		function removeit(){
			var node = $('#tt').tree('getSelected');
			var sprintId=node.id;
			alert(sprintId);
			 $.ajax({
				  "url":"${pageContext.request.contextPath }/sprint/deleteSprint.action",
				  "type":"post",
				  "data":{sprintId:sprintId},
				  "success":function(data,status){
					  $('#tt').tree('remove', node.target);
				},
				"error":function(xhr,s1,s2){
					alert('删除失败,请稍后再试!');
				}
			});
		}
		function collapse(){
			var node = $('#tt').tree('getSelected');
			$('#tt').tree('collapse',node.target);
		}
		function expand(){
			var node = $('#tt').tree('getSelected');
			$('#tt').tree('expand',node.target);
		}
		
		function loadCreateSprint(nodeId){
			var projectId = $("#projectId").val();
			DIALOG = $("#loadAddSprint");
			DIALOG.dialog({autoOpen: false, 
				                    title: "创建阶段",
					                modal: true,
					                width :450,
					                height:220,
	                                close: function() {}
	                                });
			DIALOG.load("${pageContext.request.contextPath}/sprint/createSprint.action?projectId="+projectId+"&sprintId="+nodeId).dialog('open');  
		}
		
		function loadEditSprint(nodeId){
			DIALOG = $("#loadAddSprint");
			DIALOG.dialog({autoOpen: false, 
				                    title: "修改阶段",
					                modal: true,
					                width :450,
					                height:220,
	                                close: function() {}
	                                });
			DIALOG.load("${pageContext.request.contextPath}/sprint/modifySprint.action?sprintId="+nodeId).dialog('open');
		}
		
		function rightClickMenu(e,node){
			e.preventDefault();
			$(this).tree('select',node.target);
			if(node.id){
				$('#mm #edit').show();
				$('#mm #remove').show();
				if(node.children){
					$('#mm #menu-sep').show();
					$('#mm #expand').show();
					$('#mm #collapse').show();
					$('#mm #addChild').show();
					$('#mm').menu('show',{
						left: e.pageX,
						top: e.pageY
					});
				}
				else{
					$('#mm #menu-sep').hide();
					$('#mm #expand').hide();
					$('#mm #collapse').hide();
					$('#mm #addChild').hide();
					
					$('#mm').menu('show',{
						left: e.pageX,
						top: e.pageY
					});
				}
			}
			else{
				$('#mm #edit').hide();
				$('#mm #remove').hide();
				$('#mm').menu('show',{
					left: e.pageX,
					top: e.pageY
				});
			}
			
			
		}
	</script>
</head>
<body>
	<s:hidden name="projectId" value="%{projectId}"></s:hidden>
	<div class="content">
		<div class="header">
			<p class="project_name">
				<s:property value="%{project.name}" />
			</p>
			<p class="page_info">计划</p>
		</div>
		<div class="sprints_overview">
		<span class="info">右击阶段进行相关操作！</span>
		<ul id="tt" class="easyui-tree tree" data-options="
															animate: true,
															onContextMenu: rightClickMenu">
		
		<li>
			<span>计划列表</span>
			<ul>
			    <s:iterator value="sprints" var="sprint">
			       <li data-options="state:'open',id:'<s:property value='id' />'">
					<span>
						<s:property value="name" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<s:date name="startTime" format="yyyy-MM-dd" />---
						<s:date name="endTime" format="yyyy-MM-dd" />
					</span>
					<s:if test="%{#sprint.subSprints!=null}">
					    <ul>
					      <s:iterator value="%{#sprint.subSprints}">
					        <li data-options="id:'<s:property value='id' />'">
					           <span>
					              <s:property value="name" />
						          <s:date name="startTime" format="yyyy-MM-dd" />
						          <s:date name="endTime" format="yyyy-MM-dd" />
					           </span>
					        </li>
					      </s:iterator>
					    </ul>
					</s:if>
				  </li>       
		       </s:iterator>
	      </ul>
		  <div id="mm" class="easyui-menu" style="width:120px;">
				<div onclick="append()" data-options="iconCls:'icon-add'" id="addChild">添加子阶段</div>
				<div onclick="edit()" data-options="iconCls:'icon-add'" id="edit">编辑</div>
				<div onclick="removeit()" data-options="iconCls:'icon-remove'" id="remove">移除</div>
				<div class="menu-sep" id="menu-sep"></div>
				<div onclick="expand()" id="expand">展开</div>
				<div onclick="collapse()" id="collapse">闭合</div>
		   </div>
		</div>
        <div id="loadAddSprint"/>
	</div>
	<div class="left_menu">
		<div class="logo">
			<img src="${ pageContext.request.contextPath }/images/icon/ant.jpg"
				title='首页' />
		</div>
		<ul>
			<li><a
				href="${ pageContext.request.contextPath }/project/listProject.action"><img
					src="${ pageContext.request.contextPath }/images/icon/project.png"
					title='项目库' /></a></li>
			<li><a
				href='<s:url value="/story/viewStoriesOfProject.action"><s:param name="projectId" value="%{projectId}"/></s:url>'><img
					src="${ pageContext.request.contextPath }/images/icon/story.png"
					title="事件库" /></a></li>
			<li><a
				href='<s:url value="/sprint/loadKanban.action"><s:param name="projectId" value="%{projectId}"/></s:url>'><img
					src="${ pageContext.request.contextPath }/images/icon/kanban.png"
					title="看板" /></a></li>
			<li>
               <a href='<s:url value="/user/setting.action"></s:url>'><img src="${ pageContext.request.contextPath }/images/icon/setting.jpg" title="个人设置"/></a>
              </li>
			<li><a
				href="${ pageContext.request.contextPath }/user/logout.action"><img
					src="${ pageContext.request.contextPath }/images/icon/back.png"
					title="退出" /></a></li>
		</ul>
	</div>
</body>
</html>