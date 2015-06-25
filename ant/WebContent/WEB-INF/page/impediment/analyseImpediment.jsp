<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加障碍</title>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>

</head>

<html>
	
<body>
	<div class="createStoryPage">
	<s:form name="analyseImpediment" action="saveAnalyseImpediment" method="POST" theme="simple">
	<s:hidden name="impediment.id" value="%{impedimentId}"/>
	<p class="title">障碍分析</p>
				<table id="impediment_tb" >
					<tr>
					    <td class="row_name">
                                                                                    原因分析:
					    </td>
						<td>
						  <s:textarea name="impediment.reason" rows="12" cols="70" placeholder="60字内分析根本原因"></s:textarea>
						</td>
					</tr>	
					<tr>
					    <td class="row_name">
                                                                                    改进方案:
					    </td>
						<td>
						  <s:textarea name="impediment.solution" rows="12" cols="70" placeholder="60字内定义改进方案"></s:textarea>
						</td>
					</tr>
					
					<tr>
					    <td class="row_name">
                                                                                    负责人:
					    </td>
						<td>
						  <s:select name="impediment.fixedBy.id" list="impediment.project.users" listKey="id" listValue="name"></s:select>
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
								'impediment.reason': {
									required: true,
									maxlength:60
								},
								'impediment.solution': {
									required: true,
									maxlength:60
								}
							},
							messages: {
								'impediment.reason': {
									required: "请输入分析原因！",
									maxlength:"原因不能超出60字"
								},
								'impediment.solution': {
									required: "请输入解决方案！",
									maxlength:"方案不能超出60字"
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
			
			
</script>
</body>
</html>
