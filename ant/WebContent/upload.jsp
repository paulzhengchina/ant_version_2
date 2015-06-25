<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.iframe-transport.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.fileupload.js"></script>

<script type="text/javascript">
$(function() {
	 $("#upload_image").live('click',function(){
			$("#fileupload_input").click();
		});
	$("#fileupload_input").fileupload({  
        url:"${ pageContext.request.contextPath }/common/uploadFile.action",//文件上传地址，当然也可以直接写在input的data-url属性内  
        //formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加  
        done:function(e,result){ 
            //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api  
            //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息  
            //返回的数据在result.result中，假设我们服务器返回了一个json对象            
            window.returnValue =  result.result.uploadedFilePath;
            window.close(); 
        }  
    })  
});
</script>
<title>文件上传</title>
</head>
<body class="register">

<img id="upload_image" src="${pageContext.request.contextPath}/images/upload.png" /><input type="file" name="image" id="fileupload_input" style="display:none;"/>		


</body>

</html>