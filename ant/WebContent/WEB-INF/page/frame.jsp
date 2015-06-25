<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<html>
<head>
	<title><tiles:insertAttribute name="title"/></title>
    <link href="CSS/style01.css" rel="stylesheet" type="text/css" />
    <script src="JS/jquery-1.2.6.min.js" type="text/javascript"></script>
    <script src="JS/jquery.simplemodal.js" type="text/javascript"></script>
    <script src="JS/jquery.tablesorter.min.js" type="text/javascript"></script>
    <script src="JS/jquery.tablesorter.pager.js" type="text/javascript"></script>
<script language="JavaScript">
function load(){
	if(document.all)
	{
		if(undefined==window.opener)
		{
			window.open(window.location,"","fullScreen=no;scrollbar=no,resizable=yes,menubar=no,toolbar=no,location=no,status=yes");
			window.opener=null;
			window.close();
		}
		else
		{
			self.moveTo(0,0);
			self.resizeTo(screen.availWidth,screen.availHeight);
		}
	}
	
}
</script> 
</head>
<frameset rows="73,*" frameborder="no" border="0" framespacing="0">
  <frame src="head.jsp" name="topFrame" frameborder="0" scrolling="No" noresize="noresize" id="topFrame" title="topFrame">
  <frame src="" name="mainFrame" frameborder="0" scrolling="auto" noresize="noresize" id="mainFrame" title="mainFrame">
 </frameset>
<noframes>
<body onload="load()">
</body>
</noframes>
</html>
