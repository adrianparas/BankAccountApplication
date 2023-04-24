package com.snva;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCrypt;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String usernameOrEmail = request.getParameter("usernameEmail");
        String password = request.getParameter("password");
        String res = authenticate(name, usernameOrEmail, password);
        if (res.equals("success")) {
            response.sendRedirect("accountsetup.jsp");
        } else if (res.equals("success and setup")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("name", name);
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("message", "Error: " + res);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

    private String authenticate(String name, String usernameOrEmail, String password) {
        boolean isValid = false, isSetup = false;
        try {
            Connection connection = DataSourceAccess.getDataSource().getConnection();
            PreparedStatement ps = connection
                    .prepareStatement("select * from user_accounts where name=? and (username=? or email =?)");

            ps.setString(1, name);
            ps.setString(2, usernameOrEmail);
            ps.setString(3, usernameOrEmail);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String hashedPassword = resultSet.getString("password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                    isValid = true;
                }
            }
            if (isValid) {
                String bankAccountId = resultSet.getString("bank_account_id");
                ps = connection.prepareStatement("select * from bank_accounts where account_id=?");
                ps.setString(1, bankAccountId);
                if (ps.executeQuery().next()) {
                    isSetup = true;
                }
            }
            resultSet.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        if (isValid) {
            if (isSetup) {
                return "success and setup";
            }
            return "success";
        }
        return "Invalid login credentials!";
    }
}
