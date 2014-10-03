<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
				<nav>
					<ul>
						<li>
							<a href="#">Jobs</a>
							<ul>
								<li><a href="#">Log</a></li>
								<li><a href="#">Pictures</a></li>
								<li><a href="#">Production</a></li>
								<li><a href="#">MDI Scheduling</a></li>
								<li><a href="#">Quality Control</a></li>
								<li><a href="#">Shipping</a></li>
							</ul>
						</li>
						<li>
							<a href="#">Tables</a>
							<ul>
								<li><a href="#">Order Entry</a></li>
								<li><a href="#">Engineering</a></li>
								<li><a href="#">Inventory</a></li>
								<li><a href="#">Pricing</a></li>
								<li><a href="#">Vendor</a></li>
							</ul>
						</li>
						<li>
							<a href="#">Accounting</a>
							<ul>
								<li><a href="#">Accounts Payable</a></li>
								<li><a href="#">Accounts Receivable</a></li>
								<li><a href="#">Job Log Costing Report</a></li>
								<li><a href="#">Sales and Commission</a></li>
								<li><a href="#">Sales and Use Tax</a></li>
								<li><a href="#">Spiff Program</a></li>
							</ul>
						</li>
					</ul>
				</nav>
			</td>
			<td>
				<span>Hello, ${agcModel.loggedInUser.employee.firstName}</span>
				<br>
				<a href='<%=request.getContextPath()+"/logout"%>'>Sign Out</a>
			</td>
		</tr>
	</table>
</body>
</html>