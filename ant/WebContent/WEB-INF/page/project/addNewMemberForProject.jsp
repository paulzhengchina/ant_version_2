<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>



<html>
	<head>
		<title>Project</title>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>	
		<script type="text/javascript">
		$(document).ready(function() {

		    $(".submit").click(function(){
				 $("form").validate({
						rules: {					
							'userEmail': {
								required: true,
								email: true
							}
							
						},
						messages: {
							'userEmail': {
								required: "请输入邮箱！",
								email: "请输入正确的邮箱！"
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
				 $.ajax({
					  "url":"${pageContext.request.contextPath }/project/addANewProjectMember.action?projectId="+$("#projectId").val()+"&userEmail="+$("#newMemeberEmailTextField").val(),
					  "type":"get",
					  "success":function(data,status){
						  if(data){	
							  $('<div class="member_info"><img src="${ pageContext.request.contextPath }/images/icon/default_member_photo.jpg"/>'+'<p>'+$("#newMemeberEmailTextField").val()+'</P></div>').insertBefore(".add_member");
							  $(".ui-dialog-titlebar img").click();
						  }
					  }
		    }); 
			    return false;
		});
		    
		});
		</script>
	</head>

	<body>
	<s:form name="addANewProjectMember" action="addANewProjectMember" method="POST" theme="simple">
		        <s:hidden name="projectId" value="%{projectId}"/>
		        <p class="title">添加成员</p>
				<table>
					<tr>
						
						<td>
						 <input id="newMemeberEmailTextField" type="text" placeholder="邮箱地址" value="" name="userEmail" size="40"/>
						</td>
					</tr>
					
					<tr>
					    
						<td>
						<button class="submit">提交</button>
						</td>
					</tr>
				</table>
		</s:form>
	</body>
</html>

 
