<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

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

	<h1>Main Page. Hello, ${agcModel.loggedInUser.employee.firstName}</h1>
	<br>
	<a href='<%=request.getContextPath()+"/logout"%>'>Logout</a>
</body>
</html>