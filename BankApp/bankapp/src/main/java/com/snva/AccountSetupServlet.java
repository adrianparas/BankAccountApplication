package com.snva;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/accountsetup")
public class AccountSetupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cardNumber = request.getParameter("cardNumber");
        String validCard = authenticate(cardNumber);
        if (validCard.equals("success")) {
            HttpSession session = request.getSession(true);
            session.setAttribute("cardNumber", cardNumber);
            addBankInfo(session, request, cardNumber);
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("message", "Error: " + validCard);
            RequestDispatcher rd = request.getRequestDispatcher("accountsetup.jsp");
            rd.forward(request, response);
        }  
    }

    private String authenticate(String cardNumber) {
        return cardNumber.length() < 19 || !cardNumber.matches("^\\d{4}( \\d{4}){3}$")  ? "Invalid card number" : "success";
    }

    private void addBankInfo(HttpSession session, HttpServletRequest request, String cardNumber) {
        try {
            String accound_id = (String)session.getAttribute("bank_account_id");
            String account_holder_name = request.getParameter("accountHolderName");
            int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
            String card = request.getParameter("card");
            String card_holder_name = request.getParameter("cardHolderName");
            String card_number = cardNumber;
            String card_type = request.getParameter("cardType");
            String expr_date = request.getParameter("exprDate"); 

            int cvv = Integer.parseInt(request.getParameter("cvv"));
            double account_balance = 0.00;

            Connection connection = DataSourceAccess.getDataSource().getConnection();
            PreparedStatement ps = connection
                    .prepareStatement(
                            "insert into bank_accounts (account_id, account_holder_name, account_number, " 
                            + "card, card_holder_name, card_number, card_type, expr_date, cvv, account_balance)"
                            + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, accound_id);
            ps.setString(2, account_holder_name);
            ps.setInt(3, accountNumber);
            ps.setString(4, card);
            ps.setString(5, card_holder_name);
            ps.setString(6, card_number);
            ps.setString(7, card_type);
            ps.setString(8, expr_date);
            ps.setInt(9, cvv);
            ps.setDouble(10, account_balance);
            ps.executeUpdate();

            ps.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }




}
