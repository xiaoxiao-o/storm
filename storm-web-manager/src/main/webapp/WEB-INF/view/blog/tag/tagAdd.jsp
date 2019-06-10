<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href=" <%=basePath%>">
<jsp:include page="../../head.jsp"/>
</head>
<body>
	<div class="main-content">
		<form class="layui-form layui-form-pane">
			<div class="layui-form-item">
				<label class="layui-form-label">标签名称</label>
				<div class="layui-input-block">
					<input type="text" name="tagName" lay-verify="tagName"
						autocomplete="off" placeholder="请输入标签名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">描述</label>
				<div class="layui-input-block">
					<textarea name="tagDesc" placeholder="请输入内容" class="layui-textarea" lay-verify="tagDesc"></textarea>
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
<jsp:include page="../../foot.jsp"/>
<script>
	layui.use(['form'], function(){
		var form = layui.form;
		//监听提交
		form.on('submit(form)', function(data) {
			$.ajax({
				url:'blog/tag/save',
				data:{"param":JSON.stringify(data.field)},
				dataType:'json',
				success:function(data){
					if(data.code==200){
						layer.msg("新增标签成功",{shade: 0,icon:1},function(index){
							parent.layer.closeAll();	//关闭所有
						});
					}else{
						layer.msg("新增标签失败",{icon:2});
					}
				}
			});
			return false;
		});
		
		//表单校验
		form.verify({
			tagName: function(value, item){
				if(value == ''){
					return '必填项不能为空';
				}
			},
			tagDesc: function(value, item){
				if(value == ''){
					return '必填项不能为空';
				}
			}
		});
		
	});
</script>
</html>