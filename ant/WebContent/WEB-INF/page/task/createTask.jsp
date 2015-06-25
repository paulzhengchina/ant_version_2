<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建任务</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
</head>
<body>
<div class="add_task_form">
	<s:form name="saveTask"  action="saveTask"  method="POST" theme="simple">
	   <s:hidden name="task.story.id" value="%{storyId}"></s:hidden>
	   <p class="title">创建任务</p>
	   <table>
	      <tr>
	        <td>
	        <s:textfield name="task.title" placeholder="任务标题" size="63"></s:textfield>
	        </td>
	      </tr>
	      <tr>
		      <td>
		      <s:textfield name="task.leftEffort" placeholder="工作量"></s:textfield>
		      <td>
	      </tr>
	      <tr>
		      <td>
		      <button class="submit">创建任务</button>
		      <td>
	      </tr>         
	   </table>
	</s:form>
</div>

<script type="text/javascript" >
$(function() {

	$(".submit").click(function(){
		$("form").validate({
			rules: {					
				'task.title': {
					required: true
				},
				'task.leftEffort': {
					number:true
				}
		
			},
			messages: {
				'task.title': {
					required: "请输入任务标题！"
				},
				'task.leftEffort': {
					number:"请输入数字！"
				}
			}
		});
	 if($("form").valid()){ 
		 $(".submit").prop("disabled","disabled");
		 $(".submit").css("background","gray");				
		 }
	 else{
		 return false;
	 }
	 
	 var options={
    			url: "${ pageContext.request.contextPath }/task/saveTask.action",
    			type: "post",
    			dataType : "json", 
    			success : function(data){
    				$(".task_dialog").dialog( "close" );
    				var taskHtml='<div class="task" rel="'+data.task.id + '" id="'+data.task.id+'">'+
    				                '<div class="title"><textarea>'+ data.task.title+'</textarea></div>'+
    				                '<div class="performer"><span>执行者..</span></div>'+
    				                '<div class="effort">'+data.task.leftEffort+'</div>'+
    				                "</div>";
    				 $("div[rel="+data.task.story.id+"]> .group0 >.clear").before(taskHtml);
    				 $("div[rel="+data.task.story.id+"]> .group0").css('height', '');
    				 initializeGroupsHeight();
  				
    			}
    	} 
		
    	$("#saveTask").ajaxSubmit(options);
		$(".submit").css("background","gray");
		return false;
	});
	
});
</script>
</body>
</html>