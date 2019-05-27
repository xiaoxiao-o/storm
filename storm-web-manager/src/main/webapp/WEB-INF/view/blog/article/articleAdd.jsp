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
<base href=" <%=basePath%>">
<link rel="stylesheet" href="static/plugin/layui-v2.4.5/layui/css/layui.css" />
<link rel="stylesheet" href="static/css/mainPage.css" />
<link rel="stylesheet" href="static/css/layui-select-m.css" />
</head>
<body>
	<div class="main-content">
		<form class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<label class="layui-form-label">文章标题</label>
				<div class="layui-input-block">
					<input type="text" name="blogName" lay-verify="blogName"
						   autocomplete="off" placeholder="请输入标题" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">文章摘要</label>
				<div class="layui-input-block">
					<input type="text" name="summary" lay-verify="summary"
						   placeholder="请输入摘要" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">文章内容</label>
				<div class="layui-input-block">
					<textarea id="demo" name="userDesc" placeholder="请输入内容" class="layui-textarea" lay-verify="userDesc"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">标签</label>
				<div class="layui-input-block">
					<select name="多选+搜索+大小写不敏感" lay-verify="required" multiple lay-search>
						<option value="">请选择您的兴趣爱好</option>
						<option>sing1</option>
						<option selected>sing2</option>
						<option>SING1-大写</option>
						<option>movie1</option>
						<option selected>movie2</option>
						<option>movie3</option>
						<option>MOVIE4</option>
						<option>swim</option>
						<option>moon</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">文章封面</label>
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal" id="cover"><i class="layui-icon"></i>上传封面</button>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">文章附件</label>
				<div class="layui-input-block">
					<button type="button" class="layui-btn layui-btn-normal" id="enclosure"><i class="layui-icon"></i>上传附件</button>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block right">
					<button class="layui-btn" lay-submit="" lay-filter="form">提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>

		</form>
	</div>
</body>
<script src="static/plugin/jquery-2.1.4.min.js" charset="utf-8"></script>
<script src="static/plugin/layui-v2.4.5/layui/layui.js" charset="utf-8"></script>
<script>
    layui.config({
        base: 'static/plugin/layui-v2.4.5/layui/lay/modules/'
    }).extend({
        //kzLayedit: 'kz.layedit',
		kzForm: 'kz.form'
    })

	// 	.use('kzLayedit', function(){return;//debugger
    //     var $=layui.jquery;
    //     var layedit = layui.kzLayedit;
    //     var form = layui.kzForm;
    //     layedit.set({
	// 		tool: ['strong', 'italic', 'underline', 'del',
	// 			'|', 'fontFomatt','fontfamily','fontSize','colorpicker','fontBackColor', 'face',
	// 			'|', 'left', 'center', 'right',
	// 			'|', 'link', 'images', 'image_alt','code','table',
	// 			'|', 'preview'
    //     	]
    // 	});
    //     var ieditor = layedit.build('demo');
	//
    //     form.render();
    // });
</script>
</html>