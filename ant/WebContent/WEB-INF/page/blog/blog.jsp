<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content='<s:property value="blog.summary" escape="false"/>'>
<meta name="author" content="">
<title><s:property value="blog.title" escape="false"/></title>
<!-- Bootstrap core CSS -->
<LINK rel="Bookmark" href="${pageContext.request.contextPath}/images/icon/ant.jpg" />
<LINK rel="Shortcut Icon" href="${pageContext.request.contextPath}/images/icon/shortcut.png" />
<link
	href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/blog.css"
	rel="stylesheet">
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
					<button type="button" class="navbar-toggle btn-navbar" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="http://www.antkanban.com"><img src="../learning/pictures/logo.png" alt="敏捷项目管理工具_小蚂蚁看板" /></a>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="http://www.antkanban.com"">首页</a></li>
						<li><a href="../learning/helpmain.html">帮助</a></li>
						<li  class="active"><a href="${pageContext.request.contextPath}/blog/index.action"">敏捷知识分享</a></li>
					</ul>
				</div>
			</div>
		</div>

		<!-- Main component for a primary marketing message or call to action -->
		<div class="row">
			<div class="col-sm-12 col-md-9 col-lg-9 blog_entity">
			     <h2><s:property value="blog.title"/>  <small><s:date name="blog.createdTime" format="yyyy-MM-dd" /></small></h2>
			     <p class="text-left summary"><s:property value="blog.summary" escape="false"/></p>
			     <p class="text-left"><s:property value="blog.content" escape="false"/></p>
			</div>
			<div class="hidden-xs hidden-sm col-xs-12 col-sm-3 col-md-3 recommendBlogList">
			      <h2>推荐阅读</h2>
			     <hr class="heavy">
			      <s:iterator value="recommendBlogList" var="recommendBlog">
			        <div class="row recommend_list_item">
				         <div class="hidden-xs blog_logo">
					            <img src='${pageContext.request.contextPath}/<s:property value="%{#recommendBlog.logo.path}"/>' />
					     </div>
					     <div>
						     <h3><a href='<s:url value="/blog/readblog.action"><s:param name="blogid" value="%{#recommendBlog.id}"/></s:url>'><s:property value="%{#recommendBlog.title}" escape="false"/></a>
						     </h3>
					     </div>
				    </div>
				    <hr>
			     </s:iterator>
			</div>
		</div>
	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
</body>
</html>
