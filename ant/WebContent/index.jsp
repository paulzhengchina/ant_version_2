<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="baidu-site-verification" content="dbdPP1Vxa3" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="keywords" content="免费项目管理工具,项目管理工具,敏捷项目管理 ,敏捷需求管理 ,敏捷计划管理 ,看板管理 ,敏捷任务管理 ,敏捷知识,项目需求管理"/>
<meta name="description" content="小蚂蚁看板旨在提供一款免费的简单实用的敏捷项目管理工具帮助中小企业进行项目研发管理工作。使得项目管理更加高效且项目成功率高。功能包括：敏捷项目管理，敏捷需求管理，敏捷项目需求管理，敏捷项目计划管理，及敏捷知识分享。" />
<LINK rel="Bookmark" href="${pageContext.request.contextPath}/images/icon/ant.jpg" />
<LINK rel="Shortcut Icon" href="${pageContext.request.contextPath}/images/icon/shortcut.png" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.placeholder.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>

<title>小蚂蚁-敏捷项目管理工具 </title>
</head>

<body class="index">
   <div class="top_part">
       <div class="logo_slogan">
         <img src="${pageContext.request.contextPath}/images/logo_index.jpg"/>
         <h1>简易的敏捷项目管理工具<br>让项目管理变得轻松高效</h1>       
       </div>
       <div class="login_register">
          <s:form name="userLogin" action="userLogin" method="POST" theme="simple" namespace="/user">
                <s:actionmessage/>				
				<s:textfield name="user.email" placeholder="邮箱账号"></s:textfield>
				<s:password name="user.password" placeholder="密码"></s:password>				
		  </s:form>
		  <div class="action_button">
		         <img src="${pageContext.request.contextPath}/images/login_button.jpg" id="login"/>
				 <img src="${pageContext.request.contextPath}/images/register_button.jpg" id="register"/>
		  </div>
		  <div class="find_password">
		        <a href="#">忘记密码?</a>
		  </div>
       </div>
   </div>
   <div class="second_part">
     <div class="feature_list">
      <div class="feature">
         <div class="feature_title">
	         <img src="${pageContext.request.contextPath}/images/icon/kanban_gray.png" alt="敏捷项目管理工具_看板管理"/>
	         <h2><a href="http://www.antkanban.com/learning/helpmain.html">看板管理</a></h2>
         </div>
         <img src="${pageContext.request.contextPath}/images/kanban_shotcut.png" class="feature_img"/>
      </div>
      <div class="feature">
         <div class="feature_title">
	         <img src="${pageContext.request.contextPath}/images/icon/story_gray.png" alt="敏捷项目管理工具_敏捷需求管理"/>
	         <h2><a href="http://www.antkanban.com/learning/helpmain.html">敏捷需求管理</a></h2>
         </div>
         <img src="${pageContext.request.contextPath}/images/story_shotcut.png" class="feature_img"/>
      </div>
      <div class="feature">
         <div class="feature_title">
	         <img src="${pageContext.request.contextPath}/images/icon/plan_gray.png" alt="敏捷项目管理工具_敏捷计划管理"/>
	         <h2><a href="http://www.antkanban.com/learning/helpmain.html">敏捷计划管理</a></h2>
         </div>
         <img src="${pageContext.request.contextPath}/images/plan_shotcut.png" class="feature_img"/>
      </div>
      <div class="clear"></div>
      </div>
    
    <div class="register_dialog dialog"></div>
   </div>
   <div class="foot_part">
     
     <div class="links"><a href="http://www.antkanban.com/learning/helpmain.html">帮助</a> | <a href="http://www.antkanban.com/blog/index.action">敏捷项目知识分享</a></div>
     <div class="beianhao">© 2014 antKanban 苏ICP备14031966</div>
     
   </div>
</body>
<script type="text/javascript">
$(function() {
	
	 $("#userLogin input").placeholder({
		 event :'keydown'
	 });
	 
	 $("#login").click(function(){
		 $("form").validate({
				rules: {					
					'user.email': {
						required: true,
						email: true
					},
					'user.password': {
						required: true
					}
				},
				messages: {
					'user.email': {
						required: "请输入登录邮箱！",
						email: "请输入正确的邮箱！"
					},
					'user.password': {
						required: "请输入密码！",
						minlength: "密码必需包含至少5个字母或者数字！"
					}
				}
			});
		 $("#userLogin").submit();
	 });
   
	 $("#register").click(function(){
			var DIALOG = $(".register_dialog");
			DIALOG.dialog({
				autoOpen : false,
				resizable: false,
				modal : true,
				width : 420,
				height : 250,
				close : function() {
					
				}
			});
			DIALOG.dialog('open');
			DIALOG.css('background','url("../images/loading.gif")  no-repeat  center rgba(0, 0, 0, 0)') ;
			$(".ui-dialog-titlebar").html("<img src='${pageContext.request.contextPath}/images/icon/dialog_close.png'/>");
			$(".ui-dialog-titlebar img").css("position","absolute");
			$(".ui-dialog-titlebar img").css("right","2px");
			$(".ui-dialog-titlebar img").css("height","17px");
			$(".ui-dialog-titlebar img").css("width","17px");
			$(".ui-dialog-titlebar img").css("cursor","pointer");
			$(".ui-dialog-titlebar img").live('click',function(){
				DIALOG.dialog('close');
			});
			DIALOG.load("${pageContext.request.contextPath}/register.jsp",function(){DIALOG.css('background','none') ;});
		});
	 
	 $(".find_password").click(function(){
			DIALOG = $(".register_dialog");
			DIALOG.dialog({
				autoOpen : false,
				resizable: false,
				modal : true,
				width : 420,
				height : 250,
				close : function() {
					
				}
			});
			DIALOG.dialog('open');
			DIALOG.html("");
			DIALOG.css('background','url("../images/loading.gif")  no-repeat  center rgba(0, 0, 0, 0)') ;
			$(".ui-dialog-titlebar").html("<img src='${pageContext.request.contextPath}/images/icon/dialog_close.png'/>");
			$(".ui-dialog-titlebar img").css("position","absolute");
			$(".ui-dialog-titlebar img").css("right","2px");
			$(".ui-dialog-titlebar img").css("height","20px");
			$(".ui-dialog-titlebar img").css("width","20px");
			$(".ui-dialog-titlebar img").css("cursor","pointer");
			$(".ui-dialog-titlebar img").live('click',function(){
				DIALOG.dialog('close');
			});
			DIALOG.load("${pageContext.request.contextPath}/findpassword.jsp",function(){DIALOG.css('background','none') ;});
		});
});
</script>
</html>