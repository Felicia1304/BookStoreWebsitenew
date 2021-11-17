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
				<div class="book">
					<div>
						<a href="view_book?id=${book.bookId}"> <img class="book_small"
							src="data:image/jpg;base64,${book.base64Image}" />
						</a>
					</div>
					<div>
						<a href="view_book?id=${book.bookId}"> <b>${book.title}</b>
						</a>
					</div>
					<div>
						<jsp:directive.include file="book_rating.jsp" />
					</div>
					<div>
						<i>By ${book.author}</i>
					</div>
					<div>
						<b>Rs.${book.price}</b>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="next_row">
			<h2>Best-selling books:</h2>
		</div>
		<div class="next_row">
			<h2>Most-favored books:</h2>
		</div>
	</div>
	<br />
	<br />

	<jsp:directive.include file="Footer.jsp" />
</body>
</html>