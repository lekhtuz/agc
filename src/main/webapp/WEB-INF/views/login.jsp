<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>American GlassCrafters</title>
	<link href=<%=request.getContextPath()+"/resources/css/agc.css"%> rel="stylesheet"/>
</head>
<body>
	<form:form method="post" action='<%=request.getContextPath()+"/login"%>' modelAttribute="loginForm">
		<table id="loginBox">
			<tr>
				<td colspan="2" style="font-weight: bold;">Please enter your username and password below:</td>
			</tr>
			<tr>
				<td><form:label path="username">Username:</form:label></td>
				<td><form:input path="username"/>&nbsp;<form:errors cssClass="form-errors" path="username"/></td>
			</tr>
			<tr>
				<td><form:label path="password">Password:</form:label></td>
				<td><form:password path="password"/>&nbsp;<form:errors cssClass="form-errors" path="password"/></td>
			</tr>
			<tr>
				<td colspan="2" style="font-weight: bold; text-align: center;"><input type="Submit" value="Log In"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>