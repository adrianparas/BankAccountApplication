<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Dashboard</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
		<style>
			form {
				width: 100%;
				height: auto;
				max-width: 520px;
				margin: 0 auto;
			}
			.name {
				color: red;
			}

			form input {
				height: 37px;
				font-size: 13px;
				outline: none;
			}

			@media (max-width: 768px) {
				form {
					max-width: 100%;
				}
				form input {
					font-size: 16px;
				}
			}
		</style>
	</head>
<body>
	<div id="container" class="d-flex justify-content-center align-items-center min-vh-100 bg-primary">
		<h1>Welcome to your dashboard <span class="name">${sessionScope.name}</span>!</h1>
		<a href="logout">Logout</a>
	</div>
</body>
</html>