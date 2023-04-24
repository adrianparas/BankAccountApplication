package com.snva;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCrypt;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirm = request.getParameter("confPassword");
        String uuid = UUID.randomUUID().toString();
        String res = addUser(name, username, email, password, passwordConfirm, uuid);
        if (res.equals("success")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("name", name);
            session.setAttribute("bank_account_id", uuid);
            response.sendRedirect("accountsetup.jsp");
        } else {
            request.setAttribute("message", "Error: " + res);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
        }
    }

    private String addUser(String name, String username, String email, String password, String passwordConfirm, String uuid) {
        try {
            Connection connection = DataSourceAccess.getDataSource().getConnection();
            if (userExists(username, connection)) {
                return "Username already exists!";
            }
            if (!password.equals(passwordConfirm)) {
                return "Passwords do not match!";
            }
            String res = validatePassword(password);
            if (!res.equals("valid")) {
                return res;
            }

            PreparedStatement ps = connection
                    .prepareStatement(
                            "insert into user_accounts (name, username, email, password, bank_account_id) values (?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, BCrypt.hashpw(password, BCrypt.gensalt(12)));
            ps.setString(5, uuid);
            ps.executeUpdate();

            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "success";
    }

    private boolean userExists(String username, Connection connection) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement("select username from user_accounts where username=?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    private String validatePassword(String password) {
        if (password.length() < 12) {
            return "Password does not follow criteria";
        }
        int upperCount = 0, lowerCount = 0, numCount = 0, specialCount = 0;
        for (char ch : password.toCharArray()) {
            switch (Character.getType(ch)) {
                case Character.LOWERCASE_LETTER:
                    lowerCount++;
                    break;
                case Character.UPPERCASE_LETTER:
                    upperCount++;
                    break;
                case Character.DECIMAL_DIGIT_NUMBER:
                    numCount++;
                    break;
                case Character.OTHER_PUNCTUATION:
                case Character.OTHER_SYMBOL:
                    specialCount++;
                    break;
            }
        }
        return (upperCount == 0 || lowerCount == 0 || numCount == 0 || specialCount == 0)
                ? "Password does not follow all criteria"
                : "valid";
    }

}