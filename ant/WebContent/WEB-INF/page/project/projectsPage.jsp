<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>项目库</title>
<LINK rel="Bookmark" href="${pageContext.request.contextPath}/images/icon/ant.jpg" />
<LINK rel="Shortcut Icon" href="${pageContext.request.contextPath}/images/icon/shortcut.png" />
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	
<script type="text/javascript" >
	$(document).ready(function() {
		
	
		
		$(".create_item").click(function(){
			DIALOG = $(".project_dialog");
			DIALOG.dialog({autoOpen: false, 
				                    title: "创建项目",
					                modal: true,
					                width :550,
					                height:500,
	                                close: function() {}
	                                });
			DIALOG.dialog('open');
			DIALOG.html("");
			DIALOG.css('background','url("../images/loading.gif")  no-repeat  center rgba(0, 0, 0, 0)') ;
			customizeDialog();
			DIALOG.load("${pageContext.request.contextPath}/project/loadCreatePage.action",function(){DIALOG.css('background','none') ;});
			
			
		});
		
		$("#<s:property value='id'/>").click(function(){
			  $("#update<s:property value='id'/>").css("display","");
		  });
		  
		  $(".editProject").click(function(){
			DIALOG = $(".project_dialog");
			var projectId=$(this).attr("id");
			DIALOG.dialog({autoOpen: false, 
					                    title: "编辑项目信息",
						                modal: true,
						                width :540,
						                height:520,
		                                close: function() {}
		                                });
			DIALOG.dialog('open');
			DIALOG.html("");
			DIALOG.css('background','url("../images/loading.gif")  no-repeat  center rgba(0, 0, 0, 0)') ;
			customizeDialog();
			DIALOG.load("${pageContext.request.contextPath}/project/modifyProject.action?projectId="+projectId,function(){DIALOG.css('background','none') ;});
		   });
		  
			$(".setDefaultProject").live('click',function(){
				   var currentItem=$(this);
				   $.ajax({
						  "url":"${pageContext.request.contextPath }/user/setDefaultProject.action",
						  "type":"post",
						  "data":{projectId:$(this).attr("id")},
						  "success":function(data,status){
							  if(data){
								  var defaultprojectId=$(".defaultprojectoverview").attr("id").substring(15);
								  var setDefault='<a href="#" title="设置为默认项目" id="'+defaultprojectId+'" class="setDefaultProject"><img  src="${ pageContext.request.contextPath }/images/icon/set_default.png" ></img></a>';
								  var currentProject=currentItem.parents(".project_card");
								  $(".defaultprojectoverview").find(".editProject").before(setDefault);
								  $(".defaultprojectoverview").removeClass("defaultprojectoverview");								
								  currentProject.addClass("defaultprojectoverview");
								  currentProject.find(".setDefaultProject").remove();
							  }
						},
						"error":function(xhr,s1,s2){
							alert('系统出错');
						}
					});
			   }		
			);
			
			$("#setUsersOfProject<s:property value='id'/>").click(function(){
				DIALOG = $(".project_dialog");
				DIALOG.dialog({autoOpen: false, 
						                    title: "设置项目成员",
							                modal: true,
							                width :500,
							                height:300,
			                                close: function() {}
			                                });
				DIALOG.load("${pageContext.request.contextPath}/project/setUsersOfProject.action?projectId=<s:property value='id'/>").dialog('open');
				customizeDialog();
			   });
			
			 $.ajax({
				  "url":"${pageContext.request.contextPath }/user/getDefaultProject.action",
				  "type":"get",
				  "success":function(data,status){
					  if(data){	
						  $("#projectoverview"+data).addClass("defaultprojectoverview");
						  $("#projectoverview"+data).find(".setDefaultProject").remove();
					  }
				}
				
			}); 
			
			 $(".deleteProject").click(
					   function(){
						   var project_card=$(this).parents(".project_card");
						   var project_id=$(this).attr("id");
						   DIALOG = $(".project_delete_dialog");
						   DIALOG.dialog({autoOpen: false, 
			                    title: "小蚂蚁看板提醒您：您确定退出该项目吗？",
				                modal: true,
				                resizable:false,
				                buttons:{
				                	"否" :function(){
				                		$(this).dialog("close"); 
				                		return false;
				                	},
				                	"是" :function(){
				                		  										   
										   $.ajax({
												  "url":"${pageContext.request.contextPath }/project/removeAMemberFromProject.action",
												  "type":"post",
												  "data":{projectId:project_id},
												  "success":function(data,status){
													  project_card.remove();
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
						   
						   
					   }		
					);
			 
			 $(".left_menu_wide li").mouseover(function(){
				if($(this).attr("class")!="selected" && !(typeof $(this).attr("id") === 'undefined'))
					{
					var imgPath=$(this).find("img").attr("src");
					if(imgPath.indexOf("_")<0)
						{
						  var splitedPath=imgPath.split(".");
						  imgPath=splitedPath[0]+"_selected.png";
						  $(this).find("img").attr("src",imgPath);
						}
					}					
				});
				
				$(".left_menu_wide li").mouseleave(function(){
					if($(this).attr("class")!="selected" && !(typeof $(this).attr("id") === 'undefined'))
					{
					  var imgPath=$(this).find("img").attr("src");
					  var splitedPath=imgPath.split("_");
					  imgPath=splitedPath[0]+".png";
					  $(this).find("img").attr("src",imgPath);
					}  
					
				});
				
								
				$("#menu_item_help").click(function(){
					DIALOG = $(".project_dialog");
					DIALOG.dialog({autoOpen: false, 
							                    title: "设置项目成员",
								                modal: true,
								                width :500,
								                height:600,
				                                close: function() {}
				                                });
					DIALOG.load("${pageContext.request.contextPath}/helppages/project.html").dialog('open');
					customizeDialog();
				});
				
				showToolTipForContent();
			 	
	});
	
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
</head>
<body>
	<div class="content content_with_wide_navigation">
	    <div class="header">
			<p class="project_name">
			&nbsp;
			</p>
			<p class="page_info">所有项目</p>
			<p class="create_item">+创建项目</p>
		</div>
		<s:iterator value="projects">
			<div class="project_card" id="projectoverview<s:property value='id'/>" >
	
					<div class="top_part">
					    <div id="<s:property value="id"/>" class="logo">
						    <s:if test="%{logo==null}">						     
						      <img src='${ pageContext.request.contextPath }/images/project_default_logo.png' />			
							</s:if>
							<s:else>
							  <img src='${ pageContext.request.contextPath }/<s:property  value="%{logo.path}"/>'  width="70px" height="80px" />
							</s:else>
						 </div>
						 <div class="top_part_right">
						      <table class="project_name"> 
								<tr> 
									<td style="vertical-align:middle;height:80px"> <a href='${ pageContext.request.contextPath }/project/viewProject.action?projectId=<s:property value="id"/>' >
										   <s:property value="name" />
										</a>
									</td> 
								</tr> 
							  </table> 
						</div>
						<div class="project_action_btns">
							        <a href="#" title="设置为默认项目" id="<s:property value='id'/>" class="setDefaultProject"><img  src="${ pageContext.request.contextPath }/images/icon/set_default.png" ></img></a>
							        <a href="#" title="编辑" id="<s:property value='id'/>" class="editProject"><img src="${ pageContext.request.contextPath }/images/icon/edit.png" /></a>
							        <a href="#" title="删除" id="<s:property value='id'/>" class="deleteProject"><img  src="${ pageContext.request.contextPath }/images/icon/delete.png"></img></a>
						</div>								
					</div>
					
					<div id="projectDescription" class="projectDescription" >
					    <p><s:property  value="description"/></p>
					</div>
					<div id="projectTime" class="projectTime">
						<span><s:date name="start_time" format="yyyy/MM/dd"/></span> - <span><s:date name="end_time" format="yyyy/MM/dd"/></span>
					</div>

			</div>	
		</s:iterator>
		<div class="clear"></div>
		<div class="project_dialog dialog"></div>
		<div class="project_delete_dialog" style="display:none"><p class="title">确定删除该项目吗？</p></div>
        </div>
        <div class="left_menu_wide">
			<ul>
			    <li style="height:1px;width:37px;border-bottom:1px solid #E5E5E5;"/>
					
				<li id="menu_item_projects" class="selected">
				    <a href="${ pageContext.request.contextPath }/project/listProject.action">
				       <img src="${ pageContext.request.contextPath }/images/icon/project_selected.png"/>
				       <p class="item_name">项目</p>
				    </a>
				</li>
			
				<li id="menu_item_setting">
				    <a href='<s:url value="/user/setting.action"></s:url>'>
				       <img src="${ pageContext.request.contextPath }/images/icon/setting.png"/>
				       <p class="item_name">设置</p>
				    </a>
				</li>
				
				<li id="menu_item_help">
				    <a href="#">
				       <img src="${ pageContext.request.contextPath }/images/icon/help.png"/>
				       <p class="item_name">帮助</p>
				    </a>
				</li>
				
				<li id="menu_item_quit">
				    <a href="${ pageContext.request.contextPath }/user/logout.action">
				       <img src="${ pageContext.request.contextPath }/images/icon/quit.png"/>
				       <p class="item_name">退出</p>
				    </a>
				</li>
				
				<li style="height:1px;width:37px;border-bottom:1px solid #E5E5E5;"/>
			</ul>
	</div>
    <div class="clear"></div>
</body>
		
</html>