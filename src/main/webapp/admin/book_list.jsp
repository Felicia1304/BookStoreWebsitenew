<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Manage Books - The Book Spot Administration</title>
	<link rel="stylesheet" href="../css/Style.css" >
	<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file ="Header.jsp" />
	
	<div align = "center">
		<h2 class="pageheading">Books Management</h2>
		<h3><a href = "book_form.jsp">Create New Book</a></h3>
	</div>
	
	<c:if test="${message != null}">
	<div align = "center">
		<h4 class="message">${message}</h4>
	</div>
	</c:if>
	
	<div align = "center">
		<table border = "1" cellpadding = "5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
			<c:forEach var = "book" items = "${listBooks}" varStatus = "status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${book.bookId}</td>
					
					<td></td>
					
					<td>${book.title}</td>
					<td>
						<a href = "edit_user?id=${user.userId}">Edit</a> &nbsp;
						<a href = "javascript:void(0);" class="deleteLink" id="${user.userId}">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<jsp:directive.include file ="Footer.jsp" />
	
	<script>
		$(document).ready(function() {
			$(".deleteLink").each(function() {
				$(this).on("click", function() {
					userId = $(this).attr("id");	
					if (confirm('Are you sure you want to delete the user with ID ' + userId + '?')) {
						window.location = "delete_user?id=" + userId;
					}
				});
			});
		});
	</script>
</body>
</html>