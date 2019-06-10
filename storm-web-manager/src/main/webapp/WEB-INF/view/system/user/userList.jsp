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
<base href="<%=basePath%>">
<jsp:include page="../../head.jsp"/>
</head>
<body>
	<div class="main-content">
		<!-- search -->
		<div class="layui-form layui-form-pane" id="condition">
			<div class="layui-form-item margin-bottom-0">
				<div class="layui-inline margin-bottom-5">
					<label class="layui-form-label">用户名</label>
					<div class="layui-input-block">
						<input type="text" name="username" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline margin-bottom-5">
					<button id="search" class="layui-btn layui-btn-normal"><i class="layui-icon layui-icon-search"></i>查询</button>
					<button id="add" class="layui-btn"><i class="layui-icon layui-icon-add-1"></i>新增用户</button>
					<button id="batchDel" class="layui-btn layui-btn-danger"><i class="layui-icon layui-icon-delete"></i>批量删除</button>
				</div>
			</div>
		</div>

		<table class="layui-hide" id="user-table" lay-filter="user-table"></table>
		<!-- 右侧工具栏 -->
		<script type="text/html" id="bar">
  			<a class="layui-btn layui-btn-xs" lay-event="update">编辑</a>
  			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
			<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="resetPassword">重置密码</a>
		</script>
	</div>
</body>
<jsp:include page="../../foot.jsp"/>
<script>
	layui.use(['table','form'], function() {
		var table = layui.table;
		var form  = layui.form;

		//方法级渲染
		table.render({
			elem : '#user-table',
			url  : 'system/user/selectListPage',
			loading: true,
			cols : [ [ 
				{checkbox : true,fixed : 'left',align : 'center'}, 
				{type  : 'numbers',title : '序号',align : 'center'}, 
				{field : 'username',title : '用户名',align : 'center'}, 
				{field : 'userDesc',title : '描述',align : 'center'}, 
				{field : 'userState',title : '状态',align : 'center',templet:function(d){
					return d.userState==0?"禁用":"启用"
				}},
                {field : 'createBy',title : '创建者',align : 'center'},
				{field : 'createTime',title : '创建时间',align : 'center',templet:function(d){
					return layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss");
				}},
				{fixed : 'right', title:'操作', toolbar: '#bar', width:200,align : 'center'}
			] ],
			id : 'user-table-reload',//数据重载该表格
			page : true,
			limits : [10,20,30,40,50,60,70,80,90],
			limit : 10
		});
		
		//查询按钮
		$("#search").on('click',function(){
			search();
		});

        //新增按钮
        $("#add").on('click',function(){
            edit("新增用户",'system/user/toAdd');
        });

        //批量删除
        $("#batchDel").on('click',function(){
            var checkStatus = table.checkStatus("user-table-reload");//表格id
            var data = checkStatus.data;	//选中的数据
            batchDel(data);
        });
		
		//监听行工具事件
		table.on('tool(user-table)', function(obj) {
			var data = obj.data;
			if (obj.event === 'del') {
				del(data.id);
			} else if (obj.event === 'update') {
				edit("修改用户",'system/user/toEdit?userId='+data.id);
			} else if(obj.event === 'resetPassword'){
				resetPassword(data.id)
			}
		});
		
		//执行查询
		function search(){
			var param = {	//注意：这里的会回封装成map直接传递到数据库条件中,跟实体无关，所以这里的key使用数据库字段
				'username':$('[name="username"]').val(),
			};
			//执行重载
			table.reload('user-table-reload', {
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
				area : [ '700px', '530px' ],
				content : url,
				end:function(){	//在层销毁时回调，不管是通过什么方式的销毁
					search();
				}
			});
		}
		
		//删除用户(批量)
		function batchDel(data){
			if(data.length==0){
				layer.msg("没有选择任何数据",{icon:2});
				return;
			}
			layer.confirm('确认删除选择的用户？', function(index) {
				var ids = new Array();
				$.each(data,function(){
					ids.push(this.id);
				});
				$.ajax({
					url : 'system/user/batchDel',
					data : {ids:JSON.stringify(ids)},
					success:function(data){
						if(data.code==200){
							layer.msg("删除成功",{icon:1},function(){
								search();
							});
						}else{
							layer.alert("删除失败",{icon:2});
						}
					}
				});
			});
		}
		
		//删除用户
		function del(userId){
			var ids = [userId];
			layer.confirm('确认删除该用户？', function(index) {
				$.ajax({
					url : 'system/user/batchDel',
					data : {ids:JSON.stringify(ids)},
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
		
		//重置密码
		function resetPassword(userId){
			layer.confirm('确认重置密码？', function(index) {
				$.ajax({
					url : 'system/user/resetPassword',
					data : {userId:userId},
					success:function(data){
						if(data.code==200){
							layer.alert("已重置，初始密码为：" + data.data);
						}else{
							layer.msg("重置密码失败",{icon:2});
						}
					}
				});
			});
		}
		
	});
</script>
</html>