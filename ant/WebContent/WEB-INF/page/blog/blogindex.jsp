<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords" content="项目管理 ,敏捷项目管理,scrum,scrum开发,精益,scrum学习,敏捷项目需求管理 ,敏捷项目知识,敏捷项目知识分享,敏捷需求管理 ,项目需求管理,项目成员,敏捷计划管理 ,敏捷项目计划管理,看板管理 ,项目执行过程 "/>
<meta name="description" content="分享项目管理的知识和一些常识及问题,尤其是一些敏捷的知识，如scrum,精益开发，XP等项目执行过程。同时也学习scrum，精益等开发管理。" />
<meta name="author" content="">
<title>项目管理及敏捷项目管理知识分享</title>
<LINK rel="Bookmark" href="${pageContext.request.contextPath}/images/icon/ant.jpg" />
<LINK rel="Shortcut Icon" href="${pageContext.request.contextPath}/images/icon/shortcut.png" />
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
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
						<li><a href="http://www.antkanban.com">首页</a></li>
						<li><a href="../learning/helpmain.html">帮助</a></li>
						<li  class="active"><a href="#">敏捷知识分享</a></li>
					</ul>
				</div>
			</div>
		</div>

		<!-- Main component for a primary marketing message or call to action -->
		<div class="row main_content">
			<div class="col-sm-12 col-md-8 col-lg-8 blogList">
				   <s:iterator value="blogList" var="blog">
				    <div class="row">
				           <div class="hidden-xs blog_logo">
				            <img src='${pageContext.request.contextPath}/<s:property value="%{#blog.logo.path}"/>' />
				           </div>
					       <div class="blog_info">
						     <h2><a href='<s:url value="/blog/readblog.action"><s:param name="blogid" value="%{#blog.id}"/></s:url>'>
						                <s:property value="%{#blog.title}" escape="false"/>
						     </a></h2>
						     <p class="text-left blog_summary"><s:property value="summary" escape="false"/></p>
						     <p class="text-left blog_time"><s:date name="%{#blog.createdTime}" format="yyyy-MM-dd HH:MM" /> 更新</p>
						   </div>
						   <hr>
				   </div>
				  </s:iterator>
			</div>
			<div class="hidden-xs hidden-sm col-xs-12 col-sm-4 col-md-4 recommendBlogList">
			     <h2>推荐阅读</h2>
			     <hr class="heavy">
			      <s:iterator value="recommendBlogList" var="recommendBlog">
			        <div class="row recommend_list_item">
				         <div class="hidden-xs blog_logo">
					            <img src='${pageContext.request.contextPath}/<s:property value="%{#recommendBlog.logo.path}"/>' />
					     </div>
					     <div>
						     <h3><a href='<s:url value="/blog/readblog.action"><s:param name="blogid" value="%{#recommendBlog.id}"/></s:url>'>
						                <s:property value="%{#recommendBlog.title}" escape="false"/></a>
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
