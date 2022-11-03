<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>My Orders History - The Book Spot Administration</title>
	<link rel="stylesheet" href="css/Style.css" >
</head>
<body>
	<jsp:directive.include file ="Header.jsp" />
	
	<div align = "center">
		<h2 class="pageheading">My Order History</h2>
	</div>
	
	<c:if test="${fn:length(listOrders) == 0}">
		<div align = "center">
		<h3>You have not placed any orders.</h3>
		</div>	
	</c:if>
	
	<c:if test="${fn:length(listOrders) > 0}">
	<div align = "center">
		<table border = "1" cellpadding = "5">
			<tr>
				<th>Index</th>
				<th>Order ID</th>
				<th>Quantity</th>
				<th>Total Amount</th>
				<th>Order Date</th>
				<th>Status</th>
				<th>Actions</th>
			</tr>
			<c:forEach var = "order" items = "${listOrders}" varStatus = "status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${order.orderId}</td>
					<td>${order.bookCopies}</td>
					<td><fmt:formatNumber value="${order.orderTotal}" type="currency" currencySymbol="Rs."/></td>
					<td>${order.orderDate}</td>
					<td>${order.orderStatus}</td>					
					
					<td>
						<a href = "show_order_detail?id=${order.orderId}">View Details</a> &nbsp;
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</c:if>
	
	<jsp:directive.include file ="Footer.jsp" />
	
</body>
</html>