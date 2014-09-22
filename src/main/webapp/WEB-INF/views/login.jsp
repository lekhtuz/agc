<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>American GlassCrafters</title>
</head>
<body>
	<b>Please enter your username and password below:</b><br>
	<form:form method="post" action="/login" modelAttribute="loginForm">
		<form:label path="username">Username:</form:label><form:input path="username" /><br>
		<form:label path="password">Password:</form:label><form:password path="password" /><br>
		<input type="Submit" value="Log In">
	</form:form>
</body>
</html>