<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<title>登录</title>
<base href=" <%=basePath%>">
<link rel="shortcut icon" href="static/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="static/css/login.css" />
<link rel="stylesheet" href="static/plugin/layui-v2.4.5/layui/css/layui.css" />
</head>
<body class="login-bg">
	<div class="login">
		<div class="message">会员登录</div>
		<div id="darkbannerwrap"></div>
		<form method="post" class="layui-form" action="login/doLogin">
			<input name="username" placeholder="用户名" type="text" lay-verify="required" class="layui-input">
			<hr class="hr15">
			<input name="password" placeholder="密码" type="password" lay-verify="required" class="layui-input">
			<hr class="hr15">
			<input name="kaptcha" placeholder="验证码" type="text" lay-verify="required" class="layui-input kaptcha">
			<img class="kaptcha-img" title="换一张试试" alt="验证码加载失败!" src="login/kaptcha" onclick="reloadKaptcha()">
			<hr class="hr15">
			<c:if test="${not empty error }">
				<span class="error-message">${error }</span>
				<hr class="hr15">
			</c:if>
			<input class="loginin" value="登录" lay-submit lay-filter="login" style="width: 100%;" type="submit">
		</form>
	</div>
	<!-- 底部结束 -->
</body>
<script src="static/plugin/jquery-2.1.4.min.js" charset="utf-8"></script>
<script type="text/javascript">
	$(function(){
		if(self != top){   //不是顶层页面 
		   top.location.href="login";  
		}
	});
	
	//刷新验证码
	function reloadKaptcha(){
		$('.kaptcha-img').attr('src','login/kaptcha?time='+new Date().getTime());
	}
</script>
</html>