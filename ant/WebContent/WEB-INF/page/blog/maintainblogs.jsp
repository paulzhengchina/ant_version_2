<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>博客维护</title>
<!-- Bootstrap core CSS -->
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
					<a class="navbar-brand" href="#"><img src="../learning/pictures/logo.png" alt="敏捷项目管理工具_小蚂蚁看板" /></a>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li><a href="#">首页</a></li>
						<li  class="active"><a href="${pageContext.request.contextPath}/blog/index.action">敏捷知识分享</a></li>
						<li><a href="${pageContext.request.contextPath}/blog/createblog.action">分享敏捷知识</a></li>
					</ul>
				</div>
			</div>
		</div>

		<!-- Main component for a primary marketing message or call to action -->
		<table class="table table-striped">
		      <thead>
                <tr>
                  <th>标题</th>
                  <th>创建时间</th>
                  <th>操作</th>
                </tr>
              </thead>
		   <tbody>
		      <s:iterator value="blogList">
		       <tr>
                  <td><s:property value="title"/></td>
                  <td><s:property value="createdTime"/></td>
                  <td>
                       <div class="btn-group">
                          <a href='<s:url value="editblog.action"><s:param name="blogid" value="id"/></s:url>' class="btn btn-primary">编辑</a>
                          <a href='<s:url value="deleteblog.action"><s:param name="blogid" value="id"/></s:url>' class="btn btn-primary">删除</a>
				       </div>
                  </td>
                </tr>
               </s:iterator>
		   </tbody>
        </table>

	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
</body>
</html>
