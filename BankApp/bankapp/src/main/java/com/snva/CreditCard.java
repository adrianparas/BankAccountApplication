package com.snva;

import java.io.Serializable;
import java.util.*;

public class CreditCard implements Serializable {
    private Date currentDate;
    private int availableBalance, numWithdraws, cvv;
    private String cardHolderName, cardNumber;
    private Date experationDate;
    private List<String> allTransactions;

    public CreditCard(int cvv, int balance, String cardNumber, String cardHolderName, Date experiationDate) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        currentDate = new Date();
        numWithdraws = 0;
        availableBalance = balance;
        this.cvv = cvv;
        this.experationDate = experiationDate;
        allTransactions = new ArrayList<String>();
    }

    public void deposit(float amount) {
        availableBalance += amount;
        displayDepositInfo(amount, new Date());
    }

    public void withdraw(float amount) throws InvalidTransactionException {
        Date date = new Date();
        int day = date.getDay(), month = date.getMonth(), year = date.getYear();
        boolean canWithdraw = false;
        if (day != currentDate.getDay() || month != currentDate.getMonth() || year != currentDate.getYear()) {
            currentDate = date;
            numWithdraws = 1;
        }
        if (availableBalance - amount >= 1000) {
            if (numWithdraws < 5) {
                availableBalance -= amount;
                numWithdraws++;
                canWithdraw = true;
            }
        }
        if (canWithdraw) {
            displayWithdrawalInfo(amount, date);
        } else {
            throw new InvalidTransactionException("Failed to withdraw");
        }
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getAvailableBalance() {
        return availableBalance;
    }

    public int getCCV() {
        return cvv;
    }

    public List<String> getAllTransactions() {
        return new ArrayList<>(allTransactions);
    }

    public String toString() {
        return "Card Number: " + cardNumber + ", Card Holder Name: " +
                cardHolderName + ", CVV: " + cvv + ", Expiration Date: " + experationDate;
    }

    private void displayDepositInfo(float amount, Date date) {
        String depositInfo = "Amount deposited: " + amount + "\n" + "Current balance: " +
                availableBalance + "\n" + "Time of deposit: " + date;
        System.out.println(depositInfo);
        allTransactions.add(depositInfo);
    }

    private void displayWithdrawalInfo(float amount, Date date) {
        String withdrawInfo = "Amount withdrew: " + amount + "\n" + "Current balance: " +
                availableBalance + "\n" + "Time of withdraw: " + date;
        System.out.println(withdrawInfo);
        allTransactions.add(withdrawInfo);
    }

    public Date getExperationDate() {
        return experationDate;
    }

}
