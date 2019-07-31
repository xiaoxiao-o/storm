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
  			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="delete">删除</a>
			<!-- laytpl 的模板 -->
			{{#  if(d.status == 0){ }}
            	<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="publish">发布</a>
            {{#  }else{ }}
				{{#  if(d.recom == 0){ }}
					<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="recom">推荐</a>
				{{#  }else{ }}
					<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="unRecom">取消推荐</a>
				{{#  } }}

				{{#  if(d.top == 0){ }}
					<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="top">置顶</a>
				{{#  }else{ }}
					<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="unTop">取消置顶</a>
				{{#  } }}

				{{#  if(d.lunbo == 0){ }}
					<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="lunbo">轮播</a>
				{{#  }else{ }}
					<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="unLunbo">取消轮播</a>
				{{#  } }}

			{{#  } }}
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
			url  : 'com.whyxs.controller.blog/article/selectListPage',
			loading: true,
			cols : [ [ 
				{checkbox : true,fixed : 'left',align : 'center'}, 
				{type  : 'numbers',title : '序号',align : 'center'},
                {field : 'title',title : '标题',align : 'center',templet:function(d){d.title
					return '<a class="table-a" onclick="showArticle(\''+d.id+'\')">'+d.title+'</a>';
				}},
                {field  : 'summary',title : '摘要',align : 'center'},
                {field : 'status',title : '状态',align : 'center',templet:function(d){
                    return d.status==0?"草稿":"已发布";
                }},
                {field : 'createBy',title : '创建者',align : 'center'},
				{field : 'createTime',title : '创建时间',align : 'center',templet:function(d){
					return layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss");
				}}, 
				{fixed : 'right', title:'操作', toolbar: '#bar', align : 'left',width : 320}
			] ],
			id : 'article-table-reload',//数据重载该表格
			page : true,
			limits : [10,20,30,40,50,60,70,80,90],
			limit : 10
		});
		
		//查询按钮
		$("#blog").on('click',function(){
			search();	//执行查询
		});

        //新增按钮
        $("#add").on('click',function(){
            edit("新建文章","com.whyxs.controller.blog/article/toAdd");	//执行新增
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
		table.on('tool(article-table)', function(obj) {
			var data = obj.data;
			if (obj.event === 'update') {
				edit("修改文章",'com.whyxs.controller.blog/article/toEdit?id='+data.id);
			}else if (obj.event === 'delete') {
			    del(data.id);
            }else if (obj.event === 'publish') {
                changeStatus(data.id,"status",1,"发布");
            }else if (obj.event === 'recom') {
                changeStatus(data.id,"recom",1,"推荐");
            }else if (obj.event === 'unRecom') {
                changeStatus(data.id,"recom",0,"取消推荐");
            }else if (obj.event === 'top') {
                changeStatus(data.id,"top",1,"置顶");
            }else if (obj.event === 'unTop') {
                changeStatus(data.id,"top",0,"取消置顶");
            }else if (obj.event === 'lunbo') {
                changeStatus(data.id,"lunbo",1,"轮播");
            }else if (obj.event === 'unLunbo') {
                changeStatus(data.id,"lunbo",0,"取消轮播");
            }
		});

        function changeStatus(id,key,val,msg){
            $.ajax({
                url : 'com.whyxs.controller.blog/article/changeSomeStatus',
                data : {id:id,key:key,val:val},
                success:function(data){
                    if(data.code==200){
                        layer.alert(msg+"成功",{icon:1},function(index){
                            layer.close(index);
                            search();
                        });
                    }else{
                        layer.alert(msg+"失败",{icon:2});
                    }
                }
            });
		}

		//删除
		function del(id){
			layer.confirm('确认删除该文章？', function(index) {
				$.ajax({
					url : 'com.whyxs.controller.blog/article/delete?id='+id,
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

    //文章预览
    function showArticle(id){
        layer.open({
            type : 2,
            title : "文章预览",
            area : [ '900px', '500px' ],
            content : "com.whyxs.controller.blog/article/showArticle?id="+id
        });
    }

</script>
</html>