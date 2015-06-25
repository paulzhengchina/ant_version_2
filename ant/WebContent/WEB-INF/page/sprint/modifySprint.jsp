<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>CreateSprint</title>		
		<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.css" />
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.form.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
			$( "#sttm" ).attr("value",$("#sttm").val().substring(0,10));
			$( "#endtm" ).attr("value",$("#endtm").val().substring(0,10));
		    $( "#sttm" ).datepicker({showButtonPanel: true,dateFormat : "yy-mm-dd"});
		    $( "#endtm" ).datepicker({showButtonPanel: true,dateFormat : "yy-mm-dd"});
		    
		    $("#sbm_button").click(function(){
		    	 $("#updateSprint").validate({
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
				 if($("#updateSprint").valid()){ 
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
<s:form name="updateSprint" action="updateSprint" method="POST" theme="simple">
	<s:hidden name="sprint.project.id" value="%{sprint.project.id}"/>
	<s:hidden name="sprint.parentSprint.id" value="%{sprint.parentSprint.id}"/>
	<s:hidden name="sprint.id" value="%{sprint.id}"/>
	<s:hidden name="sprint.status" value="%{sprint.status}"/>
	<p class="title">编辑阶段</p>
				<table>
				<tr>
				    <td class="row_name">
				                  名称:
				    </td>
					<td colspan="3">
					 <s:textfield name="sprint.name" size="53" cssClass="name"></s:textfield>
					</td>
				</tr>
				<tr>
					 <td class="row_name">
					        开始时间:
					 </td>
					 <td>
						<s:textfield name="sprint.startTime" id="sttm" cssClass="date"></s:textfield>
					 </td>
					 <td class="row_name">
					          结束时间:
					</td>
					<td>
					    <s:textfield name="sprint.endTime" id="endtm" cssClass="date"></s:textfield>
				    </td>
				</tr>
				<tr>
				    <td class="row_name">
				    </td>
					<td><button id="sbm_button" class="submit">提交</button></td>
				</tr>
			</table>
		</fieldset>
		</s:form>
</body>
</html>