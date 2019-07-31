<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String path = request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
	<title>后台@storm</title>
	<base href=" <%=path%>">
<link rel="stylesheet" href="static/plugin/layui-v2.4.5/layui/css/layui.css" />
<link rel="stylesheet" href="static/css/admin.css" />
</head>
<body>
	<!-- 加载动画，移除位置在common.js中 -->
	<div class="page-loading">
		<div class="ball-loader">
			<span></span><span></span><span></span><span></span>
		</div>
	</div>

	<div class="layui-card-body" style="text-align: center;">
		<h2 style="margin-top: 170px; margin-bottom: 20px; font-size: 28px; color: #91ADDC;">欢迎使用storm后台管理系统!</h2>
		<img src="static/images/welcome.png" style="max-width: 100%;">
	</div>

</body>
<!-- js部分 -->
<script type="text/javascript" src="static/plugin/layui-v2.4.5/layui/layui.js"></script>
<script type="text/javascript" src="static/js/common.js"></script>
<script type="text/javascript">
    layui.use(['layer'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
    });
</script>
</html>