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
			<div class="layui-form-item margin-bottom-0">
				<div class="layui-inline margin-bottom-5">
					<label class="layui-form-label">菜单名称</label>
					<div class="layui-input-block">
						<input type="text" name="menuName" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline margin-bottom-5">
					<button class="layui-btn layui-btn-normal" id="search"><i class="layui-icon layui-icon-search"></i>查询</button>
					<button class="layui-btn layui-btn-normal" id="expandAll">全部展开</button>
					<button class="layui-btn layui-btn-normal" id="foldAll">全部关闭</button>
					<button class="layui-btn" id="add"><i class="layui-icon layui-icon-add-1"></i>新增菜单</button>
				</div>
			</div>
		</div>
		<table id="menu-table" class="layui-hide layui-table" lay-filter="menu-table"></table>
		<!-- 右侧工具栏 -->
		<script type="text/html" id="bar">
  			<a class="layui-btn layui-btn-xs" lay-event="update">编辑</a>
  			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		</script>
	</div>

</body>
<jsp:include page="../../foot.jsp"/>
<script>
    layui.config({
        base: 'static/plugin/treetable-lay/module/'
    }).extend({
        treetable: 'treetable-lay/treetable'
    }).use(['table', 'treetable'], function () {
        var $ = layui.jquery;
        var table = layui.table;
        var treetable = layui.treetable;

        // 渲染表格
        treetable.render({
            treeColIndex: 1,
            treeSpid: '0',
            treeIdName: 'id',
            treePidName: 'pid',
            elem: '#menu-table',
            url: 'system/menu/selectList',
            cols: [[
                {type: 'numbers',title:'序号', align: 'center'},
                {field: 'menuName', title: '菜单名称', align: 'left'},
                {field: 'url', title: '菜单地址', align: 'left'},
                {field: 'sort', align: 'center', title: '排序'},
                {field: 'icon', align: 'center', title: '图标 <a onclick="iconRef()" class="table-a table-a-s">[参考]</a>',templet:function(d){
                	return '<i class="'+d.icon+'"></i>'
                }},
                {field: 'resource', align: 'left', title: '资源标志'},
				{field : 'createBy',title : '创建者',align : 'center'},
				{field : 'createTime',title : '创建时间',align : 'center',templet:function(d){
					return layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss");
				}},
                {templet: '#bar', align: 'center', title: '操作'}
            ]],
            id : 'menu-table-reload',//数据重载该表格
        });
        
      	//查询按钮
		$("#search").on('click',function(){
			search();
		});

		$("#add").on('click',function(){
			edit("新建菜单",'system/menu/toAdd');
		});

		$("#expandAll").on('click',function(){
			treetable.expandAll('#menu-table');
		});

		$("#foldAll").on('click',function(){
			treetable.foldAll('#menu-table');
		});

		//执行查询,这里的查询仅仅是假查询,把相关查询结果标记起来
		function search(){
			var keyword = $('[name="menuName"]').val();
	        var searchCount = 0;
	        $('#menu-table').next('.treeTable').find('.layui-table-body tbody tr td').each(function () {
	            $(this).css('background-color', 'transparent');
	            var text = $(this).text();
	            if (keyword != '' && text.indexOf(keyword) >= 0) {
	                $(this).css('background-color', 'rgba(250,230,160,0.5)');
	                if (searchCount == 0) {
	                    treetable.expandAll('#menu-table');
	                    $('html,body').stop(true);
	                    $('html,body').animate({scrollTop: $(this).offset().top - 150}, 500);
	                }
	                searchCount++;
	            }
	        });
		}
        
		//监听行工具事件
		table.on('tool(menu-table)', function(obj) {console.log(obj);
			var data = obj.data;
			if (obj.event === 'del') {									//删除
				del(data.id);
			} else if (obj.event === 'update') {						//更新
				edit("修改菜单",'system/menu/toEdit?menuId='+data.id);	
			}
		});
      	
		//新增、编辑菜单
		function edit(title,url){
			layer.open({
				type : 2,
				title : title,
				area : [ '700px', '440px' ],
				content : url,
				end:function(){	//在层销毁时回调，不管是通过什么方式的销毁
					window.location.reload();
				}
			});
		}
		
		//删除
		function del(menuId){
			layer.confirm('确认删除该菜单？', function(index) {
				$.ajax({
					url : 'system/menu/delete',
					data : {menuId:menuId},
					success:function(data){
						if(data.code==200){
							layer.alert("删除成功",{icon:1},function(index){
								layer.close(index);
								window.location.reload();
							});
						}else{
							layer.alert("删除失败",{icon:2});
						}
					}
				});
			});
		}
      	
    });
    
    function iconRef(){
    	layer.open({
    		title:'图标参考',
    		maxmin:true,
    		shade: 0,
  			type: 2, 
  			area: ['100%', '100%'],
  		  	content: 'http://fontawesome.dashgame.com'
  		});
    }
    
</script>
</html>