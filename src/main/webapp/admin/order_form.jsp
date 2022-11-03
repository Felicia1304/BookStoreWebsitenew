<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edit Order - The Book Spot Administration</title>
	<link rel="stylesheet" href="../css/Style.css" >
	<script type="text/javascript" src="../js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:directive.include file ="Header.jsp" />
	
	<div align = "center">
		<h2 class="pageheading">Edit Order ID: ${order.orderId}</h2>
	</div>
	
	<c:if test="${message != null}">
	<div align = "center">
		<h4 class="message">${message}</h4>
	</div>
	</c:if>
	
	<form action="update_order" method="post" id="orderForm">
	<div align = "center">
		<h2>Order Overview:</h2>
		<table>
			<tr>
				<td><b>Ordered By: </b></td>
				<td>${order.customer.fullName}</td>
			</tr>
			<tr>
				<td><b>Order Date: </b></td>
				<td>${order.orderDate}</td>
			</tr>
			<tr>
				<td><b>Recipient Name: </b></td>
				<td><input type="text" name="recipientName" value="${order.recipientName}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Recipient Phone: </b></td>
				<td><input type="text" name="recipientPhoneNo" value="${order.recipientPhoneNo}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Ship To: </b></td>
				<td><input type="text" name="shippingAddress" value="${order.shippingAddress}" size="45" /></td>
			</tr>
			<tr>
				<td><b>Payment Method: </b></td>
				<td>
					<select name="paymentMethod">
						<option value="Cash On Delivery">Cash On Delivery</option>
					</select>	
				</td>
			</tr>
			
			<tr>
				<td><b>Order Status: </b></td>
				<td>
					<select name="orderStatus">
						<option value="Processing" <c:if test="${order.orderStatus eq 'Processing' }">selected='selected'</c:if> >Processing</option>
						<option value="Shipping" <c:if test="${order.orderStatus eq 'Shipping' }">selected='selected'</c:if> >Shipping</option>
						<option value="Delivered" <c:if test="${order.orderStatus eq 'Delivered' }">selected='selected'</c:if> >Delivered</option>
						<option value="Completed" <c:if test="${order.orderStatus eq 'Completed' }">selected='selected'</c:if> >Completed</option>
						<option value="Cancelled" <c:if test="${order.orderStatus eq 'Cancelled' }">selected='selected'</c:if> >Cancelled</option>
					</select>	
				</td>
			</tr>
		</table>
	</div>
	<div align="center">
		<h2>Ordered Books</h2>
		<table border="1">
			<tr>
				<th>Index</th>
				<th>Book Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Subtotal</th>
				<th></th>
			</tr>
			<c:forEach items="${order.orderDetails}" var="orderDetails" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${orderDetails.book.title}</td>
					<td>${orderDetails.book.author}</td>
					<td>
						<input type="hidden" name="price" value="${orderDetails.book.price}" />
						<fmt:formatNumber value="${orderDetails.book.price}" type="currency" currencySymbol="Rs."/>
					</td>
					<td>
						<input type="hidden" name="bookId" value="${orderDetails.book.bookId}" />
						<input type="text" name="quantity${status.index + 1}" value="${orderDetails.quantity}" size="5" />
					</td>
					<td><fmt:formatNumber value="${orderDetails.subtotal}" type="currency" currencySymbol="Rs."/></td>
					<td><a href="remove_book_from_order?id=${orderDetails.book.bookId}">Remove</a></td>
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
				<td></td>
			</tr>
		</table>
	</div>
	<div align="center"> 
		<br/>
		<a href="javascript:showAddBookPopup()"><b>Add Books</b></a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="Save" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="Cancel" onclick="javascript:window.location.href='list_order';" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	</form>
	
	<jsp:directive.include file ="Footer.jsp" />
	
	<script>
		function showAddBookPopup() {
			var width = 600;
			var height = 250;
			var left = (screen.width - width) / 2;
			var top = (screen.height - height) / 2;
			window.open('add_book_form', '_blank', 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
		}
		
		$(document).ready(function() {
			$("#orderForm").validate({
				rules: {
					recipientName: "required",
					recipientPhoneNo: "required",
					shippingAddress: "required",
					
					 <c:forEach items="${order.orderDetails}" var="book" varStatus="status">
						quantity${status.index + 1}: {
							required: true, number: true, min: 1
						},
					</c:forEach>
				},
				messages: {
					recipientName: " Please enter recipient name",
					recipientPhoneNo: " Please enter recipient phone no",
					shippingAddress: " Please enter shipping address",
				
					<c:forEach items="${order.orderDetails}" var="book" varStatus="status">
						quantity${status.index + 1}: {
							required: " Please enter quantity",
							number: " Quantity must be a number",
							min: "Quantity must be greater than 0"
						},
					</c:forEach>
				}
			});
			
		});
	</script>
</body>
</html>