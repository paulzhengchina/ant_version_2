<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>

<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-ui.min.js"></script>

<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.form.js"></script>


<script type="text/javascript">
	$(document).ready(
			function() {
				
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
	<s:form name="updateStory" action="updateStory" method="POST" theme="simple" cssClass="form-inlinel">
	
		<s:hidden name="story.project.id" value="%{story.project.id}"/>
		<s:hidden name="story.id" value="%{story.id}"/>	
		<p class="title">编辑需求</p>

	    <div class="form-group">
	       <label  for="updateStory_story_name" class="col-sm-2">标题</label>
	       <input type="text" placeholder="输入需求名称" cssClass="col-sm-10" id="updateStory_story_name" />
          
        </div>
        
        <div class="form-group">
	       <label  for="updateStory_story_businessValue" class="col-sm-2 control-label">价值</label>
           <s:textfield name="story.businessValue" placeholder="0" cssClass="col-sm-4"></s:textfield>
           
          
        </div>
           
        <div class="form-group">
	       <label  for="priority" class="col-sm-2">重要性</label>
           <select name="story.priority"   css="col-sm-10">
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
			
			
        </div>

		<div class="form-group">
			<label for="updateStory_story_sprint_id" class="col-sm-2">执行阶段</label> 
			<select name="story.sprint.id" class="col-sm-10">
					<s:if test="%{story.sprint!=null}">
						<option class="default"
							value="<s:property value='story.sprint.id'/>">
							<s:property value='story.sprint.name' /> :
							<s:date name="story.sprint.startTime" format="yyyy-MM-dd" /> -
							<s:date name="story.sprint.endTime" format="yyyy-MM-dd" />
						</option>
					</s:if>
					<option class="top"></option>
					<s:iterator value="sprints" var="sprint">

						<option class="top" value="<s:property value='id'/>">
							<s:property value='name' /> :
							<s:date name="startTime" format="yyyy-MM-dd" /> -
							<s:date name="endTime" format="yyyy-MM-dd" />
						</option>
						<s:if test="%{#sprint.subSprints!=null}">
							<s:iterator value="%{#sprint.subSprints}">
								<option class="second" value="<s:property value='id'/>">
									&nbsp;&nbsp;
									<s:property value='name' /> :
									<s:date name="startTime" format="yyyy-MM-dd" /> -
									<s:date name="endTime" format="yyyy-MM-dd" />
								</option>
							</s:iterator>
						</s:if>
					</s:iterator>
			</select>
		</div>
		
        <div class="form-group">
	       <label  for="updateStory_story_dod" class="col-sm-3 control-label">验收条件</label>
           <s:textarea name="story.dod" rows="12" cols="59" placeholder="验收条件" cssClass="col-sm-9 form-control"></s:textarea>
        </div>
        
	    <button type="submit" class="submit btn-default">提交</button>

		</s:form>
</div>
</body>
</html>
