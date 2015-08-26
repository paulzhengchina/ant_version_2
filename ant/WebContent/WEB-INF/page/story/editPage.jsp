<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>

<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.form.js"></script>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/kindeditor-4.1.10/themes/default/default.css" />
<script charset="utf-8" src="${ pageContext.request.contextPath }/kindeditor-4.1.10/kindeditor-min.js"></script>
<script charset="utf-8" src="${ pageContext.request.contextPath }/kindeditor-4.1.10/lang/zh_CN.js"></script>


<script type="text/javascript">
	var editor = KindEditor.create('textarea[name="story.dod"]', {
	width:'82%',
	height:'200px',
	allowPreviewEmoticons : false,
	allowImageUpload : false,
	items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
				'bold', 'italic', 'underline', 'removeformat', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'insertorderedlist', 'insertunorderedlist', '|', 'emoticons','link' ]
	});

	$(document).ready(function() {

		$(".submit").click(function() {
			$("form").validate({
				rules : {
					'story.name' : {
						required : true
					},
					'story.businessValue' : {
						digits : true
					},
					'story.point' : {
						digits : true
					}
				},
				messages : {
					'story.name' : {
						required : "请输入名称！"
					},
					'story.businessValue' : {
						digits : "请输入整数"
					},
					'story.point' : {
						digits : "请输入整数"
					}
				}
			});
			if ($("form").valid()) {
				$(".submit").prop("disabled", "disabled");
				$(".submit").css("background", "gray");
				$("#updateStory_story_dod").val(editor.html());
				$("form").submit();
			} else {
				return false;
			}
		});
	});
</script>
</head>

<body>
	<div class="createStoryPage">
	<s:form name="updateStory" action="updateStory" method="POST" theme="simple" cssClass="form-horizontal">
	
		<s:hidden name="story.project.id" value="%{story.project.id}"/>
		<s:hidden name="story.id" value="%{story.id}"/>	
		<p class="title">编辑需求</p>

	    <div class="form-group">
	       <label  for="updateStory_story_name" class="col-sm-2 ">标题</label>
           <s:textfield name="story.name" placeholder="输入需求名称" cssClass="col-sm-10 " ></s:textfield>
        </div>
        
        <div class="form-group">
	       <label  for="updateStory_story_businessValue" class="col-sm-2">价值</label>
           <s:textfield name="story.businessValue" placeholder="0" cssClass="col-sm-4"></s:textfield>
           
           <label  for="updateStory_story_point" class="col-sm-2">工作量</label>
           <s:textfield name="story.point" placeholder="0" cssClass="col-sm-4"></s:textfield>
        </div>
           
        <div class="form-group">
	       <label  for="priority" class="col-sm-2">重要性</label>
           <select name="story.priority"  id="priority" class="col-sm-4">
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
			
			<label  for="status" class="col-sm-2">状态</label>
            <select name="story.status" id="status" class="col-sm-4">
			  <script>
				 var status='<s:property value="story.status"/>' ;
				 var selected=parseInt(status);
				 $("#status option").eq(selected).attr('selected', 'true');
			  </script>
			  <option value="0">等待</option>
			  <option value="1">完成</option>
			  <option value="2">移除</option>
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
	       <label  for="updateStory_story_dod" class="col-sm-2">验收条件</label>
           <s:textarea name="story.dod"    placeholder="验收条件"></s:textarea>
        </div>
        
        <div class="form-group">
		    <div class="col-sm-offset-2">
		      <button type="submit" class="submit btn-default">提交</button>
		    </div>
        </div>
	   
	</s:form>
</div>
</body>

</html>
