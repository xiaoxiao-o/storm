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
<jsp:include page="../../head.jsp"/>
</head>
<body>
	<div class="main-content">
		<!-- search -->
		<div class="layui-form layui-form-pane" id="condition">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">标题</label>
					<div class="layui-input-block">
						<input type="text" name="title" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn layui-btn-normal" id="search"><i class="layui-icon layui-icon-search"></i>查询</button>
					<button class="layui-btn" id="add"><i class="layui-icon layui-icon-add-1"></i>新建文章</button>
				</div>
			</div>
		</div>

		<table class="layui-hide" id="article-table" lay-filter="article-table"></table>
		<!-- 右侧工具栏 -->
		<script type="text/html" id="bar">
  			<a class="layui-btn layui-btn-xs" lay-event="update">编辑</a>
  			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
			<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="public">发布</a>
			<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="public">置顶</a>
			<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="public">轮播</a>
		</script>
	</div>
</body>
<jsp:include page="../../foot.jsp"/>
<script>
	layui.use(['table','form'], function() {
		var table = layui.table;

		//方法级渲染
		table.render({
			elem : '#article-table',
			url  : 'blog/article/selectListPage',
			loading: true,
			cols : [ [ 
				{checkbox : true,fixed : 'left',align : 'center'}, 
				{type  : 'numbers',title : '序号',align : 'center'},
                {field : 'title',title : '标题',align : 'center',templet:function(d){d.title
					return '<a class="table-a" onclick="showArticle(\''+d.id+'\')">'+d.title+'</a>';
				}},
                {field : 'createBy',title : '创建者',align : 'center'},
				{field : 'createTime',title : '创建时间',align : 'center',templet:function(d){
					return layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss");
				}}, 
				{fixed : 'right', title:'操作', toolbar: '#bar', align : 'center'}
			] ],
			id : 'article-table-reload',//数据重载该表格
			page : true,
			limits : [10,20,30,40,50,60,70,80,90],
			limit : 10
		});
		
		//查询按钮
		$("#search").on('click',function(){
			search();	//执行查询
		});

        //新增按钮
        $("#add").on('click',function(){
            edit("新建文章","blog/article/toAdd");	//执行新增
        });

        //执行查询
        function search(){
            var param = {	//注意：这里的会回封装成map直接传递到数据库条件中,跟实体无关，所以这里的key使用数据库字段
                'title':$('[name="title"]').val(),
            };
            //执行重载
            table.reload('article-table-reload', {
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
                area : [ '1000px', '500px' ],
                content : url,
                end:function(){	//在层销毁时回调，不管是通过什么方式的销毁
                    search();
                }
            });
        }

		//监听行工具事件
		table.on('tool(title-table)', function(obj) {
			var data = obj.data;
			if (obj.event === 'del') {
				del(data.id);
			} else if (obj.event === 'update') {
				edit("修改用户",'system/user/toEdit?userId='+data.id);
			} else if(obj.event === 'resetPassword'){
				resetPassword(data.id)
			}
		});
		

		

		
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

    //角色下的用户
    function showArticle(id){
        layer.open({
            type : 2,
            title : "文章预览",
            area : [ '770px', '380px' ],
            content : "blog/article/showArticle?id="+id
        });
    }

</script>
</html>