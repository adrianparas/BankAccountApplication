<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Dashboard</title>
	<style>
		.name {
			color: red;
		}
	</style>
</head>
<body>
	<h1>Welcome to your dashboard <span class="name">${sessionScope.name}</span>!</h1>
	<a href="logout">Logout</a>
	<c:if test="${not empty sessionScope.message}">
       <p>${sessionScope.message}</p>
    </c:if>
</body>
</html>