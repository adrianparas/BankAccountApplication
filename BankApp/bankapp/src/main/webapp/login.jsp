<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            form
            {
                height: 330px;
                width: 330px;
            }
            form input
            {
                height: 37px;
                font-size: 13px;
                outline: none;
            }
            p
            {
                font-size: 12px;
            }
        </style>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            function showAlert(msg) {
                alert(msg);
            }
        </script>
    </head>
    <body>
        <div id="container" class="d-flex justify-content-center align-items-center min-vh-100 bg-primary">
            <form action="login" class="bg-light p-4 round shadow" method="post">
                <h2 class="text-center my-3 text-primary">Login</h2>
                <input type="text" name="name" placeholder="Name" class="w-100 my-2 px-2" required><br>
                <input type="text" name="usernameEmail" placeholder="Username or email" class="w-100 my-2 px-2" required><br>
                <input type="password" name="password" placeholder="Password" class="w-100 my-2 px-2" required><br>
                <input type="submit" value="Login" class="w-100 my-2 px-2 border-0 bg-primary text-light">
                <p>Don't have a account? <a class="text-decoration-none text-primary" href="register.jsp">Register</a></p>
            </form>
        </div>
        <% String message = (String) request.getAttribute("message"); %>
	    <% if (message != null) { %>
		    <script>showAlert("<%= message %>");</script>
	    <% } %>
    </body>
</html>