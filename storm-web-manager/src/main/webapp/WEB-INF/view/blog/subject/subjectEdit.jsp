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
<link rel="stylesheet" href="static/css/mainPage.css" />
<link rel="stylesheet" href="static/plugin/layui-v2.4.5/layui/css/layui.css" />
</head>
<body>
	<div class="main-content">
		<form class="layui-form layui-form-pane">
			<input type="hidden" name="id" value="${subject.id }">
			<div class="layui-form-item">
				<label class="layui-form-label">用户名</label>
				<div class="layui-input-block">
					<input type="text" name="subjectName" lay-verify="subjectName" value="${subject.subjectName }"
						autocomplete="off" placeholder="请输入标签名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">描述</label>
				<div class="layui-input-block">
					<textarea name="subjectDesc" placeholder="请输入内容" class="layui-textarea" lay-verify="subjectDesc">${subject.subjectDesc }</textarea>
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
    layui.use(['form'], function(){
        var form = layui.form;
        //监听提交
        form.on('submit(form)', function(data) {
            $.ajax({
                url:'blog/tags/save',
                data:{"param":JSON.stringify(data.field)},
                dataType:'json',
                success:function(data){
                    if(data.code==200){
                        layer.msg("修改分类成功",{shade: 0,icon:1},function(index){
                            parent.layer.closeAll();	//关闭所有
                        });
                    }else{
                        layer.msg("修改分类失败",{icon:2});
                    }
                }
            });
            return false;
        });

        //表单校验
        form.verify({
            tagsName: function(value, item){
                if(value == ''){
                    return '必填项不能为空';
                }
            },
            tagsDesc: function(value, item){
                if(value == ''){
                    return '必填项不能为空';
                }
            }
        });

    });
</script>
</html>