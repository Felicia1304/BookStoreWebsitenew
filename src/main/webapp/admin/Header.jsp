<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align="center">
	<a href="${pageContext.request.contextPath}/admin/">
		<img src="../images/Adminlogo.png"/>
	</a>
	</div>
	<div align="center">
		Welcome <c:out value="${sessionScope.useremail}" />! | <a href="Logout">Logout</a>
		<br/><br/>
	</div>
<div align="center">
	<div id="headermenu">
		<div class="menu_item">
			<a href="list_users">
				<img src = "../images/User.png" /><br/>Users
			</a>
		</div>
		<div>
			<a href="list_category">
				<img src = "../images/Category.png" /><br/>Categories
			</a>
		</div>
		<div>
			<a href="list_books">
				<img src = "../images/Book.png" /><br/>Books
			</a>
		</div>
		<div>
			<a href="list_customer"> 
				<img src = "../images/Customer.png" /><br/>Customers
			</a>
		</div>
		<div>
			<a href="list_review">
				<img src = "../images/Review.png" /><br/>Reviews
			</a>
		</div>
		<div>
			<a href="list_order">
				<img src = "../images/Order.png" /><br/>Orders
			</a>
		</div>
	</div>
</div>

	