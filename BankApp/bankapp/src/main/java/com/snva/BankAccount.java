package com.snva;
import java.io.*;
import java.util.List;

public class BankAccount implements Serializable {
    private int accountNumber;
    private String accountHolderName;
    private CreditCard creditCard;

    public BankAccount(int accountNumber, String accountHolderName, CreditCard creditCard) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.creditCard = creditCard;
    }

    public void deposit(float amount) {
        creditCard.deposit(amount);
    }
    public void withdraw(float amount) throws InvalidTransactionException {
        creditCard.withdraw(amount);
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public void printLastNTransactions(int n) {
        if (n <= 0 || n > creditCard.getAllTransactions().size()) {
            throw new IllegalArgumentException("Invalid number of transactions");
        }
        System.out.println("Last " + n + " Transactions:\n");
        List<String> transactions = creditCard.getAllTransactions();
        int sz = transactions.size();
        for(int i = sz - 1, k = 1; i >= sz - n; i--) {
            System.out.println("Transaction #" + k++ + ":" + "\n\n" + transactions.get(i) + "\n");
        }
    }

    public void printAllTransactions() {
        int i = 1;
        for(String transaction: creditCard.getAllTransactions()) {
            System.out.println("Transaction #" + i++ + ":" + "\n\n" + transaction + "\n");
        }
    }

    public void checkBalance() {
        System.out.println("Your available balance is: " + creditCard.getAvailableBalance());
    }

    public String toString() {
        return "Account Number: " + accountNumber + ", Account Holder Name: " +
                accountHolderName + ", Available Balance: " + creditCard.getAvailableBalance()
                + "\n" +
                "Credit Card Information:"
                + "\n" + creditCard;
    }
}
