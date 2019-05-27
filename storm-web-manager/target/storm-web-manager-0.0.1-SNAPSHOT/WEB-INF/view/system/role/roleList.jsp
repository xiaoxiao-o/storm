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
</head>
<body>
	<div class="main-content">

		<!-- search -->
		<div class="layui-form layui-form-pane" id="condition">
			<div class="layui-form-item margin-bottom-0">
				<div class="layui-inline margin-bottom-5">
					<label class="layui-form-label">角色名</label>
					<div class="layui-input-block">
						<input type="text" name="roleName" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline margin-bottom-5">
					<button class="layui-btn layui-btn-normal" id="search"><i class="layui-icon layui-icon-search"></i>查询</button>
				</div>
			</div>
		</div>

		<table class="layui-hide" id="role-table" lay-filter="role-table"></table>
		<!-- 头部工具栏 -->
		<script type="text/html" id="toolbar">
  			<div class="layui-btn-container">
    			<button class="layui-btn" lay-event="add"><i class="layui-icon layui-icon-add-1"></i>新增角色</button>
  			</div>
		</script>
		<!-- 右侧工具栏 -->
		<script type="text/html" id="bar">
  			<a class="layui-btn layui-btn-xs" lay-event="update">编辑</a>
  			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
			<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="authorize">菜单权限</a>
		</script>
	</div>
</body>
<script src="static/plugin/jquery-2.1.4.min.js" charset="utf-8"></script>
<script src="static/plugin/layui-v2.4.5/layui/layui.js" charset="utf-8"></script>
<script>
	
	layui.use(['table','form'], function() {
		var table = layui.table;
		var form  = layui.form;

		//方法级渲染
		table.render({
			elem : '#role-table',
			url  : 'system/role/selectListPage',
			toolbar: '#toolbar',
			defaultToolbar: [],
			loading: true,
			cols : [ [ 
				{checkbox : true,fixed : 'left',align : 'center'}, 
				{type  : 'numbers',title : '序号',align : 'center'}, 
				{field : 'roleName',title : '角色名',align : 'center',templet:function(d){
					var count;
					$.ajax({
						url:'system/role/selectUserCountByRoleId',
						data:{'roleId':d.id},
						async:false,//这里使用同步线程
						success:function(data){
							count = data.data;
						}
					});
					return d.roleName + ' <a class="table-a table-a-s" onclick="selectUserByRoleId(\''+d.id+'\')">[共'+count+'个用户]</a>';
				}}, 
				{field : 'roleDesc',title : '描述',align : 'center'}, 
				{field : 'createTime',title : '创建时间',align : 'center',templet:function(d){
					return layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss");
				}}, 
				{field : 'roleState',title : '状态',align : 'center',templet:function(d){
					return d.userState==0?"禁用":"启用"
				}},
				{fixed : 'right', title:'操作', toolbar: '#bar', width:200,align : 'center'}
			] ],
			id : 'role-table-reload',//数据重载该表格
			page : true,
			limits : [10,20,30,40,50,60,70,80,90],
			limit : 10,
			loading :false
		});
		
		//查询按钮
		$("#search").on('click',function(){
			search();
		});
		
		//头工具栏事件
		table.on('toolbar(role-table)', function(obj) {
			switch (obj.event) {
			case 'add':
				edit("新增角色",'system/role/toAdd');
				break;
			}
		});
		
		//监听行工具事件
		table.on('tool(role-table)', function(obj) {
			var data = obj.data;
			if (obj.event === 'del') {									//删除
				del(data.id);
			} else if (obj.event === 'update') {						//更新
				edit("修改角色",'system/role/toEdit?roleId='+data.id);	
			} else if(obj.event === 'authorize'){						//授权
				authorize(data.id);
			}
		});
		
		//执行查询
		function search(){
			var param = {
				'role_name':$('[name="roleName"]').val()
			};
			//执行重载
			table.reload('role-table-reload', {
				page : {
					curr : 1
				},
				where : {
					'paramJson':JSON.stringify(param)
				}
			});
		}
		
		//新增、编辑用户
		function edit(title,url){
			layer.open({
				type : 2,
				title : title,
				area : [ '700px', '500px' ],
				maxmin : true,
				content : url,
				end:function(){	//在层销毁时回调，不管是通过什么方式的销毁
					search();
				}
			});
		}
		
		//删除
		function del(roleId){
			layer.confirm('确认删除该角色？', function(index) {
				$.ajax({
					url : 'system/role/delete',
					data : {id:roleId},
					success:function(data){
						if(data.code==200){
							layer.msg("删除成功",{icon:1},function(){
								search();
							});
						}else{
							layer.msg("删除失败",{icon:2});
						}
					}
				});
			});
		}
		
		//授权
		function authorize(roleId){
			layer.open({
				type : 2,
				title : '角色授权',
				area : [ '400px', '450px' ],
				content : 'system/role/toAuthorize?roleId=' + roleId
			});
		}
		
	});
	
	//角色下的用户
	function selectUserByRoleId(roleId){
		layer.open({
			type : 2,
			title : "用户列表",
			area : [ '770px', '380px' ],
			content : "system/role/selectUserByRoleId?roleId="+roleId 
		});
	}
	
</script>
</html>