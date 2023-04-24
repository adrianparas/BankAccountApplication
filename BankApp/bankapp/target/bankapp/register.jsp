<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registration Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            form
            {
                height: 550px;
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
            <form action="register" class="bg-light p-4 round shadow" method="post">
                <h2 class="text-center my-3 text-primary">Register</h2>
                <input type="text" name="name" placeholder="Name" class="w-100 my-2 px-2" required><br>
                <input type="text" name="username" placeholder="Username" class="w-100 my-2 px-2" required><br>
                <input type="email" name="email" placeholder="Email" class="w-100 my-2 px-2" required><br>
                <input type="password" name="password" placeholder="Password" class="w-100 my-2 px-2" required><br>
                <input type="password" name="confPassword" placeholder="Confirm password" class="w-100 my-2 px-2" required><br>
                <p style="color:red;">Password must contain:<br>&emsp; -At least 12 characters<br> &emsp; -At least 1 digit<br> &emsp; -At least 1 upper/lower case letter<br> &emsp; -At least 1 special character</p>
                <input type="submit" value="Register" class="w-100 my-2 px-2 border-0 bg-primary text-light">
                <p>Already have an account? <a class="text-decoration-none text-primary" href="login.jsp">Login</a></p>
            </form>
        </div>
        <% String message = (String) request.getAttribute("message"); %>
	    <% if (message != null) { %>
		    <script>showAlert("<%= message %>");</script>
	    <% } %>
    </body>
</html>