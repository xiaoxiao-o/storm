<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
		<div>${article.title}</div>
		<div>${article.content}</div>
		<div>
			<c:forEach var="en" items="${article.enclosure}">
				<div>${en}</div>
			</c:forEach>
		</div>
	</div>
</body>
<jsp:include page="../../foot.jsp"/>
<script>
</script>
</html>