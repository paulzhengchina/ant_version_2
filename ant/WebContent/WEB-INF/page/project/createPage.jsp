<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>Project</title>		
		<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.fileupload.js"></script>
		<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.iframe-transport.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
		    $( "#sttm" ).datepicker({showButtonPanel: true,dateFormat : "yy-mm-dd"});
		    $( "#endtm" ).datepicker({showButtonPanel: true,dateFormat : "yy-mm-dd"});
		    
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
					 return false;
					 }
				
			 });
		    
		    $("#upload_image").live('click',function(){
				$("#fileupload_input").click();
			});
		    
		    $("#fileupload_input").fileupload({  
		        url:"${ pageContext.request.contextPath }/project/changeProjectLogo.action",//文件上传地址，当然也可以直接写在input的data-url属性内  
		        //formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加  
		        done:function(e,result){ 
		            //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api  
		            //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息  
		            //返回的数据在result.result中，假设我们服务器返回了一个json对象 
		            var path= "${ pageContext.request.contextPath }/"+ result.result.updatedLogoPath;
		            if($("#photo").children("img").length)
		            	{
		            	$("#photo img").attr("src",path);
		            	$("#photo img").attr("style",'width:70px;height:80px;float:left');
		            	}
		           
		            $("#createProject_project_logo_id").attr("value",result.result.attachmentId);
		        }  
		    })  
		    
		});
		</script>
	</head>

	<body>
	<s:form name="createProject" action="createProject" method="POST"
		theme="simple" enctype="multipart/form-data">
		<s:hidden name="project.logo.id"/>
		<p class="title">创建项目</p>
		<table id="project_tb">
			<tr>
				<td class="row_name">名称:</td>
				<td colspan="3"><s:textfield name="project.name" size="54" placeholder="输入项目名称" cssClass="name"></s:textfield></td>
			</tr>
			<tr>
				<td class="row_name">开始时间:</td>
				<td><s:textfield name="project.start_time" id="sttm" placeholder="yyyy-mm-dd" cssClass="date"></s:textfield>
				</td>
				<td class="row_name">结束时间:</td>
				<td><s:textfield name="project.end_time" id="endtm" placeholder="yyyy-mm-dd" cssClass="date"></s:textfield>
			</tr>
			<tr>
				<td class="row_name">愿景:</td>
				<td  colspan="3"><s:textarea name="project.description" rows="4" cols="53" placeholder="介绍项目愿景" style="width:390px"></s:textarea>
				</td>
			</tr>

			<tr>
			    <td class="row_name">图标:</td>
				<td id="photo"><img id="upload_image" src="${pageContext.request.contextPath}/images/upload.png" /><input type="file" name="image" id="fileupload_input" style="display:none;"/></td>
			</tr>
			<tr>
			    <td></td>
				<td>
					<button class="submit">提交</button>
				</td>
			</tr>
		</table>
	</s:form>
</body>
</html>

 
