<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建需求</title>
<link rel="Stylesheet" type="text/css" href="${ pageContext.request.contextPath }/jhtmlarea/style/jHtmlArea.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/jhtmlarea/scripts/jHtmlArea-0.7.5.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript">

$(document).ready(function() {
 $("textarea").htmlarea({
	 toolbar: [
	           ["orderedList"],
	           ["bold", "italic", "underline"],
	           ["link", "unlink"]
	          
	       ]
	   });
 $("iframe").height("190px");
 $("iframe").width("394px");
});
</script>


</head>

<html>
	
<body>
	<div class="createStoryPage">
	<s:form name="createStory" action="createStory" method="POST" theme="simple">
	<s:hidden name="projectId" value="%{projectId}"/>
	<p class="title">新增需求</p>
				<table id="project_tb" >
					<tr>
					    <td class="row_name">
                                                                                     名称:
					    </td>
						<td colspan="3">
						  <s:textfield name="story.name" placeholder="输入需求名称" size="54" cssClass="name"></s:textfield>
						</td>
					</tr>
					<tr>
					    <td class="row_name">
                                                                                   价值:
					    </td>
						<td>
						  <s:textfield name="story.businessValue" placeholder="商业价值" value="0" cssClass="number"></s:textfield>
						</td>
						<td class="row_name">
                                                                                   工作量:
					    </td>
						<td>
						  <s:textfield name="story.point" placeholder="工作量" value="0" cssClass="number"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="row_name">
	                                                                                  必要性:
						</td>
						<td>
						  <select name="story.priority" class="short">
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
						  <s:textfield name="story.priorityNum" placeholder="优先级：数字越小代表优先级越高" value="0" cssClass="number"></s:textfield>
						</td>
					</tr>
					<tr>
					    <td class="row_name">
                                                                                    验收条件:
					    </td>
						<td colspan="3">
						  <s:textarea name="story.dod" rows="12" cols="59" placeholder="验收条件"></s:textarea>
						</td>
					</tr>
					
					<tr>
					    <td>
					    </td>
						<td>
						<button class="submit">提交</button>
						</td>
					</tr>
				</table>

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
