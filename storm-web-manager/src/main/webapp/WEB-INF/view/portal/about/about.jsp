<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<form class="layui-form layui-form-pane" style="width: 90%;min-width: 360px;margin: 0 auto;">
			<input type="hidden" name="id" value="${about.id}">
			<fieldset class="layui-elem-field layui-field-title">
				<legend>关于网站</legend>
			</fieldset>
			<div class="layui-form-item">
				<label class="layui-form-label">标题</label>
				<div class="layui-input-block">
					<input type="text" name="title" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.title}">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">副标题</label>
				<div class="layui-input-block">
					<input type="text" name="subTitle" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.subTitle}">
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">链接列表
					<i class="layui-icon layui-icon-add-circle" style="color:#1E9FFF;cursor: pointer" title="新增" onclick="lbodyTrAdd()"></i>
				</label>
				<div class="layui-input-block">
					<table class="layui-table" style="margin-top: 0;" id="lbody">
						<thead>
						<tr>
							<th>站点名称</th>
							<th>站点链接</th>
							<th>站点图标</th>
							<th>操作</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${links}" var="link">
							<tr>
								<td><input type="text" autocomplete="off" placeholder="请输入" class="layui-input" value="${link.siteName}"></td>
								<td><input type="text" autocomplete="off" placeholder="请输入" class="layui-input" value="${link.siteLink}"></td>
								<td><input type="text" autocomplete="off" placeholder="请输入" class="layui-input" value="${link.siteIcon}"></td>
								<td>
									<i class="layui-icon layui-icon-delete" style="color:red;cursor: pointer" title="删除" onclick="lbodyTrRemove(this)"></i>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">底部信息</label>
				<div class="layui-input-block">
					<input type="text" name="footer" autocomplete="off" placeholder="请输入" class="layui-input" value="${about.footer}">
				</div>
			</div>
			<div class="layui-form-item right">
				<button class="layui-btn" lay-submit lay-filter="form">更新站点信息</button>
			</div>
		</form>
	</div>
</body>
<jsp:include page="../../foot.jsp"/>
<script>
    layui.use(['table','form'], function() {
		var table = layui.table;
		var form = layui.form;


        //监听提交
        form.on('submit(form)', function(data) {
            var links = [];
            $("#lbody").find("tbody").children("tr").each(function(){
                var ll = {
                    siteName : $(this).children("td").eq(0).children("input").val(),
					siteLink : $(this).children("td").eq(1).children("input").val(),
                    siteIcon : $(this).children("td").eq(2).children("input").val(),
				}
                links.push(ll);
            });
            data.field.links = links;

            $.ajax({
                url:'portal/about/save',
                method:'POST',
                data:{"param":JSON.stringify(data.field)},
                dataType:'json',
                success:function(data){
                    if(data.code==200){
                        layer.alert("更新站点信息成功",{shade: 0,icon:1},function(index){
                            window.location.reload();
                        });
                    }else{
                        layer.alert("更新站点信息失败",{icon:2});
                    }
                }
            });
            return false;
        });
	});

    function lbodyTrAdd(){
        $("#lbody").find("tbody").append(
        ['<tr>'
        ,'<td><input type="text" autocomplete="off" placeholder="请输入" class="layui-input" value=""></td>'
        ,'<td><input type="text" autocomplete="off" placeholder="请输入" class="layui-input" value=""></td>'
		,'<td><input type="text" autocomplete="off" placeholder="请输入" class="layui-input" value=""></td>'
        ,'<td> <i class="layui-icon layui-icon-delete" style="color:red;cursor: pointer" title="删除" onclick="lbodyTrRemove(this)"></i> </td>'
        ,'</tr>'].join(' ')
		);
	}

    function lbodyTrRemove(t){
        $(t).parents("tr").remove();
	}

</script>
</html>