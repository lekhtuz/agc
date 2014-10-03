<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>American GlassCrafters</title>
	<link href=<%=request.getContextPath()+"/resources/css/agc.css"%> rel="stylesheet"/>
</head>
<body>
	<table width="1028px" align="center">
		<tr>
			<td rowspan="2"><img src="http://glasscraftersinc.com/assets/images/logo.jpg"></td>
			<td align="right"><span class="sizeTagLine">Distinctive Designs for Discriminating Tastes</span></td>
		</tr>
		<tr>
			<td align="right" valign="middle">
				Menu 1 | Menu 2
			</td>
		</tr>
	</table>

	<form:form method="post" action='<%=request.getContextPath()+"/login"%>' modelAttribute="loginForm">
		<div id="login-box">
			<table align="center">
				<tr>
					<td colspan="2" style="font-weight: bold;">Please enter your username and password below:</td>
				</tr>
				<tr>
					<td class="left-td-label"><form:label path="username">Username:</form:label></td>
					<td class="right-td-field"><form:input path="username"/>&nbsp;<form:errors cssClass="form-errors" path="username"/></td>
				</tr>
				<tr>
					<td class="left-td-label"><form:label path="password">Password:</form:label></td>
					<td class="right-td-field"><form:password path="password"/>&nbsp;<form:errors cssClass="form-errors" path="password"/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><form:errors cssClass="form-errors"/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input type="Submit" value="Log In"></td>
				</tr>
			</table>
		</div>
	</form:form>
</body>
</html>