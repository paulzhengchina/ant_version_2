<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>创建阶段</title>		
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$( "#sttm" ).datepicker({showButtonPanel: true,dateFormat : "yy-mm-dd"});
		$( "#endtm" ).datepicker({showButtonPanel: true,dateFormat : "yy-mm-dd"});
		    
		$("#sbm_button").click(function(){
			 $("#saveSprint").validate({
					rules: {					
						'sprint.name': {
							required: true
						},
						'sprint.startTime': {
							required: true,
							dateISO:true
						},
						'sprint.endTime': {
							required: true,
							dateISO:true
						}
						
					},
					messages: {
						'sprint.name': {
							required: "请输入名称！"
						},
						'sprint.startTime': {
							required: "请选择开始时间！",
							dateISO:"请输入正确的时间格式：yyyy-mm-dd"
						},
						'sprint.endTime': {
							required: "请选择结束时间！",
							dateISO:"请输入正确的时间格式：yyyy-mm-dd"
						}
					}
				});
			 if($("form").valid()){ 
				 $("#sbm_button").prop("disabled","disabled");
				 $("#sbm_button").css("background","gray");				
				 }
			 else{
				 return false;
			 }
		    $("form").submit();		   
			return false;
			});
			
		});
</script>
</head>
<body>
	<s:form name="saveSprint" action="saveSprint" method="POST"
		theme="simple">
		<s:hidden name="projectId" value="%{projectId}" />
		<s:hidden name="sprintId" value="%{sprintId}" />
		<p class="title">创建阶段</p>
			<table>
				<tr>
				    <td class="row_name">
				                       名称:
				    </td>
					<td colspan="3">
					 <s:textfield name="sprint.name" size="53" placeholder="输入阶段名称" cssClass="name"></s:textfield>
					</td>
				</tr>
				<tr>
					 <td class="row_name">
					             开始时间:
					 </td>
					 <td>
						<s:textfield name="sprint.startTime" id="sttm" placeholder="yyyy-mm-dd" cssClass="date"></s:textfield>
					 </td>
					 <td class="row_name">
						结束时间:
					</td>
					<td>
					    <s:textfield name="sprint.endTime" id="endtm" placeholder="yyyy-mm-dd" cssClass="date"></s:textfield>
				    </td>
				</tr>
					
				<tr>
				    <td class="row_name">
				    </td>
					<td><button id="sbm_button" class="submit">提交</button></td>
				</tr>
			</table>
	</s:form>
</body>
</html>