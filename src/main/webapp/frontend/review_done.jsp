<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Review Posted</title>
	<link rel="stylesheet" href="css/Style.css" >
	
</head>
<body>
	<jsp:directive.include file="Header.jsp" />
	
	<div align="center">
			<table class="normal" width="60%">
				<tr>
					<td><h2>Your Reviews</h2></td>
					<td>&nbsp;</td>
					<td><h2>${loggedCustomer.fullName}</h2></td>
				</tr>
				<tr>
					<td colspan="3"><hr/></td>
				</tr>
				<tr>
					<td>
						<span id="book-title">${book.title}</span>
						<img class="book_large" src="data:image/jpg;base64,${book.base64Image}" />
					</td>
					<td colspan="2">
						<h3>Your review has been posted. Thank You!</h3>
					</td>
				</tr>
			</table>
	</div>
	
	<jsp:directive.include file="Footer.jsp" />

</body>
</html>