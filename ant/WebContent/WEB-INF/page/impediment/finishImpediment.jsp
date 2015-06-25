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
	<s:form name="resultImpediment" action="saveResultImpediment" method="POST" theme="simple">
	<s:hidden name="impediment.id" value="%{impedimentId}"/>
	<s:hidden name="impediment.status" value="1"/>
	<p class="title">结果</p>
				<table id="impediment_tb" >
					<tr>
					    <td class="row_name">
                                                                                    结果:
					    </td>
						<td>
						  <s:textarea name="impediment.result" rows="12" cols="70" placeholder="60字内描述改进结果"></s:textarea>
						</td>
					</tr>	
					<tr>
					    <td>
					    </td>
						<td>
						<button class="submit failed">失败</button>
						<button class="submit completed">完成</button>
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
								'impediment.result': {
									required: true,
									maxlength:60
								}
							},
							messages: {
								'impediment.result': {
									required: "请填写结果描述！",
									maxlength:"结果描述不能超出60字"
								}
							}
						});
					  if($("form").valid()){ 
						 var btnClass=$(this).attr("class").substring(7);
						 if(btnClass=='failed')
							 $("#saveResultImpediment_impediment_status").attr("value","3");
						 else if(btnClass=='completed')
							 $("#saveResultImpediment_impediment_status").attr("value","2");
						 $(".submit").prop("disabled","disabled");
						 $(".submit").css("background","gray");
						 $("form").submit();
						 return false;
					  }
					  else
						  return false;
				});
				
				
				
			});
			
			
</script>
</body>
</html>
