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
		</div>
		<div class="sprints_overview">
		   <ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	<div class="left_menu">
		 <jsp:include page="../menu.jsp" flush="true"></jsp:include>
	</div>
<div class="sprint_dialog dialog"></div>
<div id="rMenu">
	<ul>
		<li id="m_add" onclick="addTreeNode();">创建子阶段</li>
		<li id="m_del" onclick="removeTreeNode();">删除</li>
		<li id="m_mod" onclick="modifyTreeNode();">编辑</li>
		<li id="m_check"><a href="#">查看</a></li>		
	</ul>
</div>
</body>
<SCRIPT type="text/javascript">
		<!--
		var setting = {
			view: {
				dblClickExpand: false,
				nameIsHTML: true
			},
			check: {
				enable: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: OnRightClick
			}
		};

		function OnRightClick(event, treeId, treeNode) {
			zTree.selectNode(treeNode);	   
			if (treeNode && treeNode.id=="0") {
				showRMenu("root", event.clientX, event.clientY,treeNode);
			} 
			else if(treeNode && treeNode.id!="0" &&treeNode.pId=="0"){
				showRMenu("parent", event.clientX, event.clientY,treeNode);
			}
			else if(treeNode && !treeNode.noR) {
				
				showRMenu("node", event.clientX, event.clientY,treeNode);
			}
		}

		function showRMenu(type, x, y,treeNode) {
			var linkToKanban="${ pageContext.request.contextPath }/sprint/loadKanban.action?sprintId="+treeNode.id;
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_add").show();
				$("#m_del").hide();
				$("#m_mod").hide();
			} 
			else if(type=="parent"){
				$("#m_add").show();
				$("#m_del").show();
				$("#m_mod").show();
				$("#m_check").hide();
				if(!treeNode.children){
					$("#m_check a").attr('href',linkToKanban);
					$("#m_check").show();
				}
			}
			else {
				$("#m_del").show();
				$("#m_mod").show();
				$("#m_add").hide();
				$("#m_check").show();
				$("#m_check a").attr('href',linkToKanban);
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		
		function addTreeNode() {
			hideRMenu();
			var newNode = { name:"增加"};
			if (zTree.getSelectedNodes()[0]) {
				loadAddSprint(zTree.getSelectedNodes()[0]);
				var newNode = { name:"增加",pId:zTree.getSelectedNodes()[0].id};
				newNode.checked = zTree.getSelectedNodes()[0].checked;
				//zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
			} else {
				zTree.addNodes(null, newNode);
			}
		}
		function removeTreeNode() {
			hideRMenu();
			var nodes = zTree.getSelectedNodes();
		    if (nodes[0].children && nodes[0].children.length > 0) {
					var msg = "要删除的阶段下有子阶段，如果删除将连同子阶段一起删除。\n\n请确认！";
					if (confirm(msg)==true){
						removeSprint(nodes[0]);
					}
				} else {
					removeSprint(nodes[0]);
				}
		}
		
		function modifyTreeNode(){
			var sprintId=zTree.getSelectedNodes()[0].id;
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
		}
		
		var zTree, rMenu,zNodes;
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
			DIALOG = $(".sprint_dialog");
		});
        
		
		function prepareNodes(){
			var projectId=$("#projectId").val();
			var sprintNodes;
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/sprint/getSprintsList.action?projectId="+projectId,
				cache:false,
				success:function(data){
					$.fn.zTree.init($("#treeDemo"), setting, data.sprintNodes);
				}
			});
		}
		
		function removeSprint(node){
			var sprintId=node.id;
			$.ajax({
				type:"GET",
				url:"${pageContext.request.contextPath}/sprint/deleteSprint.action?sprintId="+sprintId,
				cache:false,
				success:function(data){
					zTree.removeNode(node);
				},
				error:function(XMLHttpRequest, textStatus, errorThrown){
					var msg = "您不能删除该阶段，可能有需求跟该阶段相关！";
					confirm(msg);
				}
			});
		}
		
		function loadAddSprint(parentNode){
			var sprintId=parentNode.id;
			var projectId=$("#projectId").val();
			DIALOG.dialog({
				autoOpen : false,
				title : "添加需求",
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
		}
		
		$(document).ready(function(){
			initialCurrentMenuItem("menu_item_plan");
			prepareNodes();
			rMenu = $("#rMenu");
			
			$("#menu_item_help").click(function(){
				DIALOG = $(".sprint_dialog");
				DIALOG.dialog({autoOpen: false, 
						                    title: "设置项目成员",
							                modal: true,
							                width :500,
							                height:640,
			                                close: function() {}
			                                });
				DIALOG.load("${pageContext.request.contextPath}/helppages/plan.html").dialog('open');
				customizeDialog();
			});
		});
		
	</SCRIPT>
</html>