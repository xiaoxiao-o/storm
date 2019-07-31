<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%String path = request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
	<title>后台@storm</title>
	<base href=" <%=path%>">
<jsp:include page="../../head.jsp"/>
</head>
<body>
	<div class="main-content">
		<form class="layui-form layui-form-pane">
			<input type="hidden" name="id" value="${music.id }">
			<div class="layui-form-item">
				<label class="layui-form-label">音乐</label>
				<div class="layui-input-block">
					<input type="text" name="title" value="${music.title }" lay-verify="title"
						autocomplete="off" placeholder="请输入音乐名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">作者</label>
				<div class="layui-input-block">
					<input type="text" name="artist" value="${music.artist }" lay-verify="artist"
						   autocomplete="off" placeholder="请输入作者名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">封面</label>
				<div class="layui-input-block">
					<input type="text" name="cover" value="${music.cover }" lay-verify="cover"
						   autocomplete="off" placeholder="请输入封面地址" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">资源</label>
				<div class="layui-input-block">
					<input type="text" name="source" value="${music.source }" lay-verify="source"
						   autocomplete="off" placeholder="请输入资源地址" class="layui-input">
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
                url:'com.whyxs.controller.blog/music/save',
                data:{"param":JSON.stringify(data.field)},
                dataType:'json',
                success:function(data){
                    if(data.code==200){
                        layer.alert("修改音乐成功",{shade: 0,icon:1},function(index){
                            parent.layer.closeAll();	//关闭所有
                        });
                    }else{
                        layer.alert("修改音乐失败",{icon:2});
                    }
                }
            });
            return false;
        });

        //表单校验
        form.verify({
            title: function(value, item){
                if(value == ''){
                    return '必填项不能为空';
                }
            },
            artist: function(value, item){
                if(value == ''){
                    return '必填项不能为空';
                }
            },
            cover: function(value, item){
                if(value == ''){
                    return '必填项不能为空';
                }
            },
            source: function(value, item){
                if(value == ''){
                    return '必填项不能为空';
                }
            }
        });

    });
</script>
</html>