<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Profile - Online Book Store</title>
<link rel="stylesheet" href="css/Style.css">
</head>
<body>
	<jsp:directive.include file="Header.jsp" />

	<div align="center">
		<br />
		<h2>Welcome, ${loggedCustomer.fullName}</h2>
		<br />
		<table class="normal">
			<tr>
				<td><b>E-mail Address:</b></td>
				<td>${loggedCustomer.email}</td>
			</tr>
			<tr>
				<td><b>Full Name:</b></td>
				<td>${loggedCustomer.fullName}</td>
			</tr>
			<tr>
				<td><b>Phone Number:</b></td>
				<td>${loggedCustomer.phoneNo}</td>
			</tr>
			<tr>
				<td><b>Address:</b></td>
				<td>${loggedCustomer.address}</td>
			</tr>
			<tr>
				<td><b>City:</b></td>
				<td>${loggedCustomer.city}</td>
			</tr>
			<tr>
				<td><b>Zip Code:</b></td>
				<td>${loggedCustomer.zipCode}</td>
			</tr>
			<tr>
				<td><b>Country:</b></td>
				<td>${loggedCustomer.country}</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><b><a href="edit_profile">Edit My Profile</a></b></td>
			</tr>
		</table>
	</div>
	
	<jsp:directive.include file="Footer.jsp" />
</body>
</html>