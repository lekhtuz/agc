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

	<form:form method="post" action='<%=request.getContextPath()+"/pricingtable"%>' modelAttribute="searchForm">
		<div id="config-code-search-box">
			<table width="1028px" align="center">
				<tr>
					<td colspan="2">
						Enter config code below. Wild cards accepted. Leave blank for all codes.
					</td>
				</tr>
				<tr>
					<td class="left-td-label"><form:label path="configCode">Config code:</form:label></td>
					<td class="right-td-field"><form:input path="configCode"/>&nbsp;<form:errors cssClass="form-errors" path="configCode"/></td>
				</tr>
				<tr>
					<td colspan="2">
						Optionally select series names if you want to limit config codes to certain series. No selection means all series.
					</td>
				</tr>
				<tr>
					<td class="left-td-label" valign="top"><form:label path="selectedSeries">Series:</form:label></td>
					<td class="right-td-field">
						<form:checkboxes items="${allSeries}" path="selectedSeries" itemLabel="title" itemValue="id" delimiter="<br>"/>
						<br><form:errors cssClass="form-errors" path="selectedSeries"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><form:errors cssClass="form-errors"/></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input type="Submit" value="Search"></td>
				</tr>
			</table>
		</div>
	</form:form>
</body>
</html>
