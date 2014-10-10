<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

	<div id="pricing-table">
		<c:forEach items="${listOfTableModels}" var="tableModel">
			<center><c:out value="${tableModel.ccsi.configCode}"/> - <c:out value="${tableModel.ccsi.title}"/></center>
			<!-- ccsi = <c:out value="${tableModel.ccsi}"/> -->
			<table align="center">
				<tr>
					<td colspan="2">&nbsp;</td>
					<td colspan='<c:out value="${fn:length(tableModel.columnLabels)*2}"/>' valign="middle" style="text-align: center;">Glass</td>
				</tr>
				<c:forEach items="${tableModel.rowLabels}" var="rl" varStatus="rlstatus">
					<tr>
						<c:if test="${rlstatus.first}">
							<td rowspan='<c:out value="${fn:length(tableModel.rowLabels)}"/>' valign="middle" style="background-color: yellow; text-align: center;">H<br>e<br>i<br>g<br>h<br>t</td>
						</c:if>
	
						<td style="text-align: center;"><c:out value="${rl}"/></td>
						<c:forEach items="${tableModel.grid[rlstatus.index]}" var="r" varStatus="rstatus">
							<c:if test="${empty r}">
								<td colspan="2">&nbsp;</td>
							</c:if>
							<c:if test="${not empty r}">
								<td><fmt:formatNumber type="currency" groupingUsed="true" maxFractionDigits="0" value="${r.price}"/></td>
								<td style="background-color: purple; color: white; text-align: right;"><c:out value="${r.area}"/> ft<sup>2</sup></td>
							</c:if>
						</c:forEach>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="2" style="background-color: yellow; text-align: center;">Width(A)</td>
					<c:forEach items="${tableModel.columnLabels}" var="cl">
						<td colspan="2" style="background-color: yellow; text-align: center;"><c:out value="${cl}"/></td>
					</c:forEach>
				</tr>
			</table>
		</c:forEach>
	</div>

</body>
</html>