<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Welcome, new Customer!</title>
	</head>
	<body>
	  <form method="post" action="<c:url value="/userCreated.html"/>">

	    <table>
	        <tr>
	          <td>UserName: </td>
	          <td><input type="text" name="userName"/></td>
	        </tr>
	        <tr>
              <td>Password: </td>
              <td><input type="password" name="password"/></td>
            </tr>
            <tr>
              <td>VerifyPassword: </td>
              <td><input type="password" name="verifyPassword"/></td>
            </tr>
            <tr>
              <td colspan="2"><input type="submit" name="submit"/></td>
            </tr>
            </table>
		</form>
		<c:if test="${!empty error}">
              	        <font color="red"><c:out value="${error}" /></font>
        </c:if>
	</body>
</html>
