<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Welcome to baobaotao Web</title>
	</head>
	<body>
		<c:if test="${!empty error}">
	        <font color="red"><c:out value="${error}" /></font>
		</c:if>
		<form action="<c:url value="registerCheck.html"/>" method="post">
			用户名：
			<input type="text" name="userName">
			<br>
			密 码：
			<input type="password" name="password">
			<br>
			重新输入密码：
			<input type="password" name="password">
			<br>
		</form>
	</body>
</html>
