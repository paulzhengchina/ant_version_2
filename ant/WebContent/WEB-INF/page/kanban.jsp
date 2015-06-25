<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
	<meta charset="utf-8">
	<LINK rel="Shortcut Icon" href="${pageContext.request.contextPath}/images/icon/shortcut.png" />
	<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.css" />
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery.jscrollpane.css" />
	<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.6.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jscrollpane/jquery.jscrollpane.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jscrollpane/jquery.mousewheel.js"></script>
	<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-ui.min.js"></script>	 
	<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.livequery.min.js"></script>
	<script type="text/javascript" src="${ pageContext.request.contextPath }/js/scrum-shrink.js"></script>
	<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.form.js"></script>
	<title>看板</title>
</head>
<body onload="oninit()">
    <div class="content">
    <div class="header">
       <p class="project_name"><s:property value="project.name"/>>><s:property value="sprint.name"/></p>
       <p class="page_info">看板</p>          
    </div>
    <s:hidden name="sprintId" value="%{sprintId}"></s:hidden>
    <s:hidden name="projectId" value="%{projectId}"/>
	
		<div class="kanban_area">
		   <div class="status_indicator">
		       <div class="story">需求</div>
		       <div class="new">新建任务</div>
		       <div class="under_doing">进行中任务</div>
		       <div class="done">已完成任务</div>
		   </div>
		   <div class="workspace">
		   <s:if test="%{sprintId == null||''==sprintId}">
		        <div class="no_content">当前没有阶段，请进入<a href='<s:url value="/sprint/listSprints.action"><s:param name="projectId" value="%{projectId}"/></s:url>'><strong>计划页面</strong></a>创建 。</div>
		   </s:if>
		   <s:else>
			    <s:if  test="%{storysByStatus1.size>0}">
					<s:iterator value="storysByStatus1" var="story1">
						<div class="story_detail" rel="<s:property value='%{#story1.id}' />">
							<div class="story_group_box story_group ui-droppable">
								<div class="story_card_kanban priority<s:property value='%{#story1.priority}' />" id="<s:property value='%{#story1.id}' />">
								    
								      <h1><s:property value="%{#story1.name}" /></h1>
								      <div class="acceptance_criteria">  
								          <div>
								              <s:property value="%{#story1.dod}" escape="false"/>
								          </div>	     
								      </div>
								      <s:if test="%{#story1.status==0}">
									      <div class="create_task_icon">
										      <a href="#" title="创建任务"><img alt="" src="${ pageContext.request.contextPath }/images/icon/plus.png" /></a>
										  </div>
										  <div class="done_story" rel="0" title="点击完成">
										      <a href="#" ><img alt="" src="${ pageContext.request.contextPath }/images/icon/story_complet.png"/></a>
										  </div>
									  </s:if>
									  <s:if test="%{#story1.status==1}">
									      <div class="done_story" rel="1"  title="点击继续">
										      <a href="#"><img alt="" src="${ pageContext.request.contextPath }/images/icon/flag_mark_green.png" /></a>
										  </div>
										  <div class="create_task_icon" rel="0" style="display:none"  title="完成">
										      <a href="#"><img alt="" src="${ pageContext.request.contextPath }/images/icon/plus.png"/></a>
										  </div>
									  </s:if>
								</div>
							</div>
		
		
							<div class="group_box group0">
								<s:iterator value="%{#story1.tasks}" var="task0">
									<s:if test="%{#task0.status==0}">
										<div class="task" rel="<s:property value='%{#task0.id}' />" id="<s:property value='%{#task0.id}' />">
										    <div class="title">
										       <textarea><s:property value="%{#task0.title}" /></textarea>
										    </div>
											<div class="performer">
											  <s:if test="%{#task0.performer!=null}">
											    <s:if test="%{#task0.performer.name!=null}">
											       <span><s:property value="%{#task0.performer.name}" /></span>
											    </s:if>
											    <s:else>
											        <span><s:property value="%{#task0.performer.email}" /></span>
											    </s:else>
											  </s:if>
											  <s:else>
											    <span>执行者..</span>
											  </s:else>
											</div>
											<div class="effort">
											  <s:property value="%{#task0.leftEffort}" />
											</div>									
										</div>
									</s:if>
									
								</s:iterator>
							    <div class="clear"></div>
							</div>
		
							<div class="group_box group1">
								<s:iterator value="%{#story1.tasks}" var="task1">
									<s:if test="%{#task1.status==1}">
										<div class="task" rel="<s:property value='%{#task1.id}' />" id="<s:property value='%{#task1.id}' />">
											<div class="title">
										       <textarea><s:property value="%{#task1.title}" /></textarea>
										    </div>
											<div class="performer">
											  <s:if test="%{#task1.performer!=null}">
											    <s:if test="%{#task1.performer.name!=null}">
											       <span><s:property value="%{#task1.performer.name}" /></span>
											    </s:if>
											    <s:else>
											        <span><s:property value="%{#task1.performer.email}" /></span>
											    </s:else>
											  </s:if>
											  <s:else>
											    <span>执行者..</span>
											  </s:else>
											</div>
											<div class="effort">
											  <s:property value="%{#task1.leftEffort}" />
											</div>		
										</div>
									</s:if>
									
								</s:iterator>
								<div class="clear"></div>
							</div>
		
							<div class="group_box group2">
								<s:iterator value="%{#story1.tasks}" var="task2">
									<s:if test="%{#task2.status==2}">
										<div class="task" rel="<s:property value='%{#task2.id}' />" id="<s:property value='%{#task2.id}' />">
											<div class="title">
										      <textarea><s:property value="%{#task2.title}" /></textarea>
										    </div>
											<div class="performer">
											  <s:if test="%{#task2.performer!=null}">
											    <s:if test="%{#task2.performer.name!=null}">
											       <span><s:property value="%{#task2.performer.name}" /></span>
											    </s:if>
											    <s:else>
											        <span><s:property value="%{#task2.performer.email}" /></span>
											    </s:else>
											  </s:if>
											  <s:else>
											    <span>执行者..</span>
											  </s:else>
											</div>
											<div class="effort">
											  <s:property value="%{#task2.leftEffort}" />
											</div>		
										</div>
									</s:if>
									
								</s:iterator>
								
								<div class="clear"></div>
							</div>
							<div class="clear"></div>
						</div>
						
					</s:iterator>
				</s:if>
				<s:else>
					<div class="no_content">没有需求，请去<a href='<s:url value="/story/viewStoriesOfProject.action"><s:param name="projectId" value="%{projectId}"/></s:url>'><strong>需求库</strong></a>中选择！</div>
				</s:else>
	          </div>
		</div>
	
		        
		<div class="task_dialog dialog"/>
		        
		</div>
	</s:else>
	</div>
	<div class="left_menu">
	      <jsp:include page="menu.jsp" flush="true"></jsp:include>
	</div>
	
	<script type="text/javascript" >
	   // open a dialog to create current sprint
		$(function() {
			
			initialCurrentMenuItem("menu_item_kanban");
			showToolTipForContent();

			$(".story_card_kanban .acceptance_criteria").jScrollPane();
			
			// Initiate draggable for public and groups
			  var $gallery = $( ".group_box" );
			  $(".task", $gallery).live("mouseenter", function(){
				 var $this = $(this);
				 if(!$this.is(':data(draggable)')) {
				    $this.draggable({
				    	revert:true, 
				    	opacity: 0.35,
				     	helper: "clone",
						containment: $this.parent().parent(), 
						cursor: "move"
				    });
				  }
			});  
			  
			/*  var $galleryStory = $( ".story_group_box" );
			  $(".story_card", $galleryStory).live("mouseenter", function(){
				 var $this = $(this);
				 if(!$this.is(':data(draggable)')) {
				    $this.draggable({
				    	revert:true, 
				     	helper: "clone",
						containment: "#kanban_area ", 
						cursor: "move"
				    });
				  }
			});   */
			
			// Initiate Droppable for groups
			// Adding members into groups
			// Removing members from groups
			// Shift members one group to another
			
			 $(".group_box").livequery(function(){
				var casePublic = false;
				$(this).droppable({
					
					activeClass: "ui-state-highlight",
					drop: function( event, ui ) {
						var task_id = $(ui.draggable).attr('rel');
						var box_id = $(this).attr('class').substring(15,16);
						//dropPublic(card_id, box_id, casePublic);
						if(task_id){
							$("#"+task_id).hide();
							if(box_id=="2"){
								$("#"+task_id).children(".effort").text("0.0");
							}
							$(this).children(".clear").before($(ui.draggable ));
							$("#"+task_id ).toggle();
							clearHeight($(ui.draggable ));
							initializeGroupsHeight();
							updateMember(box_id,task_id);
						}
						else{
							//updateStoryStatus(box_id,story_id);
						}
								
					},
					 out: function(event, ui) {					
					 	$(ui.draggable).hide("explode",1);
                       
					 }
				});
			}); 
			
			 $(".story_group_box").livequery(function(){
					var casePublic = false;			
					$(this).droppable({
						activeClass: "ui-state-highlight",
						drop: function( event, ui ) {
							var card_rel = $(ui.draggable).attr('rel');
							var story_id = $(ui.draggable).attr('id');
							if(card_rel){
								updateMember(4,card_rel);
							}
							else{
                               
								$("#"+story_id).hide();
								$(ui.draggable ).appendTo( this);
								$("#"+story_id ).toggle();
								
								updateStoryStatus(box_id,story_id);
							}
							
						},
						 out: function(event, ui) {					
						 	$(ui.draggable).hide("explode",1);
	                       
						 }
					});
				}); 
				
			
			 function updateMember(box_id,task_id) {
				$.ajax({
					type:"GET",
					url:"${pageContext.request.contextPath}/task/updateTaskStatus.action?taskId="+task_id+"&taskStatus="+box_id,
					cache:false,
					success:function(response){
						//alert("SUCCESS");
					}
				});
			}
			
			/* function updateStoryStatus(box_id,story_id){
				$.ajax({
					type:"GET",
					url:"${pageContext.request.contextPath}/story/updateStoryStatus.action?storyId="+story_id+"&storyStatus="+box_id,
					cache:false,
					success:function(response){
						
					}
				});
			} */
			
			
			$(".create_task_icon").click(function(){
				var storyId=$(this).parents(".story_card_kanban").attr("id");
				DIALOG = $(".task_dialog");
				DIALOG.load("${pageContext.request.contextPath}/task/addTask.action?storyId="+storyId);
				DIALOG.dialog({autoOpen: false, 
					                    title: "创建任务",
						                modal: true,
						                width :500,
						                height:300,
		                                close: function() {}
		                                });
			    DIALOG.dialog("open");
			    customizeDialog();
			});
		   
            $(".done_story").click(function(){
            	var status=$(this).attr("rel");
            	status=parseInt(status);
            	if(status==0)
            		status=1;
            	else
            		status=0;
            	var story_id=$(this).parent().attr("id");
            	var thisObj=$(this);
            	$.ajax({
					type:"GET",
					url:"${pageContext.request.contextPath}/story/updateStoryStatus.action?storyId="+story_id+"&storyStatus="+status,
					cache:false,
					success:function(response){
						if(status==1){
							thisObj.attr("rel","1");
							thisObj.find("img").attr("src","${ pageContext.request.contextPath }/images/icon/flag_mark_green.png");	
							thisObj.parents("div").children(".create_task_icon").hide();
						}
						else{
							thisObj.attr("rel","0");
							thisObj.find("img").attr("src","${ pageContext.request.contextPath }/images/icon/story_complet.png");
							thisObj.parents("div").children(".create_task_icon").toggle();
						}
						
					}
				});
            	
            });
            
			$(".effort").live('click',function(){ 
				var textfield='<input type="text" size="2" onblur="updateEffort(this)"/>';
				$(this).html(textfield);
				$(this).children('input').focus();
			});
			
			$(".performer span").live('click',function(){ 
				loadProjectUsersSelect(this);
			});
			
			$(".title textarea").live('click',function(){ 
				$(this).removeAttr("disabled");
				//$(this).css("background-color","white");
			});
			
			$(".title textarea").live('blur',function(){ 
				updateTitle(this);
			});
		
			$("#menu_item_help").click(function(){
				DIALOG = $(".dialog");
				DIALOG.dialog({autoOpen: false, 
						                    title: "设置项目成员",
							                modal: true,
							                width :500,
							                height:600,
			                                close: function() {}
			                                });
				DIALOG.load("${pageContext.request.contextPath}/helppages/kanban.html").dialog('open');
				customizeDialog();
			});
			 
		});
	   
	
	   
	   function initializeGroupsHeight(){
		   $(".story_detail").each(function(){
			   var parentHeight=$(this).height();
			 //  alert(parentHeight);
			   $(this).children("div").each(function(i,val){
				   if(i<4)
				   $(this).height(parentHeight);
			   });
		   });
	   }
	   
	   function clearHeight(moving_card){
		   var story_card=moving_card.parent().parent();
		   story_card.children("div").each(function(){
			   $(this).css('height','');
			//   alert($(this).attr("class"));
		   });
		   story_card.css('height','');
	   }
	   
	   function updateEffort(obj){
			 var taskId=$(obj).parents(".task").attr("rel");
			 var effort=$(obj).attr("value");
			 $.ajax({
				  "url":"${pageContext.request.contextPath }/task/updateEffort.action",
				  "type":"post",
				  "data":{taskId:taskId,effort:effort},
				  "success":function(data,status){
					  if(data){	
						  if(""==effort)
							  $(obj).parent().text(0.0);	
						  else
	                          $(obj).parent().text(effort);								  								  
					  }
				},
				"error":function(xhr,s1,s2){
					alert('系统出错');
				}
			});
		 }
	   
	   function loadProjectUsersSelect(obj){
		   var projectId=$("#projectId").val();
		   var select='<select name="performer" onChange="updatePerformer(this)" ><option value="0">执行者..</option>';
		   $.ajax({
				  "url":"${pageContext.request.contextPath }/project/getUsersOfProject.action",
				  "type":"get",
				  "data":{projectId:projectId},
				  "success":function(data,status){
					  if(data.usersOfProject){
						  var users=data.usersOfProject;
						  for (var i=0;i<users.length;i++){
							  if(users[i].name==null||typeof(users[i].name)=="undefined")
								  {
								  var cutend=users[i].email.indexOf('@');
								  var mailsubstring=users[i].email.substring(0,cutend);
								  select= select+ '<option value="'+users[i].id +'">' + mailsubstring +'</option>';
								  }
							    
							  else
								  select= select+ '<option value="'+users[i].id +'">' + users[i].name +'</option>';
						  }
						  
						  select=select+'</select>';
						  $(obj).parent().html(select);
					  }
				},
				"error":function(xhr,s1,s2){
					alert('系统出错');
				}
			});
	   }
	   
	   function updatePerformer(obj){
		   var taskId=$(obj).parents(".task").attr("rel");
		   var performerId=$(obj).val();
		   var performerName=$(obj).find("option:selected").text();

		   $.ajax({
				  "url":"${pageContext.request.contextPath }/task/updatePerformer.action",
				  "type":"get",
				  "data":{taskId:taskId,userId:performerId},
				  "success":function(data,status){					  
						  $(obj).parent().html('<span onClick="loadProjectUsersSelect(this)">'+performerName+'</span>');					  
				},
				"error":function(xhr,s1,s2){
					alert('系统出错');
				}
			});
		   
	   }
	   
	   function updateTitle(obj){
			 var taskId=$(obj).parents(".task").attr("rel");
			 var updateTitle=$(obj).attr("value");
			 if(updateTitle=="")
				 {
				 $(obj).attr("value","请输入标题");
				 return false;
				 }
			 $.ajax({
				  "url":"${pageContext.request.contextPath }/task/updateTitle.action",
				  "type":"post",
				  "data":{taskId:taskId,title:updateTitle},
				  "success":function(data,status){
					  if(data){	
	                     $(obj).attr("value",updateTitle);	
					  }
				},
				"error":function(xhr,s1,s2){
					alert('系统出错');
				}
			});
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
</body>
</html>