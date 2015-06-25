<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加障碍</title>
<link rel="Stylesheet" type="text/css" href="${ pageContext.request.contextPath }/jhtmlarea/style/jHtmlArea.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/jhtmlarea/scripts/jHtmlArea-0.7.5.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>

</head>

<html>
	
<body>
	<div class="createStoryPage">
	<s:form name="createImpediment" action="updateImpediment" method="POST" theme="simple">
	<s:hidden name="impediment.id" value="%{impediment.id}"/>
	<s:hidden name="impediment.status" value="%{impediment.status}"/>
	<s:hidden name="impediment.createdTime" value="%{impediment.createdTime}"/>
	<s:hidden name="impediment.createdBy.id" value="%{impediment.createdBy.id}"/>
	<s:hidden name="impediment.project.id" value="%{impediment.project.id}"/>
	<p class="title">编辑障碍</p>
				<table id="impediment_tb" >
					<tr>
					    <td class="row_name">
                                                                                     标题:
					    </td>
						<td>
						  <s:textfield name="impediment.name" placeholder="输入标题，20字以内" size="62" cssClass="name"></s:textfield>
						</td>
					</tr>
					<tr>
					    <td class="row_name">
                                                                                    描述:
					    </td>
						<td>
						  <s:textarea name="impediment.description" rows="12" cols="59" placeholder="请在60字以为描述清楚"></s:textarea>
						</td>
					</tr>					
					<tr>
						<td class="row_name">
	                                                                          严重性:
						</td>
						<td>
						  <select id="severity" name="impediment.severity" class="short">
						     <script>
						        var severity='<s:property value="impediment.severity"/>' ;
						        var selected=parseInt(severity);
						        $("#severity option").eq(selected).attr('selected', 'true');
						     </script>
						     <option value="0" class="must">紧急</option>
						     <option value="1" class="should">高</option>
						     <option value="2" class="could">一般</option>
						     <option value="3" class="wont">低</option>
						  </select>
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
				   $("#updateImpediment").validate({
					   rules: {					
							'impediment.name': {
								required: true,
								maxlength:20
							},
							'impediment.description': {
								maxlength:60
							}
			   
						},
						messages: {
							'impediment.name': {
								required: "请输入名称！",
								maxlength:"标题不能超出20字"
							},
							'impediment.description': {
								maxlength:"描述不能超出60字"
							}
						}
					});

					 if($("#updateImpediment").valid()){ 
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
