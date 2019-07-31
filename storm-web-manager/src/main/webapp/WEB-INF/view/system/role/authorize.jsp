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
		<input type="hidden" id="roleId" value="${roleId }">
		<div class="center">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block right">
				<button class="layui-btn" onclick="resetAuth()">提交</button>
				<button class="layui-btn layui-btn-primary" onclick="closeWindow()">取消</button>
			</div>
		</div>
	</div>
</body>
<jsp:include page="../../foot.jsp"/>
<script>
	var setting = {
		check: {enable: true},
		data: {simpleData: {enable: true}}
	};
	var zNodes = JSON.parse('${treeMenus}');
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
	
	function resetAuth(){
		var treeObj  = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.transformToArray(treeObj.getNodes());
		var mIds = new Array();
		$.each(nodes,function(index,node){
			if(node.checked){
				mIds.push(node.id);
			}
		});
		$.ajax({
			url : 'system/role/authorize',
			data: {roleId:$("#roleId").val() , mIds:JSON.stringify(mIds)},
			success:function(data){console.log(data)
				layui.use("layer",function(){
					var layer = layui.layer;
					if(data.code==200){
						layer.alert("菜单分配成功",{shade: 0,icon:1},function(index){
							parent.layer.closeAll();	//关闭所有
						});
					}else{
						layer.alert("菜单分配失败",{icon:2});
					}
				});
			}
		});
	}
	
	function closeWindow(){
		parent.layer.closeAll();
	}
	
	
</script>
</html>