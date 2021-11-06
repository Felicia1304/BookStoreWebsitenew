<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div align = "center">
<div>
	<img src="images/Logo.png"/>
</div>
	<div align = "center">
		<input type="text" name="keyword" size="50"/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="Search" />
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="Login">Sign-In</a>
		&nbsp;&nbsp;&nbsp;
		<a href="register">Register</a>
		&nbsp;&nbsp;&nbsp;
		<a href="view_cart">Cart</a>
	</div>
	<div>&nbsp;</div>
	<div>
		<c:forEach var= "category" items="${listCategory}" varStatus = "status">
			<a href = "view_category?id=${category.categoryId}">
				<font size = "+1"><b><c:out value = "${category.cname}" /></b></font>
			</a>
			<c:if test = "${not status.last}">
				&nbsp; | &nbsp;
			</c:if>	
		</c:forEach>
	</div>
</div>