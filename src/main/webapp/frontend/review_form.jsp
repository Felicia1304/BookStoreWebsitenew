<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Write Review</title>
	<link rel="stylesheet" href="css/Style.css" >
	<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file="Header.jsp" />
	
	<div align="center">
		<form>
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
					<td>
						<input type="text" name="headline" size="60" placeholder="Headline or summary for your review (required)" />
						<br/>
						<br/>
						<textarea name="comment" cols="70" row="10" placeholder="Write your review details..."></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	
	<jsp:directive.include file="Footer.jsp" />
	
<script type = "text/javascript">

	$(document).ready(function() {
		$("#Loginform").validate({
			rules: {
				email: {
					required: true,
					email: true
				},
				password: "required",
			},
			messages: {
				email: {
					required: " Please enter email",
					email: " Please enter a valid email address"	
				},
				password: " Please enter password"
			}
		});
	
	});
</script>
</body>
</html>