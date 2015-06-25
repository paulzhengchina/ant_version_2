<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:head theme="xhtml"/>


<html>
<head>
<link rel="Stylesheet" type="text/css"
	href="${ pageContext.request.contextPath }/jhtmlarea/style/jHtmlArea.css" />
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/jhtmlarea/scripts/jHtmlArea-0.7.5.js"></script>
<script type="text/javascript"
	src="${ pageContext.request.contextPath }/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(
			function() {
				
				$( ".editStoryInfo" ).tabs();
				$("textarea").htmlarea(
						{
							toolbar : [ [ "orderedList" ],
									[ "bold", "italic", "underline" ],
									[ "link", "unlink" ]

							]
						});
				$("iframe").height("190px");
				$("iframe").width("380px");
				
				$(".submit").click(function(){
					 $("form").validate({
							rules: {					
								'story.name': {
									required: true
								},
								'story.businessValue': {
									digits:true
								},
								'story.point': {
									digits:true
								}
							},
							messages: {
								'story.name': {
									required: "请输入名称！"
								},
								'story.businessValue': {
									digits:"请输入整数"
								},
								'story.point': {
									digits:"请输入整数"
								}
							}
						});
					 if($("form").valid()){ 
						 $(".submit").prop("disabled","disabled");
						 $(".submit").css("background","gray");
						 $("form").submit();
						 }
					 else{
						 return false;
					 }
				});
			});
</script>
</head>

<body>
	<div class="createStoryPage">
	<s:form name="updateStory" action="updateStory" method="POST" theme="simple">
	
	<s:hidden name="story.project.id" value="%{story.project.id}"/>
	<s:hidden name="story.id" value="%{story.id}"/>	
	<p class="title">编辑需求</p>
	<div class="editStoryInfo" style="height:300px;">
	    <ul>
	       <li><a href="#basicInfo">基本信息</a></li>
	       <li><a href="#description">验收条件</a></li>
	       <li><a href="#sprints">执行阶段</a></li>
	    </ul>
	    
	    <div id="basicInfo">
	          <table>
					<tr>
					   <td class="row_name">
                                                                                     名称:
					   </td>
						<td colspan="3">
						 <s:textfield name="story.name" size="53" placeholder="输入需求名称" cssClass="name"></s:textfield>
						</td>
					</tr>
					<tr>
					    <td class="row_name">
                                                                                    价值:
					    </td>
						<td>
						  <s:textfield name="story.businessValue" placeholder="0" cssClass="number"></s:textfield>
						</td>
						<td class="row_name">
                                                                                   工作量:
					    </td>
						<td>
						  <s:textfield name="story.point" placeholder="0" cssClass="number"></s:textfield>
						</td>
					</tr>

					<tr>
					   <td class="row_name">
                                                                                   必要性:
					   </td>
						<td>
						  <select name="story.priority"  id="priority" class="short">
						     <script>
						        var priority='<s:property value="story.priority"/>' ;
						        var selected=parseInt(priority);
						        $("#priority option").eq(selected).attr('selected', 'true');
						     </script>
							<option value="0" class="must">必须有</option>
							<option value="1" class="should">应该有</option>
							<option value="2" class="could">可以有</option>
							<option value="3" class="wont">不会有（但想）</option>
						</select>
					</td>
					
					<td class="row_name">
                                                                                   优先级:
					</td>
					<td>
						<s:textfield name="story.priorityNum" placeholder="0" cssClass="number"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="row_name">状态:</td>
					<td><select name="story.status" id="status" class="short">
							<script>
								var status='<s:property value="story.status"/>' ;
								var selected=parseInt(status);
								$("#status option").eq(selected).attr('selected', 'true');
							</script>
							<option value="0">等待</option>
							<option value="1">完成</option>
							<option value="2">移除</option>
					</select></td>
				</tr>
				
			</table>
	    </div>
	    
	    <div id="description">
	        <table>
		        <tr>
						<td class="row_name">验收条件:</td>
						<td><s:textarea name="story.dod" rows="12" cols="59" placeholder="验收条件"></s:textarea></td>
				</tr>
			 </table>
	    </div>
	    
	    <div id="sprints">
					<table>
						<tr>
							<td class="row_name">阶段:</td>
							<td><select name="story.sprint.id" style="width: 300px;">
									<s:if test="%{story.sprint!=null}">
										<option class="default"
											value="<s:property value='story.sprint.id'/>">
											<s:property value='story.sprint.name' />
											:
											<s:date name="story.sprint.startTime" format="yyyy-MM-dd" />
											-
											<s:date name="story.sprint.endTime" format="yyyy-MM-dd" />
										</option>
									</s:if>
									<option class="top"></option>
									<s:iterator value="sprints" var="sprint">

										<option class="top" value="<s:property value='id'/>">
											<s:property value='name' />
											:
											<s:date name="startTime" format="yyyy-MM-dd" />
											-
											<s:date name="endTime" format="yyyy-MM-dd" />
										</option>
										<s:if test="%{#sprint.subSprints!=null}">
											<s:iterator value="%{#sprint.subSprints}">
												<option class="second" value="<s:property value='id'/>">
													&nbsp;&nbsp;
													<s:property value='name' />
													:
													<s:date name="startTime" format="yyyy-MM-dd" />
													-
													<s:date name="endTime" format="yyyy-MM-dd" />
												</option>
											</s:iterator>
										</s:if>
									</s:iterator>
							</select></td>
						</tr>
					</table>
				</div>
	</div>
			<table>
				<tr>
					<td class="row_name"></td>
					<td>
						<button class="submit">提交</button>
					</td>
				</tr>
			</table>

		</s:form>
</div>
</body>
</html>
