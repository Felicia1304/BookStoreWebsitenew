<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>My Order Details - The Book Spot</title>
	<link rel="stylesheet" href="css/Style.css" >
</head>
<body>
	<jsp:directive.include file ="Header.jsp" />
	
	<c:if test="${order == null}">
		<div align = "center">
			<h2 class="pageheading">Sorry, you are not authorized to view this order.</h2> 
		</div>
	</c:if>
	
	<c:if test="${order != null}">
	<div align = "center">
		<h2 class="pageheading">Your Order ID: ${order.orderId}</h2>
	</div>

	<div align = "center">
		<table>
			<tr>
				<td><b>Order Status: </b></td>
				<td>${order.orderStatus}</td>
			</tr>
			<tr>
				<td><b>Order Date: </b></td>
				<td>${order.orderDate}</td>
			</tr> 
			<tr>
				<td><b>Quantity: </b></td>
				<td>${order.bookCopies}</td>
			</tr>
			<tr>
				<td><b>Total Amount: </b></td>
				<td><fmt:formatNumber value="${order.orderTotal}" type="currency" currencySymbol="Rs."/></td>
			</tr>
			<tr>
				<td><b>Recipient Name: </b></td>
				<td>${order.recipientName}</td>
			</tr>
			<tr>
				<td><b>Recipient Phone: </b></td>
				<td>${order.recipientPhoneNo}</td>
			</tr>
			<tr>
				<td><b>Ship to: </b></td>
				<td>${order.shippingAddress}</td>
			</tr>
			<tr>
				<td><b>Payment Method: </b></td>
				<td>${order.paymentMethod}</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<h2>Ordered Books</h2>
		<table border="1">
			<tr>
				<th>No</th>
				<th>Book</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</th>
			</tr>
			<c:forEach items="${order.orderDetails}" var="orderDetails" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>
						<img style="vertical-align: middle;" src="data:image/jpg;base64,${orderDetails.book.base64Image}" width="48" height="64"/>
						${orderDetails.book.title}
					</td>
					<td>${orderDetails.book.author}</td>
					<td><fmt:formatNumber value="${orderDetails.book.price}" type="currency" currencySymbol="Rs."/></td>
					<td>${orderDetails.quantity}</td>
					<td><fmt:formatNumber value="${orderDetails.subtotal}" type="currency" currencySymbol="Rs."/></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="4" align="right">
					<b><i>TOTAL:</i></b>
				</td>
				<td>
					<b>${order.bookCopies}</b>
				</td>
				<td>
					<b><fmt:formatNumber value="${order.orderTotal}" type="currency" currencySymbol="Rs."/></b>
				</td>
			</tr>
		</table>
	</div>
	</c:if>
	<jsp:directive.include file ="Footer.jsp" />
	
</body>
</html>