<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<!-- blog -->
		<div class="layui-form layui-form-pane" id="condition">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">通知</label>
					<div class="layui-input-block">
						<input type="text" name="note" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn layui-btn-normal" id="search"><i class="layui-icon layui-icon-search"></i>查询</button>
					<button class="layui-btn" id="add"><i class="layui-icon layui-icon-add-1"></i>新建通知</button>
				</div>
			</div>
		</div>

		<table class="layui-hide" id="note-table" lay-filter="note-table"></table>
		<!-- 右侧工具栏 -->
		<script type="text/html" id="bar">
  			<a class="layui-btn layui-btn-xs" lay-event="update">编辑</a>
  			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		</script>
	</div>
</body>
<jsp:include page="../../foot.jsp"/>
<script>
	
	layui.use(['table','form'], function() {
		var table = layui.table;

		//方法级渲染
		table.render({
			elem : '#note-table',
			url  : 'blog/note/selectListPage',
			loading: true,
			cols : [ [ 
				{checkbox : true,fixed : 'left',align : 'center'}, 
				{type  : 'numbers',title : '序号',align : 'center'}, 
				{field : 'note',title : '通知',align : 'center'},
                {field : 'highLight',title : '高亮',align : 'center',templet:function(d){
                        return d.highLight==0?"禁用":"启用"
                    }},
                {field : 'status',title : '状态',align : 'center',templet:function(d){
                        return d.status==0?"禁用":"启用"
                    }},
                {field : 'createBy',title : '创建者',align : 'center'},
				{field : 'createTime',title : '创建时间',align : 'center',templet:function(d){
					return layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss");
				}}, 
				{fixed : 'right', title:'操作', toolbar: '#bar', width:200,align : 'center'}
			] ],
			id : 'note-table-reload',//数据重载该表格
			page : true,
			limits : [10,20,30,40,50,60,70,80,90],
			limit : 10,
		});
		
		//查询按钮
		$("#blog").on('click',function(){
			search();	//执行查询
		});

        //新增按钮
        $("#add").on('click',function(){
            edit("新建通知","blog/note/toAdd");	//执行新增
        });

        //执行查询
        function search(){
            var param = {	//注意：这里的会回封装成map直接传递到数据库条件中,跟实体无关，所以这里的key使用数据库字段
                'note':$('[name="note"]').val(),
            };
            //执行重载
            table.reload('note-table-reload', {
                page : {
                    curr : 1
                },
                where : {
                    'paramJson':JSON.stringify(param)
                }
            });
        }

        //新增、编辑
        function edit(title,url){
            layer.open({
                type : 2,
                title : title,
                area : [ '500px', '370px' ],
                content : url,
                end:function(){	//在层销毁时回调，不管是通过什么方式的销毁
                    search();
                }
            });
        }

		//监听行工具事件
		table.on('tool(note-table)', function(obj) {
			var data = obj.data;
			if (obj.event === 'del') {
				del(data.id);
			} else if (obj.event === 'update') {
				edit("修改通知",'blog/note/toEdit?id='+data.id);
			}
		});
		

		//删除
		function del(id){
			layer.confirm('确认删除该通知？', function(index) {
				$.ajax({
					url : 'blog/note/del',
					data : {id:id},
					success:function(data){
						if(data.code==200){
							layer.alert("删除成功",{icon:1},function(index){
                                layer.close(index);
								search();
							});
						}else{
							layer.alert("删除失败",{icon:2});
						}
					}
				});
			});
		}
		

	});
</script>
</html>