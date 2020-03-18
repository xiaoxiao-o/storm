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
					<label class="layui-form-label">内容</label>
					<div class="layui-input-block">
						<input type="text" name="content" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<button class="layui-btn layui-btn-normal" id="search"><i class="layui-icon layui-icon-search"></i>查询</button>
				</div>
			</div>
		</div>

		<table class="layui-hide" id="comment-table" lay-filter="comment-table"></table>
		<!-- 右侧工具栏 -->
		<script type="text/html" id="bar">
			<!-- laytpl 的模板 -->
			{{#  if(d.status == 0){ }}
				<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="unShield">解除屏蔽</a>
			{{#  }else{ }}
				<a class="layui-btn layui-btn-xs" lay-event="shield">屏蔽</a>
			{{#  } }}
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
			elem : '#comment-table',
			url  : 'blog/comment/selectListPage',
			loading: true,
			cols : [ [ 
				{checkbox : true,fixed : 'left',align : 'center'}, 
				{type  : 'numbers',title : '序号',align : 'center'}, 
				{field : 'articleId',title : '文章/站点',align : 'center',templet:function(d){
					return d.articleId != ''&&d.articleId != null?d.articleId:'站点';
				}},
                {field : 'content',title : '评论',align : 'center'},
                {field : 'userInfo',title : '用户信息',align : 'center'},
                {field : 'ipAddress',title : 'IP地址',align : 'center'},
				{field : 'time',title : '创建时间',align : 'center',templet:function(d){
					return layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss");
				}}, 
				{fixed : 'right', title:'操作', toolbar: '#bar', width:200,align : 'center'}
			] ],
			id : 'comment-table-reload',//数据重载该表格
			page : true,
			limits : [10,20,30,40,50,60,70,80,90],
			limit : 10,
		});
		
		//查询按钮
		$("#blog").on('click',function(){
			search();	//执行查询
		});

        //执行查询
        function search(){
            var param = {	//注意：这里的会回封装成map直接传递到数据库条件中,跟实体无关，所以这里的key使用数据库字段
                'content':$('[name="content"]').val(),
            };
            //执行重载
            table.reload('comment-table-reload', {
                page : {
                    curr : 1
                },
                where : {
                    'paramJson':JSON.stringify(param)
                }
            });
        }


		//监听行工具事件
		table.on('tool(comment-table)', function(obj) {
			var data = obj.data;
			if (obj.event === 'del') {
				del(data.id);
			}
			if (obj.event === 'shield') {
                shield(data.id,0,"屏蔽留言");
			}
            if (obj.event === 'unShield') {
                shield(data.id,1,"解除屏蔽留言");
            }
		});

        //屏蔽
        function shield(id,status,msg){
            layer.confirm('确认'+msg+'该留言？', function(index) {
                $.ajax({
                    url : 'blog/comment/shield',
                    data : {id:id,status:status},
                    success:function(data){
                        if(data.code==200){
                            layer.alert(msg + "成功",{icon:1},function(index){
                                layer.close(index);
                                search();
                            });
                        }else{
                            layer.alert(msg + "失败",{icon:2});
                        }
                    }
                });
            });
        }
		

		//删除
		function del(id){
			layer.confirm('确认删除该留言？', function(index) {
				$.ajax({
					url : 'blog/comment/del',
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