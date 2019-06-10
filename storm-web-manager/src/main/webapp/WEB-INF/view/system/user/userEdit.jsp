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
<base href="<%=basePath%>">
<jsp:include page="../../head.jsp"/>
</head>
<body>
	<div class="main-content">
		<form class="layui-form layui-form-pane">
			<input type="hidden" name="id" value="${user.id }">
			<div class="layui-form-item">
				<label class="layui-form-label">用户名</label>
				<div class="layui-input-block">
					<input type="text" name="username" value="${user.username }"
						autocomplete="off" placeholder="请输入用户名" class="layui-input" readonly>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码</label>
				<div class="layui-input-block">
					<input type="password" name="password" lay-verify="password"
						placeholder="请输入密码,不输入则不修改" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">确认密码</label>
				<div class="layui-input-block">
					<input type="password" name="password2" lay-verify="password2"
						placeholder="请再次输入密码" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">描述</label>
				<div class="layui-input-block">
					<textarea name="userDesc" placeholder="请输入内容" class="layui-textarea" lay-verify="userDesc">${user.userDesc }</textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">角色</label>
				<div class="layui-input-block">
					<select name="roleId" lay-verify="roleId" multiple lay-search>
						<option value="">请选择角色</option>
						<c:forEach var="role" items="${roles }">
							<option value="${role.id }"
								<c:forEach var="userRole" items="${userRoles}">
									<c:if test="${role.id == userRole.id }">selected</c:if>
								</c:forEach>
							>${role.roleName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">状态</label>
				<div class="layui-input-block">
					<input type="radio" name="userState" value="1" title="启用" <c:if test="${user.userState==1 }">checked</c:if>>
					<input type="radio" name="userState" value="0" title="禁用" <c:if test="${user.userState==0 }">checked</c:if>>
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
    layui.config({
        base: 'static/plugin/layui-v2.4.5/layui/lay/modules/'
    }).extend({
        kzForm: 'kz.form'
    }).use(['kzForm'], function(){
		var form = layui.kzForm;
		//监听提交
		form.on('submit(form)', function(data) {
			$.ajax({
				url:'system/user/save',
				data:{"param":JSON.stringify(data.field)},
				dataType:'json',
				success:function(data){
					if(data.code==200){
						layer.msg("修改用户成功",{shade: 0,icon:1},function(index){
							parent.layer.closeAll();	//关闭所有
						});
					}else{
						layer.mag("修改用户失败",{icon:2});
					}
				}
			});
			return false;
		});
		
		//表单校验
		form.verify({
			password2: function(value, item){
				var password = $("[name='password']").val();
				if(password != ''){
					if(value == ''){
						return '请再次输入密码';
					}
					if(value!=password){
						return '两次密码输入不一致';
					}
				}else{
					if(value!=password){
						return '两次密码输入不一致';
					}
				}
			},
			userDesc: function(value, item){
				if(value == ''){
					return '必填项不能为空';
				}
			},
			roleId: function(value, item){
				if(value == null){
					return '请为用户至少分配一个角色';
				}
			}
		});
		
	});
</script>
</html>