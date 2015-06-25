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
	<div class="impediment_detail">

	   <div class="left-part">
	     <img src="${pageContext.request.contextPath}/images/icon/impediment_serverity_<s:property value='impediment.severity'/>.png"/>
	     <p class="column">标题</p>
	     <p class="column_content"><s:property value='impediment.name'/></p>
	     <p class="column">报告人</p>
	     <p class="column_content"><s:property value='impediment.createdBy.name'/></p>
	     <p class="column">负责人</p>
	     <p class="column_content"><s:property value='impediment.fixedBy.name'/></p>
	     <p class="column">时间</p>
	     <p class="column_content"><s:date name="impediment.createdTime" format="yyyy/MM/dd" /> -- <s:date name="impediment.fixedTime" format="yyyy/MM/dd" /></p>
	     <p class="column_content status">
	        <s:if test="%{impediment.status==2||impediment.status==3}">
	          <img src="${pageContext.request.contextPath}/images/icon/impediment_final_status_<s:property value='impediment.status'/>.png"/>
	        </s:if>
	     </p>	     
	   </div>
	   <div class="right-part">
	     <p class="column">描述</p>
	     <p class="column_content"><s:property value='impediment.description'/></p>
	     <p class="column">原因分析</p>
	     <p class="column_content"><s:property value='impediment.reason'/></p>
	     <p class="column">解决方案</p>
	     <p class="column_content"><s:property value='impediment.solution'/></p>
	     <p class="column">结果描述</p>
	     <p class="column_content"><s:property value='impediment.result'/></p>
	   </div>


	</div>
</body>
</html>
