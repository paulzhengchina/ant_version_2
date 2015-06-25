<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>个人设置</title>
<LINK rel="Shortcut Icon" href="${pageContext.request.contextPath}/images/icon/shortcut.png" />
<link rel="stylesheet" type="text/css" href="${ pageContext.request.contextPath }/css/common.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/jquery-ui.css" />
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.6.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery.iframe-transport.js"></script>

<style>
  
  .ui-tooltip {
    padding: 8px;
    color: white;
    border-radius: 10px;
    font: 14px;
    text-transform: uppercase;
    box-shadow: 0 0 7px black;
    background: black;
  }
</style>
<script>
	$(document).ready(function() {
		
		showTooltip();
		
		$(".update_photo").live('click',function(){
			$("#fileupload_input").click();
		});
		
		$(".name").live('click',function(){
			$(".name").html('<input type="text" value="'+$(".name").text().trim()+'" onBlur="updateUserName(this)"/>');
			$(".name input").focus();
		});
		
		$(".reset_password").live('click',function(){
			$(".reset_password").html('<input type="input" value="" onBlur="updateUserPassword(this)" placeholder="请输入新密码！"/>');
			$(".reset_password input").focus();
		});
		
	    $("#fileupload_input").fileupload({  
	        url:"user/updatePhoto.action",//文件上传地址，当然也可以直接写在input的data-url属性内  
	        formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加  
	        done:function(e,result){  
	            //done方法就是上传完毕的回调函数，其他回调函数可以自行查看api  
	            //注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息  
	            //返回的数据在result.result中，假设我们服务器返回了一个json对象  
	            $(".user_photo").attr("src","${ pageContext.request.contextPath }/"+result.result.photoFileName);
	        }  
	    }) 
	    
	    $(".left_menu li").mouseover(function(){
			if($(this).attr("class")!="selected" && $(this).parent().attr("id")!="project_logo"){				
				 var imgPath=$(this).find("img").attr("src");
				 if(imgPath.indexOf("_")<0)
					 {
					    var splitedPath=imgPath.split(".");
					    imgPath=splitedPath[0]+"_selected.png";
						 $(this).find("img").attr("src",imgPath);
						 }
			}
		});
		
		$(".left_menu li").mouseleave(function(){
			if($(this).attr("class")!="selected" && $(this).parent().attr("id")!="project_logo"){
				 var imgPath=$(this).find("img").attr("src");
				 var splitedPath=imgPath.split("_");
				 imgPath=splitedPath[0]+".png";
				 $(this).find("img").attr("src",imgPath);
			}
		});
		
	});
	
	function updateUserName(obj){
		var name=$(obj).attr("value");
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/user/updateUserName.action?username="+encodeURI(encodeURI(name)),
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			cache:false,
			success:function(data){
				var html='<span>'+name+'</span>';
				$(".name").html(html);
			}
		});
	}
	
	function updateUserPassword(obj){
		var password=$(obj).attr("value");
		if(password==null||password.length==0)
			 return false;
		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/user/updateUserPassword.action?password="+password,
			cache:false,
			success:function(data){
				$(".reset_password").html("密码更新成功！");
			}
		});
	}
</script>
</head>
<body>

<div class="content">
    <div class="header">
       <p class="project_name"></p>
       <p class="page_info">个人设置</p>
    </div>

	<div class="user_setting">
	    <div class="photo">
		   <s:if test="%{user.photo!=null}">
		    <img class="user_photo" src='${ pageContext.request.contextPath }/<s:property  value="%{user.photo.path}"/>'  width="156px" height="156px" />
		   </s:if>
		   <s:else>
		    <img class="user_photo" src='${ pageContext.request.contextPath }/images/icon/default_user_photo.png'  width="156px" height="156px" />
		   </s:else>
		   <div class="update_photo">
		      <img src='${ pageContext.request.contextPath }/images/icon/update_photo.png' alt="更新照片"/>
		   </div>		   
	     </div>
		 <div class="name">
		       <s:if test="%{user.name==null}">
		       <span>设置名称</span>
		       </s:if>
		       <s:if test="%{user.name==''}">
		       <span>设置名称</span>
		       </s:if>
		       <s:else>
		       <span><s:property value="user.name"/></span>
		       </s:else>
		 </div>
		 <div class="email">
		      <span><s:property value="user.email"/></span>
		 </div>
		 <div class="reset_password">
		      <span>更新密码</span>
		      <span class="password_message"></span>
		 </div>
		 <input type="file" name="photo" id="fileupload_input" style="display:none;"/>
	   </div>

	     

	</div>

    <div id="myDialog"></div>	
</div>
 <div class="left_menu">
			<ul>
					
				<li id="menu_item_projects">
				    <a href="${ pageContext.request.contextPath }/project/listProject.action">
				       <img src="${ pageContext.request.contextPath }/images/icon/project.png" title="项目"/>
				    </a>
				</li>
			
				<li id="menu_item_setting" class="selected">
				    <a href='<s:url value="/user/setting.action"></s:url>'>
				       <img src="${ pageContext.request.contextPath }/images/icon/setting_selected.png" title="设置"/>
				    </a>
				</li>
				
				<li id="menu_item_quit">
				    <a href="${ pageContext.request.contextPath }/user/logout.action">
				       <img src="${ pageContext.request.contextPath }/images/icon/quit.png" title="退出"/>
				    </a>
				</li>
				
			</ul>
</div>
</body>
</html>