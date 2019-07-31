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
		<table lay-filter="users-table">
			<thead>
				<tr>
					<th lay-data="{type:'numbers',align:'center'}">序号</th>
					<th lay-data="{field:'username',align:'center'}">用户名</th>
					<th lay-data="{field:'userDesc',align:'center'}">描述</th>
					<th lay-data="{field:'createTime',align:'center'}">创建日期</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${users }">
					<tr>	
						<td></td>
						<td>${user.username }</td>
						<td>${user.userDesc }</td>
						<td><fmt:formatDate value="${user.createTime }" pattern="yyyy-MM-dd hh:mm:ss"></fmt:formatDate></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
<jsp:include page="../../foot.jsp"/>
<script>
	layui.use('table',function(){
		var table = layui.table;
		//转换静态表格
		table.init('users-table', {
			limits : [5,10],
			limit : 5,
			page : true
		});
	});
</script>
</html>