<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>The Book Spot - Online Book Store</title>
<link rel="stylesheet" href="css/Style.css">
</head>
<body>
	<jsp:directive.include file="Header.jsp" />

	<div class="center">
		<div>
			<h2>New books:</h2>
			<c:forEach items="${listNewBooks}" var="book">
				<jsp:directive.include file = "book_group.jsp" />
			</c:forEach>
		</div>
		<div class="next_row">
			<h2>Best-selling books:</h2>
			<c:forEach items="${listBestSellingBooks}" var="book">
				<jsp:directive.include file = "book_group.jsp" />
			</c:forEach>
		</div>
		<div class="next_row">
			<h2>Most-favored books:</h2>
			<c:forEach items="${listFavoredBooks}" var="book">
				<jsp:directive.include file = "book_group.jsp" />
			</c:forEach>
		</div>
	</div>
	<br />
	<br />

	<jsp:directive.include file="Footer.jsp" />
</body>
</html>