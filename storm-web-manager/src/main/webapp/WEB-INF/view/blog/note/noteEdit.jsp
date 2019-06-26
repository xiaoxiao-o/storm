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
			<input type="hidden" name="id" value="${note.id }">
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">通知</label>
				<div class="layui-input-block">
					<textarea name="note" placeholder="请输入内容" class="layui-textarea" lay-verify="note">${note.note }</textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">高亮</label>
				<div class="layui-input-block">
					<input type="radio" name="highLight" value="1" title="启用" <c:if test="${note.highLight==1 }">checked</c:if>>
					<input type="radio" name="highLight" value="0" title="禁用" <c:if test="${note.highLight==0 }">checked</c:if>>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">状态</label>
				<div class="layui-input-block">
					<input type="radio" name="status" value="1" title="启用" <c:if test="${note.status==1 }">checked</c:if>>
					<input type="radio" name="status" value="0" title="禁用" <c:if test="${note.status==0 }">checked</c:if>>
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
                url:'blog/note/save',
                data:{"param":JSON.stringify(data.field)},
                dataType:'json',
                success:function(data){
                    if(data.code==200){
                        layer.alert("修改通知成功",{shade: 0,icon:1},function(index){
                            parent.layer.closeAll();	//关闭所有
                        });
                    }else{
                        layer.alert("修改通知失败",{icon:2});
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