<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>敏捷知识创建</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

   <div class="container">

      <!-- Static navbar -->
      <div class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><img src="../learning/pictures/logo.png" alt="敏捷项目管理工具_小蚂蚁看板" /></a>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li><a href="#">首页</a></li>
              <li><a href="${pageContext.request.contextPath}/blog/index.action">敏捷知识分享</a></li>
              <li  class="active"><a href="${pageContext.request.contextPath}/blog/createBlog.action">分享敏捷知识</a></li>
              <li><a href="${pageContext.request.contextPath}/blog/maintainblogs.action">维护敏捷知识</a></li>
            </ul>
          </div>
        </div>
      </div>
     
      <!-- Main component for a primary marketing message or call to action -->
     <s:form name="createblog" action="saveblog" method="POST" theme="simple" role="form" class="form-horizontal" >
        <s:hidden name="blog.logo.id"/>
        <div class="form-group">       
            <lable for="saveblog_blog_title">标题 </lable>
            <s:textfield name="blog.title" cssClass="form-control"  placeholder="输入标题"></s:textfield>
        </div>
        <div class="form-group">
            <lable for="saveblog_blog_summary">摘要 </lable><s:textarea name="blog.summary" cssClass="form-control"  rows="5" placeholder="输入内容"></s:textarea>
        </div>
        <div class="form-group">
            <lable for="saveblog_blog_content">内容 </lable><s:textarea name="blog.content" cssClass="form-control"  rows="15" placeholder="输入内容"></s:textarea>
        </div>
        <div class="form-group logo">
            <img id="upload_image" src="${pageContext.request.contextPath}/images/upload.png" /><input type="file" name="image" id="fileupload_input" style="display:none;"/>		
        </div>
        <div class="form-group">
	      <div class="checkbox">
	        <label>
	            <s:checkbox name="blog.recommend" value="true" fieldValue="1"></s:checkbox> 是否推荐
	        </label>
	      </div>
	    </div>

        <button type="submit" class="btn btn-primary">提交</button>
     </s:form>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
    <script src="${pageContext.request.contextPath}/ckeditor/adapters/jquery.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.iframe-transport.js"></script>
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.fileupload.js"></script>
    <script type="text/javascript">
       $(function() { 
    	   
	    	$( 'textarea#saveblog_blog_content' ).ckeditor();
	    	
	    	$("#upload_image").live('click',function(){
	   			$("#fileupload_input").click();
	   		});
	    	
	   	    $("#fileupload_input").fileupload({  
	           url:"${ pageContext.request.contextPath }/blog/uploadLogo.action",//文件上传地址，当然也可以直接写在input的data-url属性内  
	           //formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加  
	           done:function(e,result){ 
	               //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api  
	               //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息  
	               //返回的数据在result.result中，假设我们服务器返回了一个json对象            
	           var path= "${ pageContext.request.contextPath }/"+ result.result.updatedLogoPath;
	           alert(path);
	           $("#saveblog_blog_logo_id").attr("value",result.result.attachmentId);
	           $("#upload_image").attr("src",path);
	           $("#upload_image").attr("style",'width:70px;height:80px');
	           }   
	       });
       
       });
     </script>
       
  </body>
</html>
