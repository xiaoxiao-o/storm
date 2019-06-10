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
				<label class="layui-form-label">父级菜单</label>
				<div class="layui-input-block">
					<select name=pId lay-verify="pId" lay-search="">
						<option value="">请选择或搜索选择菜单</option>
						<option value="0">顶级菜单</option>
						<c:forEach var="menu" items="${menus }">
							<!-- 若当前菜单的url不为空,则为跳转菜单,不可添加子菜单 -->
							<option value="${menu.id }" <c:if test="${not empty menu.url }">disabled</c:if>>${menu.menuName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">菜单名称</label>
				<div class="layui-input-block">
					<input type="text" name="menuName" lay-verify="menuName"
						autocomplete="off" placeholder="请输入菜单名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">菜单地址</label>
				<div class="layui-input-block">
					<input type="text" name="url"
						autocomplete="off" placeholder="请输入菜单地址" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">图标</label>
				<div class="layui-input-block">
					<input type="text" name="icon"
						autocomplete="off" placeholder="请输入菜单图标代码" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">排序</label>
				<div class="layui-input-block">
					<input type="number" name="sort"
						autocomplete="off" placeholder="请输入菜单排序" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">资源名称</label>
				<div class="layui-input-block">
					<input type="text" name="resource"
						autocomplete="off" placeholder="请输入资源名称" class="layui-input">
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
				url:'system/menu/save',
				data:{"param":JSON.stringify(data.field)},
				dataType:'json',
				success:function(data){
					if(data.code==200){
						layer.msg("新增菜单成功",{shade: 0,icon:1},function(index){
							parent.layer.closeAll();	//关闭所有
						});
					}else{
						layer.msg("新增菜单失败",{icon:2});
					}
				}
			});
			return false;
		});
		
		//表单校验
		form.verify({
			pId: function(value, item){ 
				if(value == ''){
					return '必填项不能为空';
				}
			},
			menuName: function(value, item){
				if(value == ''){
					return '必填项不能为空';
				}
			}
		});
		
	});
</script>
</html>