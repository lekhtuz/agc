<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>American GlassCrafters</title>
	<link href=<%=request.getContextPath()+"/resources/css/agc.css"%> rel="stylesheet"/>
	<link href=<%=request.getContextPath()+"/resources/css/menu.css"%> rel="stylesheet"/>
</head>

<body>
	<table width="1028px" align="center">
		<tr>
			<td rowspan="2"><img src="http://glasscraftersinc.com/assets/images/logo.jpg"></td>
			<td colspan="2" align="right"><span class="sizeTagLine">Distinctive Designs for Discriminating Tastes</span></td>
		</tr>
		<tr>
			<td align="left" valign="middle">
				<c:import url="../menu.jsp"/>
			</td>
			<td>
				<span>Hello, ${agcModel.loggedInUser.employee.firstName}</span>
				<br>
				<a href='<%=request.getContextPath()+"/logout"%>'>Sign Out</a>
			</td>
		</tr>
	</table>

	<form:form method="post" action='<%=request.getContextPath()+"/pricing"%>' modelAttribute="searchForm">
		<div id="config-code-results-box">
			<table width="1028px" align="center">
				<tr>
					<th>
						&nbsp;
					</th>
					<th>Config Code</th>
					<th>Description</th>
					<th>
						&nbsp;
					</th>
				</tr>
				<c:for >
					<tr>
						<td>
							<form:checkbox items="${configCodeSearchResult}" path="selected" itemLabel="" itemValue="id" delimiter="<br>"/>
						</td>
						<td>
						</td>
						<td>
						</td>
					</tr>
				</c:for>
			</table>
		</div>
	</form:form>
</body>
</html>
