<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
	<head>
		<title>Project</title>
		<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.css" />
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.form.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.fileupload.js"></script>
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.iframe-transport.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
			$( "#sttm" ).attr("value",$("#sttm").val().substring(0,10));
			$( "#endtm" ).attr("value",$("#endtm").val().substring(0,10));
		    $( "#sttm" ).datepicker({showButtonPanel: true ,dateFormat : "yy-mm-dd"});
		    $( "#endtm" ).datepicker({showButtonPanel: true ,dateFormat : "yy-mm-dd"});
		    
		    $("#changeLogo").live('click',function(){
				$("#fileupload_input").click();
			});
		    
		    $("#fileupload_input").fileupload({  
		        url:"${ pageContext.request.contextPath }/project/changeProjectLogo.action",//文件上传地址，当然也可以直接写在input的data-url属性内  
		        //formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加  
		        done:function(e,result){ 
		            //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api  
		            //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息  
		            //返回的数据在result.result中，假设我们服务器返回了一个json对象 
		        	$("#projectLogo").attr("src","${ pageContext.request.contextPath }/"+result.result.updatedLogoPath);
    				$("#uploadLogo").css("display","none");
    				$("#changeLogo").text("更换");
    				$("#updateProject_project_logo_id").attr("value",result.result.attachmentId);
		        }  
		    });  
		   
		    $(".submit").click(function(){
				 $("form").validate({
						rules: {					
							'project.name': {
								required: true
							},
							'project.start_time': {
								required: true,
								dateISO:true 
							},
							'project.end_time': {
								required: true,
								dateISO:true 
							}
							
						},
						messages: {
							'project.name': {
								required: "请输入项目名称！"
							},
							'project.start_time': {
								required: "请选择项目开始时间！",
								dateISO:"请输入正确的时间格式：yyyy-mm-dd"
							},
							'project.end_time': {
								required: "请选择项目结束时间！",
								dateISO:"请输入正确的时间格式：yyyy-mm-dd"
							}
						}
					});
				 if($("form").valid()){ 
					 $(".submit").prop("disabled","disabled");
					 $(".submit").css("background","gray");						
					 $("form").submit();
					 }
			
			 });		    
		});
		</script>
	</head>

	<body>
	<s:form name="updateProject" action="updateProject" method="POST"
		theme="simple" enctype="multipart/form-data">
		<s:hidden name="project.id" value="%{project.id}"></s:hidden>
		<s:hidden name="project.user.id" value="%{project.user.id}"></s:hidden>
		<s:hidden name="project.logo.id" value="%{project.logo.id}"></s:hidden>
		<s:hidden name="project.status" value="%{project.status}"></s:hidden>
		<p class="title">编辑项目</p>
		<table id="project_tb">
			<tr>
				<td class="row_name">名称:</td>
				<td colspan="3"><s:textfield name="project.name" size="53" placeholder="输入项目名称" cssClass="name"></s:textfield></td>
			</tr>
			
			<tr>
				<td class="row_name">开始时间:</td>
				<td><s:textfield name="project.start_time" id="sttm" placeholder="yyyy-mm-dd" cssClass="date"></s:textfield>
				</td>
				<td class="row_name">结束时间:</td>
				<td><s:textfield name="project.end_time" id="endtm" placeholder="yyyy-mm-dd" cssClass="date"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="row_name">愿景:</td>
				<td colspan="3"><s:textarea name="project.description" rows="4" cols="50"></s:textarea>
				</td>
			</tr>
			<tr style="margin-top:10px">
				<td class="row_name">图标:</td>
				<td><s:if test="%{project.logo==null}">
						<img src='${ pageContext.request.contextPath }/images/project_default_logo.png' id="projectLogo">
					</s:if>
					<s:else>
						<img
							src='${ pageContext.request.contextPath }/<s:property  value="%{project.logo.path}"/>'
							width="60px" height="80px" id="projectLogo">
					</s:else> 
					<a href="#" id="changeLogo"><span style="color:blue;">更改...</span></a>
					<input type="file" name="image" id="fileupload_input" style="display:none;"/>						
					</td>
			</tr>
			<tr>
			    <td></td>
				<td><button class="submit">提交</button></td>
			</tr>
		</table>
	</s:form>
</body>
</html>
