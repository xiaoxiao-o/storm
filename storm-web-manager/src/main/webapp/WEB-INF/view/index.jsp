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
<title>storm后台管理</title>
<base href=" <%=basePath%>">
<link rel="shortcut icon" href="static/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="static/plugin/layui-v2.4.5/layui/css/layui.css" />
<link rel="stylesheet" href="static/plugin/font-awesome-4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="static/css/admin.css" />
<script type="text/javascript" src="static/plugin/jquery-2.1.4.min.js" charset="utf-8"></script>
</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<!-- 头部 -->
		<div class="layui-header">
			<div class="layui-logo">
				<img src="static/images/logo.png" /> <cite>&nbsp;storm&emsp;</cite>
			</div>
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item" lay-unselect><a ew-event="flexible"
					title="侧边伸缩"><i class="layui-icon layui-icon-shrink-right"></i></a>
				</li>
				<li class="layui-nav-item" lay-unselect><a ew-event="refresh"
					title="刷新"><i class="layui-icon layui-icon-refresh-3"></i></a></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item layui-hide-xs"><a
					ew-event="fullScreen" title="全屏"><i class="fa fa-arrows-alt"></i></a></li>
				<li class="layui-nav-item"><a
					href="logout" title="退出"><i class="fa fa-power-off"></i></a></li>
			</ul>
		</div>

		<!-- 侧边栏 -->
		<div class="layui-side">
			<div class="layui-side-scroll">
				<ul class="layui-nav layui-nav-tree" lay-filter="admin-side-nav" lay-accordion="true" style="margin: 15px 0;" id="treeMenus">
				</ul>
			</div>
		</div>

		<!-- 主体部分 -->
		<div class="layui-body"></div>
		<!-- 底部 -->
		<div class="layui-footer">
			copyright © 2019 storm.vip all rights reserved. 
			<span class="pull-right">Version 3.1.1</span>
		</div>
	</div>

	<!-- 加载动画，移除位置在common.js中 -->
	<div class="page-loading">
		<div class="ball-loader">
			<span></span><span></span><span></span><span></span>
		</div>
	</div>
	<script>
		//此处代码必须优先执行，初始化html代码
		$.ajax({
			url:'selectTreeMenu',
			dataType:'json',
			async: false,	//取消异步加载
			success:function(data){
				$("#treeMenus").append($(initTreeMenu(data,true)));
			}
		});
		function initTreeMenu(data,isParent){ 
			var treeMenus = '',prefix = '',suffix = '' ;
			prefix = isParent? '<li class="layui-nav-item">' : '<dd>';
			suffix = isParent? '</li>' : '</dd>';
			$.each(data,function(i,menu){
				var childLength = menu.children.length;
				var treeMenu = '';
				if(childLength == 0){
					treeMenu = 	prefix 
							+ 		'<a lay-href="'+menu.href+'"><i class="'+menu.icon+'"></i>&emsp;<cite>'+menu.name+'</cite></a>' 
							+ 	suffix;
				}else{
					treeMenu = 	prefix 
							+ 		'<a><i class="'+menu.icon+'"></i>&emsp;<cite>'+menu.name+'</cite></a>' 
							+		'<dl class="layui-nav-child">'
							+			initTreeMenu(menu.children,false)
							+		'</dl>'
							+ 	suffix;
				}
				treeMenus += treeMenu;
			});
			return treeMenus;
		}
	</script>

</body>
<!-- js部分 -->
<script type="text/javascript" src="static/plugin/layui-v2.4.5/layui/layui.js"></script>
<script type="text/javascript" src="static/js/common.js"></script>
<script>
	//layui初始化
	layui.use(['index'], function() {
		var $ = layui.jquery;
		var index = layui.index;

		// 默认加载主页
		index.loadHome({
			menuPath: 'home',
			menuName: '<i class="layui-icon layui-icon-home"></i>'
		});

	});
</script>
</html>