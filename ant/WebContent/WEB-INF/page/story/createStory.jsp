<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建需求</title>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/kindeditor-4.1.10/themes/default/default.css" />
<script charset="utf-8" src="${ pageContext.request.contextPath }/kindeditor-4.1.10/kindeditor-min.js"></script>
<script charset="utf-8" src="${ pageContext.request.contextPath }/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script type="text/javascript">

$(document).ready(function() {
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
});
</script>


</head>

<html>
	
<body>
	<div class="createStoryPage">
	<s:form name="createStory" action="createStory"  method="POST" theme="simple" cssClass="form-horizontal">
	
		<s:hidden name="projectId" value="%{projectId}"/>
		<p class="title">创建需求</p>

	    <div class="form-group">
	       <label  for="createStory_story_name" class="col-sm-2 ">标题</label>
           <s:textfield name="story.name" placeholder="输入需求名称" cssClass="col-sm-10 " ></s:textfield>
        </div>
        
        <div class="form-group">
	       <label  for="createStory_story_businessValue" class="col-sm-2">价值</label>
           <s:textfield name="story.businessValue" placeholder="0" cssClass="col-sm-4"></s:textfield>
           
           <label  for="createStory_story_point" class="col-sm-2">工作量</label>
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
	       <label  for="createStory_story_dod" class="col-sm-2">验收条件</label>
           <s:textarea name="story.dod"    placeholder="验收条件"></s:textarea>
        </div>
        
        <div class="form-group">
		    <div class="col-sm-offset-2">
		      <button type="submit" class="submit btn-default">提交</button>
		    </div>
        </div>
	   
	</s:form>
		</div>
		<script type="text/javascript" >
			$(function() {
			
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
								},
								'story.priorityNum': {
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
								},
								'story.priorityNum': {
									digits:"请输入整数"
								}
							}
						});
					 if($("form").valid()){ 
						 $(".submit").prop("disabled","disabled");
						 $(".submit").css("background","gray");
						 $("form").submit();
						 return false;
						 }
					 else{
						 return false;
					 }
					 
				});
			});
			
			function calculateStoryCardSize(point){
				if(!point)
					return 1;
				else if(point==1)
					return 1;
				else if(point>1 && point<=3)
					return 2;
				else if(point>3 && point <=6)
					return 3;
				else if(point>6 && point<=13)
					return 4;
				else if(point>13 && point<=20)
					return 5;
				else
					return 6;
			}
</script>
</body>
</html>
