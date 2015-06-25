<%@ page contentType="text/html; charset=gbk" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>KTV</title>
		<link href="${pageContext.request.contextPath}/style/login.css"
			type="text/css" rel="stylesheet" />
		<script type="text/javascript">
		<!--
			$(document).ready(function(){
			  	$("#changePic").click(function(){
			  	 var newsrc= $("#validatePic").attr("src");
			  	  $("#validatePic").attr("src",newsrc+"A");
			     });
			  });
		  	function validate(){
		  		var account = document.getElementById("account").value;
				var password = document.getElementById("password").value;
				if(account == "" || password == ""){
					alert("用户名或密码不能能为空！");
					return false;
				}else{
					var uCheckCode = document.getElementById("uCheckCode").value;
					if(uCheckCode == ""){
						alert("请填写校验码！");
						return false;
					}else{
						document.userLoginForm.submit();
					}
				}				
			}
		//-->
		</script>
	</head>

	<body>
		<table id="login_tb" width="600" height="400" border="0"
			cellpadding="0" cellspacing="0" class="login_tb2">
			<tr>
				<td height="137" colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td width="234" height="169">
					&nbsp;
				</td>
				<td width="366" height="169">
					<s:form name="userLoginForm" action="login" method="POST" theme="simple">
						<s:textfield name="username"></s:textfield>
						<s:password name="password"></s:password>
						<s:submit></s:submit>
					</s:form>
				</td>
			</tr>
			<tr>
				<td colspan="2" height="90">
					&nbsp;
				</td>
			</tr>
		</table>
	</body>
</html>
