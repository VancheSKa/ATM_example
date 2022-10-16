package com.kaivse;

/**
 * @author Ivan Kalinin 16.10.2022
 */
public class Transactions {
    double amount;
    String comment;
    Account transactionAccount;

    public Transactions(double amount, Account transactionAccount) {
        this.amount = amount;
        this.transactionAccount = transactionAccount;
    }

    public Transactions(double amount, String comment, Account account) {
        this.amount = amount;
        this.comment = comment;
        this.transactionAccount = account;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transactions " + '\n' +
                "amount = " + amount + '\n' +
                "comment = " + comment + '\n' +
                "name = " + transactionAccount;
    }
}
