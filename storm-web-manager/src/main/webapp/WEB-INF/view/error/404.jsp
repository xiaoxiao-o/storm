<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%String path = request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
    <title>后台@storm</title>
    <base href=" <%=path%>">
<link rel="stylesheet" href="static/plugin/layui-v2.4.5/layui/css/layui.css" />
<link rel="stylesheet" href="static/css/admin.css" />
<link rel="stylesheet" href="static/css/error-page.css" />
</head>
<body>

<!-- 加载动画，移除位置在common.js中 -->
<div class="page-loading">
    <div class="ball-loader">
        <span></span><span></span><span></span><span></span>
    </div>
</div>

<!-- 正文开始 -->
<div class="error-page">
    <img class="error-page-img" src="static/images/ic_404.svg">
    <div class="error-page-info">
        <h1>404</h1>
        <div class="error-page-info-desc">啊哦，你访问的页面不存在(⋟﹏⋞)</div>
        <div>
            <button ew-href="home" class="layui-btn">返回首页</button>
        </div>
    </div>
</div>
</body>
<!-- js部分 -->
<script type="text/javascript" src="static/plugin/layui-v2.4.5/layui/layui.js"></script>
<script type="text/javascript" src="static/js/common.js"></script>

<script>
    layui.use(['admin'], function () {
        var $ = layui.jquery;
        var admin = layui.admin;
    });
</script>

</html>