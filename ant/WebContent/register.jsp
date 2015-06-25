<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.placeholder.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

<title>小蚂蚁看板 欢迎您！</title>
</head>
<body class="register">
  <s:form name="userRegister" action="registerUser" method="POST" theme="simple" >
      <p class="title">注册  <strong>Ant</strong>Kanban</span></p>
      <p class="message"></p>
      <input type="text" class="register_email" name="email" placeholder="请输入邮件地址"></input>
      <div id="register_button">免费注册</div>				
  </s:form>
  <script type="text/javascript">
$(function() { 
	 $("#registerUser input").placeholder({
		 event :'keydown'
	 });
	 
	 $("#register_button").click(function(){
		  $("#register_button").attr("id","faked_button");
		  var options={
	    			url: "${ pageContext.request.contextPath }/user/registerUser.action",
	    			type: "post",
	    			dataType : "json",
	    			beforeSend: function(){ 
	    				
	    				$("#registerUser").validate({
	    					rules: {					
	    						'email': {
	    							required: true,
	    							email: true
	    						}
	    					},
	    					messages: {
	    						'email': {
	    							required: "请输入邮箱！",
	    							email: "请输入正确的邮箱！"
	    						}
	    					}
	    				});
	    				if(!$("#registerUser").valid())
                            return false;
	    				$("#register_button").attr("id","faked_button");
	    			}, 

	    			success : function(data){
	    				if(data.result=="success")
	    					{
	    					$(".register_dialog").html('<p class="success_message">注册成功，初始密码已发送到您的邮箱</p>');
	    				}
	    				else{
	    					
	    					   var existedMessage="邮箱已经存在";
	    						$(".message").text(existedMessage);
	    				}
	    			},
	    			
	    			error: function(XMLHttpRequest, textStatus, errorThrown) {
                       
                    }
			}
	    	$("#registerUser").ajaxSubmit(options);
		    
		    return false;
	 });
   
	 $(".register_email").focus(function(){
		 $("#faked_button").attr("id","register_button");
	 });
});
</script>
</body>

</html>