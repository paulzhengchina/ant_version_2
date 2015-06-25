<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Project</title>
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-ui-1.8.18.custom.min.js"></script>
	
<script type="text/javascript" >
	
	
	$(document).ready(function() {
		
		$("#btn").click(function(){
			$("#testImg").attr("src","${ pageContext.request.contextPath }/statistics/testchart.action"); 
		});
				

	});

	
</script>
</head>
<body>

	<button id="btn">Click Me!</</button>
	<img id="testImg" width=500 height=270 border=0></pre> 
	<div id="jchart"></div>
         
</body>
</html>